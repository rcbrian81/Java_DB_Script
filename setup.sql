-- setup_db.sql
-- 
-- Run this file in SQLite with:
--   sqlite3 mydatabase.db < setup_db.sql
-- This will create the tables and insert sample data.

-- 1. DROP TABLES IF THEY ALREADY EXIST (to avoid conflicts while testing)
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS customers;

-- 2. CREATE TABLES

-- Table: customers
CREATE TABLE customers (
    customer_id   INTEGER PRIMARY KEY AUTOINCREMENT,
    first_name    VARCHAR(50) NOT NULL,
    last_name     VARCHAR(50) NOT NULL,
    email         VARCHAR(100) NOT NULL,
    phone         VARCHAR(20),
    create_date   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Table: accounts
CREATE TABLE accounts (
    account_id    INTEGER PRIMARY KEY AUTOINCREMENT,
    customer_id   INTEGER NOT NULL,
    account_type  VARCHAR(30) NOT NULL,  -- e.g., 'Checking', 'Savings', etc.
    balance       DECIMAL(15, 2) NOT NULL DEFAULT 0.00,
    status        VARCHAR(20) NOT NULL DEFAULT 'Active',
    create_date   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- Table: transactions
CREATE TABLE transactions (
    transaction_id   INTEGER PRIMARY KEY AUTOINCREMENT,
    account_id       INTEGER NOT NULL,
    transaction_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    amount           DECIMAL(15, 2) NOT NULL,
    transaction_type VARCHAR(30) NOT NULL,   -- e.g., 'Deposit', 'Withdrawal', etc.
    reference        VARCHAR(100),
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);

-- 3. SEED (INSERT) SAMPLE DATA

-- Insert some customers
INSERT INTO customers (first_name, last_name, email, phone)
VALUES
  ('John', 'Doe', 'john.doe@example.com', '555-1234'),
  ('Jane', 'Smith', 'jane.smith@example.com', '555-5678'),
  ('Bob',  'Williams', 'bob.williams@example.com', '555-9012');

-- Insert some accounts (assumes auto-increment IDs for customers are 1..3)
INSERT INTO accounts (customer_id, account_type, balance, status)
VALUES
  (1, 'Checking',  500.00, 'Active'),
  (1, 'Savings',   1500.00, 'Active'),
  (2, 'Checking',  250.00,  'Active'),
  (2, 'Loan',     -1000.00, 'Active'),  -- negative balance to represent a loan
  (3, 'Savings',   2000.00, 'Active');

-- Insert some transactions (assumes auto-increment IDs for accounts are 1..5)
INSERT INTO transactions (account_id, amount, transaction_type, reference)
VALUES
  (1,  200.00,  'Deposit',    'Initial deposit'),
  (1,  -50.00,  'Withdrawal', 'ATM withdrawal'),
  (2,  -25.00,  'Withdrawal', 'Groceries'),
  (4,  200.00,  'Payment',    'Loan Payment'),
  (5,  500.00,  'Deposit',    'Payroll');

-- 4. OPTIONAL: SELECT queries to verify data was inserted correctly
SELECT 'CUSTOMERS' AS table_name;
SELECT * FROM customers;

SELECT 'ACCOUNTS' AS table_name;
SELECT * FROM accounts;

SELECT 'TRANSACTIONS' AS table_name;
SELECT * FROM transactions;

-- End of script

