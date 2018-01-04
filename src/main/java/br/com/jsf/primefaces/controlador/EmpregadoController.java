package br.com.jsf.primefaces.controlador;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;

import br.com.jsf.primefaces.entidade.TbPessoa;
import br.com.jsf.primefaces.servico.EmpregadoServico;

@ManagedBean
public class EmpregadoController {

	private TbPessoa empregado = new TbPessoa();
	
	@EJB
	private EmpregadoServico empregadoServico;

	public TbPessoa getEmpregado() {
		return empregado;
	}

	public void setEmpregado(TbPessoa empregado) {
		this.empregado = empregado;
	}
	
	public void salvarNovoEmpregado(TbPessoa novoEmpregado) {
		this.empregadoServico.salvar(novoEmpregado);
	}
	
}
