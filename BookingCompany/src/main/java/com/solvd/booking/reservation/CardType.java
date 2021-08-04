package com.solvd.booking.reservation;


import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public enum CardType {
    AMEX {
        @Override
        public boolean isValid(String number) {
            return isValidNumber(
                    length -> length == 15,
                    n -> n.startsWith("34") || n.startsWith("37"),
                    number);
        }
    },
    MASTER {
        @Override
        public boolean isValid(String number) {
            return isValidNumber(
                    length -> length == 16,
                    n -> IntStream.concat(IntStream.rangeClosed(51, 55),
                                          IntStream.rangeClosed(2221, 2720))
                                   .mapToObj(String::valueOf)
                                   .anyMatch(n::startsWith),
                    number);
        }
    },
    VISA {
        @Override
        public boolean isValid(String number) {
            return isValidNumber(
                    length -> length == 13 || length == 16,
                    n -> n.startsWith("4"),
                    number);
        }
    };


    public abstract boolean isValid(String number);

    private static boolean isValidNumber(IntPredicate lengthConditions, Predicate<String> startConditions,
                                         String number){

        return lengthConditions.test(number.length())
            && startConditions.test(number);
    }

}
