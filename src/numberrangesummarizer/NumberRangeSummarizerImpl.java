package numberrangesummarizer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Produces a list of numbers, grouping sequential numbers in a range
 * @author Sihle
 * */
public class NumberRangeSummarizerImpl<T extends Number> implements NumberRangeSummarizer<T> {

    private String delimiter = ",";

    /**
     * description
     * @param input ##
     * @return
     * */
    @Override
    public Collection<T> collect(String input) {
        input = input.replaceAll("\\s+ ", "");
        String[] split = input.split(delimiter);
        List<T> sequence = new ArrayList<>();
        for (String str : split) {
            try {
                T num = (T) NumberFormat.getInstance().parse(str);
                sequence.add(num);
            } catch (ParseException e) {
                System.out.println("String should only contain numbers");
                throw new RuntimeException(e);
            }
        }
//        Collections.sort(sequence);
        return sequence;
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
