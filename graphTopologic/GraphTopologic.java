package myOffer.graphTopologic;

import java.util.Stack;

//求一个图的拓扑排序
public class GraphTopologic {

	// 链表接点
	class EdgeNode {

		private int adjVert;// 顶点编号

		private EdgeNode next;

		private int weight;

		// 构造方法
		public EdgeNode(int adjVert) {
			this.adjVert = adjVert;
		}

		public int getAdjVert() {
			return adjVert;
		}

		public void setAdjVert(int adjVert) {
			this.adjVert = adjVert;
		}

		public EdgeNode getNext() {
			return next;
		}

		public void setNext(EdgeNode next) {
			this.next = next;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}
	}

	// 邻接顶点
	class VertexNode {

		private int in;// 入度

		private String data;// 数据域

		private EdgeNode firstEdge;

		// 构造方法
		public VertexNode(int in, String data) {
			this.in = in;
			this.data = data;
		}

		public int getIn() {
			return in;
		}

		public void setIn(int in) {
			this.in = in;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public EdgeNode getFirstEdge() {
			return firstEdge;
		}

		public void setFirstEdge(EdgeNode firstEdge) {
			this.firstEdge = firstEdge;
		}
	}

	// 顶点数量
	private int numVertexes;

	// 存放邻接顶点的一维数组
	private VertexNode[] adjList;

	// 构造方法
	public GraphTopologic(int numVertexes) {

		this.numVertexes = numVertexes;
	}

	// 构建图，使用一维数组存放邻接顶点，然后使用链表链接顶点与顶点之间的边表接点
	// 顶点数量
	private static int num = 14;

	private void createGraph() {
		VertexNode node0 = new VertexNode(0, "v0");
		VertexNode node1 = new VertexNode(0, "v1");
		VertexNode node2 = new VertexNode(2, "v2");
		VertexNode node3 = new VertexNode(0, "v3");
		VertexNode node4 = new VertexNode(2, "v4");
		VertexNode node5 = new VertexNode(3, "v5");
		VertexNode node6 = new VertexNode(1, "v6");
		VertexNode node7 = new VertexNode(2, "v7");
		VertexNode node8 = new VertexNode(2, "v8");
		VertexNode node9 = new VertexNode(1, "v9");
		VertexNode node10 = new VertexNode(1, "v10");
		VertexNode node11 = new VertexNode(2, "v11");
		VertexNode node12 = new VertexNode(1, "v12");
		VertexNode node13 = new VertexNode(2, "v13");

		// 构建边
		node0.firstEdge = new EdgeNode(11);
		node0.firstEdge.next = new EdgeNode(5);
		node0.firstEdge.next.next = new EdgeNode(4);

		node1.firstEdge = new EdgeNode(8);
		node1.firstEdge.next = new EdgeNode(4);
		node1.firstEdge.next.next = new EdgeNode(2);

		node2.firstEdge = new EdgeNode(9);
		node2.firstEdge.next = new EdgeNode(6);
		node2.firstEdge.next.next = new EdgeNode(5);

		node3.firstEdge = new EdgeNode(13);
		node3.firstEdge.next = new EdgeNode(2);

		node4.firstEdge = new EdgeNode(7);

		node5.firstEdge = new EdgeNode(12);
		node5.firstEdge.next = new EdgeNode(8);

		node6.firstEdge = new EdgeNode(5);

		node8.firstEdge = new EdgeNode(7);

		node9.firstEdge = new EdgeNode(11);
		node9.firstEdge.next = new EdgeNode(10);

		node10.firstEdge = new EdgeNode(13);

		node12.firstEdge = new EdgeNode(9);

		// 放入数组中
		adjList = new VertexNode[numVertexes];
		adjList[0] = node0;
		adjList[1] = node1;
		adjList[2] = node2;
		adjList[3] = node3;
		adjList[4] = node4;
		adjList[5] = node5;
		adjList[6] = node6;
		adjList[7] = node7;
		adjList[8] = node8;
		adjList[9] = node9;
		adjList[10] = node10;
		adjList[11] = node11;
		adjList[12] = node12;
		adjList[13] = node13;
	}

	// 拓扑排序
	private void topologicalSort() {

		// 存放入度为0的邻接顶点
		Stack<Integer> stack = new Stack<Integer>();

		// 用于统计顶点的个数
		int count = 0;

		// 先将入度为0的邻接顶点放入栈中
		for (int i = 0; i < numVertexes; i++) {
			if (0 == adjList[i].in) {
				stack.push(i);
			}
		}

		while (!stack.isEmpty()) {

			// 取出栈顶的顶点输出
			int top = stack.pop();
			System.out.println("顶点：" + adjList[top].data);
			count++;

			// 遍历top顶点的链表
			for (EdgeNode node = adjList[top].firstEdge; node != null; node = node.next) {

				int num = node.adjVert;// 链接的顶点编号

				// 弹出栈顶的顶点后，将栈顶接点连接的顶点入度-1，再判断是否为0，
				// 如果为0要入栈。
				if (--adjList[num].in == 0) {
					stack.push(num);
				}
			}
		}

		if (count < numVertexes) {
			System.out.println("拓扑排序失败了");
		} else {
			System.out.println("拓扑排序成功了");
		}
	}

	public static void main(String[] args) {
		// 创建
		GraphTopologic g = new GraphTopologic(num);
		g.createGraph();

		g.topologicalSort();
	}
}
