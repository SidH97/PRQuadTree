/**
 * 
 */

/**
 * A basic node stored in an unbalanced binary search tree.
 *
 * @author Siddharth Hingorani (sid97)
 * @author Matthew Evans (matce93)
 * @version 09.16.2017
 *
 * @param <T>
 *            the object to be used as a node.
 */
class BSTNode<T>
{
    // ~ Instance/static variables .............................................

    private T data;
    private BSTNode<T> left;
    private BSTNode<T> right;

    // ~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a node with no children.
     * 
     * @param theData
     *            the element to store in this node.
     */
    BSTNode(T theData)
    {
        data = theData;
        left = null;
        right = null;
    }

    // ~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Get the current data value stored in this node.
     * 
     * @return the data
     */
    public T getData()
    {
        return data;
    }

    // ----------------------------------------------------------
    /**
     * Set the data value stored in this node.
     * 
     * @param value
     *            the new data value to set
     */
    public void setData(T value)
    {
        data = value;
    }

    // ----------------------------------------------------------
    /**
     * Get the left child of this node.
     * 
     * @return a reference to the left child.
     */
    public BSTNode<T> getLeft()
    {
        return left;
    }

    // ----------------------------------------------------------
    /**
     * Set this node's left child.
     * 
     * @param value
     *            the node to point to as the left child.
     */
    public void setLeft(BSTNode<T> value)
    {
        left = value;
    }

    // ----------------------------------------------------------
    /**
     * Get the right child of this node.
     * 
     * @return a reference to the right child.
     */
    public BSTNode<T> getRight()
    {
        return right;
    }

    // ----------------------------------------------------------
    /**
     * Set this node's right child.
     * 
     * @param value
     *            the node to point to as the right child.
     */
    public void setRight(BSTNode<T> value)
    {
        right = value;
    }

    /**
     * Provides an in-order representation of the node
     * 
     * @return a string representation of the node
     * 
     * @Override public String toString() { StringBuilder builder = new
     *           StringBuilder(); if (left != null) {
     *           builder.append(left.toString() + ", "); }
     *           builder.append(data.toString()); if (right != null) {
     *           builder.append(", " + right.toString()); } return
     *           builder.toString(); }
     */
}
