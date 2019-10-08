package nfs;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args)  throws RemoteException, MalformedURLException, AlreadyBoundException {

        FilesUtilIF filesUtil = new FilesUtil();

        Registry registry = LocateRegistry.createRegistry(1099);

        
        registry.rebind("FilesUtil", filesUtil);

        System.out.println("Service de banco registrado ....");

    }
}