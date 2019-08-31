/***
 * Group member names: Gopala Sai Uppalapati (Z1840615),Sneha Konatham (Z1838982), Anusha Chanduri(Z1840609)
 * Assignment-3
 * CSCI 502
 *****/
import java.awt.*;
import java.io.*;
/***
 * Maze class
 * Datamembers-
 * maxrows
 * maxcols
 * startindexrow-saves and returns the start row index of maze
 * startindexcol-saves and returns the start column index of maze
 * endindexrow-saves and returns the end row index of maze
 * endindexcol-saves and returns the end column index of maze
 * numberOfColumns-stores total number of rows
 * numberOfRows-stores total number of colomn
 * */
public class Maze {
    public static final int maxrows=30;
    public static final int maxcols=30;
    public int startindexrow;
    public int startindexcol;
    public int endindexrow;
    public int endindexcol;
    public int numberOfColumns;
    public int numberOfRows;

 	//Declaration of MazeSquare array mz
    MazeSquare[][] mz = null;
    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }


    //readMaze method reads the text file
	 public void readMaze(File file){
        try {
        	//Buffered reader to read the file
            BufferedReader br = new BufferedReader(new FileReader(file));
			//storing the total no of rows
			numberOfRows=Integer.parseInt(br.readLine());
			//storing total no of columns
			numberOfColumns=Integer.parseInt(br.readLine());
			//declaring size of MazeSquare array
            mz=new MazeSquare[numberOfRows][numberOfColumns];
            String reading;
            int i = 0;
            while ((reading=br.readLine())!=null){
            	//spliting each char from string and storing in line array
	            String[] line = reading.trim().split("");
	            for(int p=0;p<line.length;p++){
	            	SquareType type = null;
	            	//sqitch case to assign type,store start and end of maze
		            switch (line[p]){
		                case "#":
		                    type = SquareType.WALL;
		                    break;
		                case ".":
		                	type = SquareType.SPACE;
		                    break;
		                case "s":
							startindexrow = i;
							startindexcol = p;
		                	type = SquareType.SPACE;
		                    break;
		                case "e":
							endindexrow = i;
							endindexcol = p;
		                    type = SquareType.SPACE;
		                    break;
		                default:
		                	throw new NumberFormatException();
		            }
		            MazeSquare ms = new MazeSquare(i,p,type);
		            mz[i][p] = ms;
	            }
	            i++;
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    //draw maze method to draw the whole maze
    public void drawMaze(Graphics g,int x,int y){
		for (int i =0; i<mz.length; i++){
			for (int j=0; j<mz[i].length; j++){
				mz[i][j].drawSquare(g, x, y);
			}
		}
    }
	//clearMAzepath clears the solved maze to unsolved
    public void clearMAzePath(){
		for (int i =0; i<mz.length; i++){
			for (int j=0; j<mz[i].length; j++){
				mz[i][j].clearSquare();
			}
		}
	}
    //solveMaze method to solve the maze
    public boolean solveMaze(){
    	boolean solved = solveMaze(startindexrow, startindexcol);
    	if (solved) {
    		mz[startindexrow][startindexcol].setToPath();
    	}
    	return solved;
    }
    //solveMaze method sets each square visited to true
    private boolean solveMaze(int row, int col){
    	if(row == endindexrow && col == endindexcol){
    		mz[row][col].setToPath();
    		return true;
    	}
    	if(mz[row][col].getVisited() == true || mz[row][col].isWall()){
    		return false;
    	}
    	mz[row][col].markVisited();
    	if(row-1 >= 0){
    		if(solveMaze(row-1,col)){
    			mz[row-1][col].setToPath();
    			return true;
    		}
    	}
    	if(row != numberOfRows -1 && row<numberOfRows-1){
    		if(solveMaze(row+1,col)){
    			mz[row+1][col].setToPath();
    			return true;
    		}
    	}
    	if(col-1 >= 0){
    		if(solveMaze(row,col-1)){
    			mz[row][col-1].setToPath();
    			return true;
    		}
    	}
    	if(col<numberOfColumns-1){
    		if(solveMaze(row,col+1)){
    			mz[row][col+1].setToPath();
    			return true;
    		}
    	}
    	return false;
    }
}
