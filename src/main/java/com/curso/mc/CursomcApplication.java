package com.curso.mc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso.mc.domain.Bairro;
import com.curso.mc.domain.Categoria;
import com.curso.mc.domain.Cep;
import com.curso.mc.domain.Cidade;
import com.curso.mc.domain.Estado;
import com.curso.mc.domain.Logradouro;
import com.curso.mc.domain.Produto;
import com.curso.mc.repositories.BairroRepository;
import com.curso.mc.repositories.CategoriaRepository;
import com.curso.mc.repositories.CepRepository;
import com.curso.mc.repositories.CidadeRepository;
import com.curso.mc.repositories.EstadoRepository;
import com.curso.mc.repositories.LogradouroRepository;
import com.curso.mc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired private CategoriaRepository categoriaRepository;
	@Autowired private ProdutoRepository produtoRepository;
	@Autowired private EstadoRepository estadoRepository;
	@Autowired private CidadeRepository cidadeRepository;
	@Autowired private BairroRepository bairroRepository;
	@Autowired private LogradouroRepository logradouroRepository;
	@Autowired private CepRepository cepRepository;
	
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
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		Estado est1 = new Estado(null,"MINAS GERAIS");
		Estado est2 = new Estado(null,"SÃO PAULO");
		
		Cidade cid1 = new Cidade(null,"UBERABA",est1);
		Cidade cid2 = new Cidade(null,"BELO HORIZONTE",est1);
		Cidade cid3 = new Cidade(null,"CAMPINAS",est2);
		Cidade cid4 = new Cidade(null,"SÃO PAULO",est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1,cid2));
		est2.getCidades().addAll(Arrays.asList(cid3,cid4));
		
		
		Bairro bai1 = new Bairro(null,"SÃO BENEDITO",cid1);
		Bairro bai2 = new Bairro(null,"PARQUE DAS LARANEJIRAS",cid1);
		Bairro bai3 = new Bairro(null,"RECREIO DOS BAIDEIRANTES",cid1);
		Bairro bai4 = new Bairro(null,"JARDIM IPYRANGA",cid1);
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3,cid4));
		
		Logradouro lograd1 = new Logradouro(null,"RUA","PEDRO BARBASSA",cid1);
		Logradouro lograd2 = new Logradouro(null,"RUA","TRISTÃO DE CASTRO",cid1);
		Logradouro lograd3 = new Logradouro(null,"AVENIDA","ANTONIO ALBERTO G. RESENDE",cid1);
		
		Cep cep1 = new Cep(null,1,9999,"U","38040-290",bai3,lograd1);
		Cep cep2 = new Cep(null,1,9999,"U","38061-080",bai1,lograd2);
		Cep cep3 = new Cep(null,1,500,"U","38046-674",bai2,lograd3);
		Cep cep4 = new Cep(null,501,9999,"U","38046-675",bai4,lograd3);
		
		
		logradouroRepository.saveAll(Arrays.asList(lograd1,lograd2,lograd3));
		bairroRepository.saveAll(Arrays.asList(bai1,bai2,bai3,bai4));
		cepRepository.saveAll(Arrays.asList(cep1,cep2,cep3,cep4));
		
	}

	
}
