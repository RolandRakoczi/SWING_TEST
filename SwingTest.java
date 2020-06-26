import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingTest extends JFrame implements ActionListener{
	JPanel panel;
	JPanel subpanel;
	JPanel subpanel2;
	JComboBox<Shape> jcomboShape;
	JComboBox<String> jcomboColor;
	JCheckBox checkbox;
	JButton button;
	Shape[] shape;
	Color[] color;
	String[] col={"red","black","orange","green","yellow"};
	
	public SwingTest(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel=new JPanel();
		this.add(panel);
		
		shape=new Shape[2];
		shape[0]=new Rectangle(100,50);
		shape[1]=new Rectangle(200,100);
		
		/*color=new Color[5];
		color[0]=Color.red;
		color[1]=Color.black;
		color[2]=Color.orange;
		color[3]=Color.green;
		color[4]=Color.yellow;*/
		
		
		
		//jcomboColor=new JComboBox<Color>(color);
		jcomboColor=new JComboBox<String>(col);
		jcomboShape=new JComboBox<Shape>(shape);
		
		subpanel=new JPanel();
		subpanel.setPreferredSize(new Dimension(500,300));
		subpanel.setBackground(Color.gray);
		panel.add(subpanel,BorderLayout.CENTER);
		
		subpanel2=new JPanel();
		subpanel2.setLayout(new BoxLayout(subpanel2,BoxLayout.Y_AXIS));
		subpanel2.add(Box.createRigidArea(new Dimension(0,10)));
		subpanel2.add(jcomboColor);
		subpanel2.add(Box.createRigidArea(new Dimension(0,5)));
		subpanel2.add(jcomboShape);
		subpanel2.add(Box.createRigidArea(new Dimension(0,5)));
		
		checkbox=new JCheckBox("fill out");
		subpanel2.add(checkbox);
		subpanel2.add(Box.createRigidArea(new Dimension(0,5)));
		
		button=new JButton("Button");
		button.addActionListener(this);
		subpanel2.add(button);
		
		panel.add(subpanel2,BorderLayout.SOUTH);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		
		g.setColor(Color.jcomboColor.getSelectedItem()); /*ezt kellene atirni*/
		
		Shape s = (Shape)jcomboShape.getSelectedItem();
		Rectangle rect=s.getBounds();
		Insets insets =subpanel.getInsets();
		
		if (checkbox.isSelected()){
		g.fillRect((int)(subpanel.getWidth()-insets.left-insets.right)/2-(int)(rect.getWidth()/2),(int)(subpanel.getHeight()-insets.top-insets.bottom)/2/*-(int)(rect.getHeight()/2)*/,(int)rect.getWidth(),(int)rect.getHeight());	
		} else{
		g.drawRect((int)(subpanel.getWidth()-insets.left-insets.right)/2-(int)(rect.getWidth()/2),(int)(subpanel.getHeight()-insets.top-insets.bottom)/2,(int)rect.getWidth(),(int)rect.getHeight());
		}
	}
	
	/*public void update(Graphics g){
		paint(g);
	}*/
	
	public void actionPerformed(ActionEvent e){
		repaint();
	}
	
	public static void main(String[] args){
		SwingTest test=new SwingTest();
		test.setBounds(50,50,500,500);
		test.setVisible(true);
		test.setResizable(false);
	}
}