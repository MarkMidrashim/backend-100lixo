package br.com.fiap.coletaseletiva.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.coletaseletiva.converter.custom.MaterialConverter;
import br.com.fiap.coletaseletiva.data.model.Material;
import br.com.fiap.coletaseletiva.data.model.repository.MaterialRepository;
import br.com.fiap.coletaseletiva.data.vo.MaterialVO;
import br.com.fiap.coletaseletiva.exception.ResourceNotFoundException;

@Service
public class MaterialService {

	@Autowired
	MaterialRepository repository;

	@Autowired
	MaterialConverter converter;

	public Page<MaterialVO> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(entity -> converter.convertEntityToVO(entity));
	}

	public MaterialVO findById(Long id) {
		return converter.convertEntityToVO(
				repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado"))
			);
	}

	public MaterialVO create(MaterialVO materialVO) {
		Material entity = converter.convertVOToEntity(materialVO);
		Material material = repository.save(entity);
		return converter.convertEntityToVO(material);
	}

	public MaterialVO update(MaterialVO materialVO) {
		Material entity = repository.findById(materialVO.getId()).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado"));

		entity.setMaterial(materialVO.getMaterial());
		entity.setValorPeso(materialVO.getValorPeso());
		entity.setTipoMaterial(materialVO.getTipoMaterial());

		Material material = repository.save(entity);
		return converter.convertEntityToVO(material);
	}

	public void delete(Long id) {
		Material entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado"));
		repository.delete(entity);
	}

}
