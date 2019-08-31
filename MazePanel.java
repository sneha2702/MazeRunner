/***
 * Group member names: Gopala Sai Uppalapati (Z1840615),Sneha Konatham (Z1838982), Anusha Chanduri(Z1840609)
 * Assignment-3
 * CSCI 502
 *****/
import javax.swing.*;
import java.awt.*;
import java.io.File;
//MazePanel class
public class MazePanel extends JPanel {


	private static final long serialVersionUID = 1L;
	//object reference to a Maze object initialized to null
	Maze mazeObj=null;
    //boolean values for solutionFound and Solution attemted
    private boolean solutionFound =false;
    private boolean solutionAttempted = false;
    //readMaze method passes the file into Mazeclass method readMaze and calls repaint
    public void readMaze(File givenFile){
        try {
            mazeObj = new Maze();
            mazeObj.readMaze(givenFile);
            this.repaint();
        }catch (Exception ex){
            throw ex;
        }
    }
    //clearMaze method passes clear to Maze class clearMAze method and calls repaint
    public void clearMazePath(){
        solutionAttempted = false;
        solutionFound = false;

        mazeObj.clearMAzePath();
        repaint();
    }
    //solvemaze method calls Maze class solveMaze method
    public void solveMaze(){
        solutionAttempted = true;
        solutionFound = mazeObj.solveMaze();
        //System.out.print(solutionFound);
        repaint();
    }
    //override paint method called each time to repaint maze
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mazeObj != null){
            mazeObj.drawMaze(g, MazeSquare.dimension*2, MazeSquare.dimension*2);
        }
        if (solutionAttempted && solutionFound){
            g.setFont(new Font("TimesRoman", Font.BOLD, 25));
            g.drawString("Solved", 250, 500);
        }
        if (solutionAttempted && solutionFound == false){
            g.setFont(new Font("TimesRoman", Font.BOLD, 25));
            g.drawString("Solution doesn't exists", 250, 500);
        }
    }
}
