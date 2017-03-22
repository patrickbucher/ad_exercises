package ch.hslu.ad.sw05.ex03;

public class Transfer extends Thread implements Runnable {

    // when logging is active, threads will synchronize properly
    private static final boolean LOGGING = false;

    private static final boolean WORKING_SOLUTION = true;

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

            if (WORKING_SOLUTION) {
                synchronized (sourceAccount) {
                    sourceAccount.deposite(-PARTIAL_AMOUNT);
                }
                synchronized (targetAccount) {
                    targetAccount.deposite(+PARTIAL_AMOUNT);
                }
            } else {
                // can't possibly be synchronized
                sourceAccount.transfer(targetAccount, PARTIAL_AMOUNT);
            }
            amountDue -= PARTIAL_AMOUNT;
            if (LOGGING) {
                String logMsg = String.format("%d.- from %s to %s", PARTIAL_AMOUNT, sourceAccount, targetAccount);
                System.out.println(logMsg);
            }
        }
    }

}
