import java.net.*;
import java.util.*;
import java.io.*;
 
public class MTEchoServer {

//Defining Stock
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
    private List<Stock> stocks = new ArrayList<>();
    static HashMap<String, String> users = new HashMap<>();
    //Read Stocks available 
    private void readStocksFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 2) {
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1]);
                    Stock stock = new Stock(name, price);
                    stocks.add(stock);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading stocks from file: " + e.getMessage());
        }
    }
    //Write to stocks file after admin edits
    private void writeStocksToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Stock stock : stocks) {
                writer.write(stock.getName() + " " + stock.getPrice());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error while writing stocks to file: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        //Define Server on port 8080
        int portNumber =8080;
        //Authentication users
        users.put("user1", "password1");
        users.put("admin", "password2");
        MTEchoServer es = new MTEchoServer();
        es.readStocksFromFile("stocks.txt");
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
    PrintWriter out;
    BufferedReader in;

    public Connection(Socket s) { // constructor
       client = s;
       try {
        //Define Input and Output streams
        out = new PrintWriter(client.getOutputStream(), true);                  
        in = new BufferedReader(
             new InputStreamReader(client.getInputStream()));
       } catch (IOException e) {
           try {
             client.close();
           } catch (IOException ex) {
             System.out.println("Error while getting socket streams.."+ex);
           }
           return;
       }
        this.start(); 
        
    }
 
    public void run() {
      try {
                //Request user credentials from the client
                String username = in.readLine();
                System.out.println(username);
                String password = in.readLine();
                System.out.println(password);
            if (authenticate(username, password)) {
                out.println("SUCCESS");
                System.out.println("in the loop");
                
                //read choice and cast it to int
                int choice= Integer.parseInt(in.readLine()); 
                switch(choice){
                    //1. searching of stock price from stock file feature
                    case  1:
                    
                    String stockName = in.readLine();
                    Stock stock = null;
                    //Looping over stocks file to search for the stock entered
                    for (Stock s : stocks) {
                        if (s.getName().equals(stockName)) 
                        {
                            stock = s;
                            break;
                            
                        }
                    else 
                        {
                            out.println ("Stock not found");
                            out.flush();
                        }
                }
                    
                    if (stock != null) {
                        
                        System.out.println(stock.getPrice());
                        out.println(stock.getPrice());
                        out.flush();
                    
                    }
                    
                    
                    break;
                    case 2:
                    //2. Calculate investment feature
                    
                    String invest= in.readLine();
                    int nstocks= Integer.parseInt(in.readLine());
                    stock = null;
                    System.out.println(invest);
                    System.out.println(nstocks);
                    for (Stock s : stocks) {
                        System.out.println("I am in loop");
                        if (s.getName().equals(invest)) {
                            stock = s;
                            break;
                    }
                }
                    
                    if (stock != null) {
                        System.out.println("I am in final condition");
                        Long investment = (long) (nstocks*stock.getPrice());
                        out.println(investment);
                        out.flush();
                        System.out.println(investment);
                    

                    }
                    
                
                    
                    break;
                    case 3:
                    //3. Update stock prices (admin only)
                   
                    if (username.equals("admin") ){
                        //add the code to add or delete stockes file
                        System.out.println("I am the admin");
                        String updateName = in.readLine();
                        Double updatePrice = Double.parseDouble(in.readLine());
                        Stock stockupdate = new Stock(updateName, updatePrice);
                    
                        stocks.add(stockupdate);
                        out.println("New stock added successfully");

                        for (Stock s : stocks) {
                            System.out.println("Stock " + s.name);
                            System.out.println("Price " + s.price);
                        
                            
                        }
                        writeStocksToFile("stocks.txt");
                    }
                    else out.println("you are not admin");

                    break;
                    
                    case 4:
                    //Exit

                    break;
                    }
                    
                    }
                else{
                    out.println("FAIL");
                    out.println("username or password invalid !!");

                }
            
         client.close();
       } catch (IOException e) {
           System.out.println("Exception caught...");
           System.out.println(e.getMessage());
       }
    }
}

private boolean authenticate(String username, String password) {
    return users.containsKey(username) && users.get(username).equals(password);
}



}