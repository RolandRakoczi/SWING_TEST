import java.awt.*;
import java.awt.color.ColorSpace;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class MyPaintingPanel extends JPanel {
	
	private boolean isSelected;
	private MyShape shape;
	private MyColor color;
	
	public MyPaintingPanel(int x,int y,int v,int z,boolean isSelected,MyShape shape,MyColor color){

		super.setBounds(x,y,v,z);
		super.setPreferredSize(new Dimension(v,z));
		System.out.println(this.getBounds().getWidth()+" "+this.getBounds().getHeight());
		this.isSelected=isSelected;
		this.shape=shape;
		this.color=color;
		init();
	}
	
	public void init(){
		this.setBorder(LineBorder.createBlackLineBorder());
        this.setBackground(Color.gray);
		repaint();
	}
	
	public void setParameters(boolean isSelected,MyShape shape,MyColor color){
		this.isSelected=isSelected;
		this.shape=shape;
		this.color=color;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
        g.setColor(color.getColor());	
		if (shape.toString().equals("Rectangle")){
			if (isSelected) {
				g.fillRect((int) (this.getBounds().getWidth()) / 2 - (int) (shape.getWidth() / 2), (int) (this.getBounds().getHeight()) / 2 -(int) (shape.getHeight()/2), (int) shape.getWidth(), (int) shape.getHeight());
				} else {
					g.drawRect((int) (this.getBounds().getWidth()) / 2 - (int) (shape.getWidth() / 2), (int) (this.getBounds().getHeight()) / 2-(int) (shape.getHeight()/2), (int) shape.getWidth(), (int) shape.getHeight());
				}
		}					
		if (shape.toString().equals("Triangle")){	
				if (isSelected) {
					g.fillPolygon(shape.getXcoord(),shape.getYcoord(),3);
				} else {
					g.drawPolygon(shape.getXcoord(),shape.getYcoord(),3);
				}
		}
		repaint();
	}
}