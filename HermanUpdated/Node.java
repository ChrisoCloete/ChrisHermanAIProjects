//u13037618 Herman Keuris
//u13029721 Chris Cloete

package project;

import java.util.LinkedList;

class Node
{
    //class: This class stores the information of a player's piece
    class coordinates
    {
        //class: This class stores cell co-ordinates
	private int row;
	private int col;
	public coordinates(int r, int c)
	{
            row = r;
            col = c;
	}
    }
    //used to send coordinate values
    private coordinates co;
    //Useful variable
    private int min = 0;
    //Useful variable
    private int max = 0;
    //A linked list of the cells around the player's piece which form the piece's rectangle (personal area)
    public LinkedList<Cell> myCells;
    //Variable that stores the number of other player pieces in this piece's rectangle (used by various functions in the Player class)
    public int groupSize = 1;
    //boolean to show if the node has been checked, used by the Player class's setGroupSize function
    public boolean checked;
    
    public Node(int row, int col, int m)
    {
        //constructor: creates a node with the supplied coordinates (row and col) which will be within a range (max)
        
        //initialise variables
        max = m;
        co = new coordinates(row, col);
        checked = false;
        myCells = new LinkedList<>();
        
        //check if the proposed cell is within the range, if it is then create it
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
        //function: returns the piece's column coordinate
        return co.col;
    }

    public int getRow()
    {
         //function: returns the piece's row coordinate
        return co.row;
    }
    
    public void setCol(int _c)
    {
        //function: sets the piece's column coordinate
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
        //function: sets the piece's row coordinate
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