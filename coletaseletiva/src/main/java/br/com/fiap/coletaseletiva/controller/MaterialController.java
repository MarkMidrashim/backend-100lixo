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

import br.com.fiap.coletaseletiva.data.vo.MaterialVO;
import br.com.fiap.coletaseletiva.services.MaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="MaterialEndpoint", tags={"MaterialController"})
@RestController
@RequestMapping("/api/v1/material")
public class MaterialController {

	@Autowired
	private MaterialService services;

	@Autowired
	private PagedResourcesAssembler<MaterialVO> assembler;

	/* METHODS */

	@ResponseBody
	@ApiOperation(value="Retornar todos materiais")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> findAll(
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="10") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "material"));

		Page<MaterialVO> materiais = services.findAll(pageable);
		materiais
			.stream()
			.forEach(item -> item.add(
					linkTo(methodOn(MaterialController.class).findById(item.getId())).withSelfRel()
				)
			);

		PagedModel<?> resources = assembler.toModel(materiais);
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@ResponseBody
	@ApiOperation(value="Buscar material pelo id")
	@GetMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public MaterialVO findById(@PathVariable("id") Long id) {
		MaterialVO materialVO = services.findById(id);
		materialVO.add(linkTo(methodOn(MaterialController.class).findById(id)).withSelfRel());
		return materialVO;
	}

	@ResponseBody
	@ApiOperation(value="Criar um material")
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> create(@RequestBody MaterialVO material) {
		MaterialVO materialVO = services.create(material);
		materialVO.add(linkTo(methodOn(MaterialController.class).findById(material.getId())).withSelfRel());
		return ResponseEntity.ok(materialVO);
	}

	@ResponseBody
	@ApiOperation(value="Atualizar um material")
	@PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> update(@RequestBody MaterialVO material) {
		MaterialVO materialVO = services.update(material);
		materialVO.add(linkTo(methodOn(MaterialController.class).findById(material.getId())).withSelfRel());
		return ResponseEntity.ok(materialVO);
	}

	@ApiOperation(value="Remover um material")
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		services.delete(id);
		return ResponseEntity.ok().build();
	}

}
