package br.edu.denis.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_LIVROS")

public class Livro {
	
	//variaveis
	@Id
	@Column(name = "LIVRO_ID")
	private int codigo;
	
	@Column(name = "TITULO")
	private String titulo;

	@ManyToOne( fetch = FetchType.LAZY)//varios libros podem ser de um mesmo autor
	@JoinColumn(name = "AUTOR_ID")//aiciona coluna de chave esrangeira
	private Autor autor;

	//construtor
	public Livro() {
		/*caso o livro nao seja cadastrado com um autor ele recebera um autor assim que for 
		 * adionado a um Autor nos metodos addLivros e setLivros em Autor.
		 * 
		 * porem se ele for impresso antes de ser associad a um autor ele estara iniciado com um autor nulo para nao
		 * dar erro na impressao
		 */	
	}
	public Livro(int codigo, String titulo, Autor autor) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.autor = autor;
	}
	
	
	//getters e setters
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	//metodos
	@Override
	public String toString() {
		
		String resultado = (this.autor == null) ? "null": Integer.toString(this.autor.getCodigo());//se o autor for null nao dara erro
		return "Livro [codigo=" + codigo + ", titulo=" + titulo + ", autor=" + resultado + "]";//printar so o id do autor se nao vira loop infinito
	}
	
	
	

}
