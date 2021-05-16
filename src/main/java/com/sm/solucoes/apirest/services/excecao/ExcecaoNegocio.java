package com.sm.solucoes.apirest.services.excecao;

public class ExcecaoNegocio extends Exception {

	private static final long serialVersionUID = 1L;

	public ExcecaoNegocio(String msg) {
		super(msg);
	}
}
