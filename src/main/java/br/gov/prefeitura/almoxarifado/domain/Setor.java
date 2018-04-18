package br.gov.prefeitura.almoxarifado.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

//Setor são entidades do orgão
@Entity
@Table(name = "setor")
public class Setor implements Serializable {

	private static final long serialVersionUID = 1L;

	// Dados pessoais
	private Integer id;
	private String nome;
	private Orgao orgao;
	private List<Grupo> grupos = new ArrayList<Grupo>();
	private String responsavel;
	private String cpf_responsavel;
	private String senha;
	private String token;

	// Endereco
	private EnderecoSetor endereco_setor  = new EnderecoSetor();

	// Contato
	private ContatoSetor contato_setor = new ContatoSetor();
	
	@Id
	@SequenceGenerator(name = "setor_id", sequenceName = "setor_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "setor_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotBlank @Size(max = 100)
	@Column(nullable = false,length=100)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@ManyToOne
	@JoinColumn(name = "orgao_id", nullable = false)
	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name = "setor_grupo", joinColumns = @JoinColumn(name="setor_id"),
			inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	@Fetch(FetchMode.SELECT)
	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	@NotBlank @Size(max = 100)
	@Column(nullable = false,length=100)
	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	@NotBlank @Size(max = 20) @CPF
	@Column(nullable = false,length=20)
	public String getCpf_responsavel() {
		return cpf_responsavel;
	}

	public void setCpf_responsavel(String cpf_responsavel) {
		this.cpf_responsavel = cpf_responsavel;
	}

	@NotEmpty @Size(max = 255)
	@Column(nullable = false,length=255)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Column(nullable = false,length=255)
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Embedded
	public EnderecoSetor getEndereco_setor() {
		return endereco_setor;
	}

	public void setEndereco_setor(EnderecoSetor endereco_setor) {
		this.endereco_setor = endereco_setor;
	}

	@Embedded
	public ContatoSetor getContato_setor() {
		return contato_setor;
	}

	public void setContato_setor(ContatoSetor contato_setor) {
		this.contato_setor = contato_setor;
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
		Setor other = (Setor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
