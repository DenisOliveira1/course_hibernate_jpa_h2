package br.edu.denis.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_AUTORES")

public class Autor {
	
	//variaveis
	@Id
	@Column(name = "AUTOR_ID")
	private int codigo;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "MACIONALIDADE")
	private String nacionalidade;
	
	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)//um autor pode ter varios livros
	private List<Livro> livros = new ArrayList<>();
	
	//construtor
	
	public Autor() {
		
	}
	public Autor(int codigo, String nome, String nacionalidade) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.nacionalidade = nacionalidade;
	}

	//getters e setters
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivro(List<Livro> livros) {
		this.livros = livros;
		
		//faz com que jamais um livro fique sem referencia de seu autor aula17
		for(Livro liv:this.livros) {
			liv.setAutor(this);
		}

	}
	
	//esse eu fiz
	public void addLivro(Livro livro) {
		
		//faz com que jamais um livro fique sem referencia de seu autor aula17
		if(!livros.contains(livro)) {
			livro.setAutor(this);
			this.livros.add(livro); 		
		}
	}
	
	//esse eu fiz
	public void removeLivro(Livro livro) {
		if(!livros.contains(livro)) {
			livros.remove(livro);
			livro.setAutor(null);
		}
	}
	
	//metodos
	@Override
	public String toString() {
		return "Autor [codigo=" + codigo + ", nome=" + nome + ", nacionalidade=" + nacionalidade + ", livros=" + livros
				+ "]";
	}

	
}
