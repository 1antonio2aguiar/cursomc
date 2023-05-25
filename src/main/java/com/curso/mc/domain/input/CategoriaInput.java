package com.curso.mc.domain.input;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.curso.mc.domain.Produto;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class CategoriaInput implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotNull
	private String nome;
	
	private List<ProdutoInput> produtos = new ArrayList<>();
	
}
