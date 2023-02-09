import java.io.*;
import java.net.*;
import java.util.*;

public class StockMarketClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
       /*  try (Socket socket = new Socket("localhost", 8080);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
*/
            Scanner scanner = new Scanner(System.in);
            try {
                Socket echoSocket = new Socket("localhost", 8080);
                PrintWriter out =
                    new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in =
                    new BufferedReader(
                        new InputStreamReader(echoSocket.getInputStream()));
                BufferedReader stdIn =
                    new BufferedReader(
                        new InputStreamReader(System.in));
            
            
                //Auth with server admin/password2 or user/password1
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                out.println(username);
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                out.println(password);

                
                    //Taking user choice
                System.out.println("Please choose from the following options");
                System.out.println("1 Search for a stock");
                System.out.println("2. Buy stocks");
                System.out.println("3. Edit stocks (admins only)");
                System.out.println("4. Exit");
                String choice = scanner.nextLine();
                out.println(choice);

                switch(Integer.parseInt(choice)){
                    case 1:
                   
                        System.out.print("Enter stock name: ");
                        String stockName = scanner.nextLine();
                        out.println(stockName);
                        out.flush();
                        String price = in.readLine();
                        System.out.println("Price: " + price);
                    break;
                    case 2:
                        System.out.println("Which stock would you like to invest?");
                        String invest = scanner.nextLine();
                        out.println(invest); 
                        System.out.println("How many stocks would you like to buy?");
                        String nstocks = scanner.nextLine();
                        out.println(nstocks);
                        System.out.print("Your investment = "+ in.readLine());    
                    break;
                    case 3:
                    System.out.print("Please enter the stock you want to add ");
                    String NewStock = scanner.nextLine();
                    out.println(NewStock);
                    System.out.print("Please enter the stock price ");
                    String NewPrice = scanner.nextLine();
                    out.println(NewPrice);
                    System.out.println(in.readLine());

                    break;
                }
            
                
            } catch (UnknownHostException e) {
                System.err.println("Don't know about host " + "localhost");
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to " +
                    "localhost");
                System.exit(1);
                
            }
        }
    }



