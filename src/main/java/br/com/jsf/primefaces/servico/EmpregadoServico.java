package br.com.jsf.primefaces.servico;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.jsf.primefaces.entidade.TbPessoa;

@Stateless
public class EmpregadoServico {

	@PersistenceContext(name = "hibernateH2PU")
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(TbPessoa empregado) {
		try {
			
			this.em.merge(empregado);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
