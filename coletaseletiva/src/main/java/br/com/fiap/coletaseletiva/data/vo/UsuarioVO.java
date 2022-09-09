package br.com.fiap.coletaseletiva.data.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

import br.com.fiap.coletaseletiva.data.model.Endereco;

@SuppressWarnings("rawtypes")
@JsonPropertyOrder({
	"id",
	"nome",
	"cpfCnpj",
	"email",
	"senha",
	"nascimento",
	"ativo",
	"endereco"
})
public class UsuarioVO extends RepresentationModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/* CONSTRUCTOR */

	public UsuarioVO() {}

	/* PROPERTIES */

	@Mapping("id")
	@JsonProperty("id")
	private Long id;
	private String nome;

	@JsonProperty("cpf_cnpj")
	private String cpfCnpj;

	private String email;
	private String senha;
	private String celular;
	private boolean ativo;
	private Date nascimento;
	private Endereco endereco;

	/* GETTERS & SETTERS */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public boolean getAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
