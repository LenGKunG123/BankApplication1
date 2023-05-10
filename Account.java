package BankApplication1;

public class Account {
    private int number;
    private String name;
    private double balance;

    public Account (int number, String name, double balance){
        this.name = name;
        this.number = number;
        this.balance = balance;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
    public void deposit (double amount) {
        balance = balance + amount;
        System.out.println("Deposit = " + amount);
        System.out.println("Total balance =" + balance );
    }
    public void withdraw (double amount){
        balance = balance - amount;
        System.out.println("Withdraw = " + amount);
        System.out.println("Total balance =" + balance );
    }
}