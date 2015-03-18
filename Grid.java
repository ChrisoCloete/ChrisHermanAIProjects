//u13037618 Herman Keuris
//u13029721 Chris Cloete

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author user
 */
public class Grid {
    
    private int cols;
    private int rows;
    private int numOfPlayers = 1;
    JFrame frame2 = new JFrame();
    JButton[][]grid;
    private Player player1;
    private Player player2;
    public int buttonPressedX = -1;
    public int buttonPressedY = -1; 
    private pvpGame pvp;
    private String radio;
    
    public Grid(int num, int pieces, String radioAnswer)
    {
        radio = radioAnswer;
        numOfPlayers = pieces;
        if (numOfPlayers == 0)
            numOfPlayers = 1;
        if (num < 10)
        {
            cols = 10;
            rows = 10;
        }
        else
        {
            cols = num;
            rows = num;
        }
        
        frame2.setLayout(new GridLayout(rows, cols));
        
        player1 = new Player(1, numOfPlayers, cols);
        player2 = new Player(2, numOfPlayers, cols);
        pvp = new pvpGame(player1, player2, this, cols);
        grid = new JButton[rows][cols];
        for (int i = 0; i < rows;i++)
        {
            final int x = i;
            for (int j = 0; j < cols; j++)
            {
                final int y = j;
                grid[i][j] = new JButton(i + "," + j);
                grid[i][j].setBackground(Color.WHITE);
                grid[i][j].setForeground(Color.BLACK);
                grid[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //mainPanel.firePropertyChange(BUTTON2, false, true);
                        if (radio.equals("Player vs player"))
                        {
                            pvpGame pvp = new pvpGame(player1, player2, Grid.this, cols);
                        }
                        buttonPressedX = x;
                        buttonPressedY = y;
                        pvp.pvpMakeMove();
                    }
                });
                
                frame2.add(grid[i][j]);
            }
        }
        
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setVisible(true);
        setPlayers(player1, player2);
    }

    public void setPlayers(Player p1, Player p2)
    {
        for (int i =0; i < rows;i++)
            for (int j = 0; j < cols;j++)
                grid[i][j].setBackground(Color.WHITE);
        if (!(p1.tempCells.isEmpty()))
        {
            for (int i = 0; i < p1.tempCells.size(); i++)
            {
                grid[(p1.tempCells.get(i)).getRow()][(p1.tempCells.get(i)).getCol()].setBackground(new Color(255, 180, 180));
            }
        }
        
        if (!(p2.tempCells.isEmpty()))
        {
            for (int i = 0; i < p2.tempCells.size(); i++)
            {
                grid[(p2.tempCells.get(i)).getRow()][(p2.tempCells.get(i)).getCol()].setBackground(new Color(0, 255, 255));
            }
        }
        
        for (int i = 0; i < numOfPlayers; i++)
        {
            for (int j = 0; j < p1.nodes.get(i).myCells.size(); j++)
            {
                grid[(p1.nodes.get(i).myCells.get(j)).getRow()][(p1.nodes.get(i).myCells.get(j)).getCol()].setBackground(new Color(255, 180, 180));
            }
            for (int j = 0; j < p2.nodes.get(i).myCells.size(); j++)
            {
                grid[(p2.nodes.get(i).myCells.get(j)).getRow()][(p2.nodes.get(i).myCells.get(j)).getCol()].setBackground(new Color(0, 255, 255));
            }
        }
        for (int i = 0; i < numOfPlayers; i++)
        {
            grid[(p1.nodes.get(i)).getRow()][(p1.nodes.get(i)).getCol()].setBackground(Color.RED);
            grid[(p2.nodes.get(i)).getRow()][(p2.nodes.get(i)).getCol()].setBackground(Color.BLUE);
        }
    }
    
    /*public void chooseGameType(String answer)
    {
        if (answer.equals("Player vs player"))
        {
            System.out.println("Good so far");
            //pvpGame pvp = new pvpGame(player1, player2, this, cols);
        }
    }*/
}