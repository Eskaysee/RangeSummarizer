package test;

import numberrangesummarizer.NumberRangeSummarizer;
import numberrangesummarizer.NumberRangeSummarizerImpl;
import org.junit.Test;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Collection;

/**
 * Tests the NumberRangeSummarizer
 * @author sihle
 * */
public class NumberRangeSummarizerTest {

    @Test
    public void collectTest() {
        NumberRangeSummarizer<Integer> nrs = new NumberRangeSummarizerImpl<>();
        Collection<Integer> expected = Arrays.asList(1,3,6,7,8,12,13,14,15,21,22,23,24,31);
        Collection<Integer> actual = nrs.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void summarizeCollectionTest() {
        NumberRangeSummarizer<Integer> nrs = new NumberRangeSummarizerImpl<>();
        Collection<Integer> input = Arrays.asList(1,3,6,7,8,12,13,14,15,21,22,23,24,31);
        String expected = "1, 3, 6-8, 12-15, 21-24, 31";
        String actual = nrs.summarizeCollection(input);
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void longRangeSummarizerTest() {
        NumberRangeSummarizer<Long> nrs = new NumberRangeSummarizerImpl<>();
        Collection<Long> input = Arrays.asList(1L,3L,2L,5L,7L,10L,8L);
        String expected = "1, 2-3, 5, 7-8, 10";
        String actual = nrs.summarizeCollection(input);
        Assert.assertEquals(expected, actual);
    }
}