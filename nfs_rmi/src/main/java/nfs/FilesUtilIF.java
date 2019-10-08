package nfs;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FilesUtilIF extends Remote{
	
	public boolean exists() throws RemoteException ;
	public void createFile(String fileName) throws RemoteException ;
	public void deleteFile(String fileName) throws RemoteException ;
	public void createDir(String dirName) throws RemoteException ;
	public void renomear(String oldName, String newName) throws RemoteException ;
	public String listar() throws IOException, RemoteException ;
}
