package chapter25;

public class TestBSTWithIterator {
	public static void main(String[] args) {
		BST<String> tree = new BST<>();
		tree.insert("George");
		tree.insert("Michale");
		tree.insert("Tom");
		tree.insert("Adam");
		tree.insert("Jones");
		tree.insert("Peter");
		tree.insert("Daniel");
		
		for (String string : tree) {
			System.out.print(string.toUpperCase() + " ");
		}
	}
}
