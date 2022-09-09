package br.com.fiap.coletaseletiva.converter.custom;

import org.springframework.stereotype.Service;

import br.com.fiap.coletaseletiva.data.model.Usuario;
import br.com.fiap.coletaseletiva.data.vo.UsuarioVO;

@Service
public class UsuarioConverter {

	public UsuarioVO convertEntityToVO(Usuario usuario) {
		UsuarioVO vo = new UsuarioVO();
		vo.setId(usuario.getId());
		vo.setNome(usuario.getNome());
		vo.setCpfCnpj(usuario.getCpfCnpj());
		vo.setNascimento(usuario.getNascimento());
		vo.setEndereco(usuario.getEndereco());
		return vo;
	}

	public Usuario convertVOToEntity(UsuarioVO usuario) {
		Usuario entity = new Usuario();
		entity.setId(usuario.getId());
		entity.setNome(usuario.getNome());
		entity.setCpfCnpj(usuario.getCpfCnpj());
		entity.setNascimento(usuario.getNascimento());
		entity.setEndereco(usuario.getEndereco());
		return entity;
	}

}
