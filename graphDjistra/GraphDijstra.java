package myOffer.graphDjistra;

//求一个图的最短路径 迪杰斯特拉算法
public class GraphDijstra {

	// 求一个图的最短路径
	public static void getShortestPathDijstr(Graph g) {

		/*
		 * 此数组用来存放最短路径和。 例如 {0,1,2,3,4,5,6,7} 比如 shortTablePath[0] =
		 * 0代表V0到V0的最短路径为0 shortTablePath[3] = 3代表V0到V3的最短路径为3
		 */
		int[] shortTablePath = new int[g.vertexSize];

		// 标记是否已经获取到最短路径
		boolean[] isgetPath = new boolean[g.vertexSize];

		// 暂时先将第一行数据放入最短路径数组。
		for (int i = 0; i < g.vertexSize; i++) {
			shortTablePath[i] = g.matrix[0][i];
		}

		// V0到V0的最短路径为0
		shortTablePath[0] = 0;
		// V0到V0的最短路径已经得到
		isgetPath[0] = true;

		int min = 0;// 记录最短路径
		int k = 0;// 记录最短路径的下标
		// 开始遍历
		for (int i = 1; i < g.vertexSize; i++) {

			min = Graph.MAX_WEIGHT;

			// 找出当前shortTablePath中的最短路径和下标
			for (int j = 0; j < g.vertexSize; j++) {
				if (!isgetPath[j] && (shortTablePath[j] < min)) {
					k = j;
					min = shortTablePath[j];
				}
			}

			// 此处已经找到最短路径以及其下标，将此下标标记为true
			isgetPath[k] = true;

			// 遍历找出V0通过Vk(当前最短路径的下标顶点)到Vx的最短路径。
			for (int x = 0; x < g.vertexSize; x++) {

				if (!isgetPath[x] && (min + g.matrix[k][x]) < shortTablePath[x]) {

					// 如果k为1，x为2，shortTablePath[2]存放V0通过V1到达V2的最短路径
					shortTablePath[x] = min + g.matrix[k][x];
				}
			}

			// //调试 输出
			// for (int t = 0; t < shortTablePath.length; t++) {
			//
			// System.out.println("V0到V" + t + "的最短路为：" + shortTablePath[t]);
			// }
			//
			// return;
		}

		// 输出
		for (int t = 0; t < shortTablePath.length; t++) {

			System.out.println("V0到V" + t + "的最短路为：" + shortTablePath[t]);
		}
	}

	public static void main(String[] args) {

		// 创建图
		Graph graph = new Graph(Graph.VertexNum);
		graph.createGraph();

		// 调用算法
		getShortestPathDijstr(graph);
	}
}
