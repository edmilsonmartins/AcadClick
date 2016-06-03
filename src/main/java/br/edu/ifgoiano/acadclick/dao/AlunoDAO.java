package br.edu.ifgoiano.acadclick.dao;

import java.text.ParseException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Ignore;
import org.junit.Test;

import br.edu.ifgoiano.acadclick.domain.Aluno;
import br.edu.ifgoiano.acadclick.domain.Grupo;
import br.edu.ifgoiano.acadclick.domain.Sexo;
import br.edu.ifgoiano.acadclick.util.HibernateUtil;

public class AlunoDAO extends GenericDAO<Aluno> {
	@SuppressWarnings({ "unchecked" })
	public List<Aluno> buscarPorSexo(Sexo sexo, Long grupoCodigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Aluno.class);
			consulta.add(Restrictions.eq("sexo", sexo));
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			List<Aluno> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Aluno> buscarPorGrupo(Long grupoCodigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Aluno.class);
			consulta.add(Restrictions.eq("grupo.codigo", grupoCodigo));
			consulta.addOrder(Order.asc("nome"));
			List<Aluno> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@Test
	@Ignore
	public void buscar() throws ParseException {

		Grupo grupo = new Grupo();
		grupo.setCodigo(1L);

		AlunoDAO alunoDAO = new AlunoDAO();
		List<Aluno> resultado = alunoDAO.buscarPorGrupo(3L);

		System.out.println("Total de Registros Encontrados: " + resultado.size());

		for (Aluno aluno : resultado) {
			System.out.println("Data do Cadastro: " + aluno.getDataCadastro() + " Nome: " + aluno.getNome()
					+ " Data de Nascimento: " + aluno.getDataNascimento() + "Grupo: " + aluno.getGrupo());
		}
	}

}
