package edu.miamioh.kollinsa;

import javax.swing.JPanel;

/**
 * @author Sarah Kollins
 * Got code from Big Java Late Objects by Cay Horstmann
 *
 */
/**
 * @author Sarah Kollins
 *
 */
public class Queen extends JPanel{

	private int row;
	private int column;
	
	/**
	 * @param r of type int
	 * @param c of type int
	 */
	public Queen(int r, int c){
		row=r;
		column=c;
	}
	/**
	 * Checks if two queens are in range to attack
	 * @param q of type Queen
	 * @return boolean expression
	 */
	public boolean attacks(Queen q){
		return row==q.row
				|| column==q.column
				|| Math.abs(row - q.row) == Math.abs(column - q.column);
	}
	
	public String toString(){
		return "" + "12345678".charAt(column)+","+(row+1);

	}
}
