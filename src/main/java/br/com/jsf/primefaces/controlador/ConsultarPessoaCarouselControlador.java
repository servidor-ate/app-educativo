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

@Named(value="consultarPessoaCarouselControlador")
@ViewScoped
public class ConsultarPessoaCarouselControlador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3445765414838976981L;

	@Inject transient
	private PessoaRepositorio pessoaRepositorio;
 
	@Produces 
	private List<PessoaModelo> pessoas;
 
	public List<PessoaModelo> getPessoas() {
		return pessoas;
	}
 
	@PostConstruct
	private void init(){
		this.pessoas = pessoaRepositorio.recuperarPessoas();
	}
 	
	
}
