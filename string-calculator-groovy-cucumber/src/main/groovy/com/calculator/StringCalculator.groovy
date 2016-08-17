package com.calculator

import java.util.List;

class StringCalculator {

    public static void main(String[] args) {
        StringCalculator calc = new StringCalculator();
        print calc.add("1,2,5,6");

    }

    public int add(String numbers) {
        List<String> numberList = numbers.split(",").toList();

        int sum = 0;
        for (String num : numberList) {
            sum += Integer.valueOf(num);
        }
        return sum;
    }
}
