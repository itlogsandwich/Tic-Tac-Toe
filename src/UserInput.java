import java.util.Scanner;

//CONSOLE-TERMINAL version of tic-tac-toe
public class UserInput 
{
    char[][] grid = {{'0','0','0'},
                     {'0', '0','0'},
                     {'0','0','0'}};
    char player = 'X';

    int counter = 0;
    public void playerAction(Scanner sc)
    {
        while(true)
        {
            System.out.println("Place " + player + " somewhere");

            System.out.print("Row: ");
            int row = sc.nextInt();

            System.out.print("Column: ");
            int col = sc.nextInt();

            counter++;
            if(checkIfEmpty(row, col))
            {
                grid[row][col] = player;
                break;
            }
            else
            {
                System.out.println("Space occupied");
                counter--;
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

    public boolean checkIfEmpty(int row, int col)
    {
        if(grid[row][col] == '0')
            return true;
        else 
            return false;
    }

    public boolean checkForVictory()
    {
        for(int i = 0; i < grid.length; i++)
            if(grid[i][0] == player && grid[i][1] == player && grid[i][2] == player || grid[i][0] == player && grid[i][1] == player && grid[i][2] == player)
                return true;

        if(grid[0][0] == player && grid[1][1] == player && grid[2][2] == player || grid[2][0] == player && grid[1][1] == player && grid[0][2] == player)
            return true;

        return false;
    }

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

    public void gameStart()
    {
        Scanner sc = new Scanner(System.in);

        while(true)
        {
            displayGrid();
            playerAction(sc);

            if(checkForVictory())
            {
                System.out.println(player + "has won!");
                break;
            }
            else if(counter == 9)
            {
                System.out.println("DRAW!");
                break;
            }

            switchPlayer();
        }
        sc.close();
    }
}
