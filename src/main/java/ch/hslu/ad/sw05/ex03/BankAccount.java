package ch.hslu.ad.sw05.ex03;

public final class BankAccount {
    private int balance;
    private String name;

    public BankAccount(final int balance, final String name) {
        this.balance = balance;
        this.name = name;
    }

    public int getBalance() {
        return this.balance;
    }

    public void deposite(final int amount) {
        this.balance += amount;
    }

    public void transfer(final BankAccount target, final int amount) {
        this.balance -= amount;
        target.deposite(amount);
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + " (" + String.valueOf(balance) + ".-)";
    }
}