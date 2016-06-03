package br.edu.ifgoiano.acadclick.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "tb_curriculos")
public class Curriculo extends GenericDomain {
	@Temporal(TemporalType.DATE)
	@Column(name = "cur_dt_cadastro", updatable = false)
	private Date dataCadastro;
	
	@Column(name = "cur_nome", nullable = false)
	private String nome;
	
	@Column(name = "cur_descricao", nullable = false)
	private String descricao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
