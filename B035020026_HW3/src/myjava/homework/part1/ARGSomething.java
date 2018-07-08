package myjava.homework.part1;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ARGSomething {
	private static int Digit = 16;
	public static int avg(int... args) {
		int sum = 0;
		for (int i : args) sum += i;
		return sum / args.length;
	}

	public double avg(double... args) {
		BigDecimal sum = new BigDecimal(0);
		for (double i : args) sum = sum.add(new BigDecimal(String.valueOf(i))); // Double型態不可直接給(型態構造問題)
		return sum.divide(BigDecimal.valueOf(args.length), Digit, RoundingMode.HALF_UP).doubleValue(); // 解決無限小數問題
	}

	public static String avg(String... args) {
		if (args.length == 1) {
			return "The number of this word is " + args[0].length();
		} else {
			BigDecimal sum = new BigDecimal(0);
			for (String i : args) sum = sum.add(BigDecimal.valueOf(i.length()));
			return "The avg number of these words is " + sum.divide(BigDecimal.valueOf(args.length), Digit, RoundingMode.HALF_UP);
		}
	}

	public static String avg(Apple... args) {
		BigDecimal price = new BigDecimal(0);
		BigDecimal weight = new BigDecimal(0);
		for (Apple i : args) {
			price = price.add(new BigDecimal(String.valueOf(i.getPrice())));
			weight = weight.add(new BigDecimal(String.valueOf(i.getWeight())));
		}
		return "The price per gram is " + price.divide(weight, Digit, RoundingMode.HALF_UP);
	}

	public static void main(String[] args) {
		// You cannot modify anything in the main function!!!!
		ARGSomething t=new ARGSomething();
		System.out.println(t.avg(10,20));
		System.out.println(t.avg(20,60,120));
		System.out.println(t.avg(10,20,30,40));

		System.out.println(t.avg(0.1,0.2));
		System.out.println(t.avg(20,60.0,120));
		System.out.println(t.avg(10,20,new Integer(30),40.0));

		System.out.println(avg("Apple"));
		System.out.println(avg("Apple","Cat"));
		System.out.println(avg("Apple","Cat",new String("Bee")));

		System.out.println(avg(new Apple(10,59.5)));
		System.out.println(avg(new Apple(10,59.5),new Apple("Washington",30,80.3)));
		Apple green=new Apple("Granny Smith",30,88);
		System.out.println(avg(new Apple(10,59.5),new Apple("Washington",30,80.3),green));

	}

}
