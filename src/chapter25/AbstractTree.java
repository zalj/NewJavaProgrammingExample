package chapter25;

public abstract class AbstractTree<E> implements Tree<E> {
	@Override /** Inorder traversal from the root */
	public void inorder() {
	}

	@Override /** Postorder traversal from the root */
	public void postorder() {
	}

	@Override /** Preorder traversal from the root */
	public void preorder() {
	}

	@Override
	public boolean isEmpty() {
		return getSize() == 0;
	}

}
