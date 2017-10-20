
/**
 * 
 */

import java.util.Iterator;
import java.util.Stack;

/**
 * Implements an unbalanced binary search tree. Note that all "matching" is
 * based on the compareTo() method.
 *
 * @author Siddharth Hingorani (sid97)
 * @author Matthew Evans (matce93)
 * @version 2017.09.16
 * 
 * @param <T>
 *            the object to be held in our bst.
 *
 */
public class BST<T extends Comparable<T>> implements Iterable<T>
{
    // ~ Constructor

    private BSTNode<T> root;

    // ~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Constructs an empty tree.
     */
    public BST()
    {
        root = null;
    }

    // ~ Public methods ........................................................

    /**
     * This method will dump the tree to std out.
     */
    public void dump()
    {
        System.out.println("BST dump:");
        if (root == null)
        {
            System.out.println("Node has depth 0, Value (null)");
        }
        this.inOrder(root, 0);
    }
    
    /**
     * This method will be recursively called dump our bst in order
     * 
     * @param t
     *            the starting node
     * @param depth
     *            the recursive depth, starts at 0
     * @return the max depth of the tree
     */
    private int inOrder(BSTNode<T> t, int depth)
    {
        int l = 0;
        int r = 0;
        int m = 0;
        m = depth;
        if (t != null)
        {
            l = inOrder(t.getLeft(), depth + 1);
            // Visit the node by Printing the node data
            System.out.println("Node has depth " + depth + ", Value "
                            + t.getData().toString());
            r = inOrder(t.getRight(), depth + 1);
        }
        return Math.max(l, Math.max(r, m));

    }

    // ----------------------------------------------------------
    /**
     * Insert into the tree.
     *
     * @param x
     *            the item to insert.
     */
    public void insert(T x)
    {
        root = insert(x, root);
    }

    // ----------------------------------------------------------
    /**
     * Remove the specified value from the tree.
     *
     * @param x
     *            the item to remove.
     * @throws Exception
     *             if not found
     */
    public void remove(T x) throws Exception
    {
        root = remove(x, root);
    }

    // ----------------------------------------------------------
    /**
     * Find an item in the tree.
     *
     * @param x
     *            the item to search for.
     * @return the matching item or null if not found.
     */
    public T find(T x)
    {
        return dataAt(find(x, root));
    }

    // ----------------------------------------------------------
    /**
     * Make the tree logically empty.
     */
    public void makeEmpty()
    {
        root = null;
    }

