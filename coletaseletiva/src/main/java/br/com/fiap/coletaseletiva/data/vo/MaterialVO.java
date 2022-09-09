package br.com.fiap.coletaseletiva.data.vo;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

import br.com.fiap.coletaseletiva.data.model.TipoMaterial;

@SuppressWarnings("rawtypes")
@JsonPropertyOrder({
	"id",
	"material",
	"valorPeso",
	"tipoMaterial"
})
public class MaterialVO extends RepresentationModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/* CONSTRUCTOR */

	public MaterialVO() {}

	/* PROPERTIES */

	@Mapping("id")
	@JsonProperty("id")
	private Long id;
	private String material;

	@JsonProperty("valor_peso")
	private Double valorPeso;

	@JsonProperty("tipo_material")
	private TipoMaterial tipoMaterial;

	/* GETTERS & SETTERS */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Double getValorPeso() {
		return valorPeso;
	}

	public void setValorPeso(Double valorPeso) {
		this.valorPeso = valorPeso;
	}

	public TipoMaterial getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(TipoMaterial tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

}
