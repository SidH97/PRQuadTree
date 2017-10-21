import java.util.ArrayList;

/**
 * This class will hold our quadtree
 *
 * @author Siddharth Hingorani (sid97)
 * @author Matthew Evans (matce93)
 * @version 2016.xx.xx
 *
 * @param <T>
 *            the data type to be used with bst.
 */
public class PrQuadTree<T extends Comparable2D<? super T>>
{
    /**
     * This field will
     */
    static int worldXmin = 0;
    /**
     * This field will
     */
    static int worldXmax = 1024;
    /**
     * This field will
     */
    static int worldYmin = 0;
    /**
     * This field will
     */
    static int worldYmax = 1024;

    private PrNode<T> root;

    /**
     * This class will
     *
     * @author Siddharth Hingorani (sid97)
     * @version 2016.xx.xx
     *
     * @param <T>
     *            the abstract
     */
    @SuppressWarnings("hiding")
    abstract class PrNode<T>
    {

    }

    /**
     * This class will implement our leaf node
     *
     * @author Siddharth Hingorani (sid97)
     *
     */
    public class PrLeaf extends PrNode<T>
    {

        private ArrayList<T> data;

        /**
         * This is our default constructor
         *
         * @param x
         *            the element to add
         */
        PrLeaf(T x)
        {
            data = new ArrayList<T>();
            data.add(x);
        }

        /**
         * This method will return the data in the node
         * 
         * @return the data in the node.
         */
        public ArrayList<T> getData()
        {
            return data;
        }

        /**
         * This method will remove data
         * 
         * @param x
         *            the data to remove
         * @return the node
         */
        public PrNode<T> remove(T x)
        {
            if (data.remove(x))
            {
                if (data.isEmpty())
                {
                    return new PrEmpty();
                }
                else
                {
                    return this;
                }
            }
            return null;
        }

        /**
         * This method will insert the data
         * 
         * @param theData
         *            to insert
         * @return true if success
         */
        public boolean insert(T theData)
        {
            boolean check = true;
            int x = 0;
            while (check && x < data.size())
            {
                if (!theData.sameXY(data.get(x)))
                {
                    check = false;
                }
                x++;
            }
            if (check || data.size() < 3)
            {
                data.add(theData);
                check = true;
            }
            return check;
        }
    }

    /**
     * This class will be our internal node
     *
     * @author Siddharth Hingorani (sid97)
     *
     */
    class PrInternal extends PrNode<T>
    {

        private PrNode<T> nW;
        private PrNode<T> nE;
        private PrNode<T> sW;
        private PrNode<T> sE;

        /**
         * This is our default constructor
         *
         * @param northWest
         *            nW
         * @param northEast
         *            nE
         * @param southWest
         *            sW
         * @param southEast
         *            sE
         */
        PrInternal(PrNode<T> northWest, PrNode<T> northEast,
                        PrNode<T> southWest, PrNode<T> southEast)
        {
            nW = northWest;
            nE = northEast;
            sW = southWest;
            sE = southEast;
        }

        /**
         * This method will return the node
         * 
         * @return nW
         */
        public PrNode<T> getnW()
        {
            return nW;
        }

        /**
         * This method will return the node
         * 
         * @return nE
         */
        public PrNode<T> getnE()
        {
            return nE;
        }

        /**
         * This method will return the node
         * 
         * @return sW
         */
        public PrNode<T> getsW()
        {
            return sW;
        }

        /**
         * This method will return the node
         * 
         * @return sE
         */
        public PrNode<T> getsE()
        {
            return sE;
        }

        /**
         * This method will return the node
         * 
         * @param value
         *            nW
         */
        public void setnW(PrNode<T> value)
        {
            nW = value;
        }

        /**
         * This method will return the node
         * 
         * @param value
         *            sE
         */
        public void setnE(PrNode<T> value)
        {
            nE = value;
        }

        /**
         * This method will return the node
         * 
         * @param value
         *            sW
         */
        public void setsW(PrNode<T> value)
        {
            sW = value;
        }

        /**
         * This method will return the node
         * 
         * @param value
         *            sE
         */
        public void setsE(PrNode<T> value)
        {
            sE = value;
        }

