package br.edu.ifgoiano.acadclick.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_tipoUsuario")
public class TipoUsuario extends GenericDomain {

	@Column(name = "tip_nome", nullable = false, length = 50)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}