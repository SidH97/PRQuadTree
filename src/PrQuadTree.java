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

    public class prLeaf extends PRnode<T>
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
        root = insert(x, root, 1024, 0, 1024, 0);
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
    private PRnode<T> insert(T x, PRnode<T> node, int max_x, int min_x,
                    int max_y, int min_y)
    {
        if (node == null) // root is null or internal node is null
        {
            return new prLeaf(x);
        }
        else if (node.getClass().getName().equals("PrQuadTree$prLeaf"))
        { // is a leaf node or empty
            if (((prLeaf) node).insert(x))
            {
                return node; // if insert is successful means there was space
                             // in the current leaf node to hold the new data.
            }
            else
            {
                // NEED TO SPLIT Current code breaks the pointers.
                ArrayList<T> temp = ((prLeaf) node).getData();
                prEmpty northeast = new prEmpty();
                prEmpty northwest = new prEmpty();
                prEmpty southwest = new prEmpty();
                prEmpty southeast = new prEmpty();
                node = new prInternal(northeast, northwest, southwest,
                                southeast);

                for (int i = 0; i < temp.size(); i++)
                {
                    insert(temp.get(i), node, max_x, min_x, max_y, min_y);
                }
                insert(x, node, max_x, min_x, max_y, min_y);
                return node;
            }

        }
        else if (node.getClass().getName().equals("PrQuadTree$prInternal"))
        {
            int mid_x = (min_x + max_x) / 2;
            int mid_y = (min_y + max_y) / 2;
            if ((x.compareToX(mid_x) <= 0) && (x.compareToY(mid_y) == 1))
            { // quadrant 2
                if (((prInternal) node).getNW().getClass().getName()
                                .equals("PrQuadTree$prEmpty"))
                {
                    ((prInternal) node).setNW(new prLeaf(x));
                }
                else
                {
                    ((prInternal) node).setNW(insert(x,
                                    ((prInternal) node).getNW(), mid_x, min_x,
                                    max_y, mid_y));
                }

            }
            else if ((x.compareToX(mid_x) == -1) && (x.compareToY(mid_y) <= 0))
            { // quadrant 3
                if (((prInternal) node).getSW().getClass().getName()
                                .equals("PrQuadTree$prEmpty"))
                {
                    ((prInternal) node).setSW(new prLeaf(x));
                }
                else
                {
                    ((prInternal) node).setSW(insert(x,
                                    ((prInternal) node).getSW(), mid_x, min_x,
                                    mid_y, min_y));
                }

            }
            else if ((x.compareToX(mid_x) >= 0) && (x.compareToY(mid_y) == -1))
            { // quadrant 4
                if (((prInternal) node).getSE().getClass().getName()
                                .equals("PrQuadTree$prEmpty"))
                {
                    ((prInternal) node).setSE(new prLeaf(x));
                }
                else
                {
                    ((prInternal) node).setSE(insert(x,
                                    ((prInternal) node).getSE(), max_x, mid_x,
                                    mid_y, min_y));
                }

            }
            else
            { // quadrant 1
                if (((prInternal) node).getNE().getClass().getName()
                                .equals("PrQuadTree$prEmpty"))
                {
                    ((prInternal) node).setNE(new prLeaf(x));
                }
                else
                {
                    ((prInternal) node).setNE(insert(x,
                                    ((prInternal) node).getNE(), max_x, mid_x,
                                    max_y, mid_y));
                }

            }
            return node;
        }
        else
        {
            System.out.println("Fucked up");
            return node;
        }
    }

    // @SuppressWarnings("unused")
    // private PRnode<T> spittersRquiters(T x, prLeaf node, int max_x, int
    // min_x,
    // int max_y, int min_y) // also known as splitter
    // {
    // prLeaf northeast = new prLeaf(null);
    // prLeaf northwest = new prLeaf(null);
    // prLeaf southwest = new prLeaf(null);
    // prLeaf southeast = new prLeaf(null);
    // prInternal returnNode = new prInternal(northeast, northwest, southwest,
    // southeast);
    //
    // return new prLeaf(null);
    //
    // }

    /**
     * is the PR QuadTree Empty
     * 
     * @return if root is null
     */
    public boolean isEmpty()
    {
        return root == null;
    }

    // public T find(T x)
    // {
    // PRnode<T> temp = find(x, root);
    // if ((temp != null) && (temp.getClass().getName()
    // .equals("PrQuadTree$prLeaf")))
    // {
    // ArrayList<T> list = ((prLeaf) temp).getData();
    // for (int i = 0; i < list.size(); i++)
    // {
    // if (x.equals(list.get(i)))
    // {
    // return list.get(i);
    // }
    // }
    // return null;
    // }
    // else
    // {
    //
    // return null;
    // }
    // }

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

    public boolean find(T x)
    {
        return find(x, root, 1024, 0, 1024, 0);
    }

    private boolean find(T x, PRnode<T> node, int max_x, int min_x, int max_y,
                    int min_y)
    {
        if (node == null)
        {
            return false; // Not found
        }
        else if (node.getClass().getName().equals("PrQuadTree$prLeaf"))
        {
            ArrayList<T> temp = ((prLeaf) node).getData();
            return (temp.contains(x));
        }
        else if (node.getClass().getName().equals("PrQuadTree$prInternal"))
        {
            int mid_x = (min_x + max_x) / 2;
            int mid_y = (min_y + max_y) / 2;
            if ((x.compareToX(mid_x) <= 0) && (x.compareToY(mid_y) == 1))
            { // quadrant 2
                return find(x, ((prInternal) node).getNW(), mid_x, min_x, max_y,
                                mid_y);
            }
            else if ((x.compareToX(mid_x) == -1) && (x.compareToY(mid_y) <= 0))
            { // quadrant 3
                return find(x, ((prInternal) node).getSW(), mid_x, min_x, mid_y,
                                min_y);
            }
            else if ((x.compareToX(mid_x) >= 0) && (x.compareToY(mid_y) == -1))
            { // quadrant 4
                return find(x, ((prInternal) node).getSE(), max_x, mid_x, mid_y,
                                min_y);
            }
            else
            { // quadrant 1
                return find(x, ((prInternal) node).getNE(), max_x, mid_x, max_y,
                                mid_y);
            }
        }
        return false;

    }

}
