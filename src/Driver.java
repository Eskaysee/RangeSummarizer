import numberrangesummarizer.NumberRangeSummarizer;
import numberrangesummarizer.NumberRangeSummarizerImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        String delimiter = ",";
        String sequence = "";
        int type = 1;
        Scanner in = new Scanner(System.in);
        boolean again = true;

        do {
            System.out.println("Enter a delimiter or press enter to use default (\",\"):");
            String input;
            if (in.hasNextLine()) {
                input = in.nextLine().strip();
                if (!input.isEmpty()) {
                    delimiter = input;
                }
            }
            String numberTypes = "Will your sequence contain whole numbers or decimals (Enter number)?\n" +
                    "1. Whole number\n" +
                    "2. Decimals" ;
            System.out.println(numberTypes);
            input = in.nextLine();
            if (!input.isEmpty()) {
                type = Integer.parseInt(input);
            } else {
                System.out.println("Enter a valid number next time!");
                continue;
            }
            System.out.println("Enter a sequence of numbers separated by your chosen delimiter.");
            if (in.hasNextLine()) {
                sequence = in.nextLine();
            }

            if (type == 1) {
                NumberRangeSummarizer<Long> summarizer = new NumberRangeSummarizerImpl<>(delimiter);
                try {
                    System.out.println(summarizer.summarizeCollection(
                            summarizer.collect(sequence)
                    ));
                } catch (ParseException | InputMismatchException e) {
                    System.out.println(e.getMessage());
                }
            } else if (type == 2) {
                NumberRangeSummarizer<Double> summarizer = new NumberRangeSummarizerImpl<>(delimiter);
                try {
                    System.out.println(summarizer.summarizeCollection(
                            summarizer.collect(sequence)
                    ));
                } catch (ParseException | InputMismatchException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("Do you want to go again? (Y/N)");
            if (in.hasNextLine()) {
                again = in.nextLine().equalsIgnoreCase("Y");
            } else {
                again = false;
            }
        } while (again);
        in.close();
    }
}
