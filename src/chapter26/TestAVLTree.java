package chapter26;

import chapter25.BST;

public class TestAVLTree {
	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<>(new Integer[] {25, 20, 5});
		System.out.print("After inserting 25, 20, 5:");
		printTree(tree);
		
		tree.insert(34);
		tree.insert(50);
		System.out.print("After inserting 34, 50:");
		printTree(tree);
		
		tree.insert(30);
		System.out.print("After inserting 30:");
		printTree(tree);
		
		tree.delete(34);
		tree.delete(30);
		tree.delete(50);
		System.out.print("After removing 34, 30, 50:");
		printTree(tree);
		
		tree.delete(5);
		System.out.print("After removing 5:");
		printTree(tree);
		
		System.out.print("\nTraverse the elements in the tree: ");
		for(int e: tree) {
			System.out.print(e + " ");
		}
	}
	@SuppressWarnings("rawtypes") 
	private static void printTree(BST tree) {
		System.out.print("\nInorder (sorted): ");
		tree.inorder();
		System.out.print("\nPostorder (sorted): ");
		tree.postorder();
		System.out.print("\nPreorder (sorted): ");
		tree.preorder();
		System.out.print("\nThe number of nodes is " + tree.getSize());
		System.out.println();
	}
}
