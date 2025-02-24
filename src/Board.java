import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board 
{
    private JFrame frame;
    private JPanel mainPanel, panel;
    private JButton[][] button = new JButton[3][3];
    private char player = 'X';
    private ActionListener listener;
    private int drawCounter = 0;
    private char[][] grid = {{'0','0','0'},
                            {'0', '0','0'},
                            {'0','0','0'}};

    public Board()
    {
        buttonFunction();
        createBoard();
        createFrame();
    }

    public boolean checkForVictory()
    {
        for(int i = 0; i < grid.length; i++)
            if(grid[i][0] == player && grid[i][1] == player && grid[i][2] == player || grid[0][i] == player && grid[1][i] == player && grid[2][i] == player)
                return true;

        if(grid[0][0] == player && grid[1][1] == player && grid[2][2] == player || grid[2][0] == player && grid[1][1] == player && grid[0][2] == player)
            return true;

        return false;
    }
    
    //used for testing if value has been stored correctly
    public void displayGrid()
    {
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
                System.out.print(grid[i][j] + "\t");

            System.out.println();
        }
        System.out.println();
    }

    public void buttonFunction()
    {
        listener = new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                if(player == 'X')
                {
                    ((JButton) e.getSource()).setText("X");
                    ((JButton) e.getSource()).setEnabled(false);
                    drawCounter++;
                    storeValue(e);

                    if(checkForVictory())
                    {   
                        JOptionPane.showMessageDialog(frame, player + " has won!");
                        frame.dispose();
                    }
                    else if(drawCounter == 9 && checkForVictory() != true)
                    {
                        JOptionPane.showMessageDialog(frame, "DRAW!");
                        frame.dispose();
                    }
                    
                    switchPlayer();
                }
                else
                {
                
                    ((JButton) e.getSource()).setText("O");
                    ((JButton) e.getSource()).setEnabled(false);
                    drawCounter++;
                    storeValue(e);

                    if(checkForVictory())
                    {
                        JOptionPane.showMessageDialog(frame, player + " has won!");
                        frame.dispose();    
                    }
                    else if(drawCounter == 9 && checkForVictory() != true)
                    {
                        JOptionPane.showMessageDialog(frame, "DRAW!");
                        frame.dispose();
                    }

                    switchPlayer();
                }
            }
        };
    }

    public void storeValue(ActionEvent e)
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(button[i][j] == e.getSource())
                {
                    grid[i][j] = ((JButton) e.getSource()).getText().charAt(0);
                }
            } 
        }
    }
    public void switchPlayer()
    {
        if(player == 'X')
            player = 'O';
        else
            player = 'X';
    }

    public void createBoard()
    {

        GridBagConstraints gbc = new GridBagConstraints();
        panel = new JPanel(new GridBagLayout());
        panel.setMaximumSize(new Dimension(900,900));
        panel.setBackground(new Color(255,0,0));

        for(int i = 0; i < 3; i++)
        {   
            for(int j = 0; j < 3; j++)
            {
                button[i][j] = new JButton();
                button[i][j].addActionListener(listener);
                button[i][j].setPreferredSize(new Dimension(300,300));
                button[i][j].setFont(new Font("Arial", Font.BOLD, 300));
                gbc.gridx = j;
                gbc.gridy = i;
                panel.add(button[i][j], gbc);
            }   
        }
    }

    public void createFrame()
    {
        frame = new JFrame("TicTacToe");
        mainPanel = new JPanel();

        mainPanel.add(panel);

        frame.add(mainPanel);
        frame.setSize(new Dimension(900,900));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
