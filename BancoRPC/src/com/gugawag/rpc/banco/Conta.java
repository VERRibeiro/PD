package com.gugawag.rpc.banco;

import java.io.Serializable;

public class Conta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String conta;
	private Double saldo;
	
	public Conta(String conta, Double saldo) {
		super();
		this.conta = conta;
		this.saldo = saldo;
	}
	
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Conta [conta=" + conta + ", saldo=" + saldo + "]";
	}
	
}
