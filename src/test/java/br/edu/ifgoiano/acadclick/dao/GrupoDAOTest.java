package br.edu.ifgoiano.acadclick.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.edu.ifgoiano.acadclick.domain.Grupo;

public class GrupoDAOTest {
	@Test
	@Ignore
	public void salvar() {
		Grupo grupo = new Grupo();
		grupo.setNome("GGM");
		grupo.setDescricao("Mocidade");

		GrupoDAO dao = new GrupoDAO();
		dao.salvar(grupo);
	}

	@Test
	@Ignore
	public void listar() {
		GrupoDAO dao = new GrupoDAO();
		List<Grupo> resultado = dao.listar();

		System.out.println("Total de Registros Encontrados: " + resultado.size());

		for (Grupo grupo : resultado) {
			System.out.println(grupo.getCodigo() + " - " + grupo.getNome() + " - " + grupo.getDescricao());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 2L;

		GrupoDAO dao = new GrupoDAO();
		Grupo grupo = dao.buscar(codigo);

		if (grupo == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro encontrado:");
			System.out.println(grupo.getCodigo() + " - " + grupo.getNome() + " - " + grupo.getDescricao());
		}

	}

	@Test
	@Ignore
	public void editar() {
		Long codigo = 1L;
		GrupoDAO dao = new GrupoDAO();
		Grupo grupo = dao.buscar(codigo);

		if (grupo == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro editado - Antes:");
			System.out.println(grupo.getCodigo() + " - " + grupo.getNome() + " - " + grupo.getDescricao());

			grupo.setDescricao("Adolescentes");

			dao.editar(grupo);

			System.out.println("Registro editado:");
			System.out.println(grupo.getCodigo() + " - " + grupo.getNome() + " - " + grupo.getDescricao());
		}

	}
	
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 1L;
		GrupoDAO dao = new GrupoDAO();

		Grupo grupo = dao.buscar(codigo);

		if (grupo == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			dao.excluir(grupo);
			System.out.println("Registro removido:");
			System.out.println(grupo.getCodigo() + " - " + grupo.getNome() + " - " + grupo.getDescricao());
		}
	}
	
	@Test
	@Ignore
	public void merge() {
		//Grupo grupo = new Grupo();
		//grupo.setNome("GGM");
		//grupo.setDescricao("Mocidade");

		//GrupoDAO dao = new GrupoDAO();
		//dao.merge(grupo);
		
		GrupoDAO grupoDAO = new GrupoDAO();
		Grupo grupo = grupoDAO.buscar(1L);
		
		grupo.setDescricao("Mocidades");
		grupoDAO.merge(grupo);
	}
}
