import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class StockMarketClient {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 8080);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter stock name: ");
                String stockName = scanner.nextLine();
                out.writeUTF(stockName);
                out.flush();
                double price = in.readDouble();
                System.out.println("Price: " + price);
            }
        }
    }
}
