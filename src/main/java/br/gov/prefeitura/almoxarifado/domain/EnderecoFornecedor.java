package br.gov.prefeitura.almoxarifado.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
public class EnderecoFornecedor implements Serializable {

	private static final long serialVersionUID = 1L;

	private String pais;
	private String uf;
	private String cidade;
	private String bairro;
	private String rua;
	private String numero;

	@NotBlank @Size(max = 60)
	@Column(nullable = false, length=60)
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@NotBlank @Size(max = 40)
	@Column(nullable = false, length=40)
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@NotBlank @Size(max = 60)
	@Column(nullable = false, length=60)
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@NotBlank @Size(max = 60)
	@Column(nullable = false, length=60)
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@NotBlank @Size(max = 100)
	@Column(nullable = false, length=100)
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	@NotBlank @Size(max = 20)
	@Column(nullable = false, length=20)
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
