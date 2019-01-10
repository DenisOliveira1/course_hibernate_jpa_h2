package br.edu.denis.entidades;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_EMPREGADOS")

public class Empregado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//variaveis
	@Id()//define chave principal
	//@GeneratedValue
	@Column(name = "COD_EMPREGADO")
	private int codigo;
	
	@Column(name = "SOBRENOME")
	private String sobrenome;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "DATA_NASCIMENTO")
	private LocalDate dataNascimento;
		
	@OneToOne(cascade = {CascadeType.ALL})//um empregado so tem um endereco
	//faz relaçao unidirecional de emrpegado para endereco, pai tem acesso ao filho
	//onetoone faz uma associacao de UM empregado com UM endereco
	//cascade faz com que os endereco seja incorporado ao empregado, nao necessitando dar persist separadamente nos 2 ao adicionar a tabela
	@JoinColumn(name = "ID_ENDERECO")//adiciona coluna de chave estrangeira
	private Endereco endereco;

	//construtor
	public Empregado(){
		
	}
	
	public Empregado(int codigo, String sobrenome, String nome, LocalDate dataNascimento, Endereco endereco) {
		super();
		this.codigo = codigo;
		this.sobrenome = sobrenome;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
	}

	//getters e setters
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	//metodos
	@Override
	public String toString() {
		return "Empregado [codigo=" + codigo + ", sobrenome=" + sobrenome + ", nome=" + nome + ", dataNascimento="
				+ dataNascimento + ", endereco=" + endereco + "]";
	}

}
