package com.gugawag.rpc.banco;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BancoServiceIF extends Remote {

    double saldo(String conta) throws RemoteException;
    int quantidadeContas() throws RemoteException;
    void cadastrarConta(Double saldo) throws RemoteException;
    Conta pesquisarConta(String conta) throws RemoteException;
    void removerConta(String conta) throws RemoteException;
}
