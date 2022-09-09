package br.com.fiap.coletaseletiva.data.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="material")
public class Material implements Serializable {

	private static final long serialVersionUID = 1L;

	/* CONSTRUCTOR */

	public Material() {}

	/* PROPERTIES */

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_MATERIAL")
	private Long id;

	@Column(name="DS_MATERIAL", length=100, nullable=false)
	private String material;

	@Column(name="VL_PESO", nullable=false, precision=18, scale=2)
	private Double valorPeso;

	@ManyToOne(targetEntity=TipoMaterial.class, fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinColumn(name="ID_TIPO_MATERIAL", referencedColumnName="ID_TIPO_MATERIAL", nullable=false)
	private TipoMaterial tipoMaterial;

	/* GETTERS & SETTTERS */

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

	/* METHODS */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((material == null) ? 0 : material.hashCode());
		result = prime * result + ((tipoMaterial == null) ? 0 : tipoMaterial.hashCode());
		result = prime * result + ((valorPeso == null) ? 0 : valorPeso.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Material other = (Material) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (material == null) {
			if (other.material != null)
				return false;
		} else if (!material.equals(other.material))
			return false;
		if (tipoMaterial == null) {
			if (other.tipoMaterial != null)
				return false;
		} else if (!tipoMaterial.equals(other.tipoMaterial))
			return false;
		if (valorPeso == null) {
			if (other.valorPeso != null)
				return false;
		} else if (!valorPeso.equals(other.valorPeso))
			return false;
		return true;
	}

}
