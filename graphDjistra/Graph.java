package myOffer.graphDjistra;

public class Graph {
	// 描述图
	public int vertexSize;// 顶点数量
	public int[] vertexs;// 顶点数组
	public int[][] matrix;// 二维邻接矩阵

	public static final int MAX_WEIGHT = 9999;// 最大路径

	public Graph(int vertextSize) {
		this.vertexSize = vertextSize;

		vertexs = new int[vertextSize];
		for (int i = 0; i < vertextSize; i++) {
			vertexs[i] = i;
		}

		matrix = new int[vertextSize][vertextSize];
	}

	// 创建图
	public static final int VertexNum = 9;

	public void createGraph() {

		int[] a0 = new int[] { 0, 1, 5, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT };
		int[] a1 = new int[] { 1, 0, 3, 7, 5, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT };
		int[] a2 = new int[] { 5, 3, 0, MAX_WEIGHT, 1, 7, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT };
		int[] a3 = new int[] { MAX_WEIGHT, 7, MAX_WEIGHT, 0, 2, MAX_WEIGHT, 3, MAX_WEIGHT, MAX_WEIGHT };
		int[] a4 = new int[] { MAX_WEIGHT, 5, 1, 2, 0, 3, 6, 9, MAX_WEIGHT };
		int[] a5 = new int[] { MAX_WEIGHT, MAX_WEIGHT, 7, MAX_WEIGHT, 3, 0, MAX_WEIGHT, 5, MAX_WEIGHT };
		int[] a6 = new int[] { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 3, 6, MAX_WEIGHT, 0, 2, 7 };
		int[] a7 = new int[] { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 9, 5, 2, 0, 4 };
		int[] a8 = new int[] { MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, MAX_WEIGHT, 7, 4, 0 };

		int[][] data = { a0, a1, a2, a3, a4, a5, a6, a7, a8 };

		for (int i = 0; i < data.length; i++) {
			matrix[i] = data[i];
		}
	}
}
