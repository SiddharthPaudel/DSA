import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) {
        val = x;
    }
}

public class Question4B{
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        Stack<Integer> s1 = new Stack<>(); // predecessors
        Stack<Integer> s2 = new Stack<>(); // successors

        inorder(root, target, false, s1);
        inorder(root, target, true, s2);

        while (k --> 0) {
            if (s1.isEmpty()) {
                res.add(s2.pop());
            } else if (s2.isEmpty()) {
                res.add(s1.pop());
            } else if (Math.abs(s1.peek() - target) < Math.abs(s2.peek() - target)) {
                res.add(s1.pop());
            } else {
                res.add(s2.pop());
            }
        }

        // Reverse the list to get the correct order
        Collections.reverse(res);

        return res;
    }

    // Inorder traversal
    void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack) {
        if (root == null) {
            return;
        }

        inorder(reverse ? root.right : root.left, target, reverse, stack);

        // Early terminate: no need to traverse the whole tree
        if ((reverse && root.val <= target) || (!reverse && root.val > target)) {
            return;
        }

        // Track the value of the current node
        stack.push(root.val);

        inorder(reverse ? root.left : root.right, target, reverse, stack);
    }

    public static void main(String[] args) {
        Question4B cv = new Question4B();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(5);

        double target = 3.8;
        int x = 2;
        List<Integer> result = cv.closestKValues(root, target, x);
        System.out.println("Closest elements to " + target + ": " + result);
    }
}
