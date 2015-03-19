
import java.util.LinkedList;

class Player
{
    private int playerNum;
    private int min;
    private int max;
    private int gridSize;
    public LinkedList<Node> nodes;
    public LinkedList<Node> groupMembers;
    public LinkedList<Cell> tempCells;
    public LinkedList<Cell> checked;
    
    public Player(int pNum, int maxNodes, int gSize)
    {
        playerNum = pNum;
        gridSize = gSize;
        nodes = new LinkedList<>();
        tempCells = new LinkedList<>();
        checked = new LinkedList<>();

        groupMembers = new LinkedList<>();
        
        min = 0;
        max = gridSize - 1;
        
        while (nodes.size() != maxNodes)
        { 
            int randomCols;
            int randomRows;
            if (playerNum == 1)
            {
                randomCols = (int) (Math.random() * ((gridSize/2 - 1) - 0)) + 0;
                randomRows = (int) (Math.random() * (gridSize - 0)) + 0;
            }
            else
            {
                randomCols = (int) (Math.random() * (gridSize - (gridSize/2 + 1))) + (gridSize/2 + 1);
                randomRows = (int) (Math.random() * (gridSize) - 0) + 0;
            }
            
            Node tmp = new Node(randomRows, randomCols, gridSize - 1);
 
            boolean tempBool = true;
            for (int i = 0; i < nodes.size(); i++)
            {
                if((nodes.get(i).getRow() == randomRows) && (nodes.get(i).getRow() == randomRows))
                {
                    tempBool = false;
                }
            }
            if (tempBool)
            {
                nodes.add(tmp);
                for (int o = 0; o < tmp.myCells.size();o++)
                {
                    tempCells.add(new Cell(tmp.myCells.get(o).getRow(),tmp.myCells.get(o).getCol()));
                }
            }
        }
        updateCells(null);
    }
    public int[] getValidMoves(String type, int index, Player other)
    {
        int distance = nodes.get(index).groupSize;
        int row = nodes.get(index).getRow();
        int col = nodes.get(index).getCol();
        int count = 0;
        int []temp = new int[distance];
        int []answer;
        boolean tempBool;
        
        if (type.equals("top"))
        {
            System.out.println("top :" + distance);
            for (int j = row - 1; j > row - distance - 1; j--)
            {
                tempBool = true;
                for (int i =0; i < nodes.size(); i++)
                {
                    if ((nodes.get(i).getRow() == j) && (nodes.get(i).getCol() == col))
                    {
                        tempBool = false;
                    }
                }
                for (int i =0; i < other.nodes.size(); i++)
                {
                    if ((other.nodes.get(i).getRow() == j) && (other.nodes.get(i).getCol() == col))
                    {
                        tempBool = false;
                    }
                }
                if (tempBool && j >= 0)
                {
                    temp[count] = j;
                    ++count;
                }
            }

            answer = new int[count];

            for (int i = 0; i < count; i++)
            {
                answer[i] = temp[i];
            }

            return answer;
        } else if (type.equals("bottom"))
        {
            System.out.println("bottom :" + distance);
            for (int j = row + 1; j < row + distance + 1; j++)
            {
                tempBool = true;
                for (int i =0; i < nodes.size(); i++)
                {
                    if ((nodes.get(i).getRow() == j) && (nodes.get(i).getCol() == col))
                    {
                        tempBool = false;
                    }
                }
                for (int i =0; i < other.nodes.size(); i++)
                {
                    if ((other.nodes.get(i).getRow() == j) && (other.nodes.get(i).getCol() == col))
                    {
                        tempBool = false;
                    }
                }
                if (tempBool && j <= max)
                {
                    temp[count] = j;
                    ++count;
                }
            }

            answer = new int[count];

            for (int i = 0; i < count; i++)
            {
                answer[i] = temp[i];
            }

            return answer;
        } else if (type.equals("left"))
        {
            System.out.println("left :" + distance);
            for (int j = col - 1; j > col - distance - 1; j--)
            {
                tempBool = true;
                for (int i =0; i < nodes.size(); i++)
                {
                    if ((nodes.get(i).getCol() == j) && (nodes.get(i).getRow() == row))
                    {
                        tempBool = false;
                    }
                }
                for (int i =0; i < other.nodes.size(); i++)
                {
                    if ((other.nodes.get(i).getCol() == j) && (other.nodes.get(i).getRow() == row))
                    {
                        tempBool = false;
                    }
                }
                if (tempBool && j >= 0)
                {
                    temp[count] = j;
                    ++count;
                }
            }

            answer = new int[count];

            for (int i = 0; i < count; i++)
            {
                answer[i] = temp[i];
            }

            return answer;
        }
        else if (type.equals("right"))
        {
            System.out.println("right :" + distance);
            for (int j = col + 1; j < col + distance + 1; j++)
            {
                tempBool = true;
                for (int i =0; i < nodes.size(); i++)
                {
                    if ((nodes.get(i).getCol() == j) && (nodes.get(i).getRow() == row))
                    {
                        tempBool = false;
                    }
                }
                for (int i =0; i < other.nodes.size(); i++)
                {
                    if ((other.nodes.get(i).getCol() == j) && (other.nodes.get(i).getRow() == row))
                    {
                        tempBool = false;
                    }
                }
                if (tempBool && j < max)
                {
                    temp[count] = j;
                    ++count;
                }
            }

            answer = new int[count];

            for (int i = 0; i < count; i++)
            {
                answer[i] = temp[i];
            }

            return answer;
        }
        return new int[0];
    }
    public void updateCells(Player other)
    {
        int rectLengthStart = 0;
        int rectLengthEnd = 0;
        int rectWidthStart = 0;
        int rectWidthEnd = 0;
        boolean tempBool = false;
        tempCells = new LinkedList<>();

        for (int i = 0; i < nodes.size() - 1; i++)
        {
            tempCells.add(new Cell((nodes.get(i).getRow()), (nodes.get(i).getCol())));
            for (int j = 0; j < nodes.get(i).myCells.size();j++)
            {
                tempCells.add(new Cell((nodes.get(i).myCells.get(j).getRow()), (nodes.get(i).myCells.get(j).getCol())));
            }
        }
        
        /*if(other != null)
        {
            for (int i = 0; i < this.tempCells.size(); i++)
                for (int j = 0; j < other.tempCells.size(); j++)
                {
                    if ((this.tempCells.get(i).getRow() == other.tempCells.get(j).getRow()) && (this.tempCells.get(i).getCol() == other.tempCells.get(j).getCol()))
                    {
                        for (int k = 0; k < other.nodes.size() - 1; k++)
                            for (int m = 0; m < other.nodes.get(k).myCells.size(); m++)
                            {
                                if ((other.tempCells.get(j).getRow() == other.nodes.get(k).myCells.get(m).getRow()) && (other.tempCells.get(j).getCol() == other.nodes.get(k).myCells.get(m).getCol()))
                                {
                                    this.nodes.add(other.nodes.remove(k));
                                }
                            }
                    }
                }
        }*/
        
        for (int i = 0; i < nodes.size() - 1; i++)
        {
            for (int j = i + 1; j < nodes.size(); j++)
            {
                tempBool = false;
                if (Math.abs(nodes.get(i).getCol() - nodes.get(j).getCol()) <= 3)
                {
                    if (Math.abs(nodes.get(i).getRow() - nodes.get(j).getRow()) <= 3)
                    {
                        tempBool = true;
                        
                        if (nodes.get(i).getCol() < nodes.get(j).getCol())
                        {
                            rectWidthStart = nodes.get(i).getCol() - 1;
                            rectWidthEnd = nodes.get(j).getCol() + 1;
                        }
                        else
                        {
                            rectWidthStart = nodes.get(j).getCol() - 1;
                            rectWidthEnd = nodes.get(i).getCol() + 1;
                        }
                        
                        if (nodes.get(i).getRow() < nodes.get(j).getRow())
                        {
                            rectLengthStart = nodes.get(i).getRow() - 1;
                            rectLengthEnd = nodes.get(j).getRow() + 1;
                        }
                        else
                        {
                            rectLengthStart = nodes.get(j).getRow() - 1;
                            rectLengthEnd = nodes.get(i).getRow() + 1;
                        }
                        
                        for (int k = 0; k < max; k++)
                        {
                            for (int l = 0; l < max; l++)
                            {
                                for (int m = 0; m < nodes.size();m++)
                                {
                                    if (nodes.get(m).getRow() == k && nodes.get(m).getCol() == l)
                                    {
                                        for (int n = 0; n < nodes.get(m).myCells.size();n++)
                                        {
                                            tempCells.add(new Cell((nodes.get(m).myCells.get(n).getRow()), (nodes.get(m).myCells.get(n).getCol())));
                                        }
                                    }
                                }
                            }
                        }
                        
                        if (tempBool)
                        {
                            tempBool = false;
                            for (int row = rectLengthStart; row <= rectLengthEnd; row++)
                            {
                                for (int col = rectWidthStart; col <= rectWidthEnd; col++)
                                {
                                    int min = 0;

                                    if(col >= min && col <= max && row >= min && row <= max)
                                    {
                                        tempCells.add(new Cell(row, col));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        if (!(tempCells.isEmpty()))
        {
            this.fixGrid();
            //System.out.println("outWhile");
        }
        
        /*for (int i = 0; i < nodes.size() - 1; i++)
        {
            tempCells.add(new Cell((nodes.get(i).getRow()), (nodes.get(i).getCol())));
            for (int j = 0; j < nodes.get(i).myCells.size();j++)
            {
                tempCells.add(new Cell((nodes.get(i).myCells.get(j).getRow()), (nodes.get(i).myCells.get(j).getCol())));
            }
        }*/
        
        for (int i = 0; i < nodes.size(); i++)
        {
            nodes.get(i).checked = false;
        }
        
        if (!groupMembers.isEmpty())
            groupMembers.clear();
        
        int count = 1;
        int leftBound = 0;
        int rightBound = 0;
        int topBound = 0;
        int bottomBound = 0;
        for (int i = 0; i < nodes.size();i++)
        {
            if (!nodes.get(i).checked)
            {
                nodes.get(i).checked = true;
                groupMembers.add(nodes.get(i));
                leftBound = nodes.get(i).getCol() - 1;
                if (leftBound < 0)
                    leftBound = 0;
                while(leftBound > 0 && isTempCell(nodes.get(i).getRow(), leftBound))
                {
                    int j = isNode(nodes.get(i).getRow(), leftBound);
                    if(j > -1 && !nodes.get(j).checked)
                    {
                        nodes.get(j).checked = true;
                        groupMembers.add(nodes.get(j));
                        ++count;
                    }
                    --leftBound;
                }
                if(leftBound != 0)
                {
                    ++leftBound;
                }
                topBound = nodes.get(i).getRow() - 1;
                if (topBound < 0)
                    topBound = 0;
                while(topBound > 0 && isTempCell(topBound, nodes.get(i).getCol()))
                {
                    int k = isNode(topBound, nodes.get(i).getCol());
                    if(k > -1 && !nodes.get(k).checked)
                    {
                        nodes.get(k).checked = true;
                        groupMembers.add(nodes.get(k));
                        ++count;
                    }
                    --topBound;
                }
                if(topBound != 0)
                {
                    ++topBound;
                }

                rightBound = nodes.get(i).getCol() + 1;
                if (rightBound > max)
                    rightBound = max;
                while(rightBound < max && isTempCell(nodes.get(i).getRow(), rightBound))
                {
                    int j = isNode(nodes.get(i).getRow(), rightBound);
                    if(j > -1 && !nodes.get(j).checked)
                    {
                        nodes.get(j).checked = true;
                        groupMembers.add(nodes.get(j));
                        ++count;
                    }
                    --rightBound;
                }
                if(rightBound != max)
                {
                    ++rightBound;
                }
                bottomBound = nodes.get(i).getRow() + 1;
                if (bottomBound > max)
                    bottomBound = max;
                while(bottomBound < max && isTempCell(bottomBound, nodes.get(i).getCol()))
                {
                    int k = isNode(bottomBound, nodes.get(i).getCol());
                    if(k > -1 && !nodes.get(k).checked)
                    {
                        nodes.get(k).checked = true;
                        groupMembers.add(nodes.get(k));
                        ++count;
                    }
                    --bottomBound;
                }
                if(bottomBound != max)
                {
                    ++bottomBound;
                }
                if (allNodesChecked())
                {
                    for (int z = 0; z < groupMembers.size();z++)
                    {
                        groupMembers.get(z).groupSize = count;
                    }
                }
                else
                {
                    boolean end = false;
                    for (int a = topBound; a <= bottomBound; a++)
                    {
                        if (end)
                            break;
                        for (int b = leftBound; b <= rightBound; b++)
                        {
                            int index = isNode(b,a);
                            if(index > -1 && !nodes.get(index).checked)
                            {
                                nodes.get(index).checked = true;
                                groupMembers.add(nodes.get(index));
                                ++count;
                            }
                            if (allNodesChecked())
                            {
                                for (int z = 0; z < groupMembers.size();z++)
                                {
                                    groupMembers.get(z).groupSize = count;
                                    end = true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void fixGrid()
    {
        boolean again = true;
            //System.out.println("while");
            while(again)
            {
                for (int i = 0; i <= max; i++)
                {
                    for (int j = 0; j <= max; j++)
                    {
                        int x = 0;
                        int y = 0;
                        boolean found = false;
                        for(int m = 0; m < tempCells.size(); m++)
                        {
                            if(tempCells.get(m).getRow() == i && tempCells.get(m).getCol() == (j))
                            {
                                found = true;
                                break;
                            }
                        }
                        if(!found)
                        {
                                if(j!=0)
                                {
                                    for(int k = 0; k < tempCells.size(); k++)
                                    {
                                        if(tempCells.get(k).getRow() == i && tempCells.get(k).getCol() == (j-1))
                                        {
                                            x++;
                                            //System.out.println("counter:  " + i + "   " + j + "  " +"left");
                                            break;
                                        }
                                    }
                                }
                                if(j!=max)
                                {
                                    for(int k = 0; k < tempCells.size(); k++)
                                    {
                                        if(tempCells.get(k).getRow() == i && tempCells.get(k).getCol() == (j+1))
                                        {
                                            x++;
                                            //System.out.println("counter:  " + i + "   " + j + "  " +"right");
                                            break;
                                        }
                                    }
                                }
                                if(i!=0)
                                {
                                    for(int k = 0; k < tempCells.size(); k++)
                                    {
                                        if(tempCells.get(k).getRow() == (i-1) && tempCells.get(k).getCol() == j)
                                        {
                                            y++;
                                            //System.out.println("counter:  " + i + "   " + j + "  " +"up");
                                            break;
                                        }
                                    }
                                }
                                if(i!=max)
                                {
                                    for(int k = 0; k < tempCells.size(); k++)
                                    {
                                        if(tempCells.get(k).getRow() == (i+1) && tempCells.get(k).getCol() == j)
                                        {
                                            y++;
                                            //System.out.println("counter:  " + i + "   " + j + "  " +"down");
                                            break;
                                        }
                                    }
                                }
                                again = false;
                                //System.out.println("counter:  " + i + "   " + j + "  x" + x + "  y"  + y);    
                                if(x >=1 && y >=1)
                                {
                                    
                                    tempCells.add(new Cell(i, j));
                                    i=max;
                                    j=max;
                                    again = true;
                                }
                        }
                    }
                }
                //System.out.println("restart");
            }
    }
    
    private boolean isTempCell(int row, int col)
    {
        for (int i = 0; i < tempCells.size(); i++)
        {
            if (tempCells.get(i).getRow() == row && tempCells.get(i).getRow() == col)
                return true;
        }
        return false;
    }
    
    private int isNode(int row, int col)
    {
        for (int i = 0; i < nodes.size(); i++)
        {
            if (nodes.get(i).getRow() == row && nodes.get(i).getRow() == col)
                return i;
        }
        return -1;
    }
    
    private boolean allNodesChecked()
    {
        for (int i = 0; i < nodes.size(); i++)
        {
            if (!nodes.get(i).checked)
                return false;
        }
        return true;
    }
    
    public void underAttack(Player opp)
    {
        boolean again = true;
        //System.out.println("lets see");
        while(again)
        {
            //System.err.println("again");
            again = false;
            for(int a = 0; a < nodes.size();a++)
            {
           // System.out.print(".");
                if(opp.checkMyCells(nodes.get(a).getRow(), nodes.get(a).getCol()))
                {
                    //give up this cell
                            //remove from my list
                            //add to opp
                   // System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>shit");
                    opp.getNode(nodes.get(a).getRow(), nodes.get(a).getCol());
                    this.removeNode(a);
                    this.fixGrid();
                    opp.fixGrid();
                    again = true;
                    break;
                }
                if(opp.checkMyCells(nodes.get(a).getRow(), nodes.get(a).getCol()-1))
                {
                    //give up this cell
                            //remove from my list
                            //add to opp
                    opp.getNode(nodes.get(a).getRow(), nodes.get(a).getCol());
                    this.removeNode(a);
                    //this.fixGrid();
                    //opp.fixGrid();
                    this.updateCells(opp);
                    opp.updateCells(this);
                    again = true;
                    break;
                }
                if(opp.checkMyCells(nodes.get(a).getRow(), nodes.get(a).getCol()+1))
                {
                    //give up this cell
                            //remove from my list
                            //add to opp
                    opp.getNode(nodes.get(a).getRow(), nodes.get(a).getCol());
                    this.removeNode(a);
                    //this.fixGrid();
                    //opp.fixGrid();
                    this.updateCells(opp);
                    opp.updateCells(this);
                    again = true;
                    break;
                }
                if(opp.checkMyCells(nodes.get(a).getRow()-1, nodes.get(a).getCol()))
                {
                    //give up this cell
                            //remove from my list
                            //add to opp
                    opp.getNode(nodes.get(a).getRow(), nodes.get(a).getCol());
                    this.removeNode(a);
                    //this.fixGrid();
                    //opp.fixGrid();
                    this.updateCells(opp);
                    opp.updateCells(this);
                    again = true;
                    break;
                }
                if(opp.checkMyCells(nodes.get(a).getRow()+1, nodes.get(a).getCol()))
                {
                    //give up this cell
                            //remove from my list
                            //add to opp
                    opp.getNode(nodes.get(a).getRow(), nodes.get(a).getCol());
                    this.removeNode(a);
                    //this.fixGrid();
                    //opp.fixGrid();
                    this.updateCells(opp);
                    opp.updateCells(this);
                    again = true;
                    break;
                }
            }
        }
        this.updateCells(opp);
        opp.updateCells(this);
        //System.out.println("oh well");
        
    }
    
    public void getNode(int i, int j)
    {
        nodes.add(new Node(i,j,max));
    }
    
    public void removeNode(int a)
    {
        LinkedList<Node> tmp = new LinkedList<>();
        for(int i = 0; i < nodes.size();i++)
        {
            if(i != a)
            {
                tmp.add(nodes.get(i));
            }
        }
        nodes = null;
        nodes = tmp;
    }
    
    public boolean checkMyCells(int i, int j)
    {
        for(int a = 0; a < tempCells.size(); a++)
        {
            if(tempCells.get(a).getRow() == i && tempCells.get(a).getCol() == j)
            {
                 return true;
            }
        }
        return false;
    }
}