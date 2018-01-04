package br.com.jsf.primefaces.utilidades;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

public class Utilidades {

	public static EntityManager getJpaEntityManager() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		ExternalContext externalContext = facesContext.getExternalContext();

		HttpServletRequest request = (HttpServletRequest) externalContext
				.getRequest();

		return (EntityManager) request.getAttribute("entityManager");
	}

	// MOSTRAR MENSAGEM
	public static void exibirMensagem(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage("Alerta", mensagem));
	}

	// MOSTRAR MENSAGEM
	public static void exibirMensagemAtencao(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_WARN, "Atenção:", mensagem));
	}

	// MOSTRAR MENSAGEM
	public static void exibirMensagemInfo(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_INFO, "", mensagem));
	}

	// MOSTRAR MENSAGEM
	public static void exibirMensagemFatal(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_FATAL, "", mensagem));
	}
}
