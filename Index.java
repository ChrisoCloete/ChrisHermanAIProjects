//u13037618 Herman Keuris
//u13029721 Chris Cloete
package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Herman
 */
public class Index {

    private JFrame frame;
    private JLabel labelSpinner1;
    private JLabel labelSpinner2;
    private JLabel labelRadio;
    private JPanel controlPanel1;
    private JPanel controlPanel2;
    private int spinnerValue1;
    private int spinnerValue2;
    private int cols, rows;
    private String pvp = "Player vs player";
    private String pva = "Player vs AI";
    private String ava = "AI vs AI";
    private String radioAnswer = pvp;

    public Index(){
        frame = new JFrame("Index");
        frame.setSize(300,400);
        frame.setLayout(new GridLayout(8, 1));
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
            }        
        });    
        labelSpinner1 = new JLabel("", JLabel.CENTER);  
        labelSpinner2 = new JLabel("", JLabel.CENTER);   
        labelRadio = new JLabel("",JLabel.CENTER); 
        
        JRadioButton pvpButton = new JRadioButton(pvp);
        JRadioButton pvaButton = new JRadioButton(pva);
        JRadioButton avaButton = new JRadioButton(ava);
        
        pvpButton.setActionCommand(pvp);
        pvaButton.setActionCommand(pva);
        avaButton.setActionCommand(ava);
        
        pvpButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                if (pvp.equals(e.getActionCommand())) {
                    radioAnswer = pvp;
                }
            }
        });
        pvaButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                if (pva.equals(e.getActionCommand())) {
                    radioAnswer = pva;
                }
            }
        });
        avaButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                if (ava.equals(e.getActionCommand())) {
                    radioAnswer = ava;
                }
            }
        });
        
        
        pvpButton.setSelected(true);
        
        ButtonGroup group = new ButtonGroup();
        group.add(pvpButton);
        group.add(pvaButton);
        group.add(avaButton);

        JButton button = new JButton("Continue");
        button.setVerticalTextPosition(AbstractButton.CENTER);
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        button.setActionCommand("Continue");
        button.setMnemonic(KeyEvent.VK_C);
        button.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                if (("Continue").equals(e.getActionCommand())) {
                    cols = spinnerValue1;
                    rows = spinnerValue1;
                    
                    generateGrid(spinnerValue1,spinnerValue2, radioAnswer);
                }
            } 
        });
        
        controlPanel1 = new JPanel();
        controlPanel1.setLayout(new FlowLayout());
        
        controlPanel2 = new JPanel();
        controlPanel2.setLayout(new FlowLayout());
        
        frame.add(labelSpinner1);
        frame.add(controlPanel1);
        frame.add(controlPanel2);
        frame.add(labelRadio);
        frame.add(pvpButton);
        frame.add(pvaButton);
        frame.add(avaButton);
        frame.add(button);
        
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        Index index = new Index();      
        index.displayGUI();
    }

    private void displayGUI(){
        labelSpinner1.setText("Select a size for the grid (atleast 10)"); 
        int gridSize = 10;
        spinnerValue2 = 3;
        SpinnerModel spinnerModel1 =
        new SpinnerNumberModel(gridSize,gridSize, gridSize + 100, 2);
        JSpinner spinner1 = new JSpinner(spinnerModel1);
        spinner1.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                spinnerValue1 = (int) (((JSpinner)e.getSource()).getValue());
            }
        });
        labelSpinner2.setText("Select starting number of pieces each player has"); 
        labelRadio.setText("Select a game type:");
        SpinnerModel spinnerModel2 =
        new SpinnerNumberModel(3,1,10,1);
        JSpinner spinner2 = new JSpinner(spinnerModel2);
        spinner2.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                spinnerValue2 = (int) (((JSpinner)e.getSource()).getValue());
            }
        });
        controlPanel1.add(spinner1);
        controlPanel2.add(labelSpinner2);
        controlPanel2.add(spinner2);
        frame.setVisible(true);  
    } 

    private void generateGrid(int num,int pieces, String type)
    {
        Grid grid = new Grid(num, pieces, radioAnswer);
        frame.setVisible(false);
        //grid.chooseGameType(radioAnswer);
    }
}
