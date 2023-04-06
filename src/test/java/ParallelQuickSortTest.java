import junit.framework.Assert;
import org.example.ParallelQuickSort;
import org.junit.jupiter.api.Test;

public class ParallelQuickSortTest {
    @Test
    public void testParallelQuickSortSize() {
        int size = 1000;
        ParallelQuickSort pqs = new ParallelQuickSort(size, size / 1000);
        Assert.assertEquals(1000, pqs.array.length);
    }
    @Test
    public void testParallelQuickSortOrder() {
        int size = 1000;
        ParallelQuickSort pqs = new ParallelQuickSort(size, size / 1000);
        for(int i = 0; i < size - 1; i++){
            Assert.assertTrue(pqs.array[i] <= pqs.array[i + 1]);
        }
    }
}
