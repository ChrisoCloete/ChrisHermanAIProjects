//u13037618 Herman Keuris
//u13029721 Chris Cloete

package project;
class Cell
{
    //class: The cell class provides functionality for the individual cells of the game's grid
    
    class coordinates
    {
        //class: This class stores the cell's co-ordinates
        
	private int row;
	private int col;
	public coordinates(int r, int c)
	{
            row = r;
            col = c;
	}
    }
    //The coordinates of the cell's position
    private coordinates co;
    
    public Cell(int r, int c)
    {
        //constructor: saves the cell's coordinates
        co = new coordinates(r,c);
    }
    
    public int getCol()
    {
        //function: returns the cell's column number
        return co.col;
    }
    
    public int getRow()
    {
        //function: returns the cell's row number
        return co.row;
    }
}