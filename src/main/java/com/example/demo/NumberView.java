package com.example.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;

/*Number view class. Contains various Business logic methods that work on Number Entity.*/
public class NumberView {
	
	/*
	 * Function Name: calulateResult
	 * 
	 * @param-1: NumberModel number
	 * @return: NumberModel number
	 * 
	 * Function Description: 
	 * This function calculates the square root of sum of square of three highest numbers in this Integers list of NumberModel object.
	 * The calculated result is stored in the result variable of the NumberModel object.
	 * */
	public NumberModel calulateResult(NumberModel number) {
		List<Integer> numList = number.getData();
		int sum = numList.stream().sorted(Comparator.reverseOrder()).limit(3).map(n -> n * n).reduce(0, Integer::sum);
		BigDecimal bd = new BigDecimal(Math.sqrt(sum)).setScale(2, RoundingMode.HALF_UP);
		number.setOutput(bd.toString());
		return number;
	}
}
