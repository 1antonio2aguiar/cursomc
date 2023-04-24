package com.curso.mc.domain.enums;

public enum TipoCliente {
	PESSOAFISICA(1, "Pessoa fisica"),
	PESSOAJURIDICA(2, "Pessoa Juricida");
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	// static pode chamar sem que o obj seja instanciado
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) return null;
		
		for (TipoCliente tc : TipoCliente.values()) {
			if(cod.equals(tc.getCod())) {
				return tc;
			}
		}
		throw new IllegalArgumentException("Tipo pessoa inválido!" + cod);
	}
}
