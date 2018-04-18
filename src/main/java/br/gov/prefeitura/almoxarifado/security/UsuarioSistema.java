package br.gov.prefeitura.almoxarifado.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.gov.prefeitura.almoxarifado.domain.Setor;

public class UsuarioSistema extends User {

	private static final long serialVersionUID = 1L;
	
	private Setor setor;
	
	public UsuarioSistema(Setor setor, Collection<? extends GrantedAuthority> authorities) {
		super(setor.getCpf_responsavel(), setor.getSenha(), authorities);
		this.setor = setor;
	}

	public Setor getSetor() {
		return setor;
	}

	
}
