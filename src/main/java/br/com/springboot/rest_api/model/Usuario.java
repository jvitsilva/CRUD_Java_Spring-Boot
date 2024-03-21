package br.com.springboot.rest_api.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity //uma entidade representa uma tabela no banco de dados, e cada instância representa uma linha dessa tabela
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1) //gerador de sequência
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L; //implementacao da versão serial para controle

	@Id //chave primaria

	 @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_usuario") //apontando pro gerador
	private long id;
	private String nome;
	private int idade;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	
}
