package ch.hslu.ad.sw06.ex05;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SumScatterGatherTest {
    private static final int NUMBERS = 1_000;

    private SumTask sumTask;
    private long sum;

    @Before
    public void prepareTaskAndSum() {
        this.sumTask = new SumTask(NUMBERS);
        this.sum = 0;
        for (int i = 0; i < sumTask.getNumbers().length; i++) {
            sum += sumTask.getNumbers()[i];
        }
    }

    @Test
    public void testSumOneThread() {
        Assert.assertEquals(sum, new SumOperator(sumTask, 1).calculateSum());
    }

    @Test
    public void testSumThreeThreads() {
        Assert.assertEquals(sum, new SumOperator(sumTask, 3).calculateSum());
    }

    @Test
    public void testSumOneThreadLessThanNumbers() {
        int nThreads = sumTask.getNumbers().length - 1;
        Assert.assertEquals(sum, new SumOperator(sumTask, nThreads).calculateSum());
    }

    @Test
    public void testSumOneNumberPerThread() {
        int nThreads = sumTask.getNumbers().length;
        Assert.assertEquals(sum, new SumOperator(sumTask, nThreads).calculateSum());
    }

}
