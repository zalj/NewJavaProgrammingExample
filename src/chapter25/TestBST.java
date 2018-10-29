package chapter25;

import java.util.ArrayList;

public class TestBST {

	public static void main(String[] args) {
		BST<String> tree = new BST<>();
		tree.insert("Geroge");
		tree.insert("Miachel");
		tree.insert("Tom");
		tree.insert("Adam");
		tree.insert("Jones");
		tree.insert("Peter");
		tree.insert("Daniel");
		
		tree.breadthFirstTraversal();
//		System.out.print("Inorder (sorted): ");
//		tree.inorder();
//		System.out.print("\nPostorder: ");
//		tree.postorder();
//		System.out.print("\nPreorder: ");
//		tree.preorder();
//		System.out.print("\nThe number of nodes is " + tree.getSize());
//		
//		System.out.print("\nIs Peter in the tree? " + tree.search("Peter"));
//		
//		System.out.print("\nA path from the root to Peter is: ");
//		ArrayList<BST.TreeNode<String>> path = tree.path("Peter");
//		for(int i = 0; path != null && i < path.size(); i++)
//			System.out.print(path.get(i).element + " ");
//		
//		Integer[] numbers = {2, 4, 3, 1, 8, 5, 6, 7};
//		BST<Integer> intTree = new BST<>(numbers);
//		System.out.print("\nA path from the root to 7 is: ");
//		ArrayList<BST.TreeNode<Integer>> intPath = intTree.path(7);
//		for(int i = 0; intPath != null && i < intPath.size(); i++)
//			System.out.print(intPath.get(i).element + " ");
//		
//		System.out.print("\nInorder (sorted): ");
//		intTree.inorder();
	}

}
