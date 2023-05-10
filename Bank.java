package BankApplication1;

import java.sql.*;

public class Bank {
    private String name;

    public Bank() {
    }

    public Bank(String name) {
        this.name = name;
    }

    public void listAccount() {
        Connection connection = BankingConnection.connect();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM account";
            ResultSet results = statement.executeQuery(sql);

            while (results.next()) {
                System.out.println(results.getString(1)
                        + results.getString(2)
                        + results.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void openAccount(Account account) {
        Connection connection = BankingConnection.connect();
        String sql = "INSERT INTO account(accNumber,accName,accBalance)" + "VALUES(?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account.getNumber());
            preparedStatement.setString(2, account.getName());
            preparedStatement.setDouble(3, account.getBalance());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void closeAccount(int number) {
        Connection connection = BankingConnection.connect();
        String sql = "DELECT FROM account WHERE accNumber = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, number);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void depositMoney(int number, double amount) {
        Account account = getAccount(number);
        account.deposit(amount);
        Connection connection = BankingConnection.connect();
        String sql = "UPDATE account SET accBalance = ? WHERE accNumber = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getNumber());

            System.out.println("Balance : " + account.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void withdrawMoney(int number, double amount) {
        Account account = getAccount(number);
        account.withdraw(amount);
        Connection connection = BankingConnection.connect();
        String sql = "UPDATE account SET accBalance = ? WHERE accNumber = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getNumber());

            System.out.println("Balance : " + account.getBalance());
            preparedStatement.executeUpdate();
            System.out.println("Balance : " + account.getBalance());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Account getAccount(int number) {
        Connection connection = BankingConnection.connect();
        String sql = "SELECT * FROM account WHERE accNumber = ?";
        PreparedStatement preparedStatement;
        Account account = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,number);
            ResultSet result = preparedStatement.executeQuery();

            result.next();
            String accName = result.getString(2);
            double balance = result.getDouble(3);
            account = new Account(number, accName, balance);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }

    public double getBalance(int number) {
        return getBalance(number);
    }

}
