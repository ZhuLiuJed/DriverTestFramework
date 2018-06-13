package tool;

import java.util.List;
import java.util.Stack;

public class FindShortestPath {
//	标记
    boolean bLeafIsFound = false;  
    String path1;  
    public String findPath(ManyTreeNode root, Stack<String> path, TreeNode nodeToFind){  
          
        if (root == null) {  
              
            return null;  
              
        }  
          
        //将路径节点添加到栈中  
        path.push(root.getData().getNodeId());  
        //如果到达了子节点  
        if (!bLeafIsFound && root.getData().getNodeId() == nodeToFind.getNodeId()) {  
              
            //打印路径  
            path1 = printPath(path);  
            bLeafIsFound = true;  
            return path1;  
              
        } 
        for(ManyTreeNode n:  root.getChildList()){
//        	查找集合是否拥有该节点
             if(!nodeToFind.getNodeId().equals(n.getData().getNodeId())){ 
            	 findPath(n,path, nodeToFind);
             }
        }
        //如果没找到则弹栈  
        if (!bLeafIsFound) {  
              
            path.pop();  
              
        }  
          
        return path1 == null ? null : path1;  
          
    }  
      
    public String printPath(Stack<String> path){  
          
        int len = path.size();  
          
        String s = ""+ path.pop();  
          
        for (int i = 1; i < len; i++) {  
              
            if (path.peek() != null) {  
                  
                s += "->" + path.pop();  
  
            }  
                          
        }  
          
        System.out.println(s);  
          
        return s;  
          
          
    }  
      
    public ManyTreeNode lowestCommonAncestor(ManyTreeNode root, TreeNode p, TreeNode q){  
          
        //发现目标节点则通过返回值标记该子树发现了某个目标节点  
        if (root == null || root.getData() == p || root.getData() == q) {  
              
            return root;  
              
        }  
        for(ManyTreeNode n: root.getChildList()){
        	ManyTreeNode node =  lowestCommonAncestor(n, p, q);
        }
          return null;
    }  
      
    public void findPathOfTwoNode(ManyTreeNode root,TreeNode p, TreeNode q){  
          
        Stack<String> path1 = new Stack<String>();  
        Stack<String> path2 = new Stack<String>();  
          
        //寻找两个路径的交点，即最小公共祖先  
        ManyTreeNode lca = lowestCommonAncestor(root, p, q);  
        //得到p节点的路径  
        System.out.println("最小公共祖先节点" + lca.getData().getNodeId() + "和节点" + p.getNodeId() + "之间的路径");  
        String s1 = findPath(lca, path1, p);  
        bLeafIsFound = false;//全局变量复位   
        //得到q节点的路径  
        System.out.println("最小公共祖先节点" + lca.getData().getNodeId() + "和节点" + q.getNodeId() + "之间的路径");  
        String s2 = findPath(lca, path2, q);  
        bLeafIsFound = false;//全局变量复位   
          
        //合并两条路径去掉重复的最小公共祖先  
        String[] split = s2.split("->");  
        String s3 = s1 + "->" + split[0];  
          
        for (int i = 1; i < split.length; i++) {  
              
            if (split[i].equals(lca.getData().getNodeId())) {  
                  
                s3 +="->" + split[i];  
            }  
              
        }  
          
          
        System.out.println("归并后的路径为：" + s3);  
          
    }  
}
