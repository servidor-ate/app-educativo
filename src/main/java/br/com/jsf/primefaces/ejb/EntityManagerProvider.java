package br.com.jsf.primefaces.ejb;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.jsf.primefaces.entidade.TbPessoa;
import br.com.jsf.primefaces.entidade.TbUsuario;

@Stateless
public class EntityManagerProvider {

	@PersistenceContext
	private EntityManager em;
	
	public EntityManager getEntityManager() {
		if (em != null) {
			System.out.println("EM ok!");
		}
		return this.em;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean registrarUsuario(TbUsuario usuario) {
		try {
			
			this.em.persist(usuario);
			
			return true;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean registrarPessoa(TbPessoa pessoa) {
		try {
			
			this.em.persist(pessoa);
			
			return true;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean atualizarPessoa(TbPessoa pessoa) {
		try {
			
			this.em.merge(pessoa);
			
			return true;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean removerPessoa(TbPessoa pessoa) {
		try {
			
			// Para que o objeto torne-se 'managed' antes de ser removida via JPA
			this.em.remove(this.em.merge(pessoa));
			
			return true;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
}
