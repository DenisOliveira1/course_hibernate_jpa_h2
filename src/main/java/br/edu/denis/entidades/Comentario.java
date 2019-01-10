package br.edu.denis.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_COMENTARIO")
public class Comentario {
	
	@Id
	@Column(name = "ID_COMENTARIO")
	private int id;
	
	@Column(name = "MENSAGEM")
	private String mensagem;
	
	@ManyToOne(fetch = FetchType.LAZY)// muitos comentarios podem pertencer a mesma publicaca
	@JoinColumn
	private Publicacao publicacao;
	
	
	//contrutor
	public Comentario() {
		
	}

	public Comentario(int id, String mensagem, Publicacao publicacao) {
		super();
		this.id = id;
		this.mensagem = mensagem;
		this.publicacao = publicacao;
	}
	
	//getters e setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Publicacao getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Publicacao publicacao) {
		this.publicacao = publicacao;
	}

	//metodos
	@Override
	public String toString() {
		return "Comentario [id=" + id + ", mensagem=" + mensagem + ", publicacao=" + publicacao.getId() + "]";//printar so id da publicacao, se nao vira loop infinito
	}

	
	
	
	
	

}
