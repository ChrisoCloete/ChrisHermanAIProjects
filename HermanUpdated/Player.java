//u13037618 Herman Keuris
//u13029721 Chris Cloete

package project;
import java.util.LinkedList;

class Player
{
    //number of pieces each player may initially have
    private int playerNum;
    //minimum edge of the grid
    private int min;
    //maximum edge of the grid
    private int max;
    //grid length and width
    private int gridSize;
    //Linked list of player's pieces
    public LinkedList<Node> nodes;
    //Linked list of all pieces that share a rectangle
    public LinkedList<Node> groupMembers;
    //Linked list of all cells of the grid which are filled for that player (i.e. nodes and rectangle spaces)
    public LinkedList<Cell> tempCells;
    //public LinkedList<Cell> checked;
    
    public Player(int pNum, int maxNodes, int gSize)
    {
        //Constructor: initialization of values
        playerNum = pNum;
        gridSize = gSize;
        nodes = new LinkedList<>();
        tempCells = new LinkedList<>();
        //checked = new LinkedList<>();

        groupMembers = new LinkedList<>();
        
        min = 0;
        max = gridSize - 1;
        
        //Set it so that initially the first player only spawns his pieces on the left side of the grid and the second player only spawns his pieces on the right side of the grid
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
        
        //Add all nodes and filled rectangle spaces to the tempCells linked list
        for (int i = 0; i < nodes.size() - 1; i++)
        {
            tempCells.add(new Cell((nodes.get(i).getRow()), (nodes.get(i).getCol())));
            for (int j = 0; j < nodes.get(i).myCells.size();j++)
            {
                tempCells.add(new Cell((nodes.get(i).myCells.get(j).getRow()), (nodes.get(i).myCells.get(j).getCol())));
            }
        }
        //call updateCells to make sure all cells are up to date
        updateCells(null);
    }
    public int[] getValidMoves(String type, int index, Player other)
    {
        //getValidMoves gets the direction in which the piece wants to move (type), the player's piece to move (index) and the player's opponent (other)
        //this function returns an array of the spaces to which this piece may move in the chosen direction
        int distance = nodes.get(index).groupSize;
        int row = nodes.get(index).getRow();
        int col = nodes.get(index).getCol();
        int count = 0;
        int []temp = new int[distance];
        int []answer;
        boolean tempBool;
        
        if (type.equals("top"))
        {
           // System.out.println("top :" + distance);
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
            //System.out.println("bottom :" + distance);
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
            //System.out.println("left :" + distance);
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
            //System.out.println("right :" + distance);
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
        /*System.out.println("Update: " + nodes.get(0).getRow() + nodes.get(0).getCol());
        System.out.println("UC1");*/
        int rectLengthStart = 0;
        int rectLengthEnd = 0;
        int rectWidthStart = 0;
        int rectWidthEnd = 0;
        boolean tempBool = false;
        tempCells = new LinkedList<>();

        //System.out.println("UC2");
        boolean taken = false;
        if(other != null)
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
                                    taken = true;
                                }
                            }
                    }
                }
        }
        if (taken)
            updateCells(other);
        
        //System.out.println("UC3");
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
        //System.out.println("UC4");
        
        if (!(tempCells.isEmpty()))
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
            //System.out.println("outWhile");
        }
        //System.out.println("UC5");
        //System.out.println("Player1: " + nodes.get(0).getRow() + nodes.get(0).getCol());
        setGroupSize();
    }
    
    public void setGroupSize()
    {
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
        /*System.out.println("UC6");
        System.out.println("Player: " + nodes.get(0).getRow() + nodes.get(0).getCol());*/
        for (int i = 0; i < nodes.size();i++)
        {
            if (!nodes.get(i).checked)
            {
                nodes.get(i).checked = true;
                groupMembers.add(nodes.get(i));
                leftBound = nodes.get(i).getCol() - 1;
                if (leftBound < 0)
                    leftBound = 0;
                /*if (isTempCell(nodes.get(i).getRow(), leftBound))
                    System.out.println("X: " + nodes.get(i).getRow() + "Y: " + nodes.get(i).getCol() + "isTemp");
                else
                    System.out.println("X: " + nodes.get(i).getRow() + "Y: " + nodes.get(i).getCol() + "isNotTemp");*/
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
                //System.out.println("X: " + nodes.get(i).getRow() + "Y: " + nodes.get(i).getCol() + "LeftBound: " + leftBound);
                topBound = nodes.get(i).getRow() - 1;
                if (topBound < 0)
                    topBound = 0;
                /*if (isTempCell(topBound, nodes.get(i).getCol()))
                    System.out.println("X: " + nodes.get(i).getRow() + "Y: " + nodes.get(i).getCol() + "isTemp");
                else
                    System.out.println("X: " + nodes.get(i).getRow() + "Y: " + nodes.get(i).getCol() + "isNotTemp");*/
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
                //System.out.println("X: " + nodes.get(i).getRow() + "Y: " + nodes.get(i).getCol() + "topBound: " + topBound);
                rightBound = nodes.get(i).getCol() + 1;
                if (rightBound > max)
                    rightBound = max;
                /*if ((isTempCell(nodes.get(i).getRow(), rightBound)))
                    System.out.println("X: " + nodes.get(i).getRow() + "Y: " + nodes.get(i).getCol() + "isTemp");
                else
                    System.out.println("X: " + nodes.get(i).getRow() + "Y: " + nodes.get(i).getCol() + "isNotTemp");*/
                while(rightBound < max && isTempCell(nodes.get(i).getRow(), rightBound))
                {
                    int j = isNode(nodes.get(i).getRow(), rightBound);
                    if(j > -1 && !nodes.get(j).checked)
                    {
                        nodes.get(j).checked = true;
                        groupMembers.add(nodes.get(j));
                        ++count;
                    }
                    ++rightBound;
                }
                if(rightBound != max)
                {
                    --rightBound;
                }
                //System.out.println("X: " + nodes.get(i).getRow() + "Y: " + nodes.get(i).getCol() + "rightBound: " + rightBound);
                bottomBound = nodes.get(i).getRow() + 1;
                if (bottomBound > max)
                    bottomBound = max;
                /*if (isTempCell(bottomBound, nodes.get(i).getCol()))
                    System.out.println("X: " + nodes.get(i).getRow() + "Y: " + nodes.get(i).getCol() + "isTemp");
                else
                    System.out.println("X: " + nodes.get(i).getRow() + "Y: " + nodes.get(i).getCol() + "isNotTemp");*/
                while(bottomBound < max && isTempCell(bottomBound, nodes.get(i).getCol()))
                {
                    int k = isNode(bottomBound, nodes.get(i).getCol());
                    if(k > -1 && !nodes.get(k).checked)
                    {
                        nodes.get(k).checked = true;
                        groupMembers.add(nodes.get(k));
                        ++count;
                    }
                    ++bottomBound;
                }
                if(bottomBound != max)
                {
                    --bottomBound;
                }
                //System.out.println("X: " + nodes.get(i).getRow() + "Y: " + nodes.get(i).getCol() + "bottomBound: " + bottomBound);
                if (allNodesChecked())
                {
                    //System.out.println("All chekced");
                    for (int z = 0; z < groupMembers.size();z++)
                    {
                        //System.out.println("GroupMembers" + groupMembers.get(z).getRow() + groupMembers.get(z).getCol() + " count: " + groupMembers.size());
                        groupMembers.get(z).groupSize = groupMembers.size(); 
                    }
                    if (!groupMembers.isEmpty())
                        groupMembers.clear();
                }
                else
                {
                    //System.out.println("Not all chekced");
                    boolean end = false;
                    for (int a = topBound; a <= bottomBound; a++)
                    {
                        for (int b = leftBound; b <= rightBound; b++)
                        {
                            int index = isNode(a,b);
                            /*if (index > -1)
                                System.out.println("isNode " + a + b);
                            else
                                System.out.println("isNotNode " + a + b);*/
                            if(index > -1 && !nodes.get(index).checked)
                            {
                                nodes.get(index).checked = true;
                                groupMembers.add(nodes.get(index));
                                ++count;
                            }
                        }
                    }
                    for (int z = 0; z < groupMembers.size();z++)
                    {
                        //System.out.println("NACGroupMembers" + groupMembers.get(z).getRow() + groupMembers.get(z).getCol() + " count: " + groupMembers.size());
                        groupMembers.get(z).groupSize = groupMembers.size();
                    }
                    if (!groupMembers.isEmpty())
                        groupMembers.clear();
                    }
            }
        }
        //System.out.println("UC7");
    }
    
    private boolean isTempCell(int row, int col)
    {
        //System.out.println("PassedValue" + row + col);
        for (int i = 0; i < tempCells.size(); i++)
        {
            //System.out.println("CheckValue" + tempCells.get(i).getRow() + col);
            if (tempCells.get(i).getRow() == row && tempCells.get(i).getCol() == col)
                return true;
        }
        return false;
    }
    
    private int isNode(int row, int col)
    {
        for (int i = 0; i < nodes.size(); i++)
        {
            if (nodes.get(i).getRow() == row && nodes.get(i).getCol() == col)
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
}