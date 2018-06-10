package myOffer.graphMatrix;

import java.util.LinkedList;

//深度优先算法和广度优先算法
public class Graph2 {

	private int VertexSize;// 顶点数量

	private int[] vertexs;// 顶点数组

	private int[][] matrix;

	private boolean[] isVisited;

	private static final int MAX_WEIGHT = 1000;

	public Graph2(int vertexsize) {
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

		Graph2 graph = new Graph2(9);

		int[] a1 = new int[] { 0, 10, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 11, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT };
		int[] a2 = new int[] { 10, 0, 18, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 16, MAX_WEIGHT, 12 };
		int[] a3 = new int[] { MAX_WEIGHT, MAX_WEIGHT, 0, 22, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 8 };
		int[] a4 = new int[] { MAX_WEIGHT, MAX_WEIGHT, 22, 0, 20, MAX_WEIGHT, MAX_WEIGHT, 16, 21 };
		int[] a5 = new int[] { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 20, 0, 26, MAX_WEIGHT, 7, MAX_WEIGHT };
		int[] a6 = new int[] { 11, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 26, 0, 17, MAX_WEIGHT, MAX_WEIGHT };
		int[] a7 = new int[] { MAX_WEIGHT, 16, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 17, 0, 19, MAX_WEIGHT };
		int[] a8 = new int[] { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 16, 7, MAX_WEIGHT, 19, 0, MAX_WEIGHT };
		int[] a9 = new int[] { MAX_WEIGHT, 12, 8, 21, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 0 };

		graph.matrix[0] = a1;
		graph.matrix[1] = a2;
		graph.matrix[2] = a3;
		graph.matrix[3] = a4;
		graph.matrix[4] = a5;
		graph.matrix[5] = a6;
		graph.matrix[6] = a7;
		graph.matrix[7] = a8;
		graph.matrix[8] = a9;

		System.out.println("深度优先算法:");
		graph.depthFirstSearch();

		System.out.println("广度优先算法:");
		graph.broadFirstSearch();
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
