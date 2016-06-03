package br.edu.ifgoiano.acadclick.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Ignore;
import org.junit.Test;

import br.edu.ifgoiano.acadclick.domain.Aluno;
import br.edu.ifgoiano.acadclick.domain.Responsavel;
import br.edu.ifgoiano.acadclick.util.HibernateUtil;

public class ResponsavelDAO extends GenericDAO<Responsavel> {
	private Responsavel responsavel;

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public Responsavel buscar(Aluno alu) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Responsavel.class);
			consulta.add(Restrictions.eq("codigo", alu.getResponsavel()));
			consulta.addOrder(Order.asc("nome"));
			responsavel = (Responsavel) consulta.uniqueResult();

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}

		return responsavel;
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<Aluno> buscarPorResponsavel(Responsavel responsavel) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		List<Aluno> resultado;
		try {
			Criteria consulta = sessao.createCriteria(Aluno.class);
			consulta.add(Restrictions.eq("responsavel", responsavel));
			resultado = consulta.list();

		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}

		return resultado;
	}


	@Test
	@Ignore
	public void listarResponsavel() {
		Responsavel responsavel = new Responsavel();
		responsavel.setCodigo(1L);
		
		ResponsavelDAO responsavelDAO = new ResponsavelDAO();
		List<Aluno> resultado = responsavelDAO.buscarPorResponsavel(responsavel);

		System.out.println("Total de Registros Encontrados: " + resultado.size());

		for (Aluno aluno : resultado) {
			System.out.println("Data do Cadastro: " + aluno.getDataCadastro() + " Nome: " + aluno.getNome()
					+ " Data de Nascimento: " + aluno.getDataNascimento());
		}
	}

}
