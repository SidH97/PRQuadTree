import java.util.Iterator;

import prQuadTree.PRnode;
import prQuadTree.prInternal;
import prQuadTree.prLeaf;

/**
 * 
 * @author Matthew Evans (matce93)
 * @author Siddharth Hingorani (sid97)
 */
public class prQuadTree <T extends Comparable<T>> implements Iterable<T>
{
	abstract class PRnode<T> {

	}


	class prLeaf extends PRnode { 
		
		private T data;
//		private int yMin;
//		private int yMax;
//		private int xMin;
//		private int xMax;
		
		prLeaf(T x)
		{
			data = x;
		}
		
		public T getData()
		{
			return data;
		}
		
		public void setData(T theData)
		{
			data = theData;
		}
		
//		public void setYMin(int y)
//		{
//			yMin = y;
//		}
//		
//		public int getYMin() 
//		{
//			return yMin;
//		}
//		
//		public void setYMax(int y)
//		{
//			yMax = y;
//		}
//		
//		public int getYMax()
//		{
//			return yMax;
//		}
//		
//		public void setXMin(int x)
//		{
//			xMin = x;
//		}
//		
//		public int getXMin()
//		{
//			return xMin;
//		}
//		
//		public void setXMax(int x)
//		{
//			xMax = x;
//		}
//		
//		public int getXMax()
//		{
//			return xMax;
//		}
	}


	class prInternal extends PRnode {

		private PRnode<T> NW;
		private PRnode<T> NE;
		private PRnode<T> SW;
		private PRnode<T> SE;
//		private int yMin;
//		private int yMax;
//		private int xMin;
//		private int xMax;
		
		prInternal(PRnode<T> northEast, PRnode<T> northWest, PRnode<T> southWest, PRnode<T> southEast)
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
		
//		public void setYMin(int y)
//		{
//			yMin = y;
//		}
//		
//		public int getYMin() 
//		{
//			return yMin;
//		}
//		
//		public void setYMax(int y)
//		{
//			yMax = y;
//		}
//		
//		public int getYMax()
//		{
//			return yMax;
//		}
//		
//		public void setXMin(int x)
//		{
//			xMin = x;
//		}
//		
//		public int getXMin()
//		{
//			return xMin;
//		}
//		
//		public void setXMax(int x)
//		{
//			xMax = x;
//		}
//		
//		public int getXMax()
//		{
//			return xMax;
//		}
		
		
	}

	private PRnode<T> root;
	
	public prQuadTree()
	{
		root = null;
//		root = new prLeaf(null);
//		((prLeaf) root).setYMax(1024);
//		((prLeaf) root).setXMax(1024);
//		((prLeaf) root).setYMin(0);
//		((prLeaf) root).setYMin(0);
	}
	
    public void insert(T x)
    {
        root = insert(x, root, 1024, 0, 1024, 0);
    }
	
    /**
     * this method inserts data into the PR qradtree
     * this method assumes that the item is within the world
     * @param x object to be inserted
     * @param node
     * @return 
     */
	@SuppressWarnings("unchecked")
	private PRnode<T> insert(T x, PRnode<T> node, int max_x, int min_x, int max_y, int min_y)
	{
		if(node == null) {   //root is null or internal node is null
			return new prLeaf(x);
		} else if(node.getClass().getName().equals("PRnode$prLeaf")) { //is a leaf node
			if(((prLeaf) node).getData().compareTo(x) == 0) {  //this is a duplicate, also may not work!!!
				//add another leaf node
				return new prLeaf(x);  //this maybe wrong
			} else {  //not equal
				//while(){}
				int yMid = (max_y + min_y)/2;
				int xMid = (max_x + min_x)/2;
				//needs to be a while loop that loops until the two leafs are added to different quardrant within a internal node
			}
		} else if(node.getClass().getName().equals("PRnode$prInternal")){  //is an internal node
			
		}
		//should never get here
		System.out.println("node.getClass().getName().equals(...) does not work!!!");
		return null;
	}
	
	private PRnode<T> spittersRquiters(T x, prLeaf node, int max_x, int min_x, int max_y, int min_y)  //also known as splitter
	{
		prLeaf northeast = new prLeaf(null);
		prLeaf northwest = new prLeaf(null);
		prLeaf southwest = new prLeaf(null);
		prLeaf southeast = new prLeaf(null);
		prInternal returnNode = new prInternal(northeast, northwest, southwest, southeast);
		if(node.getData())
		return null;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
