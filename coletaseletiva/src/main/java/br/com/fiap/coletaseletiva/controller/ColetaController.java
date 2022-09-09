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

import br.com.fiap.coletaseletiva.data.vo.ColetaVO;
import br.com.fiap.coletaseletiva.services.ColetaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="ColetaEndpoint", tags={"ColetaController"})
@RestController
@RequestMapping("/api/v1/coleta")
public class ColetaController {

	@Autowired
	private ColetaService services;

	@Autowired
	private PagedResourcesAssembler<ColetaVO> assembler;

	/* METHODS */

	@ResponseBody
	@ApiOperation(value="Retornar todas coletas")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> findAll(
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="10") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "periodo"));

		Page<ColetaVO> coletas = services.findAll(pageable);
		coletas
			.stream()
			.forEach(item -> item.add(
					linkTo(methodOn(ColetaController.class).findById(item.getId())).withSelfRel()
				)
			);

		PagedModel<?> resources = assembler.toModel(coletas);
		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@ResponseBody
	@ApiOperation(value="Buscar coleta pelo id")
	@GetMapping(value="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ColetaVO findById(@PathVariable("id") Long id) {
		ColetaVO coletaVO = services.findById(id);
		coletaVO.add(linkTo(methodOn(ColetaController.class).findById(id)).withSelfRel());
		return coletaVO;
	}

	@ResponseBody
	@ApiOperation(value="Criar uma coleta")
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> create(@RequestBody ColetaVO coleta) {
		ColetaVO coletaVO = services.create(coleta);
		coletaVO.add(linkTo(methodOn(ColetaController.class).findById(coleta.getId())).withSelfRel());
		return ResponseEntity.ok(coletaVO);
	}

	@ResponseBody
	@ApiOperation(value="Atualizar uma coleta")
	@PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> update(@RequestBody ColetaVO coleta) {
		ColetaVO coletaVO = services.update(coleta);
		coletaVO.add(linkTo(methodOn(ColetaController.class).findById(coleta.getId())).withSelfRel());
		return ResponseEntity.ok(coletaVO);
	}

	@ApiOperation(value="Remover uma coleta")
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		services.delete(id);
		return ResponseEntity.ok().build();
	}

}
