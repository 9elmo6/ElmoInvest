# client-server-app
A multi-threaded Java application
I Implemented investment app, an online investment application to check stock prices and buy stocks. This app is a multithreaded server to allow 
more than one client to connect to the server at the same time. Once a connection is established with the server, 
the user will be required to authenticate first from the client side by providing a username and a password. There are two types of 
users in the application admins, and users. Admins will have access to add new stock prices to the stocks file, unlike normal users who only 
can view stock prices list and buy stocks. The application features consist of:
1. Authentication: the first step in the application is to authenticate with the server using a username and password. There are two types of users 
in the system (admin and normal users)
2. Search for a stock: Users will be asked to enter a stock name and the price will be returned to them from the file.
3. Buy stocks: this feature will allow the logged in user to calculate the investment by choosing one of the stocks they want to invest in and the 
number of stocks they want to buy. The total price required to be paid will be displayed.
4. Adding new stock: admin users can add new stocks to the application by entering the name of the stock to be added, and the stock price. The stock 
file will then be updated with the added stock.

To Run this application:
1. Compile and run the MTEchoServer.java file to start the server
2. Compile and run the StockMarketClient.java file to start the client (You can start as many as you want)
3. authenticate using admin or user account.

To Do:

1. Add authenitcation from files not hardcoded 
2. Add Registration feature 
3. Add a Wallet feature for every user
4. Add the ability buy and sell stocks for each user
5. Add the ability for admins to edit/delete stocks
