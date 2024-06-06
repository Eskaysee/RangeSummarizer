package test;

import numberrangesummarizer.NumberRangeSummarizer;
import numberrangesummarizer.NumberRangeSummarizerImpl;
import org.junit.Test;
import org.junit.Assert;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Tests the NumberRangeSummarizer
 * @author sihle
 * */
public class NumberRangeSummarizerTest {

    @Test
    public void collectLongTest() throws ParseException {
        NumberRangeSummarizer<Long> nrs = new NumberRangeSummarizerImpl<>();
        Collection<Long> expected = Arrays.asList(1L,3L,6L,7L,8L,12L,13L,14L,15L,21L,22L,23L,24L,31L);
        Collection<Long> actual = nrs.collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void collectFloatTest() throws ParseException {
        NumberRangeSummarizer<Double> nrs = new NumberRangeSummarizerImpl<>();
        Collection<Double> expected = Arrays.asList(1.5D,3.5D,6.5D,7.5D,8.5D,12.5D,13.5D,14.5D,15.5D,21.5D,22.5D,23.5D,24.5D,31.5D);
        Collection<Double> actual = nrs.collect("1.5,3.5,6.5,7.5,8.5,12.5,13.5,14.5,15.5,21.5,22.5,23.5,24.5,31.5");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void summarizeCollectionTest() {
        NumberRangeSummarizer<Integer> nrs = new NumberRangeSummarizerImpl<>();
        Collection<Integer> input = Arrays.asList(1,3,6,7,8,12,13,14,15,21,22,23,24,31);
        String expected = "1, 3, 6-8, 12-15, 21-24, 31";
        String actual = nrs.summarizeCollection(input);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void longRangeSummarizerTest() throws ParseException {
        NumberRangeSummarizer<Integer> nrs = new NumberRangeSummarizerImpl<>();
        Collection<Integer> input = Arrays.asList();
        String expected = "1-3, 5, 7-8, 10";
        String actual = nrs.summarizeCollection(nrs.collect("1,3,2,5,7,10,8"));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void invalidSequenceTest() {
        ParseException pe = Assert.assertThrows(
                ParseException.class,
                () -> new NumberRangeSummarizerImpl().collect("1, 3, 2, 5, 7, 10, 8, a")
        );
        Assert.assertEquals("String should only contain numbers", pe.getMessage());
    }

    @Test
    public void summarizeEmptyCollectionTest() {
        NumberRangeSummarizer<Integer> nrs = new NumberRangeSummarizerImpl<>();
        String expected = "Input should not be empty or null!";
        String actual = nrs.summarizeCollection(null);
        Assert.assertEquals(expected, actual);
    }
}