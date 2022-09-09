package br.com.fiap.coletaseletiva.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.coletaseletiva.converter.custom.UsuarioConverter;
import br.com.fiap.coletaseletiva.data.model.Usuario;
import br.com.fiap.coletaseletiva.data.model.repository.UsuarioRepository;
import br.com.fiap.coletaseletiva.data.vo.UsuarioVO;
import br.com.fiap.coletaseletiva.exception.ResourceNotFoundException;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	UsuarioRepository repository;

	@Autowired
	UsuarioConverter converter;

	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}

	public Page<UsuarioVO> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(entity -> converter.convertEntityToVO(entity));
	}

	public UsuarioVO findById(Long id) {
		return converter.convertEntityToVO(
				repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado"))
			);
	}

	public UsuarioVO create(UsuarioVO usuarioVO) {
		Usuario entity = converter.convertVOToEntity(usuarioVO);
		Usuario usuario = repository.save(entity);
		return converter.convertEntityToVO(usuario);
	}

	public UsuarioVO update(UsuarioVO usuarioVO) {
		Usuario entity = repository.findById(usuarioVO.getId()).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado"));

		entity.setNome(usuarioVO.getNome());
		entity.setCpfCnpj(usuarioVO.getCpfCnpj());
		entity.setNascimento(usuarioVO.getNascimento());
		entity.setEmail(usuarioVO.getEmail());
		entity.setCelular(usuarioVO.getCelular());
		entity.setEndereco(usuarioVO.getEndereco());

		Usuario usuario = repository.save(entity);
		return converter.convertEntityToVO(usuario);
	}

	public void delete(Long id) {
		Usuario entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado"));
		repository.delete(entity);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var usuario = repository.findByEmail(username);
		if (usuario != null) {
			return usuario;
		}
		throw new UsernameNotFoundException("Usuário " + username + " não encontrado");
	}

}
