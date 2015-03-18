/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JButton;

/**
 *
 * @author user
 */
public class pvpGame {
    private Player player1;
    private Player player2;
    private int gridSize;
    private Grid grid;
    private JButton[][]buttons;
    boolean endGame = false;
    int max;
    int nodeToMove;
    boolean nodeSelected = false;
    boolean nodeMoved = false;
    boolean p1t1 = true;
    boolean p1t2 = false;
    boolean p2t1 = false;
    boolean p2t2 = false;
    int [] validTopMoves = new int[0];
    int [] validBottomMoves = new int[0];
    int [] validLeftMoves = new int[0];
    int [] validRightMoves = new int[0];
    public pvpGame(Player p1, Player p2, Grid g, int size)
    {
        player1 = p1;
        player2 = p2;
        gridSize = size;
        max = gridSize - 1;
        grid = g;
        System.out.println("Still good");
    }
    public void pvpMakeMove()
    {
        System.out.println("Ok");
        if (p1t1 || p1t2)
            playerTurn(1);
        else
            playerTurn(2);
        System.out.println("Yes");
        grid.setPlayers(player1, player2);
    }
    private void playerTurn(int p)
    {
        System.out.println("Player" + p + " 1");
        Player player;
        Player opponent;
        boolean tempBool = false;
        if(p == 1)
        {
            player = player1;
            opponent = player2;
        }
        else
        {
            player = player2;
            opponent = player1;
        }
        
        if ((p == 1 && p1t1) || (p == 2 && p2t1)) 
        {
            System.out.println("Player" + p + " 2");
            if (grid.buttonPressedX != -1 && grid.buttonPressedY != -1)
            {
                System.out.println("Player" + p + " 2.1");
                if (!player.nodes.isEmpty())
                {
                    for (int i = 0; i < player.nodes.size(); i++)
                    {
                        System.out.println("Player" + p + " node.size: " + player.nodes.size());
                        if ((player.nodes.get(i).getRow() == grid.buttonPressedX) && (player.nodes.get(i).getCol() == grid.buttonPressedY))
                        {
                            System.out.println("Player" + p + " 2.2, X: " + player.nodes.get(i).getRow() + ", Y: " + player.nodes.get(i).getCol());
                            nodeToMove = i;
                            validTopMoves = new int[player.getValidMoves("top", i, opponent).length];
                            validTopMoves = player.getValidMoves("top", i, opponent);
                            validBottomMoves = new int[player.getValidMoves("bottom", i, opponent).length];
                            validBottomMoves = player.getValidMoves("bottom", i, opponent);
                            validLeftMoves = new int[player.getValidMoves("left", i, opponent).length];
                            validLeftMoves = player.getValidMoves("left", i, opponent);
                            validRightMoves = new int[player.getValidMoves("right", i, opponent).length];
                            validRightMoves = player.getValidMoves("right", i, opponent);
                            if (validTopMoves.length > 0)
                                System.out.println("Player" + p + " 2, vtm0: " + validTopMoves.length);
                            if (validBottomMoves.length > 0)
                                System.out.println("Player" + p + " 2, vbm0: " + validTopMoves.length);
                            if (validLeftMoves.length > 0)
                                System.out.println("Player" + p + " 2, vlm0: " + validTopMoves.length);
                            if (validRightMoves.length > 0)
                                System.out.println("Player" + p + " 2, vrm0: " + validTopMoves.length);
                            nodeSelected = true;
                            nodeMoved = false;
                            if(p == 1)
                            {
                                p1t1 = false;
                                p1t2 = true;
                            }                            
                            else
                            {
                                p2t1 = false;
                                p2t2 = true;
                            }
                        }
                    }
                }
            }
        }else if ((p == 1 && p1t2) || (p == 2 && p2t2))
        { 
            System.out.println("Player" + p + " 3");
            if (validTopMoves.length > 0)
                System.out.println("Player" + p + " 3, vtm0: " + validTopMoves.length);
            if (validBottomMoves.length > 0)
                System.out.println("Player" + p + " 3, vbm0: " + validTopMoves.length);
            if (validLeftMoves.length > 0)
                System.out.println("Player" + p + " 3, vlm0: " + validTopMoves.length);
            if (validRightMoves.length > 0)
                System.out.println("Player" + p + " 3, vrm0: " + validTopMoves.length);
            
            boolean temp = false;
            for (int i = 0; i < validTopMoves.length; i++)
            {
                if (grid.buttonPressedX == validTopMoves[i] && grid.buttonPressedY == player.nodes.get(nodeToMove).getCol())
                {
                    System.out.println("Player" + p + " 3.1");
                    player.nodes.get(nodeToMove).setRow(grid.buttonPressedX);
                    player.updateCells(nodeToMove);
                    if(p == 1)
                    {
                        p1t1 = false;
                        p1t2 = false;
                        p2t1 = true;
                        temp = true;
                    }                            
                    else
                    {
                        p2t1 = false;
                        p2t2 = false;
                        p1t1 = true;
                        temp = true;
                    }
                }
            }
            if(!temp)
            {
                if(p == 1)
                {
                    p1t1 = true;
                    p1t2 = false;
                }                            
                else
                {
                    p2t1 = true;
                    p2t2 = false;
                }
            }
            for (int i = 0; i < validBottomMoves.length; i++)
            {
                if (grid.buttonPressedX == validBottomMoves[i] && grid.buttonPressedY == player.nodes.get(nodeToMove).getCol())
                {
                    System.out.println("Player" + p + " 3.2");
                    player.nodes.get(nodeToMove).setRow(grid.buttonPressedX);
                    player.updateCells(nodeToMove);
                    nodeSelected = false;
                    nodeMoved = true;
                    if(p == 1)
                    {
                        p1t1 = false;
                        p1t2 = false;
                        p2t1 = true;
                    }                            
                    else
                    {
                        p2t1 = false;
                        p2t2 = false;
                        p1t1 = true;
                    }
                }
            }
            for (int i = 0; i < validLeftMoves.length; i++)
            {
                if (grid.buttonPressedX == player.nodes.get(nodeToMove).getRow() && grid.buttonPressedY == validLeftMoves[i])
                {
                    System.out.println("Player" + p + " 3.3");
                    player.nodes.get(nodeToMove).setCol(grid.buttonPressedY);
                    player.updateCells(nodeToMove);
                    nodeSelected = false;
                    nodeMoved = true;
                    if(p == 1)
                    {
                        p1t1 = false;
                        p1t2 = false;
                        p2t1 = true;
                    }                            
                    else
                    {
                        p2t1 = false;
                        p2t2 = false;
                        p1t1 = true;
                    }
                }
            }
            for (int i = 0; i < validRightMoves.length; i++)
            {
                if (grid.buttonPressedX == player.nodes.get(nodeToMove).getRow() && grid.buttonPressedY == validRightMoves[i])
                {
                    System.out.println("Player" + p + " 3.4");
                    player.nodes.get(nodeToMove).setCol(grid.buttonPressedY);
                    player.updateCells(nodeToMove);
                    nodeSelected = false;
                    nodeMoved = true;
                    if(p == 1)
                    {
                        p1t1 = false;
                        p1t2 = false;
                        p2t1 = true;
                    }                            
                    else
                    {
                        p2t1 = false;
                        p2t2 = false;
                        p1t1 = true;
                    }
                }
            }
        }
        else
        {
            System.out.println("Player" + p + " 100");
            if(p == 1)
            {
                if(p1t1)
                    System.out.println("p1t1");
                else
                    System.out.println("p1t2");
            }              
            else
            {
                if(p2t1)
                    System.out.println("p2t1");
                else
                    System.out.println("p2t2");
            }
        }
        System.out.println("Player" + p + " 4");
    }
}
