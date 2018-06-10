package myOffer.graphMatrix;

import java.util.LinkedList;

//求最小生成树 Prim 普里姆算法 
public class GraphPrim {

	private int VertexSize;// 顶点数量

	private int[] vertexs;// 顶点数组

	private int[][] matrix;

	private boolean[] isVisited;

	private static final int MAX_WEIGHT = 1000;

	public GraphPrim(int vertexsize) {
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

		GraphPrim graph = new GraphPrim(9);

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

		// System.out.println("深度优先算法:");
		// graph.depthFirstSearch();
		//
		// System.out.println("广度优先算法:");
		// graph.broadFirstSearch();

		graph.prim();
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

	// 详细解释和调试
	// prim 普里姆算法
	public void prim() {

		int[] lowcost = new int[VertexSize]; // 最小权值顶点权值的数组，为0表示已经获取最小权值
		int[] adjvex = new int[VertexSize]; // 最小权值顶点的数组

		// 最小权值的和
		int sum = 0;

		// 先将第一行的数据（除去第一个）存放到权值数组中
		for (int i = 1; i < VertexSize; i++) {
			lowcost[i] = matrix[0][i];
		}

		for (int i = 1; i < VertexSize; i++) {

			System.out.println("第" + i + "轮合并前");
			System.out.println("最小顶点数组：");
			for (int j : adjvex) {
				System.out.println(j);
			}
			System.out.println("最小权值数组：");
			for (int j : lowcost) {
				System.out.println(j);
			}

			int min = MAX_WEIGHT;// 最小权值
			int minId = 0;// 最小权值下标

			// 求出本行数组最小的权值和下标
			for (int j = 1; j < VertexSize; j++) {
				if (lowcost[j] < min && lowcost[j] > 0) {
					min = lowcost[j];
					minId = j;
				}
			}

			// 已经找到本行最小的权值和下标
			System.out.println("顶点：" + minId + " 权值：" + min);
			lowcost[minId] = 0;// 将最小权值下标里的权值设置为0，相当于一个标记，为0 代表已经找到最小权值

			// 然后遍历 最小下标所在行对应的数组1 和 最小权值数组2，两个数组进行比较，
			// 将对应位置最小的权值放入数组2，同时将最小权值下标放入最小权值数组
			int[] minIdArr = matrix[minId];

			System.out.println("找到要合并的数组:");
			for (int j : minIdArr) {
				System.out.println(j);
			}

			for (int j = 1; j < VertexSize; j++) {
				if (lowcost[j] != 0 && minIdArr[j] < lowcost[j]) {
					lowcost[j] = minIdArr[j];
					adjvex[j] = minId;
				}
			}
			sum += min;// 累加权值

			System.out.println("第" + i + "合并后");
			System.out.println("最小顶点数组：");
			for (int j : adjvex) {
				System.out.println(j);
			}
			System.out.println("最小权值数组：");
			for (int j : lowcost) {
				System.out.println(j);
			}

			System.out.println();
			System.out.println();
			System.out.println();
		}

		System.out.println("最小生成树权值和为:" + sum);

		System.out.println("最后合并后");
		System.out.println("最小顶点数组：");
		for (int j : adjvex) {
			System.out.println(j);
		}
		System.out.println("最小权值数组：");
		for (int j : lowcost) {
			System.out.println(j);
		}
	}

	// // prim 普里姆算法
	// public void prim() {
	//
	// int[] lowcost = new int[VertexSize]; // 最小权值顶点权值的数组，为0表示已经获取最小权值
	// int[] adjvex = new int[VertexSize]; // 最小权值顶点的数组
	//
	// int sum = 0;// 最小权值的和
	//
	// // 先将第一行的数据存放到最小权值数组中
	// for (int i = 1; i < VertexSize; i++) {
	// lowcost[i] = matrix[0][i];
	// }
	//
	// for (int i = 1; i < VertexSize; i++) {
	//
	// int min = MAX_WEIGHT;// 最小权值
	// int minId = 0;// 最小权值下标
	//
	// // 求出本行数组最小的权值和下标
	// for (int j = 1; j < VertexSize; j++) {
	// if (lowcost[j] < min && lowcost[j] > 0) {
	// min = lowcost[j];
	// minId = j;
	// }
	// }
	//
	// // 到此已经找到本行最小的权值和下标
	// lowcost[minId] = 0;// 将最小权值下标里的权值设置为0，相当于一个标记，为0 代表已经找到最小权值，方便比较
	//
	// // 然后遍历 最小下标所在行对应的数组1 和 最小权值数组2，两个数组进行比较，
	// // 将对应位置最小的权值放入数组2，同时将最小权值下标放入最小权值顶点数组
	// int[] minIdArr = matrix[minId];
	//
	// // 比较合并数组
	// for (int j = 1; j < VertexSize; j++) {
	// if (lowcost[j] != 0 && minIdArr[j] < lowcost[j]) {
	// lowcost[j] = minIdArr[j];
	// adjvex[j] = minId;
	// }
	// }
	// sum += min;// 累加权值
	// }
	// System.out.println("最小生成树权值和为:" + sum);
	// }

	// 克鲁斯卡尔算法

}
