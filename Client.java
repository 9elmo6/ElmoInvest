import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        dataOutputStream.writeUTF("Hello from the client!");
        dataOutputStream.flush();

        String message = dataInputStream.readUTF();
        System.out.println("Message received from server: " + message);

        dataInputStream.close();
        dataOutputStream.close();
        socket.close();
    }
}

