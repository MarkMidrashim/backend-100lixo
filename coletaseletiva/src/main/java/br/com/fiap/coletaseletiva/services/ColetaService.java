package br.com.fiap.coletaseletiva.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.coletaseletiva.converter.custom.ColetaConverter;
import br.com.fiap.coletaseletiva.data.model.Coleta;
import br.com.fiap.coletaseletiva.data.model.repository.ColetaRepository;
import br.com.fiap.coletaseletiva.data.vo.ColetaVO;
import br.com.fiap.coletaseletiva.exception.ResourceNotFoundException;

@Service
public class ColetaService {

	@Autowired
	ColetaRepository repository;

	@Autowired
	ColetaConverter converter;

	public Page<ColetaVO> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(entity -> converter.convertEntityToVO(entity));
	}

	public ColetaVO findById(Long id) {
		return converter.convertEntityToVO(
				repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado"))
			);
	}

	public ColetaVO create(ColetaVO coletaVO) {
		Coleta entity = converter.convertVOToEntity(coletaVO);
		Coleta coleta = repository.save(entity);
		return converter.convertEntityToVO(coleta);
	}

	public ColetaVO update(ColetaVO coletaVO) {
		Coleta entity = repository.findById(coletaVO.getId()).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado"));

		entity.setDataColeta(coletaVO.getDataColeta());
		entity.setPeriodo(coletaVO.getPeriodo());
		entity.setUsuario(coletaVO.getUsuario());

		Coleta coleta = repository.save(entity);
		return converter.convertEntityToVO(coleta);
	}

	public void delete(Long id) {
		Coleta entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado"));
		repository.delete(entity);
	}

}
