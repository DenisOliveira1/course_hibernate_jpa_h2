package br.edu.denis.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario {
	
	@Id
	@Column(name = "ID_USUARIO")
	private int id;
	
	@Column(name = "ID_NOME")
	private String nome;
	
	@OneToMany(mappedBy = "usuario")//um usuario pode ter varias publicacoes
	private List<Publicacao> publicacoes = new ArrayList<>();
	
	//constutor
	public Usuario() {
		
	}

	public Usuario(int id, String nome, List<Publicacao> publicacoes) {
		super();
		this.id = id;
		this.nome = nome;
		this.publicacoes = publicacoes;
	}

	//getters e setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Publicacao> getPublicacoes() {
		return publicacoes;
	}

	public void setPublicacao(List<Publicacao> publicacoes) {//adiciona a lista toda
		this.publicacoes = publicacoes;
		
		//faz com que jamais uma publicacao fique sem referencia de seu usuario
		for(Publicacao pub:this.publicacoes) {
			pub.setUsuario(this);
		}

	}
	
	//esse eu fiz
	public void addPublicacao(Publicacao publicacao) {//adiciona so uma
		
		//faz com que jamais uma publicacao fique sem referencia de seu usuario
		if(!publicacoes.contains(publicacao)) {
			publicacao.setUsuario(this);
			this.publicacoes.add(publicacao) ;		
		}
	}
	
	//esse eu fiz
	public void removePublicacao(Publicacao publicacao) {
		if(!publicacoes.contains(publicacao)) {
			publicacoes.remove(publicacao);
			publicacao.setUsuario(null);
		}
	}

	//metodos
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", publicacoes=" + publicacoes + "]";
	}
	
	
	@PreRemove
	public void nullificarPublicacoes() {
		publicacoes.forEach(pub -> pub.setUsuario(null));
	}


}
