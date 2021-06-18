Transfer Service API:

It accepts as input - source account number, destination account number and the amount in json format like below:

{
    "srcAccountNumber": "6101161",
    "destAccountNumber": "6101162",
    "amount": "1000.00"
}

It makes a post request to execute the transfer and returns appropriate response.

If the transfer is successful is returns the following response message : "Transaction Successful : Funds transffered from Source Account to Destination Account".

If the transfer details are invalid with incorrect source account number or incorrect destination account number or in correct source and destination account number, it will return following response messages : "Invalid Transfer Details : Source Account is not valid", "Invalid Transfer Details : Destination Account is not valid", "Invalid Transfer Details : Source and Destination Accounts are not valid".

Have managed the implementation using in-memory H2 Database. Files schema.sql and data within src/main/resources are responsible for schema creation and initial data insertion.

Unit and Integration test coverage has been provided using jUnit and all test cases have have ran successfully.
