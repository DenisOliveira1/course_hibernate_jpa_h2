package br.edu.denis.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.edu.denis.entidades.Autor;
import br.edu.denis.entidades.Empregado;
import br.edu.denis.entidades.Livro;

public class AutoresTeste {
	
	/**
	 * tem que ter cuidado..os metodos da entidade servem apenas antes de colcoar o objto no banco de dados.
	 * mudar o objeto criado no main que ja foi cadastrado mudara somente o objeto no conteto do programa...
	 * nao em seu cadastro.
	 * 
	 * Para usar essas funcoes no cadastro deve-se recuperar o objeto do cadastro antes
	 * 
	 */

	// variaveis
	private EntityManager em;
	private EntityManagerFactory emf;

	// main
	public static void main(String[] args) {
		new AutoresTeste();
	}

	// construtor
	public AutoresTeste() {
		// associa com o xml de persistencia
		emf = Persistence.createEntityManagerFactory("denisPersistencia");

		Autor autor1 = new Autor(1, "Pablo Perez", "Espanhola");
		Autor autor2 = new Autor(2, "Gomez Elena ", "Mexicano");
		Autor autor3 = new Autor(3, "Miguel Lopez ", "Chinlena");
		
		adicionarAutor(autor1);
		adicionarAutor(autor2);
		adicionarAutor(autor3);

		Livro livro1 = new Livro(1, "Livro 1", autor1);
		Livro livro2 = new Livro(2, "Livro 2 ", autor2);
		Livro livro3 = new Livro(3, "Livro 3", autor3);
		Livro livro4 = new Livro(4, "Livro 4", autor2);
		Livro livro5 = new Livro(5, "Livro 5", autor2);
		Livro livro6 = new Livro();
		livro6.setCodigo(6);
		livro6.setTitulo("Livro 6");
		
		adicionarLivro(livro1);
		adicionarLivro(livro2);
		adicionarLivro(livro3);
		adicionarLivro(livro4);
		//autor2.addLivro(livro5); //so faz a acao aqui, nao no banco de dados
		adicionarLivro(livro5);
		adicionarLivro(livro6);

		listarAutores();	
		buscarAutor(2);	
		listarLivros();
		buscarLivro(1);
		buscarLivrosPorAutor(2);
		editarLivro(new Livro(3, "Livro 3", autor2));
		//removerLivro(new Livro(2, "", null));//so precisa do id//comentei essa funcao, fiz outra que so precisa passar o id
		removerLivro(2);
		listarLivros();
		listarAutores();
				
		//livro6.setAutor(autor2); //so faz a acao aqui, nao no banco de dados
		editarLivro(new Livro(6,"Livro 6",autor2));
		buscarLivrosPorAutor(2);
		
		editarLivro(new Livro(6,"Livro 6",null));
		listarLivros();
		listarAutores();

	}

	// metodos
	public void listarAutores() {
		System.out.println();
		em = emf.createEntityManager();
		List<Autor> autores = (List<Autor>) em.createQuery("FROM Autor").getResultList();// Autor é o nome da classe
		System.out.println("- Em esta base de dados existem " + autores.size() + " autores");

		for (Autor aut : autores) {
			System.out.println("* "+aut.toString());
		}
		em.close();
	}

	public void adicionarAutor(Autor a) {
		em = emf.createEntityManager();// liga manager
		em.getTransaction().begin();// inicio
		// em.persist(e.getEndereco());//o cascade a anotacoa onetoone ja fz isso
		em.persist(a);
		em.getTransaction().commit();// fim
		em.close();// desliga manager
	}
	
	public void buscarAutor(int id) {
		System.out.println();
		em = emf.createEntityManager();
		em.getTransaction().begin();//inicio
		Autor autor = em.find(Autor.class, id);
		em.getTransaction().commit();//fim		
		em.close();
		
		System.out.println("-> "+autor);//so funciona antes de em.close(), nao sei porque. aula16 responde: poe eager no na clase Autor
		
	}


	public void editarAutor(Autor a) {
		em = emf.createEntityManager();
		em.getTransaction().begin();// inicio
		Autor autor = em.find(Autor.class, a.getCodigo());
		autor.setNome(a.getNome());
		autor.setNacionalidade(a.getNacionalidade());
		em.getTransaction().commit();// fim
		em.close();
	}

	/*
	public void removerAutor(Autor a) {
		em = emf.createEntityManager();
		em.getTransaction().begin();// inicio

		// modo 1
		// em.remove(em.find(Autor.class, a.getCodigo()));

		// modo2
		a = em.merge(a);
		em.remove(a);

		em.getTransaction().commit();// fim
		em.close();
	}
	*/
	
	public void removerAutor(int id) {
		em = emf.createEntityManager();
		em.getTransaction().begin();// inicio
		em.remove(em.find(Autor.class, id));
		em.getTransaction().commit();// fim
		em.close();
	}
	
	public void listarLivros() {
		System.out.println();
		em = emf.createEntityManager();
		List<Livro> livros = (List<Livro>) em.createQuery("FROM Livro").getResultList();// Livro é o nome da classe
		System.out.println("- Em esta base de dados existem " + livros.size() + " livros");
		em.close();
		
		for (Livro liv : livros) {
			//liv.setAutor(new Autor(7,"",""));//funciona, mas nao faz sentido associar um livro a um autor que nao existe. perguntei na aula 17
			System.out.println("* "+liv.toString());
		}
	}
	
	public void adicionarLivro(Livro l) {
		em = emf.createEntityManager();// liga manager
		em.getTransaction().begin();// inicio
		em.persist(l);
		em.getTransaction().commit();// fim
		em.close();// desliga manager
	}
	
	public void buscarLivro(int id) {
		System.out.println();
		em = emf.createEntityManager();
		em.getTransaction().begin();//inicio
		Livro livro = em.find(Livro.class, id);
		em.getTransaction().commit();//fim	
		em.close();
		
		System.out.println("-> "+livro);
		
	}
	
	public void buscarLivrosPorAutor(int id) {
		System.out.println();
		em = emf.createEntityManager();
		em.getTransaction().begin();//inicio
		Autor autor = em.find(Autor.class, id);
		em.getTransaction().commit();//fim			
		em.close();

		for (Livro liv:autor.getLivros()) {//so funciona antes de em.close(), nao sei porque. aula16 responde: poe eager no na clase Autor
			System.out.println("* "+liv.toString());
		}
		
	}
	
	public void editarLivro(Livro l) {
		em = emf.createEntityManager();
		em.getTransaction().begin();// inicio
		Livro livro = em.find(Livro.class, l.getCodigo());
		livro.setTitulo(l.getTitulo());
		livro.setAutor(l.getAutor());
		em.getTransaction().commit();// fim
		em.close();
	}
	/*
	public void removerLivro(Livro l) {
		em = emf.createEntityManager();
		em.getTransaction().begin();// inicio

		// modo 1
		// em.remove(em.find(Livro.class, l.getCodigo()));

		// modo2
		l = em.merge(l);
		em.remove(l);

		em.getTransaction().commit();// fim
		em.close();
	}
	*/
	
	public void removerLivro(int id) {/*
		em = emf.createEntityManager();
		em.getTransaction().begin();// inicio
		em.remove(em.find(Livro.class, id));
		em.getTransaction().commit();// fim
		em.close();
		*/
		em = emf.createEntityManager();
		em.getTransaction().begin();// inicio
		Livro livro = em.find(Livro.class, id);
		em.remove(livro);
		em.getTransaction().commit();// fim
		em.close();
	}
	
	



}
