package project;
import java.util.LinkedList;

class Node
{
    class coordinates
    {
	private int row;
	private int col;
	public coordinates(int r, int c)
	{
            row = r;
            col = c;
	}
    }
    private coordinates co;
    private int min = 0;
    private int max = 0;
    public LinkedList<Cell> myCells;
    //public LinkedList<Node> groupMembers;
    public int groupSize = 1;
    public boolean checked;
    
    public Node(int row, int col, int m)
    {
        max = m;
        co = new coordinates(row, col);
        //groupMembers = new LinkedList<>();
        checked = false;
        myCells = new LinkedList<>();
        if(col - 1 >= min)
        {
            myCells.add(new Cell(row, col-1));
            if(row - 1 >= min)
            {
                myCells.add(new Cell(row-1, col-1));  
            }
            if(row + 1 <= max)
            {
                myCells.add(new Cell(row+1, col-1)); 
            }
        }
        if(row-1 >= min)
        {
            myCells.add(new Cell(row-1, col)); 
        }
        if(row+1 <= max)
        {
            myCells.add(new Cell(row+1, col));
        }
        if(col + 1 <= max)
        {
            myCells.add(new Cell(row, col+1)); 
            if(row - 1 >= min)
            {
                myCells.add(new Cell(row-1, col+1));
            }
            if(row + 1 <= max)
            {
                myCells.add(new Cell(row+1, col+1)); 
            }
        }
    }
    
    public int getCol()
    {
        return co.col;
    }

    public int getRow()
    {
        return co.row;
    }
    
    public void setCol(int _c)
    {
        myCells.clear();
        co.col = _c;
        if(co.col - 1 >= min)
        {
            myCells.add(new Cell(co.row, co.col-1));
            if(co.row - 1 >= min)
            {
                myCells.add(new Cell(co.row-1, co.col-1));  
            }
            if(co.row + 1 <= max)
            {
                myCells.add(new Cell(co.row+1, co.col-1)); 
            }
        }
        if(co.row-1 >= min)
        {
            myCells.add(new Cell(co.row-1, co.col)); 
        }
        if(co.row+1 <= max)
        {
            myCells.add(new Cell(co.row+1, co.col));
        }
        if(co.col + 1 <= max)
        {
            myCells.add(new Cell(co.row, co.col+1)); 
            if(co.row - 1 >= min)
            {
                myCells.add(new Cell(co.row-1, co.col+1));
            }
            if(co.row + 1 <= max)
            {
                myCells.add(new Cell(co.row+1, co.col+1)); 
            }
        }
    }

    public void setRow(int _r)
    {
        myCells.clear();
        co.row = _r;
        if(co.col - 1 >= min)
        {
            myCells.add(new Cell(co.row, co.col-1));
            if(co.row - 1 >= min)
            {
                myCells.add(new Cell(co.row-1, co.col-1));  
            }
            if(co.row + 1 <= max)
            {
                myCells.add(new Cell(co.row+1, co.col-1)); 
            }
        }
        if(co.row-1 >= min)
        {
            myCells.add(new Cell(co.row-1, co.col)); 
        }
        if(co.row+1 <= max)
        {
            myCells.add(new Cell(co.row+1, co.col));
        }
        if(co.col + 1 <= max)
        {
            myCells.add(new Cell(co.row, co.col+1)); 
            if(co.row - 1 >= min)
            {
                myCells.add(new Cell(co.row-1, co.col+1));
            }
            if(co.row + 1 <= max)
            {
                myCells.add(new Cell(co.row+1, co.col+1)); 
            }
        }
    }
}