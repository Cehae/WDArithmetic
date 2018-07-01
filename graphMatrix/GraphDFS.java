package com.cehae.graphMatrix;
/**
 * 深度优先搜索
 * @author Cehae
 */
public class GraphDFS {
    
    public void DFSraverse(graph gh){
        
        //初始化visited数组，用  false 以表示该顶点是否已经访问过
        boolean[] visited = new boolean[gh.numVexs];
        for(int i = 0; i < gh.numVexs; i++){
            visited[i] = false;
        }
        System.out.println();
        for(int i = 0; i < gh.numVexs; i++){
            if(!visited[i])
                DFS(gh,i,visited);
        }
    }
 
    private void DFS(graph gh, int k,boolean[] vi) {
        // TODO Auto-generated method stub
    
        vi[k] = true;
        System.out.println("正访问的结点是 ： " + gh.vexs[k]);
        
        for(int i = 0; i < gh.numVexs; i++){
            if(gh.arc[k][i] == 1 && !vi[i])
                DFS(gh,i,vi);
        }
    }   
}