package br.edu.denis.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PUBLICACAO")
public class Publicacao {
	/**
	 * sempre que a classe tiver um ManyToOne ela vai ter um JoinColumn
	 * logo se a classe tem um JoinColumn deve-e printar somente o id dele, se nao fica loop infinito
	 * 
	 * se a classe tem:
	 * ManyToOne = variavel
	 * OneToMany = List
	 * 
	 */
	
	//variaveis
	@Id
	@Column(name = "ID_PUBLICACAO")
	private int id;	
	
	@Column(name = "TITULO")
	private String titulo;

	@ManyToOne//varias publicacoes podem ser do mesmo autor
	@JoinColumn
	private Usuario usuario;
	
	@OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL)//uma publicacao pode ter varios comentarios
	private List<Comentario> comentarios = new ArrayList<>();

	//construtor
	public Publicacao() {
		
	}

	public Publicacao(int id, String titulo, Usuario usuario, List<Comentario> comentarios) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.usuario = usuario;
		this.comentarios = comentarios;
	}
	
	//getters e setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
		
		//faz com que jamais um comentario fique sem referencia de sua publicacao
		for(Comentario com:this.comentarios) {
			com.setPublicacao(this);
		}

	}
	
	//esse eu fiz
	public void addComentario(Comentario comentario) {
		
		//faz com que jamais um comentario fique sem referencia de sua publicacao
		if(!comentarios.contains(comentario)) {
			comentario.setPublicacao(this);
			this.comentarios.add(comentario); 		
		}
	}
	
	//esse eu fiz
	public void removeComentario(Comentario comentario) {
		if(!comentarios.contains(comentario)) {
			comentarios.remove(comentario);
			comentario.setPublicacao(null);
		}
	}
	

	//metodos
	@Override
	public String toString() {
		String resultado = (this.usuario == null) ? "null" : Integer.toString(this.usuario.getId());//se o usuario for null nao dara erro
		return "Publicacao [id=" + id + ", titulo=" + titulo + ", usuario=" + resultado + ", comentarios=" + comentarios
				+ "]";//printar so id do usuario, se nao vira loop infinito
	}
	
	
	
	
}