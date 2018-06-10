package myOffer.graphMatrix;

import java.util.LinkedList;

//如何构建一个图
public class Graph {

	private int VertexSize;// 顶点数量

	private int[] vertexs;// 顶点数组

	private int[][] matrix;

	private boolean[] isVisited;// 用于记录顶点是否被访问过

	private static final int MAX_WEIGHT = 1000;

	public Graph(int vertexsize) {
		this.VertexSize = vertexsize;

		this.vertexs = new int[vertexsize];
		for (int i = 0; i < vertexsize; i++) {
			this.vertexs[i] = i;
		}

		this.matrix = new int[vertexsize][vertexsize];

		this.isVisited = new boolean[vertexsize];
	}

	public int[] getVertexs() {
		return vertexs;
	}

	public void setVertexs(int[] vertexs) {
		this.vertexs = vertexs;
	}

	public static void main(String[] args) {
		System.out.println("图1");
		Graph g = new Graph(5);

		int[] a0 = { 0, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 6 };
		int[] a1 = { 9, 0, 3, MAX_WEIGHT, MAX_WEIGHT };
		int[] a2 = { 2, MAX_WEIGHT, 0, 5, MAX_WEIGHT };
		int[] a3 = { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 0, 1 };
		int[] a4 = { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 0 };

		int[][] as = { a0, a1, a2, a3, a4 };
		for (int i = 0; i < as.length; i++) {
			g.matrix[i] = as[i];
		}

		System.out.println("获取某个接点的出度" + g.getOutDegree(4));
		System.out.println("获取两个顶点之间的权值" + g.getWeight(0, 4));

		System.out.println("深度优先算法:");
		g.depthFirstSearch();

		System.out.println("广度优先算法:");
		g.broadFirstSearch();

		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println("图2");
		Graph graph = new Graph(9);

		int[] b1 = new int[] { 0, 10, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 11, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT };
		int[] b2 = new int[] { 10, 0, 18, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 16, MAX_WEIGHT, 12 };
		int[] b3 = new int[] { MAX_WEIGHT, MAX_WEIGHT, 0, 22, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 8 };
		int[] b4 = new int[] { MAX_WEIGHT, MAX_WEIGHT, 22, 0, 20, MAX_WEIGHT, MAX_WEIGHT, 16, 21 };
		int[] b5 = new int[] { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 20, 0, 26, MAX_WEIGHT, 7, MAX_WEIGHT };
		int[] b6 = new int[] { 11, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 26, 0, 17, MAX_WEIGHT, MAX_WEIGHT };
		int[] b7 = new int[] { MAX_WEIGHT, 16, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 17, 0, 19, MAX_WEIGHT };
		int[] b8 = new int[] { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 16, 7, MAX_WEIGHT, 19, 0, MAX_WEIGHT };
		int[] b9 = new int[] { MAX_WEIGHT, 12, 8, 21, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 0 };

		int[][] bs = { b1, b2, b3, b4, b5, b6, b7, b8, b9 };

		for (int i = 0; i < bs.length; i++) {
			graph.matrix[i] = bs[i];
		}

		System.out.println("获取某个接点的出度" + graph.getOutDegree(4));
		System.out.println("获取两个顶点之间的权值" + graph.getWeight(0, 4));

		System.out.println("深度优先算法:");
		graph.depthFirstSearch();

		System.out.println("广度优先算法:");
		graph.broadFirstSearch();
	}

	// 获取 两个顶点之间的权值
	public int getWeight(int v1, int v2) {
		int w = matrix[v1][v2];
		return w == 0 ? 0 : (w == MAX_WEIGHT ? -1 : w);
	}

	// 获取入度 省略

	// 获取出度
	public int getOutDegree(int index) {

		int degree = 0;
		for (int i = 0; i < matrix[index].length; i++) {
			int w = matrix[index][i];
			if (0 != w && MAX_WEIGHT != w) {
				degree++;
			}
		}
		return degree;
	}

	// 图的遍历

	// 获取某个顶点的第一个邻接点
	public int getFirstNeighbor(int v) {

		for (int i = 0; i < VertexSize; i++) {
			if (matrix[v][i] > 0 && matrix[v][i] < MAX_WEIGHT) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 根据前一个邻接点的下标来取得下一个邻接点
	 * 
	 * @param v1
	 *            表示要找的顶点
	 * @param v2
	 *            表示该顶点相对于哪个邻接点去获取下一个邻接点
	 */
	public int getNextNeighbor(int pre, int next) {
		for (int i = next + 1; i < matrix.length; i++) {
			if (matrix[pre][i] > 0 && matrix[pre][i] < MAX_WEIGHT) {
				return i;
			}
		}
		return -1;
	}

	// 重点 图的深度优先算法

	/**
	 *
	 * 内部方法
	 * 
	 * @auther Cehae
	 */
	private void depthFirstSearch(int i) {

		isVisited[i] = true;

		int w = getFirstNeighbor(i);

		while (w != -1) {

			if (!isVisited[w]) {

				System.out.println("访问到了：" + w + "顶点");

				depthFirstSearch(w);
			}

			w = getNextNeighbor(i, w);
		}
	}

	/**
	 * 
	 * 对外公开的深度优先算法
	 * 
	 * @auther Cehae
	 */
	private void depthFirstSearch() {

		isVisited = new boolean[VertexSize];

		for (int i = 0; i < VertexSize; i++) {
			if (!isVisited[i]) {
				System.out.println("访问到了：" + i + "顶点");
				depthFirstSearch(i);
			}
		}
	}

	// 深度优先 测试
	/*
	 * 访问到了：0顶点 访问到了：1顶点 访问到了：2顶点 访问到了：3顶点 访问到了：4顶点 访问到了：5顶点 访问到了：6顶点 访问到了：7顶点
	 * 访问到了：8顶点
	 * 
	 */

	/**
	 * 广度优先算法
	 * 
	 * @auther Cehae
	 */
	private void broadFirstSearch() {

		isVisited = new boolean[VertexSize];

		for (int i = 0; i < VertexSize; i++) {

			if (!isVisited[i]) {

				broadFirstSearch(i);
			}
		}
	}

	private void broadFirstSearch(int i) {

		int u, w;

		LinkedList<Integer> queue = new LinkedList<Integer>();

		System.out.println("访问到" + i + "节点");

		isVisited[i] = true;

		queue.add(i);

		while (!queue.isEmpty()) {

			u = ((Integer) queue.removeFirst()).intValue();

			// 取出节点u的第一个邻接点
			w = getFirstNeighbor(u);

			while (w != -1) {

				if (!isVisited[w]) {

					System.out.println("访问到了" + w + "节点");
					isVisited[w] = true;

					queue.add(w);
				}

				// 取出节点u的从w开始的下一个邻接点
				w = getNextNeighbor(u, w);
			}
		}
	}
}
