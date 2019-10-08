package nfs;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesUtil extends UnicastRemoteObject implements FilesUtilIF {	



	protected FilesUtil() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static String HOME = System.getProperty("user.home")+"/victor";
	Path p = Paths.get(HOME);
	
	@Override
	public boolean exists()  throws RemoteException {		
		return Files.exists(p);
	}
	
	@Override
	public void createFile(String fileName)  throws RemoteException {
		Path fp = Paths.get(HOME + "/" + fileName);	    
	 
	    try {
			Files.createFile(fp);
			System.out.println("Gravou");
		} catch (IOException e) {
			System.out.println("Não gravou");
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteFile(String fileName) {
		Path fp = Paths.get(HOME + "/" + fileName);
		if(Files.exists(fp)) {
			try {
				Files.delete(fp);
				System.out.println("Apagou");
			} catch (IOException e) {
				System.out.println("Não Apagou");
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void createDir(String dirName)  throws RemoteException {
		Path dp = Paths.get(HOME + "/" + dirName);	  	 
		try {
			Files.createDirectory(p);
			System.out.println("Gravou");
		} catch (IOException e) {
			System.out.println("Não gravou");
			e.printStackTrace();
		}
	 	    
	}
	
	public void renomear(String oldName, String newName)  throws RemoteException {
		File f = new File(p+"/"+oldName);
        
        // The following file should not exist while running the program
        File rF = new File(p+"/"+newName);
        
        if(f.renameTo(rF)) {
            System.out.println(f);
        } else {
            System.out.println("Error: Unable to rename file");
        }
	}
	
	@Override
	public String listar() throws IOException, RemoteException {
		try (Stream<Path> walk = Files.walk(p)) {
			String texto= "";
			List<String> result = walk.filter(Files::isRegularFile)
					.map(x -> x.toString()).collect(Collectors.toList());

			for (String r : result) {				
				texto+=r+" ";
			}
			return texto;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
