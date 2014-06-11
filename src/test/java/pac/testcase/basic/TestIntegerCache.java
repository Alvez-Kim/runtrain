package pac.testcase.basic;

import java.lang.reflect.Field;

public class TestIntegerCache {
	public static void main(String[] args) {
		
		/*
		 * You need to change it even deeper than you can typically access. Note
		 * that this is designed for Java 6 with no funky parameters passed in
		 * on the JVM that would otherwise change the IntegerCache.
		 * 
		 * Deep within the Integer class is a Flyweight of Integers. This is an
		 * array of Integers from −128 to +127. cache[132] is the spot where 4
		 * would normally be. Set it to 5.
		 */
		
		Class cache = Integer.class.getDeclaredClasses()[0];
		System.out.println(Integer.class.getDeclaredClasses()[0].getClass().getName());
		Field c;
		try {
			c = cache.getDeclaredField("cache");
			c.setAccessible(true);
			Integer[] array = (Integer[]) c.get(cache);
			array[132] = array[133];
			String result = String.format("%d", 2+2);
			System.out.println(result);
			System.out.println(2+2);
			System.out.println(new String(2+2+""));
			result = String.format("%d", new Integer(2)+new Integer(2));
			System.out.println(result);
			
			Integer o1 = new Integer(128);
			Integer o2 = new Integer(12);
			System.out.printf("%b",o1==128);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}
}
