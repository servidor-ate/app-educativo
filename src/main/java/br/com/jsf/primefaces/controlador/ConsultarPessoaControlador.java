package br.com.jsf.primefaces.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jsf.primefaces.modelo.PessoaModelo;
import br.com.jsf.primefaces.repositorio.PessoaRepositorio;
import br.com.jsf.primefaces.utilidades.Utilidades;

@Named(value = "consultarPessoaControlador")
@ViewScoped
public class ConsultarPessoaControlador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1576470946836257856L;

	@Inject
	transient private PessoaModelo pessoaModelo;

	@Produces
	private List<PessoaModelo> pessoas;

	@Inject
	transient private PessoaRepositorio pessoaRepositorio;

	public List<PessoaModelo> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<PessoaModelo> pessoas) {
		this.pessoas = pessoas;
	}

	public PessoaModelo getPessoaModelo() {
		return pessoaModelo;
	}

	public void setPessoaModelo(PessoaModelo pessoaModelo) {
		this.pessoaModelo = pessoaModelo;
	}

	/***
	 * CARREGA AS PESSOAS NA INICIALIZAÇÃO
	 */
	@PostConstruct
	public void init() {
		// RETORNAR AS PESSOAS CADASTRADAS
		this.pessoas = this.pessoaRepositorio.recuperarPessoas();
	}

	/***
	 * CARREGA INFORMAÇÕES DE UMA PESSOA PARA SER EDITADA
	 * 
	 * @param pessoaModelo
	 */
	public void editar(PessoaModelo pessoaModelo) {
		/* PEGA APENAS A PRIMEIRA LETRA DO SEXO PARA SETAR NO CAMPO(M OU F) */
		pessoaModelo.setSexo(pessoaModelo.getSexo().substring(0, 1));

		this.pessoaModelo = pessoaModelo;
	}

	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void alterarRegistro() {
		System.out.printf("# Preparando para atualizar pessoa %s\n", this.pessoaModelo);
		this.pessoaRepositorio.atualizarPessoa(this.pessoaModelo);

		Utilidades.exibirMensagemInfo("Registro atualizado com sucesso!");

		/* RECARREGA OS REGISTROS */
		this.init();
	}

	/***
	 * EXCLUINDO UM REGISTRO
	 * 
	 * @param pessoaModelo
	 */
	public void excluirRegistro(PessoaModelo pessoaModelo) {
		// EXCLUI A PESSOA DO BANCO DE DADOS
		this.pessoaRepositorio.removerPessoa(pessoaModelo.getCodigo());

		// REMOVENDO A PESSOA DA LISTA
		// ASSIM QUE É A PESSOA É REMOVIDA DA LISTA O DATATABLE É ATUALIZADO
		this.pessoas.remove(pessoaModelo);

		Utilidades.exibirMensagemInfo("Registro removido com sucesso!");
	}
}
