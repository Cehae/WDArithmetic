package myOffer.binaryTreeTest01;

import java.util.Stack;

//搜索二叉树
public class SearchBinaryTree {

	// 节点
	public class TreeNode {

		private int key;
		private int data;
		private TreeNode leftChild;
		private TreeNode rightChild;
		private TreeNode parent;

		public TreeNode(int key, int data) {
			super();
			this.key = key;
			this.data = data;

			this.parent = null;
			this.leftChild = null;
			this.rightChild = null;
		}
	}

	private TreeNode root = null;

	/*
	 * 构造方法 A B C D E F
	 * 
	 * @auther Cehae
	 */
	public SearchBinaryTree() {
		root = new TreeNode(1, 50);
	}

	public TreeNode put(int data) {

		if (null == root) {
			root = new TreeNode(0, data);
			return root;
		}

		TreeNode parent = null;
		TreeNode node = root;

		while (null != node) {

			parent = node;

			if (data > node.data) {

				node = node.rightChild;

			} else if (data < node.data) {

				node = node.leftChild;

			} else {

				return node;
			}
		}

		// 到此处说明知道节点要插入的位置了，创建节点
		node = new TreeNode(0, data);

		// 判断是插入左边还是右边。
		if (data > parent.data) {

			parent.rightChild = node;

		} else {

			parent.leftChild = node;
		}

		// 连接上父节点
		node.parent = parent;

		return node;
	}

	//
	public static void main(String[] args) {
		SearchBinaryTree tree = new SearchBinaryTree();

		int[] data = { 50, 30, 20, 44, 88, 33, 87, 16, 7, 77 };

		for (int i : data) {

			tree.put(i);
		}

		tree.midOrder_1(tree.root);
	}

	// 中序
	public void midOrder_1(TreeNode node) {

		if (null == node) {

			return;
		}

		midOrder_1(node.leftChild);

		System.out.println("数据:" + node.data);

		midOrder_1(node.rightChild);
	}

	// 中序
	public void midOrder_2(TreeNode node) {

		if (null == node) {
			return;
		}

		Stack<TreeNode> stack = new Stack<TreeNode>();

		while (!stack.isEmpty() || null != node) {

			if (null != node) {

				stack.push(node);

				node = node.leftChild;

			} else {

				node = stack.pop();

				System.out.println("数据：" + node.data);

				node = node.rightChild;
			}
		}
	}

	/**
	 * 
	 * 循环方式前序遍历
	 * 
	 * @auther Cehae
	 */
	public void preOrderUn(TreeNode node) {

		if (null == node) {
			return;
		}

		Stack<TreeNode> stack = new Stack<TreeNode>();

		stack.push(node);

		while (!stack.isEmpty()) {
			// 弹出根节点
			TreeNode topNode = stack.pop();

			System.out.println("数据:" + topNode.data);

			if (topNode.rightChild != null) {
				// 压入右子节点
				stack.push(topNode.rightChild);
			}

			if (topNode.leftChild != null) {
				// 压入左子节点
				stack.push(topNode.leftChild);
			}
		}
	}

	/**
	 * 
	 * 循环方式中序遍历
	 * 
	 * @auther Cehae
	 */
	public void midOrderUn(TreeNode node) {

		if (null == node) {
			return;
		}

		Stack<TreeNode> stack = new Stack<TreeNode>();

		while (!stack.isEmpty() || null != node) {

			if (null != node) {

				stack.push(node);

				node = node.leftChild;

			} else {

				node = stack.pop();

				System.out.println("数据：" + node.data);

				node = node.rightChild;
			}
		}
	}

	/**
	 * 
	 * 循环方式后序遍历方式一
	 * 
	 * @auther Cehae
	 */
	public void postOrderUn_1(TreeNode node) {

		if (null == node) {

			return;
		}

		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();

		s1.push(node);

		while (!s1.isEmpty()) {

			node = s1.pop();

			s2.push(node);

			if (node.leftChild != null) {
				s1.push(node.leftChild);
			}

			if (node.rightChild != null) {
				s1.push(node.rightChild);
			}
		}

		while (!s2.isEmpty()) {

			node = s2.pop();

			System.out.println("数据:" + node.data);
		}
	}
}
