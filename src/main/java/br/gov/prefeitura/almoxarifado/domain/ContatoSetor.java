package br.gov.prefeitura.almoxarifado.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
public class ContatoSetor implements Serializable {

	private static final long serialVersionUID = 1L;

	private String telefone;
	private String celular;
	private String email;
	private String whatsapp;

	@NotBlank @Size(max = 20)
	@Column(nullable = false, length=20)
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Size(max = 20)
	@Column(nullable = true, length=20)
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@NotBlank @Size(max = 100)
	@Column(nullable = false, length=100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Size(max = 20)
	@Column(nullable = true, length=20)
	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

}
