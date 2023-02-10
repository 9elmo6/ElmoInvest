import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);

        while (true) {
            Socket socket = serverSocket.accept();
            new ClientHandler(socket).start();
        }
    }
}

class ClientHandler extends Thread {
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            String message = dataInputStream.readUTF();
            System.out.println("Message received from client: " + message);
          
            dataOutputStream.writeUTF("Hello from the server!");
            dataOutputStream.flush();
            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
