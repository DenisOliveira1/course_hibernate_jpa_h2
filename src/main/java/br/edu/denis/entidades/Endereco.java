package br.edu.denis.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ENDERECO")

public class Endereco implements Serializable{
	private static final long serialVersionUID = 1L;

	//variveis
	@Id
	@Column(name = "ID_ENDERECO")
	private int id;
	
	@Column(name = "ENDERECO")
	private String endereco;
	
	@Column(name = "LOCAL")
	private String local;
	
	@Column(name = "CIDADE")
	private String cidade;
	
	@Column(name = "PAIS")
	private String pais;
	
	@OneToOne(mappedBy = "endereco", fetch = FetchType.LAZY)//um endereco so pode ser um empregado
	//onetoone faz uma associacao de UM empregado com UM endereco
	//torna a relaçao existente bidirecional, filho tem acesso ao pai
	private Empregado empregado;
	
	
	//construtor

	public Endereco(int id, String endereco, String local, String cidade, String pais) {
		super();
		this.id = id;
		this.endereco = endereco;
		this.local = local;
		this.cidade = cidade;
		this.pais = pais;
	}

	public Endereco() {
		
	}
	
	//getters e setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public Empregado getEmpregado() {
		return empregado;
	}

	public void setEmpregado(Empregado empregado) {
		this.empregado = empregado;
	}

	//metodos
	@Override
	public String toString() {
		return "Endereco [id=" + id + ", endereco=" + endereco + ", local=" + local + ", cidade=" + cidade + ", pais="
				+ pais + ", empregado= " + empregado.getCodigo() + "]";//printar so o id do empregado se nao vira loop infinito
	}
	
	
	
}
