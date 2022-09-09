package br.com.fiap.coletaseletiva.data.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

import br.com.fiap.coletaseletiva.data.model.Usuario;

@SuppressWarnings("rawtypes")
@JsonPropertyOrder({
	"id",
	"dataColeta",
	"periodo",
	"usuario"
})
public class ColetaVO extends RepresentationModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/* CONSTRUCTOR */

	public ColetaVO() {}

	/* PROPERTIES */

	@Mapping("id")
	@JsonProperty("id")
	private Long id;

	@JsonProperty("data_coleta")
	private Date dataColeta;
	private String periodo;
	private Usuario usuario;

	/* GETTERS & SETTERS */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
