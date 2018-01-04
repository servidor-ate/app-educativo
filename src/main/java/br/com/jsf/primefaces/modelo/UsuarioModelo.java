package br.com.jsf.primefaces.modelo;

import java.io.Serializable;

public class UsuarioModelo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8015773788831257370L;

	private String codigo;
	private String usuario;
	private String senha;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UsuarioModelo(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}

	public UsuarioModelo() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsuarioModelo [codigo=");
		builder.append(codigo);
		builder.append(", usuario=");
		builder.append(usuario);
		builder.append(", senha=");
		builder.append(senha);
		builder.append("]");
		return builder.toString();
	}

}
