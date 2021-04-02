package com.jcp.numbers.s001_multiply_operation_overflow;

import java.math.BigInteger;

import static java.lang.String.format;

public class Main {

    public static void main(String[] args) {
        System.out.println(mulOrThrowIfOverflow(Long.MAX_VALUE, 1));
        System.out.println(mulOrThrowIfOverflow(Long.MAX_VALUE / 2, 2));
//        System.out.println(mulOrThrowIfOverflow(Long.MAX_VALUE, 2));

        System.out.println(Math.multiplyFull(Integer.MAX_VALUE, Integer.MAX_VALUE));
//        System.out.println(Math.multiplyExact(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    private static long mulOrThrowIfOverflow(long longVal, int intVal) {
        BigInteger longWrapped = BigInteger.valueOf(longVal);
        BigInteger intWrapped = BigInteger.valueOf(intVal);

        BigInteger resultWrapped = longWrapped.multiply(intWrapped);

        if (!resultWrapped.equals(BigInteger.valueOf(longVal * intVal))) {
            throw new ArithmeticException(format("Operation overflow: %sL x %s", longVal, intVal));
        }
        return resultWrapped.longValue();
    }

}
