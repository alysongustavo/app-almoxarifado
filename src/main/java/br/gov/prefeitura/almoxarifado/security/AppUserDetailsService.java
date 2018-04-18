package br.gov.prefeitura.almoxarifado.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.gov.prefeitura.almoxarifado.dao.SetorDAO;
import br.gov.prefeitura.almoxarifado.domain.Grupo;
import br.gov.prefeitura.almoxarifado.domain.Setor;
import br.gov.prefeitura.almoxarifado.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		SetorDAO setores = CDIServiceLocator.getBean(SetorDAO.class);
		
		Setor setor = setores.porCPF(cpf);
		
		UsuarioSistema user = null;
		
		if (setor != null) {
			user = new UsuarioSistema(setor, getGrupos(setor));
			
		}else {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}
		
		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupos(Setor setor) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	
		for (Grupo grupo : setor.getGrupos()) {
			authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
		}
		
		return authorities;
	}

}
