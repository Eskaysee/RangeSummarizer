package numberrangesummarizer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

/**
 * Produces a list of numbers, grouping sequential numbers in a range
 * @author Sihle
 * */
public class NumberRangeSummarizerImpl<T extends Number> implements NumberRangeSummarizer<T> {

    private String delimiter = ",";

    /**
     * Turns a string sequence into an ordered collection of numbers
     * @param input String sequence of delimiter separated numbers
     * @return ordered list of number sequence
     * */
    @Override
    public Collection<T> collect(String input) throws ParseException {
        if (input == null || input.isEmpty()) {
            return new ArrayList<>();
        }
        input = input.replaceAll("\\s+", "");
        String[] split = input.split(delimiter);
        List<T> sequence = new ArrayList<>();
        for (String str : split) {
            try {
                T num = (T) NumberFormat.getInstance().parse(str);
                sequence.add(num);
            } catch (ParseException e) {
                throw new ParseException("String should only contain numbers", e.getErrorOffset());
            }
        }
        //Double is only used for comparison - not to change the data type stored in the list/collection
        //Done since most built in java classes that extend Number have doubleValue
        sequence.sort(Comparator.comparingDouble(Number::doubleValue));
        return sequence;
    }

    /**
     * Summarizes a collection of numbers
     * @param input Collection of numbers
     * @return range summary of numbers
     * */
    @Override
    public String summarizeCollection(Collection<T> input) {
        if (input == null || input.isEmpty()) {
            return "Input should not be empty or null!";
        }
        List<T> in = new ArrayList<>(input);
        T current, previous = in.get(0);
        String result = ""+ previous;
        BigDecimal granularity = calcGranularity(previous);
        for (int i = 1; i < in.size(); i++) {
            current = in.get(i);
            //Used BigDecimal to avoid floating point errors.
            //May affect performance creating new BigDecimals for large iterations/number sequences
            BigDecimal bdCurrent= BigDecimal.valueOf(current.doubleValue());
            BigDecimal bdPrevious = BigDecimal.valueOf(previous.doubleValue());
            if (!(bdCurrent.subtract(bdPrevious)).equals(granularity)) {
                if (result.endsWith("-")) {
                    result += previous;
                }
                result += ", " + current;
            } else if (!(result.endsWith("-"))) {
                result += "-";
            } else if (i==in.size()-1) {
                result += current;
            }
            previous = current;
        }
        return result;
    }

    //Helper class. Finds detail to which the numbers are grouped
    //used to determine next whether numbers are sequential
    private BigDecimal calcGranularity(T number) {
        String numStr = number.toString();
        int decimalIndex = numStr.indexOf(".");
        double decimal = number.doubleValue() - Math.floor(number.doubleValue());
        if (decimalIndex == -1 || decimal == 0.0) {
            return BigDecimal.valueOf(1.0);
        }
        int decimalPlaces = numStr.length() - decimalIndex - 1;
        return BigDecimal.valueOf(1.0/Math.pow(10, decimalPlaces));
    }
}
