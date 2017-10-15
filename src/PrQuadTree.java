import java.util.ArrayList;

/**
 * 
 * @author Matthew Evans (matce93)
 * @author Siddharth Hingorani (sid97)
 */
public class PrQuadTree<T extends NewComparable<? super T>>
{
    @SuppressWarnings("hiding")
    abstract class PRnode<T>
    {

    }

    class prLeaf extends PRnode<T>
    {

        private ArrayList<T> data;

        prLeaf(T x)
        {
            data = new ArrayList<T>();
            data.add(x);
        }

        public ArrayList<T> getData()
        {
            return data;
        }

        public boolean remove(T x)
        {
            return (data.remove(x));
        }

        public boolean insert(T theData)
        {
            if (data.size() + 1 > 3)
            {
                for (int i = 0; i < data.size(); i++)
                {
                    for (int j = i; j < data.size(); j++)
                    {
                        if (!data.get(i).equals(data.get(j)))
                        {
                            return false;
                        }
                    }
                }
                if (data.get(0).equals(theData))
                {
                    data.add(theData);
                    return true;
                }
                else
                {
                    return false;
                }

            }
            else
            {
                data.add(theData);
                return true;
            }
        }
    }

    class prInternal extends PRnode<T>
    {

        private PRnode<T> NW;
        private PRnode<T> NE;
        private PRnode<T> SW;
        private PRnode<T> SE;

        prInternal(PRnode<T> northWest, PRnode<T> northEast,
                        PRnode<T> southWest, PRnode<T> southEast)
        {
            NW = northWest;
            NE = northEast;
            SW = southWest;
            SE = southEast;
        }

        public PRnode<T> getNW()
        {
            return NW;
        }

        public PRnode<T> getNE()
        {
            return NE;
        }

        public PRnode<T> getSW()
        {
            return SW;
        }

        public PRnode<T> getSE()
        {
            return SE;
        }

        public void setNW(PRnode<T> value)
        {
            NW = value;
        }

        public void setNE(PRnode<T> value)
        {
            NE = value;
        }

        public void setSW(PRnode<T> value)
        {
            SW = value;
        }

        public void setSE(PRnode<T> value)
        {
            SE = value;
        }

    }

    class prEmpty extends PRnode<T>
    {
        public prEmpty()
        {
            // Purposely empty
        }
    }

    private PRnode<T> root;

    public PrQuadTree()
    {
        root = null;
    }

    public void insert(T x)
    {
        root = insert(x, root);
    }

    /**
     * this method inserts data into the PR qradtree this method assumes that
     * the item is within the world
     * 
     * @param x
     *            object to be inserted
     * @param node
     * @return
     */
    private PRnode<T> insert(T x, PRnode<T> node)
    // int max_x, int min_x, int max_y, int min_y)
    {
        if (node == null) // root is null or internal node is null
        {
            return new prLeaf(x);
        }
        else if ((node.getClass().getName().equals("PrQuadTree$prLeaf"))
                        || (node.getClass().getName()
                                        .equals("PrQuadTree$prEmpty")))
        { // is a leaf node or empty
            if (((prLeaf) node).insert(x))
            {
                return node; // if insert is successful means there was space
                             // in the current leaf node to hold the new data.
            }
            else
            {
                // NEED TO SPLIT Current code breaks the pointers.
                // ArrayList<T> temp = ((prLeaf) node).getData();
                // prEmpty northeast = new prEmpty();
                // prEmpty northwest = new prEmpty();
                // prEmpty southwest = new prEmpty();
                // prEmpty southeast = new prEmpty();
                // node = new prInternal(northeast, northwest, southwest,
                // southeast);
                // while (!temp.isEmpty())
                // {
                // insert(temp.get(0));
                // temp.remove(0);
                // }
                return null;
            }

        }
<<<<<<< HEAD
        else if (node.getClass().getName().equals("PRnode$prInternal"))
        { // is an internal node

=======
        else if (node.getClass().getName().equals("PrQuadTree$prInternal"))
        {
            // NEED TO COMPARE TO THEN BRANCH INTO CORRECT SUBTREE
            // NEED TO ALSO FIX POINTERS WHEN SPLITTING.
            return null;
>>>>>>> branch 'master' of https://github.com/SidH97/PRQuadTree.git
        }
        else
        {
            return node;
        }

    }

    @SuppressWarnings("unused")
    private PRnode<T> spittersRquiters(T x, prLeaf node, int max_x, int min_x,
                    int max_y, int min_y) // also known as splitter
    {
        prLeaf northeast = new prLeaf(null);
        prLeaf northwest = new prLeaf(null);
        prLeaf southwest = new prLeaf(null);
        prLeaf southeast = new prLeaf(null);
        prInternal returnNode = new prInternal(northeast, northwest, southwest,
                        southeast);

        return new prLeaf(null);

    }

    /**
     * is the PR QuadTree Empty
     * 
     * @return if root is null
     */
    public boolean isEmpty()
    {
        return root == null;
    }

    public T find(T x)
    {
        PRnode<T> temp = find(x, root);
        if ((temp != null) && (temp.getClass().getName()
                        .equals("PrQuadTree$prLeaf")))
        {
            ArrayList<T> list = ((prLeaf) temp).getData();
            for (int i = 0; i < list.size(); i++)
            {
                if (x.equals(list.get(i)))
                {
                    return list.get(i);
                }
            }
            return null;
        }
        else
        {

            return null;
        }
    }

    /**
     * This method will return the root of the current tree.
     * 
     * @return the root node
     */
    public PRnode<T> findRoot()
    {
        return root;
    }

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

    private PRnode<T> remove(T x, PRnode<T> node) throws Exception
    {
        if (node == null)
        {
            throw new Exception(x.toString());
        }

        // if value should be to the left of the root
        else if (node.getClass().getName().equals("PrQuadTree$prLeaf"))
        {
            // NOT SURE ON RETURNING
            if (((prLeaf) node).remove(x))
            {
                return node; // Maybe
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    private PRnode<T> find(T x, PRnode<T> node)
    {
        if (node == null)
        {
            return null; // Not found
        }
        else if (node.getClass().getName().equals("PrQuadTree$prLeaf"))
        {
            ArrayList<T> temp = ((prLeaf) node).getData();
            if (temp.contains(x))
            {
                return node;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

}
