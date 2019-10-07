package nfs;
import java.nio.file.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("== Servidor ==");

        FilesUtil filesUtil = new FilesUtil();
        // Configurando o socket
        ServerSocket serverSocket = new ServerSocket(7001);
        Socket socket = serverSocket.accept();
        System.out.println("Entrou");
        // pegando uma refer�ncia do canal de sa�da do socket. Ao escrever nesse canal, est� se enviando dados para o
        // servidor
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        // pegando uma refer�ncia do canal de entrada do socket. Ao ler deste canal, est� se recebendo os dados
        // enviados pelo servidor
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        // la�o infinito do servidor
        while (true) {
            String mensagem = dis.readUTF();
            String[] messageArray = mensagem.split(" "); 
            String command = messageArray[0];
            System.out.println("AQUIII"+command);
            switch (command) {
	            case "readdir":
	            	mensagem = filesUtil.listar();
	                dos.writeUTF(mensagem);
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
	                System.out.println("Comando n�o definido");
	                break;
            }            
                     
        }
        /*
         * Observe o while acima. Perceba que primeiro se l� a mensagem vinda do cliente (linha 29, depois se escreve
         * (linha 32) no canal de sa�da do socket. Isso ocorre da forma inversa do que ocorre no while do Cliente2,
         * pois, de outra forma, daria deadlock (se ambos quiserem ler da entrada ao mesmo tempo, por exemplo,
         * ningu�m evoluiria, j� que todos estariam aguardando.
         */
    }
}