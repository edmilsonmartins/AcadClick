package br.edu.ifgoiano.acadclick.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_estados")
public class Estado extends GenericDomain{

	@Column(name = "est_nome", nullable = false, length = 50)
	private String nome;

	@Column(name = "est_uf", nullable = false, length = 2)
	private String uf;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	

}