package ch.hslu.ad.sw05.ex03;

public class Transfer extends Thread implements Runnable {

    private final static int PARTIAL_AMOUNT = 1;

    private final BankAccount sourceAccount;
    private final BankAccount targetAccount;
    private int amountDue;

    public Transfer(BankAccount sourceAccount, BankAccount targetAccount, int amount) {
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.amountDue = amount;
    }

    @Override
    public void run() {
        while (amountDue > PARTIAL_AMOUNT) {
            sourceAccount.transfer(targetAccount, PARTIAL_AMOUNT);
            String logMsg = String.format("transfer %d.- from %s to %s", PARTIAL_AMOUNT, sourceAccount, targetAccount);
            System.out.println(logMsg);
            amountDue -= PARTIAL_AMOUNT;
        }
    }

}
