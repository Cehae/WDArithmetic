package myOffer.graphMatrix;

//求最小生成树 Kruskal 克鲁斯卡尔算法 
public class GraphKruskal {

	class Edge {// 边对象

		private int begin;// 开始
		private int end;// 结束
		private int weight;// 权值

		public Edge(int begin, int end, int weight) {
			super();
			this.begin = begin;
			this.end = end;
			this.weight = weight;
		}

		public int getBegin() {
			return begin;
		}

		public void setBegin(int begin) {
			this.begin = begin;
		}

		public int getEnd() {
			return end;
		}

		public void setEnd(int end) {
			this.end = end;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}
	}

	private Edge[] edges;// 边的数组
	private int edgeSize;// 边的数量

	public GraphKruskal(int edgeSize) {
		super();
		this.edgeSize = edgeSize;
		this.edges = new Edge[edgeSize];
	}

	public void createEdgeArray() {

		// 起顶点，终顶点，权值
		edges[0] = new Edge(4, 7, 7);
		edges[1] = new Edge(2, 8, 8);

		edges[2] = new Edge(0, 1, 10);
		edges[3] = new Edge(0, 5, 11);

		edges[4] = new Edge(1, 8, 12);
		edges[5] = new Edge(3, 7, 16);

		edges[6] = new Edge(1, 6, 16);
		edges[7] = new Edge(5, 6, 17);

		edges[8] = new Edge(1, 2, 18);
		edges[9] = new Edge(6, 7, 19);

		edges[10] = new Edge(3, 4, 20);
		edges[11] = new Edge(3, 8, 21);

		edges[12] = new Edge(2, 3, 22);
		edges[13] = new Edge(3, 6, 24);

		edges[14] = new Edge(4, 5, 26);
	}

	public static void main(String[] args) {

		GraphKruskal g = new GraphKruskal(15);

		g.createEdgeArray();

		g.miniSpanTreeKruskal();
	}

	public void miniSpanTreeKruskal() {

		int m, n, sum = 0;

		// 这个数组很关键，是克鲁斯卡尔算法的关键所在，次数组中 a[4]=7,代表一条起点为4终点为7的边
		// 神奇数组下标为起点，值为终点
		int[] parent = new int[edgeSize];
		for (int i = 0; i < edgeSize; i++) {
			parent[i] = 0;
		}

		for (int i = 0; i < edgeSize; i++) {
			n = find(parent, edges[i].begin);
			m = find(parent, edges[i].end);

			if (n != m) {
				parent[n] = m;
				System.out.println("起始顶点：" + edges[i].begin + "---结束顶点" + edges[i].end + "--权值：" + edges[i].weight);
				sum += edges[i].weight;
			}
		}

		System.out.println("最小权值为：" + sum);
	}

	/**
	 *
	 * 对神奇数组进行查询获取非回环的值
	 * 
	 * parent 神奇数组
	 * 
	 * f 为顶点
	 * 
	 * 核心方法
	 * 
	 * @auther Cehae
	 */
	private int find(int[] parent, int f) {
		while (parent[f] > 0) {
			f = parent[f];// 为什么会跳出循环，注意改变了下标
		}
		return f;
	}
}
