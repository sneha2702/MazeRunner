/***
 * Group member names: Gopala Sai Uppalapati (Z1840615),Sneha Konatham (Z1838982), Anusha Chanduri(Z1840609)
 * Assignment-3
 * CSCI 502
 *****/
import java.awt.*;
/**
 * enum declaration for specifying type - WALL, SPACE,PATH.
 * */
enum  SquareType{WALL,SPACE,PATH};

public class MazeSquare {
	//symbolic constant declaring dimensionof square
	public static final int dimension=15;

	//row and column declaration
	public int row,column;
	//Squaretypr variable type
	SquareType type;
	//boolean variable isVisited initalize to false
	boolean isVisited=false;
	//MazeSquare constructor stores row,coloumn and type of square
	MazeSquare(int row,int column,SquareType type){
		this.row=row;
		this.column=column;
		this.type=type;
	}
	public void setSquareType(SquareType type) {
		this.type=type;
	}
	//clearSquare method changes visited square to space
	public void clearSquare() {
		isVisited=false;
		if(type==SquareType.PATH) {
			type= SquareType.SPACE;
		}
	}
	//markVisited methos changes squaretype to visited
	public void markVisited() {
		isVisited=true;
	}
	//getVisited square method  return the square’s visited data member.
	public boolean getVisited() {
		return isVisited;
	}
	//isWall method  return true if this square’s type is WALL and false if it is not.
	public boolean isWall() {
		if(type==SquareType.WALL) {
			return true;
		}
		return false;
	}
	//setToPath method set the square’s type to PATH.
	public void setToPath() {
		type=SquareType.PATH;
	}
	//drawSquare method drawSquare drws each square
	 public void drawSquare(Graphics g,int startX,int startY) {
		 Color color;
		 if (type == SquareType.SPACE){
		 	color  =Color.WHITE;
		 }else  if (type == SquareType.WALL){
		 	color = Color.DARK_GRAY;
		 }else {
		 	color = Color.RED;
		 }
		 g.setColor(color);
		 g.fillRect(startX + column*MazeSquare.dimension, startY+row*MazeSquare.dimension, dimension, dimension);
		 g.setColor(Color.BLACK);
		 g.drawRect(startX + column*MazeSquare.dimension, startY+row*MazeSquare.dimension, dimension, dimension);
	 }

}
