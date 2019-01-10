package br.edu.denis.testes;

import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.edu.denis.entidades.Empregado;
import br.edu.denis.entidades.Endereco;

public class EmpregadosTeste {

	// variaveis
	private EntityManager em;
	private EntityManagerFactory emf;

	// main
	public static void main(String[] args) {
		new EmpregadosTeste();
	}

	// construtor
	public EmpregadosTeste() {
		// associa com o xml de persistencia
		emf = Persistence.createEntityManagerFactory("denisPersistencia");
	
		Empregado empregado1 = new Empregado(10, "Denis", "Pepito", LocalDate.of(1994, 7, 15),new Endereco(1,"Rua A","Local A","Cidade A","Brasil"));
		Empregado empregado2 = new Empregado(11, "Yu", "Chen", LocalDate.of(1996, 1, 15),new Endereco(2,"Rua B","Local B","Cidade B","China"));
		
		adicionarEmpregado(empregado1);
		adicionarEmpregado(empregado2);	
		listarEmpregados();
		
		editarEmpregado(new Empregado(11, "Yuuu", "Cheen", LocalDate.of(1996, 1, 15),new Endereco(2,"Rua B","Local B","Cidade B","China")));	
		listarEmpregados();
		
		removerEmpregado(new Empregado(11, "", "", null,null));//so vai precisar da chave principal
		listarEmpregados();
		
		buscarEmpregadoPorId(10);

	}

	// metodos
	public void listarEmpregados() {
		System.out.println();
		em = emf.createEntityManager();
		List<Empregado> empregados = (List<Empregado>) em.createQuery("FROM Empregado").getResultList();// Empregados é																								// o nome da																								// sensitive
		System.out.println("- Em esta base de dados existema " + empregados.size() + " empregados");
		
		for(Empregado emp:empregados) {
			System.out.println("* "+emp.toString());
		}
		em.close();
	}
	
	public void buscarEmpregadoPorId(int id) {
		System.out.println();
		em = emf.createEntityManager();
		em.getTransaction().begin();//inicio
		Empregado empregado = em.find(Empregado.class, id);
		em.getTransaction().commit();//fim	
		em.close();
		
		System.out.println("-> "+empregado);

	}

	public void adicionarEmpregado(Empregado e) {
		em = emf.createEntityManager();//liga manager
		em.getTransaction().begin();//inicio
		//em.persist(e.getEndereco());//o cascade a anotacoa onetoone ja fz isso
		em.persist(e);
		em.getTransaction().commit();//fim
		em.close();//desliga manager
	}
	
	public void editarEmpregado(Empregado e) {
		em = emf.createEntityManager();
		em.getTransaction().begin();//inicio
		Empregado empregado = em.find(Empregado.class, e.getCodigo());
		empregado.setNome(e.getNome());
		empregado.setSobrenome(e.getSobrenome());
		empregado.setDataNascimento(e.getDataNascimento());
		em.getTransaction().commit();//fim	
		em.close();
	}
	
	public void removerEmpregado(Empregado e){
		em = emf.createEntityManager();
		em.getTransaction().begin();//inicio
		
		//modo 1
		//em.remove(em.find(Empregado.class, e.getCodigo()));
		
		//modo2
		e = em.merge(e);
		em.remove(e);
		
		em.getTransaction().commit();//fim	
		em.close();
	}
	
}


