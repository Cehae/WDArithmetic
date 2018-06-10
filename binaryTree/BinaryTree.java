package myOffer.binaryTreeTest01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//创建二叉树
public class BinaryTree {

	class Node {

		int index;
		String data;

		Node parent;
		Node leftChild;
		Node rightChild;

		public Node(int index, String data) {
			super();
			this.data = data;
			this.index = index;

			this.parent = null;
			this.leftChild = null;
			this.rightChild = null;
		}
	}

	Node root = null;

	public BinaryTree() {

	}

	public static void main(String[] args) {

		BinaryTree tree = new BinaryTree();

		String[] data = { "A", "B", "D", "#", "#", "E", "#", "#", "C", "#", "F", "#", "#" };

		ArrayList<String> dataList = new ArrayList<String>();
		for (String s : data) {
			dataList.add(s);
		}

		tree.createBinaryTreePre(dataList);

		// 检测是否正确
		System.out.println("层序遍历：");
		tree.levelOrder(tree.root);
	}

	/**
	 * 
	 * A
	 * 
	 * B C
	 * 
	 * D E # F # # # # # #
	 * 
	 * A B D## E## C # F ## 利用前序遍历快速反向创建二叉树
	 * 
	 * @auther Cehae
	 */
	public void createBinaryTreePre(ArrayList<String> data) {

		createBinaryTree(data);
	}

	private Node createBinaryTree(ArrayList<String> data) {

		if (0 == data.size()) {
			return null;
		}

		String d = data.get(0);

		if ("#".equals(d)) {
			data.remove(0);
			return null;
		}

		Node node = new Node(0, d);
		data.remove(0);

		if (null == root) {
			root = node;
		}

		node.leftChild = createBinaryTree(data);

		node.rightChild = createBinaryTree(data);

		return node;
	}

	/**
	 * 
	 * 循环方式层序遍历
	 * 
	 * @auther Cehae
	 */
	public void levelOrder(Node node) {

		if (null == node) {
			return;
		}

		Queue<Node> q = new LinkedList<Node>();

		q.add(node);

		while (!q.isEmpty()) {

			// Retrieves and removes the head of this queue,
			node = q.poll();// 取出并移除

			System.out.println("数据：" + node.data + "下标：" + node.index);

			if (null != node.leftChild) {

				q.add(node.leftChild);
			}

			if (null != node.rightChild) {

				q.add(node.rightChild);
			}
		}
	}
}
