package chapter24;

public class TestMyCircularLinkedList {

	public static void main(String[] args) {
		MyCircularLinkedList<String> list = new MyCircularLinkedList<>();
		list.add("Beijing");
		System.out.println("(1) " + list);
		list.add("Changchun");
		System.out.println("(2) " + list);
		list.remove(0);
		System.out.println("(3) " + list);
		list.add("Tianjing");
		list.add("Guangzhou");
		System.out.println("(4) " + list);
	
		System.out.println("The first element is " + list.getFirst());
		System.out.println("The last element is " + list.getLast());
		System.out.println("Is the list contains Tianjing? " + list.contains("Tianjing"));
		System.out.println("Is the list contains Chengdu? " + list.contains("Chengdu"));
		list.clear();
		System.out.println("(5) " + list);
		list.add("Lanzhou");
		System.out.println("(6) " + list);
		list.set(0, "Shenzhen");
		System.out.println("(7) " + list);
	}
}
