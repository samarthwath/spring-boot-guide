package demo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class Sample {
	public static void main(String[] args) {
		int[] ints = { 1, 2, 3, 4, 5 };
		List<Integer> listArray = new ArrayList<Integer>();
		for (int index = 0; index < ints.length; index++) {
			listArray.add(ints[index]);
		}

		Optional<Integer> sum = listArray.stream().reduce((a, b) -> a + b);
		System.out.println(sum);

		String str1 = "abcdABCDabcd";
		int[] array = { 1, 2, 3, 4, 5, 6, 7 };
		Random xyz = new Random();
		for (int index = 0; index < array.length; index++) {
			int dynamicIndex = xyz.nextInt(array.length);
//			array[dynamicIndex]
			System.out.println(array[dynamicIndex]);
		}

	}

}
