package br.gov.prefeitura.almoxarifado.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "informativo")
public class Informativo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String titulo;
	private String descricao;
	private Date data_cadastro = new Date();

	@Id
	@SequenceGenerator(name = "informativo_id", sequenceName = "informativo_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "informativo_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotBlank @Size(max = 100)
	@Column(nullable = false,length=100)
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@NotBlank @Size(max = 500)
	@Column(columnDefinition = "text")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
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
		Informativo other = (Informativo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
