import junit.framework.Assert;
import org.example.QuickSort;
import org.junit.jupiter.api.Test;

public class QuickSortTest {
    @Test
    public void testQuickSortSize() {
        int size = 1000;
        QuickSort qs = new QuickSort(size);
        Assert.assertEquals(1000, qs.array.length);
    }
    @Test
    public void testQuickSortOrder() {
        int size = 1000;
        QuickSort qs = new QuickSort(size);
        for(int i = 0; i < size - 1; i++){
            Assert.assertTrue(qs.array[i] <= qs.array[i + 1]);
        }
    }
}