    // ----------------------------------------------------------
    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return (root == null);
    }

    // ----------------------------------------------------------
    /**
     * Internal method to get element value stored in a tree node, with safe
     * handling of null nodes.
     *
     * @param node
     *            the node.
     * @return the element field or null if node is null.
     */
    private T dataAt(BSTNode<T> node)
    {
        return (node == null) ? null : node.getData();
    }

    // ----------------------------------------------------------
    /**
     * Internal method to insert a value into a subtree.
     *
     * @param x
     *            the item to insert.
     * @param node
     *            the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BSTNode<T> insert(T x, BSTNode<T> node)
    {
        if (node == null)
        {
            return new BSTNode<T>(x);
        }
        else if (x.compareTo(node.getData()) < 0)
        {
            node.setLeft(insert(x, node.getLeft()));
        }
        else if (x.compareTo(node.getData()) > 0)
        {
            node.setRight(insert(x, node.getRight()));
        }
        else if (x.compareTo(node.getData()) == 0)
        {
            node.setRight(insert(x, node.getRight()));
        }
        return node;
    }

    // ----------------------------------------------------------
    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param node
     *            the node that roots the tree.
     * @return node containing the largest item.
     */
    private BSTNode<T> findMax(BSTNode<T> node)
    {
        if (node == null)
        {
            return node;
        }
        else if (node.getRight() == null)
        {
            return node;
        }
        else
        {
            return findMax(node.getRight());
        }
    }

    // ----------------------------------------------------------
    /**
     * Internal method to remove a specified item from a subtree.
     *
     * @param x
     *            the item to remove.
     * @param node
     *            the node that roots the subtree.
     * @return the new root of the subtree.
     * @throws Exception
     *             if x not found.
     */
    private BSTNode<T> remove(T x, BSTNode<T> node) throws Exception
    {
        // This local variable will contain the new root of the subtree,
        // if the root needs to change.
        BSTNode<T> result = node;

        // If there's no more subtree to examine
        if (node == null)
        {
            throw new Exception(x.toString());
        }

        // if value should be to the left of the root
        if (x.compareTo(node.getData()) < 0)
        {
            node.setLeft(remove(x, node.getLeft()));
        }
        // if value should be to the right of the root
        else if (x.compareTo(node.getData()) > 0)
        {
            node.setRight(remove(x, node.getRight()));
        }
        // If value is on the current node
        else
        {
            // If there are two children
            if (node.getLeft() != null && node.getRight() != null)
            {
                result = findMax(node.getLeft());
                result.setLeft(null);
                result.setRight(node.getRight());
            }
            // If there is only one child on the left
            else if (node.getLeft() != null)
            {
                result = node.getLeft();
            }
            // If there is only one child on the right
            else
            {
                result = node.getRight();
            }
        }
        return result;
    }

    // ----------------------------------------------------------
    /**
     * Internal method to find an item in a subtree.
     *
     * @param x
     *            is item to search for.
     * @param node
     *            the node that roots the tree.
     * @return node containing the matched item.
     */
    private BSTNode<T> find(T x, BSTNode<T> node)
    {
        if (node == null)
        {
            return null; // Not found
        }
        else if (x.compareTo(node.getData()) < 0)
        {
            // Search in the left subtree
            return find(x, node.getLeft());
        }
        else if (x.compareTo(node.getData()) > 0)
        {
            // Search in the right subtree
            return find(x, node.getRight());
        }
        else
        {
            return node; // Match
        }
    }

    /**
     * This method will return the root of the current tree.
     * 
     * @return the root node
     */
    private BSTNode<T> findRoot()
    {
        return root;
    }

    @Override
    public Iterator<T> iterator()
    {
        return new IteratorOrder(root);
    }

    private class IteratorOrder implements Iterator<T>
    {
        BSTNode<T> current;
        Stack<BSTNode<T>> stack;

        /**
         * This is our default constructor
         *
         * @param node
         *            to iterate from.
         */
        public IteratorOrder(BSTNode<T> node)
        {
            if (node != null)
            {
                stack = new Stack<BSTNode<T>>();
                goLeft(node);
            }
            else
            {
                stack = new Stack<BSTNode<T>>();
            }
        }

        /**
         * @return next node
         */
        public T next()
        {
            if (!stack.isEmpty())
            {
                current = stack.peek();
                stack.pop();
                if (current.getRight() != null)
                {
                    goLeft(current.getRight());
                }
            }
            return (T) current.getData();
        }

        /**
         * This method will go left
         * 
         * @param t
         *            the node from which to go left.
         */
        private void goLeft(BSTNode<T> t)
        {
            while (t != null)
            {
                stack.push(t);
                t = t.getLeft();

            }
        }

        /**
         * @return is stack is empty
         */
        public boolean hasNext()
        {
            return (!stack.isEmpty());
        }

        /**
         * This method will traverse inOrder
         * 
         * @param bst
         *            the tree to traverse
         */
        @SuppressWarnings("unused")
        private void inOrder(BST<T> bst)
        {
            inOrderHelper(bst.findRoot());
        }

        /**
         * This method will help our inOrder method.
         * 
         * @param t
         *            the node being worked on
         */
        private void inOrderHelper(BSTNode<T> t)
        {
            if (current == null)
            {
                return;
            }
            inOrderHelper(t.getLeft());
            System.out.println(t.getLeft());
            inOrderHelper(t.getRight());
        }
    }

}
