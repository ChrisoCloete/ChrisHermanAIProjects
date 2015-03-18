
class Cell
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
    
    public Cell(int r, int c)
    {
        co = new coordinates(r,c);
    }
    
    public int getCol()
    {
        return co.col;
    }
    
    public int getRow()
    {
        return co.row;
    }
}