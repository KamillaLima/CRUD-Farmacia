package com.generation.farmacia.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message="NOME DA CATEGORIA É OBRIGATÓRIO")
	@Size(min=5 , message="O TAMANHO MINIMO DO NOME PARA A CATEGORIA É DE 5 CARACTERES")
	private String nome;
	@Size(min=5 , message="O TAMANHO MINIMO DA DESCRICAO PARA A CATEGORIA É DE 5 CARACTERES")
	private String descricao;
	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="categoria",cascade=CascadeType.REMOVE)
	@JsonIgnoreProperties("categoria")
	private List<Produto> produto;
	
	public Categoria() {
		super();
	}

	
	public Categoria(Long id,
			@NotBlank(message = "NOME DA CATEGORIA É OBRIGATÓRIO") @Size(min = 5, message = "O TAMANHO MINIMO DO NOME PARA A CATEGORIA É DE 5 CARACTERES") String nome,
			@Size(min = 5, message = "O TAMANHO MINIMO DA DESCRICAO PARA A CATEGORIA É DE 5 CARACTERES") String descricao,
			List<Produto> produto) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}


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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}



}
