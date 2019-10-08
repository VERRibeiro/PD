package nfs;


import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws NotBoundException, IOException {
    	Registry registry = LocateRegistry.getRegistry();
        FilesUtilIF filesUtil = (FilesUtilIF) registry.lookup("FilesUtil");
    
        System.out.println("== Cliente ==");      
        
        while (true) {
        	Scanner dis = new Scanner(System.in);   
            String mensagem = dis.nextLine();
            System.out.println(mensagem);
            String[] messageArray = mensagem.split("\\s+"); 
            String command = messageArray[0];            
            switch (command) {
	            case "readdir":
	            	mensagem = filesUtil.listar();	                
	                break;
	            case "rename":
	                filesUtil.renomear(messageArray[1], messageArray[2]);
	                break;
	            case "create":
	            	filesUtil.createFile(messageArray[1]);
	                break;
	            case "remove":
	            	filesUtil.deleteFile(messageArray[1]);
	                break;
	            default:
	                System.out.println("Comando não definido");
	                break;
            }
            System.out.println(mensagem);
        }
    }
}
