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

    private prNode<T> root;

    @SuppressWarnings("hiding")
    abstract class prNode<T>
    {

    }

    public class prLeaf extends prNode<T>
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

        public prNode<T> remove(T x)
        {
            if (data.remove(x))
            {
                if (data.isEmpty())
                {
                    return new prEmpty();
                }
                else
                {
                    return this;
                }
            }
            return null;
        }

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

    class prInternal extends prNode<T>
    {

        private prNode<T> NW;
        private prNode<T> NE;
        private prNode<T> SW;
        private prNode<T> SE;

        prInternal(prNode<T> northWest, prNode<T> northEast,
                        prNode<T> southWest, prNode<T> southEast)
        {
            NW = northWest;
            NE = northEast;
            SW = southWest;
            SE = southEast;
        }

        public prNode<T> getNW()
        {
            return NW;
        }

        public prNode<T> getNE()
        {
            return NE;
        }

        public prNode<T> getSW()
        {
            return SW;
        }

        public prNode<T> getSE()
        {
            return SE;
        }

        public void setNW(prNode<T> value)
        {
            NW = value;
        }

        public void setNE(prNode<T> value)
        {
            NE = value;
        }

        public void setSW(prNode<T> value)
        {
            SW = value;
        }

        public void setSE(prNode<T> value)
        {
            SE = value;
        }

        public prNode<T> compress()
        {
            ArrayList<T> list = new ArrayList<T>();
            if (this.SE.getClass().equals(prLeaf.class))
            {
                ArrayList<T> temp = ((prLeaf) SE).getData();
                for (int i = 0; i < temp.size(); i++)
                {
                    list.add(temp.get(i));
                }
            }
            if (this.NE.getClass().equals(prLeaf.class))
            {
                ArrayList<T> temp = ((prLeaf) NE).getData();
                for (int i = 0; i < temp.size(); i++)
                {
                    list.add(temp.get(i));
                }
            }
            if (this.NW.getClass().equals(prLeaf.class))
            {
                ArrayList<T> temp = ((prLeaf) NW).getData();
                for (int i = 0; i < temp.size(); i++)
                {
                    list.add(temp.get(i));
                }
            }
            if (this.SW.getClass().equals(prLeaf.class))
            {
                ArrayList<T> temp = ((prLeaf) SW).getData();
                for (int i = 0; i < temp.size(); i++)
                {
                    list.add(temp.get(i));
                }
            }
            if (list.size() > 0)
            {
                prLeaf leaf = new prLeaf(list.get(0));
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
                return new prEmpty();
            }

        }

        public boolean leafCheck()
        {
            return (!this.NE.getClass().equals(prInternal.class)
                            && !this.SE.getClass().equals(prInternal.class)
                            && !this.NW.getClass().equals(prInternal.class)
                            && !this.SW.getClass().equals(prInternal.class));
        }
    }

    class prEmpty extends prNode<T>
    {
        public prEmpty()
        {
            // Purposely empty
        }
    }

    public PrQuadTree()
    {
        root = new prEmpty();
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
        return (root.getClass().getName().equals("PrQuadTree$prEmpty"));
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
    private prNode<T> insert(T x, prNode<T> node, int maxX, int minX, int maxY,
                    int minY)
    {
        if (node == null || (root.getClass().getName()
                        .equals("PrQuadTree$prEmpty"))) // root is null or
                                                        // internal node is null
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
                    insert(temp.get(i), node, maxX, minX, maxY, minY);
                }
                insert(x, node, maxX, minX, maxY, minY);
                return node;
            }

        }
        else if (node.getClass().getName().equals("PrQuadTree$prInternal"))
        {
            int midX = (minX + maxX) / 2;
            int midY = (minY + maxY) / 2;
            if ((x.compareToX(midX) <= 0) && (x.compareToY(midY) == -1))
            { // quadrant 2
                if (((prInternal) node).getNW().getClass().getName()
                                .equals("PrQuadTree$prEmpty"))
                {
                    ((prInternal) node).setNW(new prLeaf(x));
                }
                else
                {
                    ((prInternal) node).setNW(
                                    insert(x, ((prInternal) node).getNW(), midX,
                                                    minX, midY, minY));
                }

            }
            else if ((x.compareToX(midX) == -1) && (x.compareToY(midY) >= 0))
            { // quadrant 3
                if (((prInternal) node).getSW().getClass().getName()
                                .equals("PrQuadTree$prEmpty"))
                {
                    ((prInternal) node).setSW(new prLeaf(x));
                }
                else
                {
                    ((prInternal) node).setSW(
                                    insert(x, ((prInternal) node).getSW(), midX,
                                                    minX, maxY, midY));
                }

            }
            else if ((x.compareToX(midX) >= 0) && (x.compareToY(midY) == 1))
            { // quadrant 4
                if (((prInternal) node).getSE().getClass().getName()
                                .equals("PrQuadTree$prEmpty"))
                {
                    ((prInternal) node).setSE(new prLeaf(x));
                }
                else
                {
                    ((prInternal) node).setSE(
                                    insert(x, ((prInternal) node).getSE(), maxX,
                                                    midX, maxY, midY));
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
                    ((prInternal) node).setNE(
                                    insert(x, ((prInternal) node).getNE(), maxX,
                                                    midX, midY, minY));
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

    public void delete(T x)
    {
        root = delete(x, root, worldXmax, worldXmin, worldYmax, worldYmin);
    }

    /**
     * This method will return the root of the current tree.
     * 
     * @return the root node
     */
    public prNode<T> findRoot()
    {
        return root;
    }

    private prNode<T> delete(T x, prNode<T> node, int maxX, int minX, int maxY,
                    int minY)
    {
        prNode<T> check = node;
        if (node == null)
        {
            return node;
        }
        else if (node.getClass().getName().equals("PrQuadTree$prLeaf"))
        {
            check = ((prLeaf) node).remove(x);
        }
        else if (node.getClass().getName().equals("PrQuadTree$prInternal"))
        {
            int midX = (minX + maxX) / 2;
            int midY = (minY + maxY) / 2;
            // checks what quadrant the point falls into
            if ((x.compareToX(midX) <= 0) && (x.compareToY(midY) == -1))
            { // quadrant 2
                prNode<T> holder = delete(x, ((prInternal) node).getNW(), midX,
                                minX, midY, minY);
                ((prInternal) node).setNW(holder);

            }
            else if ((x.compareToX(midX) == -1) && (x.compareToY(midY) >= 0))
            { // quadrant 3
                prNode<T> holder = delete(x, ((prInternal) node).getSW(), midX,
                                minX, maxY, midY);
                ((prInternal) node).setSW(holder);
            }
            else if ((x.compareToX(midX) >= 0) && (x.compareToY(midY) == 1))
            { // quadrant
              // 4
                prNode<T> holder = delete(x, ((prInternal) node).getSE(), maxX,
                                midX, maxY, midY);
                ((prInternal) node).setSE(holder);
            }
            else if (((x.compareToX(midX) == 0) && (x.compareToY(midY) == 0))
                            || ((x.compareToX(midX) > 0)
                                            && (x.compareToY(midY) <= 0)))
            { // quadrant
              // 1
                prNode<T> holder = delete(x, ((prInternal) node).getNE(), maxX,
                                midX, midY, minY);
                ((prInternal) node).setNE(holder);
            }
            if (check != null && check.getClass().equals(prInternal.class))
            {
                prInternal newCheck = ((prInternal) check);
                if (newCheck.leafCheck())
                {
                    return newCheck.compress();
                }
            }

        }
        return check;
    }

    private boolean find(T x, prNode<T> node, int maxX, int minX, int maxY,
                    int minY)
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
            int midX = (minX + maxX) / 2;
            int midY = (minY + maxY) / 2;
            if ((x.compareToX(midX) <= 0) && (x.compareToY(midY) == -1))
            { // quadrant 2
                return find(x, ((prInternal) node).getNW(), midX, minX, midY,
                                minY);
            }
            else if ((x.compareToX(midX) == -1) && (x.compareToY(midY) >= 0))
            { // quadrant 3
                return find(x, ((prInternal) node).getSW(), midX, minX, maxY,
                                midY);
            }
            else if ((x.compareToX(midX) >= 0) && (x.compareToY(midY) == 1))
            { // quadrant 4
                return find(x, ((prInternal) node).getSE(), maxX, midX, maxY,
                                midY);
            }
            else
            { // quadrant 1
                return find(x, ((prInternal) node).getNE(), maxX, midX, midY,
                                minY);
            }
        }
        return false;
    }

    public void dump()
    {
        int count = 0;
        count = dump(root, worldXmax, worldXmin, worldYmax, worldYmin,
                        worldXmax, worldXmin, worldYmax, worldYmin, count, 0);

        System.out.println(
                        "QuadTree Size: " + count + " QuadTree Nodes Printed.");
    }

    private int dump(prNode<T> node, int maxX, int minX, int maxY, int minY,
                    int regionMax_x, int regionMin_x, int regionMax_y,
                    int regionMin_y, int count, int indent)
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
        else if (node.getClass().getName().equals("PrQuadTree$prLeaf"))
        {
            System.out.println(padding + "Node at " + minX + ", " + minY + ", "
                            + (maxX - minX) + ":");
            count++;
            ArrayList<T> temp = ((prLeaf) node).getData();
            for (int i = 0; i < temp.size(); i++)
            {
                System.out.println(padding + temp.get(i).toString());
            }
            return count;
        }
        else if (node.getClass().getName().equals("PrQuadTree$prInternal"))
        {
            System.out.println(padding + "Node at " + minX + ", " + minY + ", "
                            + (maxX - minX) + ": " + "Internal");
            count++;
            int midX = (minX + maxX) / 2;
            int midY = (minY + maxY) / 2;
            indent++;
            if ((regionMin_x <= midX) && (regionMin_y < midY))
            { // quadrant 2
                count = dump(((prInternal) node).getNW(), midX, minX, midY,
                                minY, regionMax_x, regionMin_x, regionMax_y,
                                regionMin_y, count, indent);
            }

            if ((regionMax_x >= midX) && (regionMin_y < midY))
            { // quadrant 1
                count = dump(((prInternal) node).getNE(), maxX, midX, midY,
                                minY, regionMax_x, regionMin_x, regionMax_y,
                                regionMin_y, count, indent);
            }

            if ((regionMin_x < midX) && (regionMax_y >= midY))
            { // quadrant 3
                count = dump(((prInternal) node).getSW(), midX, minX, maxY,
                                midY, regionMax_x, regionMin_x, regionMax_y,
                                regionMin_y, count, indent);
            }
            if ((regionMax_x >= midX) && (regionMax_y > midY))
            { // quadrant 4
                count = dump(((prInternal) node).getSE(), maxX, midX, maxY,
                                midY, regionMax_x, regionMin_x, regionMax_y,
                                regionMin_y, count, indent);
            }

        }
        else if (node.getClass().getName().equals("PrQuadTree$prEmpty"))
        {
            System.out.println(padding + "Node at " + minX + ", " + minY + ", "
                            + (maxX - minX) + ": " + "Empty");
            count++;
        }
        return count;
    }

    public void regionSearch(int regionMax_x, int regionMin_x, int regionMax_y,
                    int regionMin_y)
    {
        int count = 0;
        count = regionSearch(root, worldXmax, worldXmin, worldYmax, worldYmin,
                        regionMax_x, regionMin_x, regionMax_y, regionMin_y,
                        count);
        System.out.println(count + " QuadTree Nodes Visited");
    }

    private int regionSearch(prNode<T> node, int maxX, int minX, int maxY,
                    int minY, int regionMax_x, int regionMin_x, int regionMax_y,
                    int regionMin_y, int count)
    {
        count++;
        if (node == null)
        {
            return 0; // Not found
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
            return count;
        }
        else if (node.getClass().getName().equals("PrQuadTree$prInternal"))
        {
            int midX = (minX + maxX) / 2;
            int midY = (minY + maxY) / 2;
            if ((regionMin_x <= midX) && (regionMin_y < midY))
            { // quadrant 2
                count = regionSearch(((prInternal) node).getNW(), midX, minX,
                                midY, minY, regionMax_x, regionMin_x,
                                regionMax_y, regionMin_y, count);
            }
            if ((regionMin_x < midX) && (regionMax_y >= midY))
            { // quadrant 3
                count = regionSearch(((prInternal) node).getSW(), midX, minX,
                                maxY, midY, regionMax_x, regionMin_x,
                                regionMax_y, regionMin_y, count);
            }
            if ((regionMax_x >= midX) && (regionMax_y > midY))
            { // quadrant 4
                count = regionSearch(((prInternal) node).getSE(), maxX, midX,
                                maxY, midY, regionMax_x, regionMin_x,
                                regionMax_y, regionMin_y, count);
            }
            if ((regionMax_x >= midX) && (regionMin_y < midY))
            { // quadrant 1
                count = regionSearch(((prInternal) node).getNE(), maxX, midX,
                                midY, minY, regionMax_x, regionMin_x,
                                regionMax_y, regionMin_y, count);
            }
        }
        return count;
    }

    public void duplicateFind()
    {
        duplicateFind(root, worldXmax, worldXmin, worldYmax, worldYmin);
    }

    private prNode<T> duplicateFind(prNode<T> node, int maxX, int minX,
                    int maxY, int minY)
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
                    if (temp.get(i).sameXY(temp.get(j)))
                    {
                        System.out.println(temp.get(i).toStringNoName());
                        return node;
                    }
                }
            }
        }
        else if (node.getClass().getName().equals("PrQuadTree$prInternal"))
        {
            int midX = (minX + maxX) / 2;
            int midY = (minY + maxY) / 2;

            duplicateFind(((prInternal) node).getNW(), midX, minX, midY, minY);
            duplicateFind(((prInternal) node).getSW(), midX, minX, maxY, midY);
            duplicateFind(((prInternal) node).getSE(), maxX, midX, maxY, midY);
            duplicateFind(((prInternal) node).getNE(), maxX, midX, midY, minY);
        }
        return node;
    }

}
