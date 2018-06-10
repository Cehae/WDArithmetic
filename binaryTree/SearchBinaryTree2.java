package myOffer.binaryTreeTest01;

//搜索二叉树
public class SearchBinaryTree2 {

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
	public SearchBinaryTree2() {
		root = new TreeNode(1, 50);
	}

	// 删除节点
	public TreeNode deleteNode(int key) throws Exception {
		TreeNode node = searchNode(key);
		if (null == node) {
			throw new Exception("该节点无法找到1");
		} else {
			delete(node);
		}
		return null;
	}

	// 删除节点
	private void delete(TreeNode node) throws Exception {

		if (null == node) {

			throw new Exception("该节点无法找到2");

		} else {

			TreeNode parent = node.parent;

			// 第一种情况，被删除的节点无左无右
			if (node.leftChild == null && node.rightChild == null) {

				// 判断该节点是在父节点的左面还是右面
				if (parent.leftChild == node) {
					parent.leftChild = null;
				} else {
					parent.rightChild = null;
				}
				return;
			}

			// 第二种情况，被删除的节点有左无右
			if (node.leftChild != null && node.rightChild == null) {

				if (node == parent.leftChild) {

					parent.leftChild = node.leftChild;

				} else {
					parent.rightChild = node.leftChild;
				}
				return;
			}

			// 第三种情况，被删除的节点有右无左
			if (node.rightChild != null && node.leftChild == null) {

				if (node == parent.leftChild) {

					parent.leftChild = node.rightChild;
				} else {
					parent.rightChild = node.rightChild;
				}

				return;
			}

			// 第四种情况，左右都有 需要先找后继节点
			TreeNode next = getNextNode(node);
			// 先找到后继节点，将后继节点的值转移给node，然后删除后继节点，
			delete(next);

			node.data = next.data;
		}
	}

	// 查找后继节点
	private TreeNode getNextNode(TreeNode node) {

		if (null == node) {

			return null;

		} else {
			if (node.rightChild != null) {

				return getMinNode(node.rightChild);

			} else {

				TreeNode parent = node.parent;

				while (parent != null && node == parent.rightChild) {
					node = parent;
					parent = parent.parent;

				}
				return parent;
			}
		}
	}

	// 查找最小节点
	private TreeNode getMinNode(TreeNode node) {

		if (null == node) {
			return null;
		} else {

			while (node.leftChild != null) {
				node = node.leftChild;
			}

			return node;
		}
	}

	// 搜索节点
	public TreeNode searchNode(int key) {

		if (null == root) {

			return null;

		} else {

			TreeNode node = root;

			while (null != node && key != node.data) {

				if (key < node.data) {
					node = node.leftChild;
				} else {
					node = node.rightChild;
				}
			}
			return node;
		}
	}

	// put方法
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

	public static void main(String[] args) throws Exception {
		SearchBinaryTree2 tree = new SearchBinaryTree2();

		int[] data = { 50, 30, 20, 44, 88, 33, 87, 16, 7, 77 };

		for (int i : data) {
			tree.put(i);
		}

		tree.midOrder_1(tree.root);

		int i = 88;
		tree.deleteNode(i);

		System.out.println("删除" + i + "后:");
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
}
