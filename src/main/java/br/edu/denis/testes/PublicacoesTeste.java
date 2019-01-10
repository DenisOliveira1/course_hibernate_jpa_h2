package br.edu.denis.testes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.edu.denis.entidades.Autor;
import br.edu.denis.entidades.Comentario;
import br.edu.denis.entidades.Publicacao;
import br.edu.denis.entidades.Usuario;

public class PublicacoesTeste {
	
	// variaveis
		private EntityManager em;
		private EntityManagerFactory emf;

		// main
		public static void main(String[] args) {
			new PublicacoesTeste();
		}
		
		//consturtor
		public PublicacoesTeste() {
			// associa com o xml de persistencia
			emf = Persistence.createEntityManagerFactory("denisPersistencia");
			
			em = emf.createEntityManager();// liga manager
			em.getTransaction().begin();// inicio
			
			Usuario usuario1 = new Usuario();
			usuario1.setId(1);
			usuario1.setNome("Denis");
			
			Usuario usuario2 = new Usuario();
			usuario2.setId(2);
			usuario2.setNome("Yu");
			
			Publicacao publicacao1 = new Publicacao();
			publicacao1.setId(1);
			publicacao1.setTitulo("Publicacao 1");
			publicacao1.setUsuario(usuario1);
			
			Comentario comentario1 = new Comentario();
			comentario1.setId(1);
			comentario1.setMensagem("Bom dia Yu");
			
			Comentario comentario2 = new Comentario();
			comentario2.setId(2);
			comentario2.setMensagem("Bom dia Denis");
			
			publicacao1.addComentario(comentario1);
			publicacao1.addComentario(comentario2);
			
			em.persist(usuario1);
			em.persist(usuario2);
			em.persist(publicacao1);
			
			em.getTransaction().commit();// fim
			em.close();// desliga manager
				
			listarPublicacoes();
			listarComentarios();
			listarUsuarios();
			
			removerUsuario(1);
			/**
			 * removerUsuario(1);
			 * 
			 * 1- o comando dara erro
			 * esse usuario tem publicacoes associadas a ele!
			 * e a publicacao tem comentarios associadas a ela! vai dar erro excluir esse usuario
			 * 
			 * 2 - o comando apagara, alem do usuario, todas as suas publicacoes e os comentarios
			 * dessas publicacoes
			 * cascade = CascadeType.ALL, se colocar isso na lista de publicacoes em Usuarios, 
			 * ao excluir um usuario suas publicacoes serao todas excluidas e os comentarios associados 
			 * a elas tambem
			 * 
			 * 3- exclui o usuario e mantem suas publicacoes associadas a nulo
			 * fazer um metodo que leava a anotacao preRemove.
			 * Ele sera chamado sempre antes da execucao de um remove.
			 * Antes de excluir o usuario, coloca o usuario de todas as publicacoes como nulo
			 * 
			 */

			listarPublicacoes();
			listarComentarios();
			listarUsuarios();
			
			
		}
		
		//metodos
		public void listarPublicacoes() {
			System.out.println();
			em = emf.createEntityManager();
			List<Publicacao> publicacoes = (List<Publicacao>) em.createQuery("FROM Publicacao").getResultList();// Publicacao é o nome da classe
			System.out.println("- Em esta base de dados existem " + publicacoes.size() + " publicacoes");

			for (Publicacao pub:publicacoes) {
				System.out.println("* "+pub.toString());
			}
			em.close();
		}
		
		public void listarComentarios() {
			System.out.println();
			em = emf.createEntityManager();
			List<Comentario> comentarios = (List<Comentario>) em.createQuery("FROM Comentario").getResultList();// Comentario é o nome da classe
			System.out.println("- Em esta base de dados existem " + comentarios.size() + " comentarios");

			for (Comentario com : comentarios) {
				System.out.println("* "+com.toString());
			}
			em.close();
		}
		
		public void listarUsuarios() {
			System.out.println();
			em = emf.createEntityManager();
			List<Usuario> usuarios = (List<Usuario>) em.createQuery("FROM Usuario").getResultList();// Usuario é o nome da classe
			System.out.println("- Em esta base de dados existem " + usuarios.size() + " usuarios");

			for (Usuario usu : usuarios) {
				System.out.println("* "+usu.toString());
			}
			em.close();
		}

		public void removerUsuario(int id) {
			em = emf.createEntityManager();// liga manager
			em.getTransaction().begin();// inicio
			em.remove(em.find(Usuario.class,id));
			em.getTransaction().commit();// fim
			em.close();// desliga manager
		}

}
