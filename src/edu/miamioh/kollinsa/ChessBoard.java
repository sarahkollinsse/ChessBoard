package edu.miamioh.kollinsa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ChessBoard extends JFrame{

	private static final int WIDTH = 1000;
	private static final int HEIGHT = 1000;
	private JPanel centerPanel = new JPanel();
	private Queen[][] panel = new Queen[8][8];
	protected Queen q;
	private MouseListener ml;
	private ActionListener al;
	private JButton safe;
	private JButton tip;
	protected JLabel buttonLabel = new JLabel("");
	protected ImageIcon queen=new ImageIcon("queen.jpg");
	protected JLabel image = new JLabel();
	protected int counter = 0;
	protected int queenC =0;
	protected ArrayList<Queen> queenList = new ArrayList<Queen>();

	public ChessBoard(){
		ml= new Listener();
		al = new ButtonListener();
		image.setIcon(queen);
		createBoard();
		createButtons();
	}
	/**
	 * Creates an 8x8 Chessboard with JPanels
	 * 
	 */
	public void createBoard(){
		int count=0;
		centerPanel.setLayout(new GridLayout(9,8));
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				panel[i][j] = new Queen(i,j);
				if(count%2==0){
					panel[i][j].setBackground(Color.black);
				}
				count++;
				centerPanel.add(panel[i][j]);
				panel[i][j].addMouseListener(ml);
			}
			count++;
		}
	}

	/**
	 * Creates the safe and tip buttons
	 * Adds buttons to the main panel
	 */
	public void createButtons(){
		safe = new JButton("Safe");
		safe.addActionListener(al);
		centerPanel.add(safe);
		add(centerPanel);
		tip = new JButton("Tip");
		tip.addActionListener(al);
		centerPanel.add(tip);
		add(centerPanel);
	}

	public class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			if(e.getSource() instanceof JButton){
				if(e.getSource().equals(safe)){
					/*Safe button doesn't display right away on the first click, so
					 * hit the safe button before adding any queens, and the button
					 * works fine for the rest of time when it is clicked*/
					for(int s=0;s<queenList.size()-1;s++){
						for(int k =s+1; k<queenList.size();k++){
							if(queenList.get(s).attacks(queenList.get(k))){
								if(queenList.size()>1){
									queenC++;
								}
							}
						}
					}
					if(counter==1){
						buttonLabel.setText("Safe");
						centerPanel.add(buttonLabel);
					}
					if(queenC==0){

						buttonLabel.setText("Safe");
						centerPanel.add(buttonLabel);

					}
					else{

						buttonLabel.setText("Not safe");
						centerPanel.add(buttonLabel);
					}
				}
				else{
					for(int k =0; k<8;k++){
						for(int f =0;f<8;f++){
							q= new Queen(k,f);
							int tipCounter =0;
							for(int s=0;s<queenList.size();s++){
								if(q.attacks(queenList.get(s))==false){
									tipCounter++;
								}
							}
							if(tipCounter==queenList.size()){
								//Tip is column by row
								buttonLabel.setText("Tip: ["+q.toString()+"]");
								centerPanel.add(buttonLabel);
							}	
						}
					}
				}
			}
		}
	}

	public class Listener implements MouseListener{


		public void mouseClicked(MouseEvent e) {

			if (e.getSource() instanceof JPanel){
				Queen panel2 = (Queen)e.getSource();

				if(counter<8){
					if(panel2.getComponentCount()==0){		
						queenC=0;
						panel2.add(image);
						counter++;
						queenList.add(panel2);
						panel2.revalidate();
						panel2.repaint();

					}
					else{

						counter--;
						queenC=0;
						panel2.remove(image);
						queenList.remove(panel2);
						queenList.remove(panel2);
						panel2.revalidate();
						panel2.repaint();
					}
				}
				else if(counter <=8){
					counter--;
					queenC=0;
					panel2.remove(image);
					queenList.remove(panel2);
					queenList.remove(panel2);
					panel2.revalidate();
					panel2.repaint();
				}
			}
		}
		public void mouseEntered(MouseEvent e){}	
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}		
	}

	public static void main(String [] args){
		//JFrame implementation
		JFrame frame = new ChessBoard();
		frame.setTitle("Chessboard");
		frame.setSize(WIDTH,HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
