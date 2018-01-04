package br.com.jsf.primefaces.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "TbUsuario.findUser", query = "SELECT u FROM TbUsuario u WHERE u.usuario = :usuario AND u.senha = :senha")
public class TbUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6555598456545107717L;

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "USUARIO")
	private String usuario;

	@Column(name = "SENHA")
	private String senha;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TbUsuario other = (TbUsuario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TbUsuario [id=");
		builder.append(id);
		builder.append(", usuario=");
		builder.append(usuario);
		builder.append(", senha=");
		builder.append(senha);
		builder.append("]");
		return builder.toString();
	}

}
