package chapter27;

public class Test {
	public static void main(String[] args) {
		Byte byte1 = 1;
		System.out.println("Byte 1 hashcode(): " + byte1.hashCode());
		Short short1 = 1;
		System.out.println("Short 1 hashcode(): " + short1.hashCode());
		Character character = 1;
		System.out.println("Char 1 hashcode(): " + character.hashCode());
		Integer integer = 1;
		System.out.println("Integer 1 hashcode(): " + integer.hashCode());
		Long long1 = (long) (Integer.MAX_VALUE * 4) ;
		System.out.println("Long 1 hashcode(): " + long1.hashCode());
		Float float1 = 1.0f;
		System.out.println("Float 1.0f hashcode(): " + float1.hashCode());
		Double double1 = 1.0;
		System.out.println("Double 1.0 hashcode(): " + double1.hashCode());
		String string = "1";
		System.out.println("String \"1\" hashcode(): " + string.hashCode());
	}
}
