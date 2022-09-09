package br.com.fiap.coletaseletiva.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.coletaseletiva.data.vo.UsuarioVO;
import br.com.fiap.coletaseletiva.services.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="UsuarioEndpoint", tags={"UsuarioController"})
@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService services;

	@Autowired
	private PagedResourcesAssembler<UsuarioVO> assembler;

	/* METHODS */

	@ResponseBody
	@ApiOperation(value="Retornar todos usuários")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> findAll(
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="10") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));

		Page<UsuarioVO> usuarios = services.findAll(pageable);
		usuarios
			.stream()
			.forEach(item -> item.add(
					linkTo(methodOn(UsuarioController.class).findById(item.getId())).withSelfRel()
				)
			);

		PagedModel<?> resources = assembler.toModel(usuarios);
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@ResponseBody
	@ApiOperation(value="Buscar usuário pelo id")
	@GetMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UsuarioVO findById(@PathVariable("id") Long id) {
		UsuarioVO usuarioVO = services.findById(id);
		usuarioVO.add(linkTo(methodOn(UsuarioController.class).findById(id)).withSelfRel());
		return usuarioVO;
	}

	@ResponseBody
	@ApiOperation(value="Criar um usuário")
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> create(@RequestBody UsuarioVO usuario) {
		UsuarioVO usuarioVO = services.create(usuario);
		usuarioVO.add(linkTo(methodOn(UsuarioController.class).findById(usuario.getId())).withSelfRel());
		return ResponseEntity.ok(usuarioVO);
	}

	@ResponseBody
	@ApiOperation(value="Atualizar um usuário")
	@PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> update(@RequestBody UsuarioVO usuario) {
		UsuarioVO usuarioVO = services.update(usuario);
		usuarioVO.add(linkTo(methodOn(UsuarioController.class).findById(usuario.getId())).withSelfRel());
		return ResponseEntity.ok(usuarioVO);
	}

	@ApiOperation(value="Remover um usuário")
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		services.delete(id);
		return ResponseEntity.ok().build();
	}

}
