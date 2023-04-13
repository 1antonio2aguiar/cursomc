package com.curso.mc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso.mc.domain.Categoria;
import com.curso.mc.domain.Produto;
import com.curso.mc.repositories.CategoriaRepository;
import com.curso.mc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired private CategoriaRepository categoriaRepository;
	@Autowired private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "ELETRÔNICOS");
		Categoria cat2 = new Categoria(null, "lIVROS");
		Categoria cat3 = new Categoria(null, "ESPORTES");
		Categoria cat4 = new Categoria(null, "MAGAZINE");
		
		Produto p1 = new Produto(null,"COMPUTADOR", 3685.23);
		Produto p2 = new Produto(null,"MONITOR", 1753.10);
		Produto p3 = new Produto(null,"MOUSE", 53.86);
		
		Produto p4 = new Produto(null,"O SENHOR DOS ANEIS", 138.25);
		Produto p5 = new Produto(null,"O CONDE DE MONTE CRISTO", 85.96);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		
	}

}
