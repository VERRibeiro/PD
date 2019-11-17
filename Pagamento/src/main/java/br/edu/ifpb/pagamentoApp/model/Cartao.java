package br.edu.ifpb.pagamentoApp.model;


public class Cartao {

	private String agencia;
	private String valor;	
	private String numeroCartao;
	private String bandeira;
	
	public Cartao(String agencia, String valor, String numeroCartao, String bandeira) {
		super();
		this.agencia = agencia;
		this.valor = valor;
		this.numeroCartao = numeroCartao;
		this.bandeira = bandeira;
	}

	public String getAgencia() {
		return agencia;
	}


	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}


	public String getValor() {
		return valor;
	}


	public void setValor(String valor) {
		this.valor = valor;
	}


	public String getNumeroCartao() {
		return numeroCartao;
	}


	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}


	public String getBandeira() {
		return bandeira;
	}


	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}


	@Override
	public String toString() {
		return "Cartao [agencia=" + agencia + ", valor=" + valor + ", numeroCartao=" + numeroCartao + ", bandeira="
				+ bandeira + "]";
	}
	
	
}
