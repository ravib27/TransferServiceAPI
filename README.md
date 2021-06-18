Transfer Service API:

This is a Spring Boot application which can accessed from Github via the following url : https://github.com/ravib27/transferservice/tree/master

It can be cloned and run and local machine as a Spring Boot Application. Once the application is up, we can call the Transfer Service via the following endpoint : http://localhost:9091/natwest/executetransfer to execute the transfer

Have managed the implementation using in-memory H2 Database. Files schema.sql and data within src/main/resources are responsible for schema creation and initial data insertion. The H2 Database Console can be accessed via the following url : http://localhost:9091/h2-console

For logging to the console following details should be used:

JDBC URL : jdbc:h2:mem:transferdb
User Name : sa
Password : password 

The account table has been loaded with below initial set of data:

INSERT INTO account (customer_id, account_number, account_balance) VALUES
  (1011, '6101161', '1500.00'),
  (1012, '6101162', '2500.00'),
  (1013, '6101163', '3500.00'),
  (1014, '6101164', '1500.00'),
  (1015, '6101165', '2500.00'),
  (1016, '6101166', '3500.00');

The Transfer Service API accepts as input - source account number, destination account number and the amount in json format like below:

{
    "srcAccountNumber": "6101161",
    "destAccountNumber": "6101162",
    "amount": "1000.00"
}

It makes a post request to execute the transfer and returns appropriate response.

If the transfer is successful is returns the following response message : "Transaction Successful : Funds transffered from Source Account to Destination Account".

If the transfer details are invalid with incorrect source account number or incorrect destination account number or in correct source and destination account number, it will return following response messages : "Invalid Transfer Details : Source Account is not valid", "Invalid Transfer Details : Destination Account is not valid", "Invalid Transfer Details : Source and Destination Accounts are not valid".

Unit and Integration test coverage has been provided using jUnit and all test cases have have ran successfully.


