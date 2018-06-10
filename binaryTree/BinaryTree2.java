package myOffer.binaryTreeTest01;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//遍历二叉树
public class BinaryTree2 {

	class Node {
		int index;
		String data;
		Node leftChild;
		Node rightChild;

		public Node(int index, String data) {
			super();
			this.index = index;
			this.data = data;

			this.leftChild = null;
			this.rightChild = null;
		}
	}

	Node root = null;

	// 手动创建二叉树
	// A
	// B C
	// D E F
	public void cteateTree() {

		root = new Node(1, "A");
		Node nodeB = new Node(2, "B");
		Node nodeC = new Node(3, "C");
		Node nodeD = new Node(4, "D");
		Node nodeE = new Node(5, "E");
		Node nodeF = new Node(6, "F");

		root.leftChild = nodeB;
		root.rightChild = nodeC;

		nodeB.leftChild = nodeD;
		nodeB.rightChild = nodeE;

		nodeC.rightChild = nodeF;
	}

	// 获取二叉树的高度
	public int getHeight(Node node) {

		if (null == node) {
			return 0;
		}

		int i = getHeight(node.leftChild);

		int j = getHeight(node.rightChild);

		return i > j ? (i + 1) : (j + 1);
	}

	// 获取而二叉树的节点数
	public int getNum(Node node) {

		if (null == node) {
			return 0;
		}

		return 1 + getNum(node.leftChild) + getNum(node.rightChild);
	}

	// 递归前序，根 左 右
	public void preOrder(Node node) {

		if (null == node) {
			return;
		}

		// 先输出根节点
		System.out.println("数据：" + node.data);

		// 输出左边节点 根节点左边的可以看成是一个子树，递归调用此方法即可
		preOrder(node.leftChild);

		// 输出右边节点
		preOrder(node.rightChild);
	}

	// 递归中序 左 根 右
	public void midOrder(Node node) {

		if (null == node) {
			return;
		}

		// 先递归输出左边的节点
		midOrder(node.leftChild);

		// 先输出根节点
		System.out.println("数据：" + node.data);

		// 最后输出右边节点
		midOrder(node.rightChild);
	}

	// 递归后序 左 右 根
	public void postOrder(Node node) {

		if (null == node) {
			return;
		}

		// 先递归输出左边的节点
		postOrder(node.leftChild);

		// 最后输出右边节点
		postOrder(node.rightChild);

		// 先输出根节点
		System.out.println("数据：" + node.data);
	}

	public static void main(String[] args) {

		BinaryTree2 tree = new BinaryTree2();

		tree.cteateTree();

		int i = tree.getHeight(tree.root);
		int num = tree.getNum(tree.root);

		// System.out.println("二叉树的高度为：" + i);
		// System.out.println("二叉树的节点数为：" + num);

		System.out.println("递归实现--");

		// System.out.println("前序:");
		// tree.preOrder(tree.root);

		// System.out.println("中序:");
		// tree.midOrder(tree.root);

		System.out.println("后序:");
		tree.postOrder(tree.root);

		// 递归调用一个方法，相当于将数据加入一个栈中，先进后出

		System.out.println("循环实现--");
		// System.out.println("前序:");
		// tree.preOrderUn(tree.root);

		// System.out.println("中序:");
		// tree.midOrderUn(tree.root);

		// System.out.println("后序1:");
		// tree.postOrderUn_1(tree.root);

		// System.out.println("后序2:");
		// tree.postOrderUn_2(tree.root);

		System.out.println("层序:");
		tree.levelOrderUn(tree.root);

	}

	// 循环 前序 根 左右
	public void preOrderUn(Node node) {

		if (null == node) {
			return;
		}

		// 借用栈实现
		Stack<Node> stack = new Stack<Node>();

		stack.push(node);

		while (!stack.isEmpty()) {

			// 从栈中取出数据。
			node = stack.pop();

			// 取出数据
			System.out.println("数据：" + node.data);

			// 因为是跟 左 右 而栈是先进后出，所以一定要先把右边压入栈中 再压入左面，

			// 如果此节点左右节点不为null，将此节点的左右节点压入栈中

			if (null != node.rightChild) {

				stack.push(node.rightChild);
			}

			if (null != node.leftChild) {

				stack.push(node.leftChild);
			}
		}
	}

	// 循环 中序 左 根 右
	public void midOrderUn(Node node) {

		if (null == node) {
			return;
		}

		// 借用栈实现
		Stack<Node> stack = new Stack<Node>();

		while (!stack.isEmpty() || null != node) {

			// 先遍历出所有左节点放入栈中,停止条件是node指针为null
			if (null != node) {

				stack.push(node);

				// node指针指向左节点
				node = node.leftChild;

			} else {

				// 此时取出栈中的数据
				node = stack.pop();

				System.out.println("数据：" + node.data);

				node = node.rightChild;
			}
		}
	}

	// 循环 后序 左 右 根
	public void postOrderUn_1(Node node) {

		if (null == node) {
			return;
		}

		// 借用双栈实现
		Stack<Node> s1 = new Stack<Node>();
		Stack<Node> s2 = new Stack<Node>();

		s1.push(node);

		while (!s1.isEmpty()) {

			node = s1.pop();

			// 先不输出，先将根节点压入栈2，最后输出
			s2.push(node);

			// 注意 以下代码顺序不能换
			// 放入栈1后 左在栈底 右 在栈顶，放入栈2后，左在栈顶，右在栈底 ，而根节点早就放在栈2底部了，
			if (null != node.leftChild) {

				s1.push(node.leftChild);
			}

			if (null != node.rightChild) {

				s1.push(node.rightChild);
			}
		}

		while (!s2.isEmpty()) {
			node = s2.pop();
			System.out.println("数据:" + node.data);
		}
	}

	// 循环 后序 左 右 根
	public void postOrderUn_2(Node node) {

		if (null == node) {

			return;
		}

		Stack<Node> stack = new Stack<Node>();

		stack.push(node);

		Node pre = null;

		while (!stack.isEmpty()) {

			pre = stack.peek();// 注意只取出不移除

			if (pre.leftChild != null && node != pre.leftChild && node != pre.rightChild) {

				stack.push(pre.leftChild);
			}

			else if (pre.rightChild != null && node != pre.rightChild) {

				stack.push(pre.rightChild);
			}

			else {
				node = stack.pop();
				System.out.println("数据:" + node.data);
				node = pre;
			}
		}
	}

	// 层序 利用队列实现
	public void levelOrderUn(Node node) {

		if (null == node) {
			return;
		}

		Queue<Node> q = new LinkedList<Node>();
		q.add(node);

		while (!q.isEmpty()) {

			// Retrieves and removes the head of this queue,
			node = q.poll();// 取出并移除

			System.out.println("数据：" + node.data);

			if (null != node.leftChild) {
				q.add(node.leftChild);
			}
			if (null != node.rightChild) {
				q.add(node.rightChild);
			}
		}
	}
}
