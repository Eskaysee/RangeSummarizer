package numberrangesummarizer;

import java.util.Collection;
import java.util.List;

/**
 * Produces a list of numbers, grouping sequential numbers in a range
 * @author Sihle
 * */
public class NumberRangeSummarizerImpl<T extends Number> implements NumberRangeSummarizer<T> {

    /**
     * description
     * @param input ##
     * @return
     * */
    @Override
    public Collection<T> collect(String input) {
        return List.of();
    }

    /**
     * description
     * @param input ##
     * @return
     * */
    @Override
    public String summarizeCollection(Collection<T> input) {
        return "";
    }
}