        /**
         * This method will compress internal if needed
         * 
         * @return the pointer
         */
        public PrNode<T> compress()
        {
            ArrayList<T> list = new ArrayList<T>();
            if (this.sE.getClass().equals(PrLeaf.class))
            {
                ArrayList<T> temp = ((PrLeaf) sE).getData();
                for (int i = 0; i < temp.size(); i++)
                {
                    list.add(temp.get(i));
                }
            }
            if (this.nE.getClass().equals(PrLeaf.class))
            {
                ArrayList<T> temp = ((PrLeaf) nE).getData();
                for (int i = 0; i < temp.size(); i++)
                {
                    list.add(temp.get(i));
                }
            }
            if (this.nW.getClass().equals(PrLeaf.class))
            {
                ArrayList<T> temp = ((PrLeaf) nW).getData();
                for (int i = 0; i < temp.size(); i++)
                {
                    list.add(temp.get(i));
                }
            }
            if (this.sW.getClass().equals(PrLeaf.class))
            {
                ArrayList<T> temp = ((PrLeaf) sW).getData();
                for (int i = 0; i < temp.size(); i++)
                {
                    list.add(temp.get(i));
                }
            }
            if (list.size() > 0)
            {
                PrLeaf leaf = new PrLeaf(list.get(0));
                for (int z = 1; z < list.size(); z++)
                {
                    if (!(leaf.insert(list.get(z))))
                    {
                        return this;
                    }
                }
                return leaf;
            }
            else
            {
                return new PrEmpty();
            }

        }

        /**
         * This method will help in compressing
         * 
         * @return true if all leaves
         */
        public boolean leafCheck()
        {
            return (!this.nE.getClass().equals(PrInternal.class)
                            && !this.sE.getClass().equals(PrInternal.class)
                            && !this.nW.getClass().equals(PrInternal.class)
                            && !this.sW.getClass().equals(PrInternal.class));
        }
    }

    /**
     * This class will be our flyweight
     *
     * @author Siddharth Hingorani (sid97)
     *
     */
    class PrEmpty extends PrNode<T>
    {
        /**
         * This is our default constructor
         *
         */
        public PrEmpty()
        {
            // Purposely empty
        }
    }

    /**
     * This is our default constructor
     *
     */
    public PrQuadTree()
    {
        root = new PrEmpty();
    }

    /**
     * This method will insert
     * 
     * @param x
     *            to inseert
     */
    public void insert(T x)
    {
        root = insert(x, root, worldXmax, worldXmin, worldYmax, worldYmin);
    }

