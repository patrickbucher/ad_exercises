package ch.hslu.ad.sw05.ex03;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TransferTest {

    public static final int AMOUNT = 1_000;

    public static final int ACCOUNTS = 10000;

    private static final Random random = new Random(System.currentTimeMillis());

    private List<BankAccount> vatican;
    private List<BankAccount> fifa;

    private int totalSourceStartBalance = 0;
    private int totalTargetStartBalance = 0;

    @Before
    public void createScenario() {
        this.vatican = createBankAccounts(ACCOUNTS, "vati");
        this.fifa = createBankAccounts(ACCOUNTS, "fifa");
        this.totalSourceStartBalance = calculateTotalBalance(vatican);
        this.totalTargetStartBalance = calculateTotalBalance(fifa);
    }

    @Test
    public void testOneWayTransfer() {
        List<Transfer> transfers = createTransfers(vatican, fifa);
        for (Transfer transfer : transfers) {
            transfer.start();
        }
        for (Transfer transfer : transfers) {
            try {
                transfer.join();
            } catch (InterruptedException e) {
                Assert.fail("could not join the threads");
            }
        }
        int totalSourceEndBalance = calculateTotalBalance(vatican);
        int totalTargetEndBalance = calculateTotalBalance(fifa);
        Assert.assertEquals(totalSourceStartBalance + totalTargetStartBalance,
                totalSourceEndBalance + totalTargetEndBalance);
    }

    @Test
    public void testTwoWayTransfer() {
        List<Transfer> forward = createTransfers(vatican, fifa);
        List<Transfer> backward = createTransfers(fifa, vatican);
        for (int n = 0; n < ACCOUNTS; n++) {
            forward.get(n).start();
            backward.get(n).start();
        }
        for (int n = 0; n < ACCOUNTS; n++) {
            try {
                forward.get(n).join();
                backward.get(n).join();
            } catch (InterruptedException e) {
                Assert.fail("could not join the threads");
            }
        }
        int totalSourceEndBalance = calculateTotalBalance(vatican);
        int totalTargetEndBalance = calculateTotalBalance(fifa);
        Assert.assertEquals(totalSourceStartBalance, totalSourceEndBalance);
        Assert.assertEquals(totalTargetStartBalance, totalTargetEndBalance);
    }

    private static List<BankAccount> createBankAccounts(int accounts, String prefix) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        for (int n = 0; n < accounts; n++) {
            int initialBalance = random.nextInt(AMOUNT) + AMOUNT;
            bankAccounts.add(new BankAccount(initialBalance, prefix + (n + 1)));

        }
        return bankAccounts;
    }

    private static List<Transfer> createTransfers(List<BankAccount> sourceAccounts, List<BankAccount> targetAccounts) {
        List<Transfer> transfers = new ArrayList<>();
        for (int i = 0; i < ACCOUNTS; i++) {
            Transfer transfer = new Transfer(sourceAccounts.get(i), targetAccounts.get(i), AMOUNT);
            transfers.add(transfer);
        }
        return transfers;
    }

    private int calculateTotalBalance(List<BankAccount> accounts) {
        int balance = 0;
        for (BankAccount account : accounts) {
            balance += account.getBalance();
        }
        return balance;
    }

}
