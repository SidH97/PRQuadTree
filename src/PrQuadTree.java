import java.util.ArrayList;

/**
 * 
 * @author Matthew Evans (matce93)
 * @author Siddharth Hingorani (sid97)
 */
public class PrQuadTree<T extends Comparable<? super T>>
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
            data.add(x);
        }

        public ArrayList<T> getData()
        {
            return data;
        }

        public boolean insert(T theData)
        {
            if (data.size() > 3)
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
                return true;
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
        else if ((node.getClass().getName().equals("PRnode$prLeaf"))
                        || (node.getClass().getName().equals("PRnode$prEmpty")))
        { // is a leaf node
            if (((prLeaf) node).insert(x))
            {
                return node; // if insert is successful means there was space
                             // in the current leaf node to hold the new data.
            }
            else
            { // not equal
              // while(){}
                int yMid = (max_y + min_y) / 2;
                int xMid = (max_x + min_x) / 2;
                ArrayList<T> temp = ((prLeaf) node).getData();
                prEmpty northeast = new prEmpty();
                prEmpty northwest = new prEmpty();
                prEmpty southwest = new prEmpty();
                prEmpty southeast = new prEmpty();
                node = new prInternal(northeast, northwest, southwest,
                                southeast);
                while (!temp.isEmpty())
                {
                    insert(temp.get(0));
                    temp.remove(0);
                }
            }

        }
        else if (node.getClass().getName().equals("PRnode$prInternal"))

        { // is an internal node

        }
        // should never get here
        System.out.println(
                        "node.getClass().getName().equals(...) does not work!!!");
        return null;
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

}
