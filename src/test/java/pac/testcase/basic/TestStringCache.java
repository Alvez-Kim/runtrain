package pac.testcase.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class TestStringCache {
	public static void main(String[] args) {
		String _ = "_";
		final String str_ = "str" + _;
		// final String str_ = "str_";
		String a = str_ + "a";

		String b = a;
		String c = "str_a";

		System.out.println("a==c:::" + (a == c));
		int[] r = { 2, 2, 1 };
		System.out.println(candy(r));
		char[][] cc = {
				{'A','B','C','E'},
				{'S','F','C','S'},
				{'A','D','E','E'}
		};
//		System.out.println(exist(cc,"ASd"));
		
		ArrayList<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println(list.size());
		list.subList(0, 1).clear();
		System.out.println(list.size());
	}
	

	public static boolean exist(char[][] b, String word) {
		char[] c = word.toCharArray();
		int index = 0;
		int i = 0;
		int j = 0;
		loop:
		for (; i < b.length; index++) {
			System.out.println(i);
			for (; j < b[i].length; j++) {
				if(c[index]==b[i][j]){
					System.out.println(c[index]+""+i+""+j+"::"+index);
					if(index==c.length-1)return true;
					
					if(i>0&&b[i-1][j]==c[index+1]){
						i-=1;
					}else if(i<b.length-1&&b[i+1][j]==c[index+1]){
						i+=1;
					}else if(j>0&&b[i][j-1]==c[index+1]){
						j-=1;
					}else if(j<b[i].length-1&&b[i][j+1]==c[index+1]){
						j+=1;
					}
					continue loop;
				}
				else i++;
			}
		}
        return false;
    }
	

	public static int candy(int[] r) {
		int total = 0;
		int[] c = new int[r.length];
		for (int i = 0; i < c.length; i++) {
			c[i] = 1;
		}
		for (int i = 1; i < r.length; i++) {
			if (r[i] < r[i - 1]) {
				c[i] = c[i - 1] - 1;
			} else if (r[i] > r[i - 1]) {
				c[i] = c[i - 1] + 1;
			} else {
			}
		}
		int min = c[0];
		for (int i = 1; i < c.length; i++) {
			if (min > c[i])
				min = c[i];
		}
		for (int i = 0; i < r.length; i++) {
			total += c[i] += (1 - min);
		}
		return total;
	}

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	class Solution {
		public boolean hasPathSum(TreeNode root, int sum) {
			int s = root.val;
			return false;
		}
		
		public int calc(TreeNode node,int sum){
			int s = node.val;
			if(s==sum)return s;
			if(node.left!=null){
				s+=node.left.val;
				calc(node.right, s);
			}
			if(node.right!=null){
				s+=node.right.val;
			}
			return s;
		}
		
		
	}
}
