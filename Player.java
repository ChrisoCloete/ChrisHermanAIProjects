
package project;
import java.util.LinkedList;
import java.util.Random;

class Player
{
    private int playerNum;
    private int min;
    private int max;
    private int gridSize;
    public LinkedList<Node> nodes;
    //public LinkedList<Node> groupMembers;
    public LinkedList<Cell> tempCells;
    public LinkedList<Cell> checked;
    
    public Player(int pNum, int maxNodes, int gSize)
    {
        playerNum = pNum;
        gridSize = gSize;
        nodes = new LinkedList<>();
        tempCells = new LinkedList<>();
        checked = new LinkedList<>();

        //groupMembers = new LinkedList<>();
        
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
        updateCells(0);
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
    public void updateCells(int nodeIndex)
    {
        int rectLengthStart = 0;
        int rectLengthEnd = 0;
        int rectWidthStart = 0;
        int rectWidthEnd = 0;
        boolean tempBool = false;
        tempCells = new LinkedList<>();
        /*if (!(groupMembers.isEmpty()))
            groupMembers.clear();*/
        
        /*for (int i = 0; i < nodes.size() - 1; i++)
        {
            nodes.get(i).groupSize = 1;
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
                        /*if (!(nodes.get(i).groupMembers.isEmpty()))
                        {
                            nodes.get(i).groupMembers.clear();
                        }
                        nodes.get(i).groupSize = 1;*/
                        tempBool = true;
                        /*boolean tmpBool2 = false;
                        int groupMax = ++(nodes.get(i).groupSize);
                        ++(nodes.get(j).groupSize);
                        nodes.get(i).groupMembers.add(nodes.get(j));
                        if (!(nodes.get(i).groupMembers.isEmpty()))
                        {
                            for (int k = 0; k < nodes.get(i).groupMembers.size(); k++)
                            {
                                nodes.get(k).groupMembers.add(nodes.get(j));
                            }
                        }
                        for (int k = 0; k < nodes.get(i).groupMembers.size(); k++)
                        {
                            if (nodes.get(i).groupMembers.get(k).groupSize > 1)
                            {
                                if (nodes.get(i).groupMembers.get(k).groupSize > groupMax)
                                {
                                    groupMax = nodes.get(i).groupMembers.get(k).groupSize;
                                }
                                tmpBool2 = true;
                            }
                        }
                        for (int k = nodes.get(i).groupMembers.size() - 1; k >= 0; k--)
                        {
                            if (nodes.get(i).groupMembers.get(k).groupSize > 1)
                            {
                                if (nodes.get(i).groupMembers.get(k).groupSize > groupMax)
                                {
                                    groupMax = nodes.get(i).groupMembers.get(k).groupSize;
                                }
                                tmpBool2 = true;
                            }
                        }
                        nodes.get(i).groupSize = groupMax;
                        if (tmpBool2)
                        {
                            for (int k = 0; k < nodes.get(i).groupMembers.size(); k++)
                            {
                                nodes.get(i).groupMembers.get(k).groupSize = groupMax;
                            }
                        }*/
                        
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
                        
                        /*int count = 0;
                        
                        for (int k = rectLengthStart; k < rectLengthEnd; k++)
                        {
                            for (int l = rectWidthStart; l < rectWidthEnd; l++)
                            {
                                for (int m = 0; m < nodes.size();m++)
                                {
                                    if (nodes.get(m).getRow() == k && nodes.get(m).getCol() == l)
                                    {
                                        ++count;
                                    }
                                }
                            }
                        }
                        
                        for (int k = rectLengthStart; k < rectLengthEnd; k++)
                        {
                            for (int l = rectWidthStart; l < rectWidthEnd; l++)
                            {
                                for (int m = 0; m < nodes.size();m++)
                                {
                                    if (nodes.get(m).getRow() == k && nodes.get(m).getCol() == l)
                                    {
                                        nodes.get(m).groupSize = count;
                                    }
                                }
                            }
                        }
                        
                        for (int n = rectLengthEnd; n >= rectLengthStart; n--)
                        {
                            for (int p = rectWidthEnd; p >= rectWidthStart; p--)
                            {
                                for (int q = 0; q < nodes.size();q++)
                                {
                                    if (nodes.get(q).getRow() == n && nodes.get(q).getCol() == p)
                                    {
                                        nodes.get(q).groupSize = count;
                                    }
                                }
                            }
                        }*/
                        
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
        
        /*if (!(tempCells.isEmpty()))
        {
            for (int a = 0; a < gridSize*gridSize; a++)
            {
                for (int i = 0; i < max; i++)
                {
                    for (int j = 0; j < max; j++)
                    {
                        int count = 0;
                        int countX = -1;
                        int countY = -1;
                        boolean inTempCells = false;
                        for (int k = 0; k < tempCells.size(); k++)
                        {
                            if((tempCells.get(k).getRow() == i) && (tempCells.get(k).getCol() == j))
                            {
                                inTempCells = true;
                            }
                        }
                        if (!inTempCells)
                        {
                            for (int k = 0; k < tempCells.size(); k++)
                            {
                                if((tempCells.get(k).getRow() == i))
                                {
                                    if((tempCells.get(k).getCol() == j + 1) || (tempCells.get(k).getCol() == j - 1))
                                    {
                                        if (countX != tempCells.get(k).getRow() && countY != tempCells.get(k).getCol())
                                        {
                                            countX = tempCells.get(k).getRow();
                                            countY = tempCells.get(k).getCol();
                                            //System.out.println("Player: " + playerNum + " ,Row: " + i + ", Col: " + j + " a, Tempcell: r: " + tempCells.get(k).getRow() + ", c: " + tempCells.get(k).getCol() + ", k: " + k);
                                            ++count;
                                        }
                                    }
                                }
                                else if((tempCells.get(k).getCol() == j))
                                {
                                    if((tempCells.get(k).getRow() == i + 1) || (tempCells.get(k).getRow() == i - 1))
                                    {
                                        if (countX != tempCells.get(k).getRow() && countY != tempCells.get(k).getCol())
                                        {
                                            countX = tempCells.get(k).getRow();
                                            countY = tempCells.get(k).getCol();
                                            //System.out.println("Player: " + playerNum + " ,Row: " + i + ", Col: " + j + " b, Tempcell: r: " + tempCells.get(k).getRow() + ", c: " + tempCells.get(k).getCol() + ", k: " + k);
                                            ++count;
                                        }
                                    }
                                }
                            }
                        }
                        if (count >= 2)
                            tempCells.add(new Cell(i, j));
                    }
                }
            }
            this.fixGrid();
        }
        
        /*boolean tempFirstWidth = true;
                        boolean tempFirstLength = true;
                        int tempRectLengthStart = -1;
                        int tempRectLengthEnd = -1;
                        int tempRectWidthStart = -1;
                        int tempRectWidthEnd = -1;
                        
                        for (int k = 0; k < max; k++)
                        {
                            for (int l = 0; l < max - 1; l++)
                            {
                                for (int m = 0; m < tempCells.size();m++)
                                {
                                    if (tempCells.get(m).getRow() == k && tempCells.get(m).getCol() == l)
                                    {
                                        if (tempFirstWidth)
                                        {
                                            tempFirstWidth = false;
                                            tempRectWidthStart = l;
                                        }
                                        for (int n = 0; n < tempCells.size();n++)
                                        {
                                            if (!(tempCells.get(n).getRow() == k && tempCells.get(n).getCol() == l + 1))
                                            {
                                                tempRectWidthEnd = l;
                                                for (int a = 0; a < max; a++)
                                                {
                                                    for (int b = 0; b < max - 1; b++)
                                                    {
                                                        for (int c = 0; c < tempCells.size();c++)
                                                        {
                                                            if (tempCells.get(c).getRow() == a && tempCells.get(c).getCol() == b)
                                                            {
                                                                if (tempFirstLength)
                                                                {
                                                                    tempFirstLength = false;
                                                                    tempRectLengthStart = a;
                                                                }
                                                                for (int d = 0; d < tempCells.size();d++)
                                                                {
                                                                    if (!(tempCells.get(d).getRow() == a + 1 && tempCells.get(d).getCol() == b))
                                                                    {
                                                                        System.out.println("tempRectWidthStart: " + tempRectWidthStart);
                                                                        System.out.println("tempRectWidthEnd: " + tempRectWidthEnd);
                                                                        System.out.println("tempRectLengthStart: " + tempRectLengthStart);
                                                                        System.out.println("tempRectLengthEnd: " + tempRectLengthEnd);
                                                                        tempRectLengthEnd = a;
                                                                        int count = 0;
                                                                        for (int e = tempRectWidthStart; e <= tempRectWidthEnd; e++)
                                                                        {
                                                                            for (int f = tempRectLengthStart; f <= tempRectLengthStart; f++)
                                                                            {
                                                                                for (int g = 0; g < nodes.size(); g++)
                                                                                {
                                                                                    if (nodes.get(g).getRow() == f && nodes.get(g).getCol() == e)
                                                                                    {
                                                                                        ++count;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        for (int h = tempRectWidthStart; h <= tempRectWidthEnd; h++)
                                                                        {
                                                                            for (int q = tempRectLengthStart; q <= tempRectLengthStart; q++)
                                                                            {
                                                                                for (int r = 0; r < nodes.size(); r++)
                                                                                {
                                                                                    if (nodes.get(r).getRow() == q && nodes.get(r).getCol() == h)
                                                                                    {
                                                                                        nodes.get(r).groupSize = count;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        tempFirstLength = true;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                tempFirstWidth = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }*/
                        
                        
                        /*boolean tempFirstWidth = true;
                        boolean tempFirstLength = true;
                        int tempRectLengthStart = -1;
                        int tempRectLengthEnd = -1;
                        int tempRectWidthStart = -1;
                        int tempRectWidthEnd = -1;
                        
                        for (int i = 0; i < nodes.size(); i++)
                        {
                            nodes.get(i).checked = false;
                        }
                        
                        for (int k = 0; k <= max; k++)
                        {
                            for (int l = 0; l <= max; l++)
                            {
                                for (int m = 0; m < tempCells.size();m++)
                                {
                                    if (tempCells.get(m).getRow() == k && tempCells.get(m).getCol() == l)
                                    {
                                        if (tempFirstWidth)
                                        {
                                            tempFirstWidth = false;
                                            tempRectWidthStart = l;
                                        }
                                        for (int n = 0; n < tempCells.size();n++)
                                        {
                                            if (l + 1 <= max)
                                            {
                                                if ((!(tempCells.get(n).getRow() == k && tempCells.get(n).getCol() == l + 1)) || (l == max))
                                                {
                                                    tempRectWidthEnd = l;
                                                    for (int a = 0; a <= max; a++)
                                                    {
                                                        for (int b = 0; b < tempCells.size();b++)
                                                        {
                                                            if (tempCells.get(b).getRow() == a && tempCells.get(b).getCol() == tempRectWidthStart)
                                                            {
                                                                if(tempFirstLength)
                                                                {
                                                                    tempFirstLength = false;
                                                                    tempRectLengthStart = a;
                                                                }
                                                                for (int c = 0; c < tempCells.size();c++)
                                                                {
                                                                    if (a + 1 <= max)
                                                                    {
                                                                        if ((!(tempCells.get(c).getRow() == a + 1 && tempCells.get(c).getCol() == tempRectWidthStart)) || (a == max))
                                                                        {
                                                                            tempRectLengthEnd = a;
                                                                            int count = 0;
                                                                            for (int e = tempRectWidthStart; e <= tempRectWidthEnd; e++)
                                                                            {
                                                                                for (int f = tempRectLengthStart; f <= tempRectLengthEnd; f++)
                                                                                {
                                                                                    for (int g = 0; g < nodes.size(); g++)
                                                                                    {
                                                                                        if ((nodes.get(g).getRow() == f && nodes.get(g).getCol() == e) && (!(nodes.get(g).checked)))
                                                                                        {
                                                                                            ++count;
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                            for (int e = tempRectWidthStart; e <= tempRectWidthEnd; e++)
                                                                            {
                                                                                for (int f = tempRectLengthStart; f <= tempRectLengthStart; f++)
                                                                                {
                                                                                    for (int g = 0; g < nodes.size(); g++)
                                                                                    {
                                                                                        if (nodes.get(g).getRow() == f && nodes.get(g).getCol() == e)
                                                                                        {
                                                                                            nodes.get(g).groupSize = count;
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }*/
                                                /*for (int a = 0; a < max; a++)
                                                {
                                                    for (int b = 0; b < max - 1; b++)
                                                    {
                                                        for (int c = 0; c < tempCells.size();c++)
                                                        {
                                                            if (tempCells.get(c).getRow() == a && tempCells.get(c).getCol() == b)
                                                            {
                                                                if (tempFirstLength)
                                                                {
                                                                    tempFirstLength = false;
                                                                    tempRectLengthStart = a;
                                                                }
                                                                for (int d = 0; d < tempCells.size();d++)
                                                                {
                                                                    if (!(tempCells.get(d).getRow() == a + 1 && tempCells.get(d).getCol() == b))
                                                                    {
                                                                        System.out.println("tempRectWidthStart: " + tempRectWidthStart);
                                                                        System.out.println("tempRectWidthEnd: " + tempRectWidthEnd);
                                                                        System.out.println("tempRectLengthStart: " + tempRectLengthStart);
                                                                        System.out.println("tempRectLengthEnd: " + tempRectLengthEnd);
                                                                        tempRectLengthEnd = a;
                                                                        int count = 0;
                                                                        for (int e = tempRectWidthStart; e <= tempRectWidthEnd; e++)
                                                                        {
                                                                            for (int f = tempRectLengthStart; f <= tempRectLengthStart; f++)
                                                                            {
                                                                                for (int g = 0; g < nodes.size(); g++)
                                                                                {
                                                                                    if (nodes.get(g).getRow() == f && nodes.get(g).getCol() == e)
                                                                                    {
                                                                                        ++count;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        for (int h = tempRectWidthStart; h <= tempRectWidthEnd; h++)
                                                                        {
                                                                            for (int q = tempRectLengthStart; q <= tempRectLengthStart; q++)
                                                                            {
                                                                                for (int r = 0; r < nodes.size(); r++)
                                                                                {
                                                                                    if (nodes.get(r).getRow() == q && nodes.get(r).getCol() == h)
                                                                                    {
                                                                                        nodes.get(r).groupSize = count;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        tempFirstLength = true;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }*//*
                                                tempFirstWidth = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }*/
        for (int i = 0; i < nodes.size(); i++)
        {
            nodes.get(i).checked = false;
        }
        int count = 1;
        for (int i = 0; i < nodes.size();i++)
        {
            nodes.get(i).checked = true;
            int leftBound = nodes.get(i).getCol() - 1;
            while(leftBound != 0 && isTempCell(nodes.get(i).getRow(), leftBound))
            {
                int j = isNode(nodes.get(i).getRow(), leftBound);
                if(j > -1 && !nodes.get(j).checked)
                {
                    nodes.get(j).checked = true;
                    ++count;
                }
                --leftBound;
            }
            if(leftBound != 0)
            {
                ++leftBound;
            }
            
            
        }
    }
    
    /*public void fixGrid()
    {
        LinkedList<Cell> tmp = null;
        for (int i = 0; i < max; i++)
        {
            for (int j = 0; j < max; j++)
            {
                for(int k = 0; k < tempCells.size();k++)
                {
                    if(tempCells.get(k).getRow() == i && tempCells.get(k).getCol() == j)
                    {
                        int x = i;
                        int maxX = 0;
                        int y = j;
                        int maxY = 0;
                        boolean go = true;
                        while(go)
                        {
                            boolean cont = true;
                            for(int o = y; o < max; o++)
                            {
                                for(int m = 0; m < tempCells.size();m++)
                                {
                                    boolean breaker = false;
                                    if(tempCells.get(m).getRow() == x && tempCells.get(m).getCol() == o)
                                    {
                                        if(maxY<o)
                                            maxY = o;
                                    }
                                    else
                                    {
                                        for(int n = 0; n < nodes.size();n++)
                                        {
                                            if(nodes.get(n).getRow() == x && nodes.get(n).getCol() == o)
                                            {
                                                if(maxY<o)
                                                    maxY = o;
                                            }
                                            else
                                            {
                                                breaker = true;
                                                cont = false;
                                                break;
                                            }
                                        }
                                        if(breaker)
                                            break;
                                    }
                                }
                                if(!cont)
                                    break;
                            }
                            
                            cont = true;
                            for(int o = x; o < max; o++)
                            {
                                for(int m = 0; m < tempCells.size();m++)
                                {
                                    boolean breaker = false;
                                    if(tempCells.get(m).getRow() == o && tempCells.get(m).getCol() == y)
                                    {
                                        if(maxX<o)
                                            maxX = o;
                                    }
                                    else
                                    {
                                        for(int n = 0; n < nodes.size();n++)
                                        {
                                            if(nodes.get(n).getRow() == o && nodes.get(n).getCol() == y)
                                            {
                                                if(maxX<o)
                                                    maxX = o;
                                            }
                                            else
                                            {
                                                breaker = true;
                                                cont = false;
                                                break;
                                            }
                                        }
                                        if(breaker)
                                            break;
                                    }
                                }
                                if(!cont)
                                    break;
                            }
                            x++;
                            y++;
                            go = false;
                            for(int m = 0; m < tempCells.size();m++)
                            {
                                if(tempCells.get(m).getRow() == x && tempCells.get(m).getCol() == y)
                                {
                                    go = true;
                                    break;
                                }
                            }
                        }
                        /*System.out.println(i);
                        System.out.println(j);
                        System.out.println(x);
                        System.out.println(y);
                        System.out.println();*/
                        /*for (int e = x; e <= maxX; e++)
                        {
                            for (int f = y; f <= maxY; f++)
                            {
                                boolean in = false;
                                for(int m = 0; m < tempCells.size();m++)
                                {
                                    if(tempCells.get(m).getRow() == e && tempCells.get(m).getCol() == f)
                                    {
                                        in = true;
                                        break;
                                    }
                                }
                                if(!in)
                                {
                                    for(int m = 0; m < nodes.size();m++)
                                    {
                                        if(nodes.get(m).getRow() == e && nodes.get(m).getCol() == f)
                                        {
                                            in = true;
                                            break;
                                        }
                                    }
                                }
                                if(!in)
                                {
                                    tempCells.add(new Cell(e, f));
                                }
                            }
                        }
                    }
                }
            }
        }
    }*/
    
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
}