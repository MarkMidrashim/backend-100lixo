package br.com.fiap.coletaseletiva.converter.custom;

import org.springframework.stereotype.Service;

import br.com.fiap.coletaseletiva.data.model.Material;
import br.com.fiap.coletaseletiva.data.vo.MaterialVO;

@Service
public class MaterialConverter {

	public MaterialVO convertEntityToVO(Material material) {
		MaterialVO vo = new MaterialVO();
		vo.setId(material.getId());
		vo.setMaterial(material.getMaterial());
		vo.setValorPeso(material.getValorPeso());
		vo.setTipoMaterial(material.getTipoMaterial());
		return vo;
	}

	public Material convertVOToEntity(MaterialVO material) {
		Material entity = new Material();
		entity.setId(material.getId());
		entity.setMaterial(material.getMaterial());
		entity.setValorPeso(material.getValorPeso());
		entity.setTipoMaterial(material.getTipoMaterial());
		return entity;
	}

}
