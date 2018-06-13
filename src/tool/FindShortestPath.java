package tool;

import java.util.List;
import java.util.Stack;

public class FindShortestPath {
//	���
    boolean bLeafIsFound = false;  
    String path1;  
    public String findPath(ManyTreeNode root, Stack<String> path, TreeNode nodeToFind){  
          
        if (root == null) {  
              
            return null;  
              
        }  
          
        //��·���ڵ���ӵ�ջ��  
        path.push(root.getData().getNodeId());  
        //����������ӽڵ�  
        if (!bLeafIsFound && root.getData().getNodeId() == nodeToFind.getNodeId()) {  
              
            //��ӡ·��  
            path1 = printPath(path);  
            bLeafIsFound = true;  
            return path1;  
              
        } 
        for(ManyTreeNode n:  root.getChildList()){
//        	���Ҽ����Ƿ�ӵ�иýڵ�
             if(!nodeToFind.getNodeId().equals(n.getData().getNodeId())){ 
            	 findPath(n,path, nodeToFind);
             }
        }
//        //��ѯ������  
//        if (!bLeafIsFound && root.left != null) {  
//              
//            findPath(root.left,path, nodeToFind);  
//              
//        }  
//          
//        //��ѯ������  
//        if (!bLeafIsFound && root.right != null) {  
//              
//            findPath(root.right, path, nodeToFind);  
//              
//        }  
//          
        //���û�ҵ���ջ  
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
      
    public TreeNode lowestCommonAncestor(ManyTreeNode root, TreeNode p, TreeNode q){  
          
        //����Ŀ��ڵ���ͨ������ֵ��Ǹ�����������ĳ��Ŀ��ڵ�  
        if (root == null || root.getData() == p || root.getData() == q) {  
              
            return root.getData();  
              
        }  
        for(ManyTreeNode n: root.getChildList()){
        	TreeNode node =  lowestCommonAncestor(n, p, q);
        }
          return null;
    }  
      
    public void findPathOfTwoNode(TreeNode root,TreeNode p, TreeNode q){  
          
        Stack<Integer> path1 = new Stack<Integer>();  
        Stack<Integer> path2 = new Stack<Integer>();  
          
        //Ѱ������·���Ľ��㣬����С��������  
        TreeNode lca = lowestCommonAncestor(root, p, q);  
        //�õ�p�ڵ��·��  
        System.out.println("��С�������Ƚڵ�" + lca.val + "�ͽڵ�" + p.val + "֮���·��");  
        String s1 = findPath(lca, path1, p);  
        bLeafIsFound = false;//ȫ�ֱ�����λ   
        //�õ�q�ڵ��·��  
        System.out.println("��С�������Ƚڵ�" + lca.val + "�ͽڵ�" + q.val + "֮���·��");  
        String s2 = findPath(lca, path2, q);  
        bLeafIsFound = false;//ȫ�ֱ�����λ   
          
        //�ϲ�����·��ȥ���ظ�����С��������  
        String[] split = s2.split("->");  
        String s3 = s1 + "->" + split[0];  
          
        for (int i = 1; i < split.length; i++) {  
              
            if (Integer.parseInt(split[i]) != lca.val) {  
                  
                s3 +="->" + split[i];  
            }  
              
        }  
          
          
        System.out.println("�鲢���·��Ϊ��" + s3);  
          
    }  
}
