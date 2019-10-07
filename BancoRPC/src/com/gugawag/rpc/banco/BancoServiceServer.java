package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private List<Conta> saldoContas = new ArrayList<Conta>();

    public BancoServiceServer() throws RemoteException {
    	saldoContas.add(new Conta("0", 100.0));
    	saldoContas.add(new Conta("1", 156.0));
    	saldoContas.add(new Conta("2", 950.0));        
    }

    @Override
    public double saldo(String conta) throws RemoteException {
    	for (Conta c : saldoContas) {
			if(c.getConta().equals(conta)) {
				return c.getSaldo();
			}
		}
    	return 0;
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return saldoContas.size();
    }

	@Override
	public void cadastrarConta(Double saldo) throws RemoteException {
		saldoContas.add(new Conta(saldoContas.size()+"", saldo));
	}

	@Override
	public Conta pesquisarConta(String conta) throws RemoteException {
		for (Conta c : saldoContas) {
			if(c.getConta().equals(conta)) {
				return c;
			}
		}
		return null;
	}

	@Override
	public void removerConta(String conta) throws RemoteException {		
		for (Conta c : saldoContas) {
			if(c.getConta().equals(conta)) {
				saldoContas.remove(c);
				return;
			}
		}
	}

}
