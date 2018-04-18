package br.gov.prefeitura.almoxarifado.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "fornecedor")
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome_fantasia;
	private String cnpj;
	private String responsavel;
	private String ramo_empresa;
	private EnderecoFornecedor endereco_fornecedor = new EnderecoFornecedor();
	private ContatoFornecedor contato_fornecedor = new ContatoFornecedor();

	@Id
	@SequenceGenerator(name = "fornecedor_id", sequenceName = "fornecedor_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fornecedor_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotBlank @Size(max = 100)
	@Column(nullable = false,length=100)
	public String getNome_fantasia() {
		return nome_fantasia;
	}

	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}

	@CNPJ @NotBlank
	@Column(nullable = false,length=20)
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@NotBlank @Size(max = 100)
	@Column(nullable = false,length=100)
	public String getResponsavel() {
		return responsavel;
	}
	
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	@NotBlank @Size(max = 60)
	@Column(nullable = false,length=60)
	public String getRamo_empresa() {
		return ramo_empresa;
	}

	public void setRamo_empresa(String ramo_empresa) {
		this.ramo_empresa = ramo_empresa;
	}

	@Embedded
	public EnderecoFornecedor getEndereco_fornecedor() {
		return endereco_fornecedor;
	}

	public void setEndereco_fornecedor(EnderecoFornecedor endereco_fornecedor) {
		this.endereco_fornecedor = endereco_fornecedor;
	}

	@Embedded
	public ContatoFornecedor getContato_fornecedor() {
		return contato_fornecedor;
	}

	public void setContato_fornecedor(ContatoFornecedor contato_fornecedor) {
		this.contato_fornecedor = contato_fornecedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Fornecedor other = (Fornecedor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