    /**
     * This method will fin
     * 
     * @param x
     *            to find
     * @return true if found
     */
    public boolean find(T x)
    {
        return find(x, root, worldXmax, worldXmin, worldYmax, worldYmin);
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty()
    {
        root = new PrEmpty();
    }

    /**
     * is the PR QuadTree Empty
     * 
     * @return if root is null
     */
    public boolean isEmpty()
    {
        return (root.getClass().getName().equals("PrQuadTree$PrEmpty"));
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
    private PrNode<T> insert(T x, PrNode<T> node, int maxX, int minX, int maxY,
                    int minY)
    {
        if (node == null || (root.getClass().getName()
                        .equals("PrQuadTree$PrEmpty"))) // root is null or
                                                        // internal node is null
        {
            node = new PrLeaf(x);
            return node;
        }
        else if (node.getClass().getName().equals("PrQuadTree$PrLeaf"))
        { // is a leaf node or empty
            if (((PrLeaf) node).insert(x))
            {
                return node; // if insert is successful means there was space
                             // in the current leaf node to hold the new data.
            }
            else
            {
                // nEED TO SPLIT Current code breaks the pointers.
                ArrayList<T> temp = ((PrLeaf) node).getData();
                PrEmpty northeast = new PrEmpty();
                PrEmpty northwest = new PrEmpty();
                PrEmpty southwest = new PrEmpty();
                PrEmpty southeast = new PrEmpty();
                node = new PrInternal(northeast, northwest, southwest,
                                southeast);

                for (int i = 0; i < temp.size(); i++)
                {
                    insert(temp.get(i), node, maxX, minX, maxY, minY);
                }
                insert(x, node, maxX, minX, maxY, minY);
                return node;
            }

        }
        else if (node.getClass().getName().equals("PrQuadTree$PrInternal"))
        {
            int midX = (minX + maxX) / 2;
            int midY = (minY + maxY) / 2;
            if ((x.compareToX(midX) <= 0) && (x.compareToY(midY) == -1))
            { // quadrant 2
                if (((PrInternal) node).getnW().getClass().getName()
                                .equals("PrQuadTree$PrEmpty"))
                {
                    ((PrInternal) node).setnW(new PrLeaf(x));
                }
                else
                {
                    ((PrInternal) node).setnW(
                                    insert(x, ((PrInternal) node).getnW(), midX,
                                                    minX, midY, minY));
                }

            }
            else if ((x.compareToX(midX) == -1) && (x.compareToY(midY) >= 0))
            { // quadrant 3
                if (((PrInternal) node).getsW().getClass().getName()
                                .equals("PrQuadTree$PrEmpty"))
                {
                    ((PrInternal) node).setsW(new PrLeaf(x));
                }
                else
                {
                    ((PrInternal) node).setsW(
                                    insert(x, ((PrInternal) node).getsW(), midX,
                                                    minX, maxY, midY));
                }

            }
            else if ((x.compareToX(midX) >= 0) && (x.compareToY(midY) == 1))
            { // quadrant 4
                if (((PrInternal) node).getsE().getClass().getName()
                                .equals("PrQuadTree$PrEmpty"))
                {
                    ((PrInternal) node).setsE(new PrLeaf(x));
                }
                else
                {
                    ((PrInternal) node).setsE(
                                    insert(x, ((PrInternal) node).getsE(), maxX,
                                                    midX, maxY, midY));
                }

            }
            else
            { // quadrant 1
                if (((PrInternal) node).getnE().getClass().getName()
                                .equals("PrQuadTree$PrEmpty"))
                {
                    ((PrInternal) node).setnE(new PrLeaf(x));
                }
                else
                {
                    ((PrInternal) node).setnE(
                                    insert(x, ((PrInternal) node).getnE(), maxX,
                                                    midX, midY, minY));
                }

            }
            return node;
        }
        else
        {
            System.out.println("Insert Fucked up");
            return node;
        }
    }

    /**
     * This method will delete
     * 
     * @param x
     *            the value to delete
     */
    public void delete(T x)
    {
        root = delete(x, root, worldXmax, worldXmin, worldYmax, worldYmin);
    }

    /**
     * This method will return the root of the current tree.
     * 
     * @return the root node
     */
    public PrNode<T> findRoot()
    {
        return root;
    }

    /**
     * This method will
     * 
     * @param x
     *            data
     * @param node
     *            node
     * @param maxX
     *            x
     * @param minX
     *            x
     * @param maxY
     *            y
     * @param minY
     *            y
     * @return
     */
    private PrNode<T> delete(T x, PrNode<T> node, int maxX, int minX, int maxY,
                    int minY)
    {
        PrNode<T> check = node;
        if (node == null)
        {
            return node;
        }
        else if (node.getClass().getName().equals("PrQuadTree$PrLeaf"))
        {
            check = ((PrLeaf) node).remove(x);
        }
        else if (node.getClass().getName().equals("PrQuadTree$PrInternal"))
        {
            int midX = (minX + maxX) / 2;
            int midY = (minY + maxY) / 2;
            // checks what quadrant the point falls into
            if ((x.compareToX(midX) <= 0) && (x.compareToY(midY) == -1))
            { // quadrant 2
                PrNode<T> holder = delete(x, ((PrInternal) node).getnW(), midX,
                                minX, midY, minY);
                ((PrInternal) node).setnW(holder);

            }
            else if ((x.compareToX(midX) == -1) && (x.compareToY(midY) >= 0))
            { // quadrant 3
                PrNode<T> holder = delete(x, ((PrInternal) node).getsW(), midX,
                                minX, maxY, midY);
                ((PrInternal) node).setsW(holder);
            }
            else if ((x.compareToX(midX) >= 0) && (x.compareToY(midY) == 1))
            { // quadrant
              // 4
                PrNode<T> holder = delete(x, ((PrInternal) node).getsE(), maxX,
                                midX, maxY, midY);
                ((PrInternal) node).setsE(holder);
            }
            else if (((x.compareToX(midX) == 0) && (x.compareToY(midY) == 0))
                            || ((x.compareToX(midX) > 0)
                                            && (x.compareToY(midY) <= 0)))
            { // quadrant
              // 1
                PrNode<T> holder = delete(x, ((PrInternal) node).getnE(), maxX,
                                midX, midY, minY);
                ((PrInternal) node).setnE(holder);
            }
            if (check != null && check.getClass().equals(PrInternal.class))
            {
                PrInternal newCheck = ((PrInternal) check);
                if (newCheck.leafCheck())
                {
                    return newCheck.compress();
                }
            }

        }
        return check;
    }

    /**
     * This method will
     * 
     * @param x
     * @param node
     * @param maxX
     * @param minX
     * @param maxY
     * @param minY
     * @return
     */
    private boolean find(T x, PrNode<T> node, int maxX, int minX, int maxY,
                    int minY)
    {
        if (node == null)
        {
            return false; // Not found
        }
        else if (node.getClass().getName().equals("PrQuadTree$PrLeaf"))
        {
            ArrayList<T> temp = ((PrLeaf) node).getData();
            return (temp.contains(x));
        }
        else if (node.getClass().getName().equals("PrQuadTree$PrInternal"))
        {
            int midX = (minX + maxX) / 2;
            int midY = (minY + maxY) / 2;
            if ((x.compareToX(midX) <= 0) && (x.compareToY(midY) == -1))
            { // quadrant 2
                return find(x, ((PrInternal) node).getnW(), midX, minX, midY,
                                minY);
            }
            else if ((x.compareToX(midX) == -1) && (x.compareToY(midY) >= 0))
            { // quadrant 3
                return find(x, ((PrInternal) node).getsW(), midX, minX, maxY,
                                midY);
            }
            else if ((x.compareToX(midX) >= 0) && (x.compareToY(midY) == 1))
            { // quadrant 4
                return find(x, ((PrInternal) node).getsE(), maxX, midX, maxY,
                                midY);
            }
            else
            { // quadrant 1
                return find(x, ((PrInternal) node).getnE(), maxX, midX, midY,
                                minY);
            }
        }
        return false;
    }

    /**
     * This method will dump the tree
     */
    public void dump()
    {
        int count = 0;
        count = dump(root, worldXmax, worldXmin, worldYmax, worldYmin,
                        worldXmax, worldXmin, worldYmax, worldYmin, count, 0);

        System.out.println(
                        "QuadTree Size: " + count + " QuadTree Nodes Printed.");
    }

    /**
     * This method will
     * 
     * @param node
     * @param maxX
     * @param minX
     * @param maxY
     * @param minY
     * @param regionMaxX
     * @param regionMinX
     * @param regionMaxY
     * @param regionMinY
     * @param count
     * @param indent
     * @return
     */
    private int dump(PrNode<T> node, int maxX, int minX, int maxY, int minY,
                    int regionMaxX, int regionMinX, int regionMaxY,
                    int regionMinY, int count, int indent)
    {
        String padding = "";
        for (int i = 0; i < indent; i++)
        {
            padding = padding + "  ";
        }
        if (node == null)
        {
            return 0; // Not found
        }
        else if (node.getClass().getName().equals("PrQuadTree$PrLeaf"))
        {
            System.out.println(padding + "Node at " + minX + ", " + minY + ", "
                            + (maxX - minX) + ":");
            count++;
            ArrayList<T> temp = ((PrLeaf) node).getData();
            for (int i = 0; i < temp.size(); i++)
            {
                System.out.println(padding + temp.get(i).toString());
            }
            return count;
        }
        else if (node.getClass().getName().equals("PrQuadTree$PrInternal"))
        {
            System.out.println(padding + "Node at " + minX + ", " + minY + ", "
                            + (maxX - minX) + ": " + "Internal");
            count++;
            int midX = (minX + maxX) / 2;
            int midY = (minY + maxY) / 2;
            indent++;
            if ((regionMinX <= midX) && (regionMinY < midY))
            { // quadrant 2
                count = dump(((PrInternal) node).getnW(), midX, minX, midY,
                                minY, regionMaxX, regionMinX, regionMaxY,
                                regionMinY, count, indent);
            }

            if ((regionMaxX >= midX) && (regionMinY < midY))
            { // quadrant 1
                count = dump(((PrInternal) node).getnE(), maxX, midX, midY,
                                minY, regionMaxX, regionMinX, regionMaxY,
                                regionMinY, count, indent);
            }

            if ((regionMinX < midX) && (regionMaxY >= midY))
            { // quadrant 3
                count = dump(((PrInternal) node).getsW(), midX, minX, maxY,
                                midY, regionMaxX, regionMinX, regionMaxY,
                                regionMinY, count, indent);
            }
            if ((regionMaxX >= midX) && (regionMaxY > midY))
            { // quadrant 4
                count = dump(((PrInternal) node).getsE(), maxX, midX, maxY,
                                midY, regionMaxX, regionMinX, regionMaxY,
                                regionMinY, count, indent);
            }

        }
        else if (node.getClass().getName().equals("PrQuadTree$PrEmpty"))
        {
            System.out.println(padding + "Node at " + minX + ", " + minY + ", "
                            + (maxX - minX) + ": " + "Empty");
            count++;
        }
        return count;
    }

    /**
     * This method will do a region search
     * 
     * @param regionMaxX
     *            the max region
     * @param regionMinX
     *            the min region
     * @param regionMaxY
     *            the max region
     * @param regionMinY
     *            the min region
     */
    public void regionSearch(int regionMaxX, int regionMinX, int regionMaxY,
                    int regionMinY)
    {
        int count = 0;
        count = regionSearch(root, worldXmax, worldXmin, worldYmax, worldYmin,
                        regionMaxX, regionMinX, regionMaxY, regionMinY, count);
        System.out.println(count + " QuadTree Nodes Visited");
    }

    /**
     * This method will
     * 
     * @param node
     * @param maxX
     * @param minX
     * @param maxY
     * @param minY
     * @param regionMaxX
     * @param regionMinX
     * @param regionMaxY
     * @param regionMinY
     * @param count
     * @return
     */
    private int regionSearch(PrNode<T> node, int maxX, int minX, int maxY,
                    int minY, int regionMaxX, int regionMinX, int regionMaxY,
                    int regionMinY, int count)
    {
        count++;
        if (node == null)
        {
            return 0; // Not found
        }
        else if (node.getClass().getName().equals("PrQuadTree$PrLeaf"))
        {
            ArrayList<T> temp = ((PrLeaf) node).getData();
            for (int i = 0; i < temp.size(); i++)
            {
                if ((temp.get(i).compareToX(regionMaxX) <= 0)
                                && (temp.get(i).compareToX(regionMinX) >= 0)
                                && (temp.get(i).compareToY(regionMaxY) <= 0)
                                && (temp.get(i).compareToY(regionMinY) >= 0))
                {
                    System.out.println(temp.get(i).toString());
                }
            }
            return count;
        }
        else if (node.getClass().getName().equals("PrQuadTree$PrInternal"))
        {
            int midX = (minX + maxX) / 2;
            int midY = (minY + maxY) / 2;
            if ((regionMinX <= midX) && (regionMinY < midY))
            { // quadrant 2
                count = regionSearch(((PrInternal) node).getnW(), midX, minX,
                                midY, minY, regionMaxX, regionMinX, regionMaxY,
                                regionMinY, count);
            }
            if ((regionMinX < midX) && (regionMaxY >= midY))
            { // quadrant 3
                count = regionSearch(((PrInternal) node).getsW(), midX, minX,
                                maxY, midY, regionMaxX, regionMinX, regionMaxY,
                                regionMinY, count);
            }
            if ((regionMaxX >= midX) && (regionMaxY > midY))
            { // quadrant 4
                count = regionSearch(((PrInternal) node).getsE(), maxX, midX,
                                maxY, midY, regionMaxX, regionMinX, regionMaxY,
                                regionMinY, count);
            }
            if ((regionMaxX >= midX) && (regionMinY < midY))
            { // quadrant 1
                count = regionSearch(((PrInternal) node).getnE(), maxX, midX,
                                midY, minY, regionMaxX, regionMinX, regionMaxY,
                                regionMinY, count);
            }
        }
        return count;
    }

    /**
     * This method will print duplicates.
     */
    public void duplicateFind()
    {
        duplicateFind(root, worldXmax, worldXmin, worldYmax, worldYmin);
    }

    /**
     * This method will
     * 
     * @param node
     * @param maxX
     * @param minX
     * @param maxY
     * @param minY
     * @return
     */
    private PrNode<T> duplicateFind(PrNode<T> node, int maxX, int minX,
                    int maxY, int minY)
    {
        if (node == null)
        {
            return null;
        }
        else if (node.getClass().getName().equals("PrQuadTree$PrLeaf"))
        {
            ArrayList<T> temp = ((PrLeaf) node).getData();
            for (int i = 0; i < temp.size(); i++)
            {
                for (int j = i + 1; j < temp.size(); j++)
                {
                    if (temp.get(i).sameXY(temp.get(j)))
                    {
                        System.out.println(temp.get(i).toStringNoName());
                        return node;
                    }
                }
            }
        }
        else if (node.getClass().getName().equals("PrQuadTree$PrInternal"))
        {
            int midX = (minX + maxX) / 2;
            int midY = (minY + maxY) / 2;

            duplicateFind(((PrInternal) node).getnW(), midX, minX, midY, minY);
            duplicateFind(((PrInternal) node).getsW(), midX, minX, maxY, midY);
            duplicateFind(((PrInternal) node).getsE(), maxX, midX, maxY, midY);
            duplicateFind(((PrInternal) node).getnE(), maxX, midX, midY, minY);
        }
        return node;
    }

}
