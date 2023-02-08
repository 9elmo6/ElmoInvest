import java.net.*;
import java.io.*;
 
public class MTEchoServer {


private static class Stock {
        private String name;
        private double price;

        public Stock(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }
        Stock google = new Stock("GOOG", 1234.56);
        Stock amazon = new Stock("AMZN", 2345.67);
        Stock apple = new Stock("AAPL", 3456.78);





    public static void main(String[] args) {
         
      
         
        int portNumber =8080;
        MTEchoServer es = new MTEchoServer();
        es.run(portNumber);
     }
     public void run(int portNumber) {
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            while(true) {
               Socket client = serverSocket.accept();
               Connection cc = new Connection(client);
               
               System.out.println("A new Connection was just made from "+ 
client.getRemoteSocketAddress() );
            }
        } catch(Exception e) {
           System.out.println("Exception: "+e);
        }
    }
         
  

class Connection extends Thread {
    Socket client;
    //PrintWriter out;
    //BufferedReader in;
     ObjectOutputStream out;
      ObjectInputStream in;
    public Connection(Socket s) { // constructor
       client = s;
          
       try {
          out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
          // out = new PrintWriter(client.getOutputStream(), true);                  
           //in = new BufferedReader(   new InputStreamReader(client.getInputStream()));
       } catch (IOException e) {
           try {
             client.close();
           } catch (IOException ex) {
             System.out.println("Error while getting socket streams.."+ex);
           }
           return;
       }
        this.start(); // Thread starts here...this start() will call run()
        
    }
 
    public void run() {
      try {

            while (true) {
           

                    String stockName = in.readUTF();
                    if(stockName.equalsIgnoreCase("EXIT"))
                    break;



                    Stock stock = null;
                    if (stockName.equals("GOOG")) {
                        stock = google;
                    } else if (stockName.equals("AMZN")) {
                        stock = amazon;
                    } else if (stockName.equals("AAPL")) {
                        stock = apple;
                    }

                    if (stock != null) {
                        out.writeDouble(stock.getPrice());
                        out.flush();
                    }

                }
            



        
         client.close();
       } catch (IOException e) {
           System.out.println("Exception caught...");
           System.out.println(e.getMessage());
       }
    }
}
}