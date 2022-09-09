package br.com.fiap.coletaseletiva.converter.custom;

import org.springframework.stereotype.Service;

import br.com.fiap.coletaseletiva.data.model.Coleta;
import br.com.fiap.coletaseletiva.data.vo.ColetaVO;

@Service
public class ColetaConverter {

	public ColetaVO convertEntityToVO(Coleta coleta) {
		ColetaVO vo = new ColetaVO();
		vo.setId(coleta.getId());
		vo.setDataColeta(coleta.getDataColeta());
		vo.setPeriodo(coleta.getPeriodo());
		vo.setUsuario(coleta.getUsuario());
		return vo;
	}

	public Coleta convertVOToEntity(ColetaVO coleta) {
		Coleta entity = new Coleta();
		entity.setId(coleta.getId());
		entity.setDataColeta(coleta.getDataColeta());
		entity.setPeriodo(coleta.getPeriodo());
		entity.setUsuario(coleta.getUsuario());
		return entity;
	}

}
