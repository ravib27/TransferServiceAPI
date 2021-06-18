DROP TABLE IF EXISTS account;

DROP TABLE IF EXISTS transaction;

CREATE TABLE account (
  customer_id INT PRIMARY KEY,
  account_number VARCHAR(250) NOT NULL,
  account_balance DECIMAL(13,2) NOT NULL
);

CREATE TABLE transaction (
  transaction_id INT AUTO_INCREMENT  PRIMARY KEY,
  src_account_number VARCHAR(250) NOT NULL,
  dest_account_number VARCHAR(250) NOT NULL,
  amount DECIMAL(13,2) NOT NULL,
  transaction_date TIMESTAMP NOT NULL
);