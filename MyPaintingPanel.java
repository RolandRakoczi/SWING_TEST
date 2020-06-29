import java.awt.*;
import java.awt.color.ColorSpace;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class MyPaintingPanel extends JPanel /*implements Runnable*/{
	
	private boolean isSelected;
	private MyShape shape;
	private MyColor color;
	
	public MyPaintingPanel(int x,int y,int v,int z){

		super.setBounds(x,y,v,z);
		super.setPreferredSize(new Dimension(v,z));
		System.out.println(this.getBounds().getWidth()+" "+this.getBounds().getHeight());
		init();
	}
	
	public void init(){
		this.setBorder(LineBorder.createBlackLineBorder());
        this.setBackground(Color.gray);
		System.out.println(this.getBackground());
	}
	
	/*public void run(){
		repaint();
	}*/
	
	public void setParameters(boolean isSelected,MyShape shape,MyColor color){
		this.isSelected=isSelected;
		this.shape=shape;
		this.color=color;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);	
		Color c= (Color)color.getColor();
        g.setColor(c);
		
		if (shape.toString().equals("Rectangle")){
			//int sum=(int) (subpanel.getHeight()) / 2 -(int) (s.getHeight()/2);

			if (isSelected) {
				g.fillRect((int) (this.getBounds().getWidth()) / 2 - (int) (shape.getWidth() / 2), (int) (this.getBounds().getHeight()) / 2 -(int) (shape.getHeight()/2), (int) shape.getWidth(), (int) shape.getHeight());
				} else {
					g.drawRect((int) (this.getBounds().getWidth()) / 2 - (int) (shape.getWidth() / 2), (int) (this.getBounds().getHeight()) / 2-(int) (shape.getHeight()/2), (int) shape.getWidth(), (int) shape.getHeight());
				}
			}			
		
			if (this.getBounds().getWidth()<(shape.getWidth()+50)){
				this.setSize((int)shape.getWidth()+1,(int)this.getBounds().getHeight());
			}
			
			if (this.getBounds().getHeight()<(shape.getHeight()+50)){
				this.setSize((int)this.getBounds().getWidth(),(int)shape.getHeight()+1);
			}
			
			repaint();
				
		if (shape.toString().equals("Triangle")){
			
				if (isSelected) {
					g.fillPolygon(shape.getXcoord(),shape.getYcoord(),3);
				} else {
					g.drawPolygon(shape.getXcoord(),shape.getYcoord(),3);
				}
		}
		
	}
}