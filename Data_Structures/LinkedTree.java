/*
 * LinkedTree.java
 *
 * Computer Science E-22/S-22
 *
 * Modifications and additions by:
 *     name: Xavier Torres
 *     username: xaviert
 */

import java.util.*;

/*
 * LinkedTree - a class that represents a binary tree containing data
 * items with integer keys.  If the nodes are inserted using the
 * insert method, the result will be a binary search tree.
 */
public class LinkedTree {
    // An inner class for the nodes in the tree
    private class Node {
        private int key;         // the key field
        private LLList data;     // list of data values for this key
        private Node left;       // reference to the left child/subtree
        private Node right;      // reference to the right child/subtree
        private Node parent;     // reference to the parent (added for Problem 7)
        
        private Node(int key, Object data){
            this.key = key;
            this.data = new LLList();
            this.data.addItem(data, 0);
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }
    
    // the root of the tree as a whole
    private Node root;
    
    public LinkedTree() {
        root = null;
    }
    
    /*
     * Prints the keys of the tree in the order given by a preorder
     * traversal.  Invokes the recursive preorderPrintTree method to
     * do the work.
     */
    public void preorderPrint() {
        if (root != null) {
            preorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a preorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void preorderPrintTree(Node root) {
        System.out.print(root.key + " ");
        if (root.left != null) {
            preorderPrintTree(root.left);
        }
        if (root.right != null) {
            preorderPrintTree(root.right);
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a postorder
     * traversal.  Invokes the recursive postorderPrintTree method to
     * do the work.
     */
    public void postorderPrint() {
        if (root != null) {
            postorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs a postorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void postorderPrintTree(Node root) {
        if (root.left != null) {
            postorderPrintTree(root.left);
        }
        if (root.right != null) {
            postorderPrintTree(root.right);
        }
        System.out.print(root.key + " ");
    }
    
    /*
     * Prints the keys of the tree in the order given by an inorder
     * traversal.  Invokes the recursive inorderPrintTree method to do
     * the work.
     */
    public void inorderPrint() {
        if (root != null) {
            inorderPrintTree(root);      
        }
        System.out.println();
    }
    
    /*
     * Recursively performs an inorder traversal of the tree/subtree
     * whose root is specified, printing the keys of the visited nodes.
     * Note that the parameter is *not* necessarily the root of the 
     * entire tree. 
     */
    private static void inorderPrintTree(Node root) {
        if (root.left != null) {
            inorderPrintTree(root.left);
        }
        System.out.print(root.key + " ");
        if (root.right != null) {
            inorderPrintTree(root.right);
        }
    }
    
    /* 
     * Inner class for temporarily associating a node's depth
     * with the node, so that levelOrderPrint can print the levels
     * of the tree on separate lines.
     */
    private class NodePlusDepth {
        private Node node;
        private int depth;
        
        private NodePlusDepth(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
    
    /*
     * Prints the keys of the tree in the order given by a
     * level-order traversal.
     */
    public void levelOrderPrint() {
        LLQueue<NodePlusDepth> q = new LLQueue<NodePlusDepth>();
        
        // Insert the root into the queue if the root is not null.
        if (root != null) {
            q.insert(new NodePlusDepth(root, 0));
        }
        
        // We continue until the queue is empty.  At each step,
        // we remove an element from the queue, print its value,
        // and insert its children (if any) into the queue.
        // We also keep track of the current level, and add a newline
        // whenever we advance to a new level.
        int level = 0;
        while (!q.isEmpty()) {
            NodePlusDepth item = q.remove();
            
            if (item.depth > level) {
                System.out.println();
                level++;
            }
            System.out.print(item.node.key + " ");
            
            if (item.node.left != null) {
                q.insert(new NodePlusDepth(item.node.left, item.depth + 1));
            }
            if (item.node.right != null) {
                q.insert(new NodePlusDepth(item.node.right, item.depth + 1));
            }
        }
        System.out.println();
    }
/**
 * sumEvens() - the wrapper method which calls 'sumEvensInTree()', which
 * makes the initial call to it and returns the called method's value. 
 */
    public int sumEvens() {

        if (root != null) {

            return sumEvensInTree(root);  

        } else {

            return 0;
        }
    }
/**
 * sumEvensInTree() - uses recursion to find and return the sum of the 
 * even values in the binary search tree utilizing approach similar to 
 * postorder during search. 
 * @param n
 * @return
 */
    private static int sumEvensInTree(Node n) {

        int evenValue = (n.key % 2 == 0) ? n.key : 0;       //ternery operator stores value if key is even, if not then sets to 0.
        int leftSum = 0;
        int rightSum = 0;
        if (n == null) {                        //base case if null returns 0.
            return 0;
        }
        if (n.left != null) {                   //recursive calls for left and right of BST.
            leftSum = sumEvensInTree(n.left);
        }
        if (n.right != null) {
            rightSum = sumEvensInTree(n.right);
        }
        return evenValue + leftSum + rightSum;      //sums and returns the total from node's even value if any.
    }

/*
 * depthIter() - method uses iteration to determine and return
 * depth of the node with int key passed, returning -1 if target
 * key is not found. 
 */
    public int depthIter(int key) {
        
        Node trav = root;
        int depth = 0;

        while (trav != null) {

            if (key == trav.key) {          //returns depth once key is found.

                return depth;

            } else if (key < trav.key) {    //traverses left or right of the nodes.

                trav = trav.left;

            } else {

                trav = trav.right;
            }
            depth++;
        }
        return -1;                      //returns -1 should key not be found.
    }
    
    /*
     * Searches for the specified key in the tree.  If it finds it, it
     * returns the list of data items associated with the key.
     * Invokes the recursive searchTree method to perform the actual
     * search.
     */
    public LLList search(int key) {
        Node n = searchTree(root, key);
        if (n == null) {
            return null;
        } else {
            return n.data;
        }
    }
    
    /*
     * Recursively searches for the specified key in the tree/subtree
     * whose root is specified. Note that the parameter is *not*
     * necessarily the root of the entire tree.
     */
    private static Node searchTree(Node root, int key) {
        if (root == null) {
            return null;
        } else if (key == root.key) {
            return root;
        } else if (key < root.key) {
            return searchTree(root.left, key);
        } else {
            return searchTree(root.right, key);
        }
    }
    
    /*
     * Inserts the specified (key, data) pair in the tree so that the
     * tree remains a binary search tree.
     */
    public void insert(int key, Object data) {
        // Find the parent of the new node.
        Node parent = null;
        Node trav = root;
        while (trav != null) {
            if (trav.key == key) {
                trav.data.addItem(data, 0);
                return;
            }
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Insert the new node.
        Node newNode = new Node(key, data);
        if (parent == null) {    // the tree was empty
            root = newNode;
        } else if (key < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        /**** added for PS 4, Problem 7 ****/
        newNode.parent = parent;
    }
    
    /*
     * FOR TESTING: Processes the integer keys in the specified array
     * from left to right, adding a node for each of them to the tree.
     * The data associated with each key is a string based on the key.
     */
    public void insertKeys(int[] keys) {
        for (int i = 0; i < keys.length; i++) {
            insert(keys[i], "data for key " + keys[i]);
        }
    }
    
    /*
     * Deletes the node containing the (key, data) pair with the
     * specified key from the tree and return the associated data item.
     */
    public LLList delete(int key) {
        // Find the node to be deleted and its parent.
        Node parent = null;
        Node trav = root;
        while (trav != null && trav.key != key) {
            parent = trav;
            if (key < trav.key) {
                trav = trav.left;
            } else {
                trav = trav.right;
            }
        }
        
        // Delete the node (if any) and return the removed data item.
        if (trav == null) {   // no such key    
            return null;
        } else {
            LLList removedData = trav.data;
            deleteNode(trav, parent);
            return removedData;
        }
    }
    
    /*
     * Deletes the node specified by the parameter toDelete.  parent
     * specifies the parent of the node to be deleted. 
     */
    private void deleteNode(Node toDelete, Node parent) {
        if (toDelete.left != null && toDelete.right != null) {
            // Case 3: toDelete has two children.
            // Find a replacement for the item we're deleting -- as well as 
            // the replacement's parent.
            // We use the smallest item in toDelete's right subtree as
            // the replacement.
            Node replaceParent = toDelete;
            Node replace = toDelete.right;
            while (replace.left != null) {
                replaceParent = replace;
                replace = replace.left;
            }
            
            // Replace toDelete's key and data with those of the 
            // replacement item.
            toDelete.key = replace.key;
            toDelete.data = replace.data;
            
            // Recursively delete the replacement item's old node.
            // It has at most one child, so we don't have to
            // worry about infinite recursion.
            deleteNode(replace, replaceParent);
        } else {
            // Cases 1 and 2: toDelete has 0 or 1 child
            Node toDeleteChild;
            if (toDelete.left != null) {
                toDeleteChild = toDelete.left;
            } else {
                toDeleteChild = toDelete.right;  // null if no children
            }

            /**** added for PS 4, Problem 7 ****/
            if (toDeleteChild != null) {
                toDeleteChild.parent = parent;
            }	    
            
            if (toDelete == root) {
                root = toDeleteChild;
            } else if (toDelete.key < parent.key) {
                parent.left = toDeleteChild;
            } else {
                parent.right = toDeleteChild;
            }
        }
    }
/**
 * deleteMax() - performs iteration to search for the key with 
 * max value and deletes it from the BST, returning the deleted 
 * key. Should the BST be empty, a -1 is returned. 
 * @return
 */
    private int deleteMax() {

        Node trav = root;
        Node parent = null;
        if (trav == null ) {
            
            return -1;
        }
        while (trav.right != null) {        //only traverse to the right since search is for maxValue
            parent = trav;
            trav = trav.right;
        }
        int maxVal = trav.key;

        Node toDeleteChild = trav.left;         //to be used for reconnect of parent to child
        if (toDeleteChild != null) {

            toDeleteChild.parent = parent;
        }
        if (parent == root) {                   

            root = toDeleteChild;
            
        } else {                                //replace occurs for new parent/child

            parent.right = toDeleteChild;
        }
        return maxVal;
    }
/**
 * returns an inorder Iterator for the BST
 * @return
 */
    public LinkedTreeIterator inorderIterator() {
        return new InorderIterator();
    }

/**
 * inner class for InorderIterator implements the LinkedTreeIterator
 * and its methods to include 'hasNext()' and 'next()' to perform a 
 * complete inorder traversal of the BST.
 */
    private class InorderIterator implements LinkedTreeIterator {

        private Node nextNode;

        private InorderIterator() {
            
            nextNode = root;
            while (nextNode.left != null) {

                nextNode = nextNode.left;
            }
        }

        public boolean hasNext() {

            return nextNode != null;
        }

        public int next() {

            if (nextNode == null) {

                throw new NoSuchElementException();
            }

            int key = nextNode.key;

            if (nextNode.right != null) {           //case for node with right child inorder incrementally increasing order. 

                nextNode = nextNode.right;

                while (nextNode.left != null) {     //if right child exist, nextNode is left in that right subtree.

                    nextNode = nextNode.left;
                }

            } else {                                //case for node with no right child and needs to move up the BST.

                Node parent = nextNode.parent;
                Node child = nextNode;

                while(parent != null && parent.right == child) {

                    child = parent;
                    parent = parent.parent;
                }
                nextNode = parent;
            }
            return key;
        }
    }
    
    /* Returns a preorder iterator for this tree. */
    public LinkedTreeIterator preorderIterator() {
        return new PreorderIterator();
    }
    
    /* 
     * inner class for a preorder iterator 
     */
    private class PreorderIterator implements LinkedTreeIterator {
        private Node nextNode;
        
        private PreorderIterator() {
            // The traversal starts with the root node.
            nextNode = root;
        }
        
        public boolean hasNext() {
            return (nextNode != null);
        }
        
        public int next() {
            if (nextNode == null) {
                throw new NoSuchElementException();
            }
            
            // Store a copy of the key to be returned.
            int key = nextNode.key;
            
            // Advance nextNode.
            if (nextNode.left != null) {
                nextNode = nextNode.left;
            } else if (nextNode.right != null) {
                nextNode = nextNode.right;
            } else {
                // We've just visited a leaf node.
                // Go back up the tree until we find a node
                // with a right child that we haven't seen yet.
                Node parent = nextNode.parent;
                Node child = nextNode;
                while (parent != null &&
                       (parent.right == child || parent.right == null)) {
                    child = parent;
                    parent = parent.parent;
                }
                
                if (parent == null) {
                    nextNode = null;  // the traversal is complete
                } else {
                    nextNode = parent.right;
                }
            }
            
            return key;
        }
    }  
    
    /*
     * "wrapper method" for the recursive anySmallerInTree() method
     * from PS 4, Problem 4
     */
    public boolean anySmaller(int v) {
        // make the first call to the recursive method,
        // passing in the root of the tree as a whole
        return anySmallerInTree(root, v);                                      
    }
    
    /*
     * original version of the recursive anySmallerInTree() method  
     * from PS 4, Problem 4. Rewritten to be more efficient. 
     */
    private static boolean anySmallerInTree(Node root, int v) {
        if (root == null) {
            return false;
        }
        if (root.key < v) {             //base case when node is smaller than item.

            return true;
        }
        boolean revised = anySmallerInTree(root.left, v);       //recursive call to check left subtree only as
        return revised;                                         // right subtree is implicitly greater in BST.
    }

/**
 * wrapper method isomorphicTo() - ensures the param passed is not null and
 * calls the isomporphic() method to perform the isomorphic comparison. 
 */
    public boolean isomorphicTo(LinkedTree other) {

        if (other == null) {

            return false;
        }

        return isomorphic(this.root, other.root);
    }

/**
 * isomorphic() - performs comparison using recursive approach for the two
 * nodes to check if isomorphic, returning true if so and false if not isomorphic.
 */
    private static boolean isomorphic(Node root1, Node root2) {
        
        if (root1 == null && root2 == null) {               //base case ensures we are done as two trees
                                                            // are considered isomorphic.
            return true;

        } 
        if (root1 == null || root2 == null) {               // base case determines right away they are not isomorphic
                                                            // should one be null and other not, returning false.
            return false;
        }
        boolean compare = isomorphic(root1.left, root2.left) && isomorphic(root1.right, root2.right);      //compares both.
        
        return compare;
    }
    
    public static void main(String[] args) {
        System.out.println("--- Test 1 anySmaller()/anySmallerInTree() from Problem 4 ---");
        System.out.println();
        System.out.println("(0) Testing on tree from Problem 3, smaller than 15");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
            tree.insertKeys(keys);
            
            boolean results = tree.anySmaller(15);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(true);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println("--- Test 2 anySmaller()/anySmallerInTree() from Problem 4 ---");
        System.out.println();
        System.out.println("(0) Testing on tree from Problem 3, smaller than 47");
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
            tree.insertKeys(keys);
            
            boolean results = tree.anySmaller(47);
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(true);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == true);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();
        System.out.println();    // include a blank line between tests
        System.out.println("--- Testing 1 smallEvens()/smallEvensInTree() from Problem 6 ---");
        System.out.println();
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {4, 1, 3, 6, 5, 2};
            tree.insertKeys(keys);
            System.out.println("tree with keys from 1 to 6: " + tree.sumEvens());

            int results = tree.sumEvens();

            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(4 + 6 + 2);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 12);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();
        System.out.println("--- Testing 2 smallEvens()/smallEvensInTree() from Problem 6 ---");
        System.out.println();
        try {
            LinkedTree tree = new LinkedTree();
            int[] keys = {1,3,5,7};
            tree.insertKeys(keys);
            int results = tree.sumEvens();
            System.out.println("tree with no even keys: " + results);

            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(0);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == 0);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();
        System.out.println("--- Testing 1 depthIter() from Problem 6 ---");
        System.out.println();
        try {
            LinkedTree tree = new LinkedTree();
            System.out.println("empty tree: " + tree.depthIter(13));
            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
            tree.insertKeys(keys);
            System.out.println();
            System.out.println("depth of 37: " + tree.depthIter(37));

            System.out.println("actual results:");
            System.out.println("depth of 37: " + tree.depthIter(37));
            System.out.println("expected results:");
            System.out.println(0);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(tree.depthIter(37) == 0);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();
        System.out.println("--- Testing 2 depthIter() from Problem 6 ---");
        System.out.println();
        try {
            LinkedTree tree = new LinkedTree();
            System.out.println("empty tree: " + tree.depthIter(13));
            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
            tree.insertKeys(keys);
            System.out.println();

            System.out.println("actual results:");
            System.out.println("depth of 50: " + tree.depthIter(50));
            System.out.println("expected results:");
            System.out.println(-1);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(tree.depthIter(50) == -1);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();
        System.out.println("--- Testing 1 deleteMax() from Problem 6 ---");
        System.out.println();
        try {
            LinkedTree tree = new LinkedTree();
            System.out.println("empty tree: " + tree.deleteMax());
            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 70};
            tree.insertKeys(keys);
            tree.levelOrderPrint();
            System.out.println();

            int deletedVal1 = tree.deleteMax();
            System.out.println("actual results:");
            System.out.println("first deletion: " + deletedVal1);
            tree.levelOrderPrint();
            System.out.println();
            System.out.println("expected results:");
            System.out.println(70);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(deletedVal1 == 70);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();
        System.out.println("--- Testing 2 deleteMax() from Problem 6 ---");
        System.out.println();
        try {
            LinkedTree tree = new LinkedTree();
            System.out.println("empty tree: " + tree.deleteMax());
            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 100};
            tree.insertKeys(keys);
            tree.levelOrderPrint();
            System.out.println();

            int deletedVal2 = tree.deleteMax();
            System.out.println("actual results:");
            System.out.println("first deletion: " + deletedVal2);
            tree.levelOrderPrint();
            System.out.println("expected results:");
            System.out.println(100);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(deletedVal2 == 100);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();
        System.out.println("--- Testing inorderIterator() from Problem 7 ---");
        System.out.println();
        try {
            LinkedTree tree = new LinkedTree();

            int[] keys = {37, 26, 42, 13, 35, 56, 30, 47, 100};
            tree.insertKeys(keys);

            System.out.println("expected results:");
            tree.inorderPrint();

            LinkedTreeIterator iter = tree.inorderIterator();

            System.out.println("actual results:");
            while (iter.hasNext()) {
                int key = iter.next();

                System.out.print(key + " ");
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();
        System.out.println("--- Testing isomorphic() from Problem 8 ---");
        System.out.println();
        try {
            LinkedTree tree3 = new LinkedTree();

            int[] keys3 = {44, 35, 53, 23, 48, 62};
            tree3.insertKeys(keys3);

            LinkedTree tree4 = new LinkedTree();

            int[] keys4 = {32, 15, 40, 7, 35};
            tree4.insertKeys(keys4);

            System.out.print("before adding final key to tree4: ");
            System.out.println(tree3.isomorphicTo(tree4));

            tree4.insert(52, "value for 52");
            System.out.print("after adding final key to tree4: ");
            System.out.println(tree3.isomorphicTo(tree4));
            System.out.println();

        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }    
    }
}
