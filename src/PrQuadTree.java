import java.util.ArrayList;

/**
 * 
 * @author Matthew Evans (matce93)
 * @author Siddharth Hingorani (sid97)
 */
public class PrQuadTree<T extends NewComparable<? super T>>
{
    static int worldXmin = 0;
    static int worldXmax = 1024;
    static int worldYmin = 0;
    static int worldYmax = 1024;

    private PRnode<T> root;

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

    public PrQuadTree()
    {
        root = null;
    }

    public void insert(T x)
    {
        root = insert(x, root, worldXmax, worldXmin, worldYmax, worldYmin);
    }

    public boolean find(T x)
    {
        return find(x, root, worldXmax, worldXmin, worldYmax, worldYmin);
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
            node = new prLeaf(x);
            return node;
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
            if ((x.compareToX(mid_x) <= 0) && (x.compareToY(mid_y) == -1))
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
                                    mid_y, min_y));
                }

            }
            else if ((x.compareToX(mid_x) == -1) && (x.compareToY(mid_y) >= 0))
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
                                    max_y, mid_y));
                }

            }
            else if ((x.compareToX(mid_x) >= 0) && (x.compareToY(mid_y) == 1))
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
                                    max_y, mid_y));
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
                                    mid_y, min_y));
                }

            }
            return node;
        }
        else
        {
            // need to fix this
            // =======================================================================================
            System.out.println("Insert Fucked up");
            return node;
        }
    }

    public boolean delete(T x)
    {

        if (isEmpty())
        {
            return false;
        }
        else
        {
            PRnode<T> hold = delete(x, root, worldXmax, worldXmin, worldYmax,
                            worldYmin); // returns null if not found
            if (hold != null)
            {
                // root = hold;
                // if (root.getClass().getName().equals("PrQuadTree$prEmpty"))
                // {
                // root = null;
                // }
                return true;
            }
            return false; //
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

    private PRnode<T> delete(T x, PRnode<T> node, int max_x, int min_x,
                    int max_y, int min_y)
    {
        if (node == null)
        {
            return null;
        }
        else if (node.getClass().getName().equals("PrQuadTree$prLeaf"))
        {
            if (((prLeaf) node).getData().contains(x)) // the data exists
            {
                if (((prLeaf) node).getData().size() == 1) // the data is the
                                                           // only element
                {
                    ((prLeaf) node).remove(x);
                    return new prEmpty(); // returns empty node recursively
                }
                else // the data is not the only element
                {
                    ((prLeaf) node).remove(x);
                    return node; // returns leaf node
                }
            }
        }
        else if (node.getClass().getName().equals("PrQuadTree$prInternal"))
        {
            int mid_x = (min_x + max_x) / 2;
            int mid_y = (min_y + max_y) / 2;
            // checks what quadrant the point falls into
            if ((x.compareToX(mid_x) <= 0) && (x.compareToY(mid_y) == -1))
            { // quadrant 2
                if (((prInternal) node).getNW().getClass().getName()
                                .equals("PrQuadTree$prEmpty")) // quadrant is
                                                               // empty
                {
                    return null; // recursively returns null
                }
                else if (((prInternal) node).getNW().getClass().getName()
                                .equals("PrQuadTree$prInternal"))
                {
                    PRnode<T> holder = delete(x, ((prInternal) node).getNW(),
                                    mid_x, min_x, mid_y, min_y); // recursively
                                                                 // call delete
                                                                 // on the
                                                                 // quadrant
                    holder = getChild(holder); // gets the correct child from
                    // helper method
                    if (holder != null) // checks not null before setting the
                                        // new child
                    {
                        ((prInternal) node).setNW(holder);
                    }
                    return holder; // returns the child
                }
                else if (((prInternal) node).getNW().getClass().getName()
                                .equals("PrQuadTree$prLeaf")) // is leaf
                {
                    PRnode<T> holder = delete(x, ((prInternal) node).getNW(),
                                    mid_x, min_x, mid_y, min_y);
                    holder = getChild(holder);
                    if (holder != null) // checks not null before setting the
                    // new child
                    {
                        return node;
                    }
                    return null;
                }

            }
            else if ((x.compareToX(mid_x) == -1) && (x.compareToY(mid_y) >= 0))
            { // quadrant 3
                if (((prInternal) node).getSW().getClass().getName()
                                .equals("PrQuadTree$prEmpty"))
                {
                    return null;
                }
                else if (((prInternal) node).getSW().getClass().getName()
                        .equals("PrQuadTree$prInternal"))
                {
                    PRnode<T> holder = delete(x, ((prInternal) node).getSW(),
                                    mid_x, min_x, max_y, mid_y);
                    // holder = getChild(holder);
                    if (holder != null)
                    {
                        ((prInternal) node).setSW(holder);
                    }
                    return holder;
                }
                else if (((prInternal) node).getSW().getClass().getName()
                        .equals("PrQuadTree$prLeaf")) // is leaf
                {
                	PRnode<T> holder = delete(x, ((prInternal) node).getSW(), mid_x, min_x, max_y, mid_y);
                	holder = getChild(holder);
                	if (holder != null) // checks not null before setting the
                	// new child
                	{
                		return node;
                	}
                	return null;
                }

            }
            else if ((x.compareToX(mid_x) >= 0) && (x.compareToY(mid_y) == 1))
            { // quadrant 4
                if (((prInternal) node).getSE().getClass().getName()
                                .equals("PrQuadTree$prEmpty"))
                {
                    return null;
                }
                else if (((prInternal) node).getSE().getClass().getName()
                                .equals("PrQuadTree$prInternal"))
                {
                    PRnode<T> holder = delete(x, ((prInternal) node).getSE(),
                                    max_x, mid_x, max_y, mid_y);
                    // holder = getChild(holder);
                    if (holder != null)
                    {
                        ((prInternal) node).setSE(holder);
                    }
                    return holder;
                }
                else if (((prInternal) node).getSE().getClass().getName()
                        .equals("PrQuadTree$prLeaf")) // is leaf
                {
                	PRnode<T> holder = delete(x, ((prInternal) node).getSE(), max_x, mid_x, max_y, mid_y);
                	holder = getChild(holder);
                	if (holder != null) // checks not null before setting the
                	// new child
                	{
                		return node;
                	}
                	return null;
                }

            }
            else if (((x.compareToX(mid_x) == 0) && (x.compareToY(mid_y) == 0))
                            || ((x.compareToX(mid_x) > 0)
                                            && (x.compareToY(mid_y) <= 0)))
            { // quadrant 1
                if (((prInternal) node).getNE().getClass().getName()
                                .equals("PrQuadTree$prEmpty"))
                {
                    return null;
                }
                else if (((prInternal) node).getNE().getClass().getName()
                        .equals("PrQuadTree$prInternal"))
                {
                    PRnode<T> holder = delete(x, ((prInternal) node).getNE(),
                                    max_x, mid_x, mid_y, min_y);
                    // holder = getChild(holder);
                    if (holder != null)
                    {
                        ((prInternal) node).setNE(holder);
                    }
                    return holder;
                }
                else if (((prInternal) node).getNE().getClass().getName()
                        .equals("PrQuadTree$prLeaf")) // is leaf
                {
                	PRnode<T> holder = delete(x, ((prInternal) node).getNE(), max_x, mid_x, mid_y, min_y);
                	holder = getChild(holder);
                	if (holder != null) // checks not null before setting the
                	// new child
                	{
                		return node;
                	}
                	return null;
                }
            }

        }
        return null;
    }

    private PRnode<T> getChild(PRnode<T> node)
    {
        if (node == null)
        {
            return null;
        }
        else if (node.getClass().getName().equals("PrQuadTree$prLeaf"))
        {
            return node;
        }
        else if (node.getClass().getName().equals("PrQuadTree$prInternal"))
        {
            int i = 0;
            int j = 0;
            if (((prInternal) node).getNE().getClass().getName()
                            .equals("PrQuadTree$prLeaf"))
            {
                i++;
            }
            if (((prInternal) node).getNE().getClass().getName()
                            .equals("PrQuadTree$prInternal"))
            {
                j++;
            }
            if (((prInternal) node).getNW().getClass().getName()
                            .equals("PrQuadTree$prLeaf"))
            {
                i++;
            }
            if (((prInternal) node).getNW().getClass().getName()
                            .equals("PrQuadTree$prInternal"))
            {
                j++;
            }
            if (((prInternal) node).getSW().getClass().getName()
                            .equals("PrQuadTree$prLeaf"))
            {
                i++;
            }
            if (((prInternal) node).getSW().getClass().getName()
                            .equals("PrQuadTree$prInternal"))
            {
                j++;
            }
            if (((prInternal) node).getSE().getClass().getName()
                            .equals("PrQuadTree$prLeaf"))
            {
                i++;
            }
            if (((prInternal) node).getSE().getClass().getName()
                            .equals("PrQuadTree$prInternal"))
            {
                j++;
            }
            if ((i == 0) && (j == 0)) // no children
            {
                return new prEmpty();
            }
            else if ((i == 1) && (j == 0)) // one child
            {
                if ((((prInternal) node).getNE().getClass().getName()
                                .equals("PrQuadTree$prLeaf"))
                                || (((prInternal) node).getNE().getClass()
                                                .getName()
                                                .equals("PrQuadTree$prInternal")))
                {
                    return ((prInternal) node).getNE();
                }
                if ((((prInternal) node).getNW().getClass().getName()
                                .equals("PrQuadTree$prLeaf"))
                                || (((prInternal) node).getNW().getClass()
                                                .getName()
                                                .equals("PrQuadTree$prInternal")))
                {
                    return ((prInternal) node).getNW();
                }
                if ((((prInternal) node).getSW().getClass().getName()
                                .equals("PrQuadTree$prLeaf"))
                                || (((prInternal) node).getSW().getClass()
                                                .getName()
                                                .equals("PrQuadTree$prInternal")))
                {
                    return ((prInternal) node).getSW();
                }
                if ((((prInternal) node).getSE().getClass().getName()
                                .equals("PrQuadTree$prLeaf"))
                                || (((prInternal) node).getSE().getClass()
                                                .getName()
                                                .equals("PrQuadTree$prInternal")))
                {
                    return ((prInternal) node).getSE();
                }
            }
            else // two or more children or internal
            {
                return node;
            }
        }
        // node is empty
        return node;
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
            if ((x.compareToX(mid_x) <= 0) && (x.compareToY(mid_y) == -1))
            { // quadrant 2
                return find(x, ((prInternal) node).getNW(), mid_x, min_x, mid_y,
                                min_y);
            }
            else if ((x.compareToX(mid_x) == -1) && (x.compareToY(mid_y) >= 0))
            { // quadrant 3
                return find(x, ((prInternal) node).getSW(), mid_x, min_x, max_y,
                                mid_y);
            }
            else if ((x.compareToX(mid_x) >= 0) && (x.compareToY(mid_y) == 1))
            { // quadrant 4
                return find(x, ((prInternal) node).getSE(), max_x, mid_x, max_y,
                                mid_y);
            }
            else
            { // quadrant 1
                return find(x, ((prInternal) node).getNE(), max_x, mid_x, mid_y,
                                min_y);
            }
        }
        return false;
    }

    public void dump()
    {
        dump(root, worldXmax, worldXmin, worldYmax, worldYmin, worldXmax,
                        worldXmin, worldYmax, worldYmin);
    }

    private PRnode<T> dump(PRnode<T> node, int max_x, int min_x, int max_y,
                    int min_y, int regionMax_x, int regionMin_x,
                    int regionMax_y, int regionMin_y)
    {
        if (node == null)
        {
            return null; // Not found
        }
        else if (node.getClass().getName().equals("PrQuadTree$prLeaf"))
        {
            ArrayList<T> temp = ((prLeaf) node).getData();
            for (int i = 0; i < temp.size(); i++)
            {
               System.out.println(temp.get(i).toString());
            }
            return null;
        }
        else if (node.getClass().getName().equals("PrQuadTree$prInternal"))
        {
            int mid_x = (min_x + max_x) / 2;
            int mid_y = (min_y + max_y) / 2;
            if ((regionMin_x <= mid_x) && (regionMin_y < mid_y))
            { // quadrant 2
            	dump(((prInternal) node).getNW(), mid_x, min_x, mid_y, min_y, regionMax_x, regionMin_x, regionMax_y, regionMin_y);
            }
            if ((regionMin_x < mid_x) && (regionMax_y >= mid_y))
            { // quadrant 3
            	dump(((prInternal) node).getSW(), mid_x, min_x, max_y, mid_y, regionMax_x, regionMin_x, regionMax_y, regionMin_y);
            }
            if ((regionMax_x >= mid_x) && (regionMax_y > mid_y))
            { // quadrant 4
            	dump(((prInternal) node).getSE(), max_x, mid_x, max_y, mid_y, regionMax_x, regionMin_x, regionMax_y, regionMin_y);
            }
            if ((regionMax_x >= mid_x) && (regionMin_y < mid_y))
            { // quadrant 1
            	dump(((prInternal) node).getNE(), max_x, mid_x, mid_y, min_y, regionMax_x, regionMin_x, regionMax_y, regionMin_y);
            }
        }
        return null;
    }

    public void regionSearch(int regionMax_x, int regionMin_x, int regionMax_y,
                    int regionMin_y)
    {
        regionSearch(root, worldXmax, worldXmin, worldYmax, worldYmin,
                        regionMax_x, regionMin_x, regionMax_y, regionMin_y);
    }

    private PRnode<T> regionSearch(PRnode<T> node, int max_x, int min_x,
                    int max_y, int min_y, int regionMax_x, int regionMin_x,
                    int regionMax_y, int regionMin_y)
    {
        if (node == null)
        {
            return null; // Not found
        }
        else if (node.getClass().getName().equals("PrQuadTree$prLeaf"))
        {
            ArrayList<T> temp = ((prLeaf) node).getData();
            for (int i = 0; i < temp.size(); i++)
            {
                if ((temp.get(i).compareToX(regionMax_x) <= 0)
                                && (temp.get(i).compareToX(regionMin_x) >= 0)
                                && (temp.get(i).compareToY(regionMax_y) <= 0)
                                && (temp.get(i).compareToY(regionMin_y) >= 0))
                {
                    System.out.println(temp.get(i).toString());
                }
            }
            return null;
        }
        else if (node.getClass().getName().equals("PrQuadTree$prInternal"))
        {
            int mid_x = (min_x + max_x) / 2;
            int mid_y = (min_y + max_y) / 2;
            if ((regionMin_x <= mid_x) && (regionMin_y < mid_y))
            { // quadrant 2
                regionSearch(((prInternal) node).getNW(), mid_x, min_x, mid_y,
                                min_y, regionMax_x, regionMin_x, regionMax_y,
                                regionMin_y);
            }
            if ((regionMin_x < mid_x) && (regionMax_y >= mid_y))
            { // quadrant 3
                regionSearch(((prInternal) node).getSW(), mid_x, min_x, max_y,
                                mid_y, regionMax_x, regionMin_x, regionMax_y,
                                regionMin_y);
            }
            if ((regionMax_x >= mid_x) && (regionMax_y > mid_y))
            { // quadrant 4
                regionSearch(((prInternal) node).getSE(), max_x, mid_x, max_y,
                                mid_y, regionMax_x, regionMin_x, regionMax_y,
                                regionMin_y);
            }
            if ((regionMax_x >= mid_x) && (regionMin_y < mid_y))
            { // quadrant 1
                regionSearch(((prInternal) node).getNE(), max_x, mid_x, mid_y,
                                min_y, regionMax_x, regionMin_x, regionMax_y,
                                regionMin_y);
            }
        }
        return null;
    }

    public void duplicateFind()
    {
        duplicateFind(root, worldXmax, worldXmin, worldYmax, worldYmin);
    }

    private PRnode<T> duplicateFind(PRnode<T> node, int max_x, int min_x,
                    int max_y, int min_y)
    {
        if (node == null)
        {
            return null;
        }
        else if (node.getClass().getName().equals("PrQuadTree$prLeaf"))
        {
            ArrayList<T> temp = ((prLeaf) node).getData();
            for (int i = 0; i < temp.size(); i++)
            {
                for (int j = i + 1; j < temp.size(); j++)
                {
                    if (temp.get(i).equals(temp.get(j)))
                    {
                        System.out.println(temp.get(i).toString());
                        return node;
                    }
                }
            }
        }
        else if (node.getClass().getName().equals("PrQuadTree$prInternal"))
        {
            int mid_x = (min_x + max_x) / 2;
            int mid_y = (min_y + max_y) / 2;

            duplicateFind(((prInternal) node).getNW(), mid_x, min_x, mid_y,
                            min_y);
            duplicateFind(((prInternal) node).getSW(), mid_x, min_x, max_y,
                            mid_y);
            duplicateFind(((prInternal) node).getSE(), max_x, mid_x, max_y,
                            mid_y);
            duplicateFind(((prInternal) node).getNE(), max_x, mid_x, mid_y,
                            min_y);
        }
        return node;
    }

}
