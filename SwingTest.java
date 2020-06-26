import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingTest extends JFrame implements ActionListener {
    private JPanel panel;
    private JPanel subpanel;
    private JPanel subpanel2;
    private JComboBox<MyShape> jcomboShape;
    private JComboBox<MyColor> jcomboColor;
    private JCheckBox checkbox;
    private JButton button;
    private MyShape[] shape;
    private MyColor[] colors;


    public SwingTest() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        this.add(panel);
		
		colors=new MyColor[5];
        colors[0]=new MyColor("red", Color.red);
        colors[1]=new MyColor("white", Color.white);
        colors[2]=new MyColor("magenta", Color.magenta);
        colors[3]=new MyColor("black", Color.black);
        colors[4]=new MyColor("yellow", Color.yellow);
		
		jcomboColor=new JComboBox<MyColor>(colors);	

        subpanel = new JPanel();
        subpanel.setPreferredSize(new Dimension(500, 300));
        subpanel.setBackground(Color.gray);
		panel.add(subpanel, BorderLayout.CENTER);

		shape = new MyShape[2];
        Shape shape1 = new Rectangle(200, 100);
		shape[0]=new MyShape("Rectangle",shape1);
		Shape shape2 = new Polygon();
        shape[1] = new MyShape("Triangle",shape2);	
		
		jcomboShape = new JComboBox<MyShape>(shape);
			
        subpanel2 = new JPanel();
        subpanel2.setLayout(new BoxLayout(subpanel2, BoxLayout.Y_AXIS));
        subpanel2.add(Box.createRigidArea(new Dimension(0, 10)));
        subpanel2.add(jcomboColor);
        subpanel2.add(Box.createRigidArea(new Dimension(0, 5)));
        subpanel2.add(jcomboShape);
        subpanel2.add(Box.createRigidArea(new Dimension(0, 5)));
        checkbox = new JCheckBox("fill out");
        subpanel2.add(checkbox);
        subpanel2.add(Box.createRigidArea(new Dimension(0, 5)));
        button = new JButton("Button");
        button.addActionListener(this);
        subpanel2.add(button);
        panel.add(subpanel2, BorderLayout.SOUTH);
		
		int[]x ={(int)(subpanel.getPreferredSize().getWidth()/2),(int)(subpanel.getPreferredSize().getWidth()/3),(int)(subpanel.getPreferredSize().getWidth()*2/3)};
		int[]y ={(int)(subpanel.getPreferredSize().getHeight()/3),(int)(subpanel.getPreferredSize().getHeight()*2/3),(int)(subpanel.getPreferredSize().getHeight()*2/3)};

		shape[1].setSize(x,y);

    }

    public void paint(Graphics g) {
        super.paint(g);

        MyColor selectedItem = (MyColor)jcomboColor.getSelectedItem();
        g.setColor(selectedItem.getColor());

        MyShape s = (MyShape) jcomboShape.getSelectedItem();
		
		if (s.toString().equals("Rectangle")){
			Rectangle rect = s.getShape().getBounds();
        
			if (checkbox.isSelected()) {
				g.fillRect((int) (subpanel.getWidth()) / 2 - (int) (rect.getWidth() / 2), (int) (subpanel.getHeight()) / 2, (int) rect.getWidth(), (int) rect.getHeight());
				} else {
					g.drawRect((int) (subpanel.getWidth()) / 2 - (int) (rect.getWidth() / 2), (int) (subpanel.getHeight()) / 2, (int) rect.getWidth(), (int) rect.getHeight());
				}
			}			
		
		if (s.toString().equals("Triangle")){
			
				if (checkbox.isSelected()) {
					g.fillPolygon(s.getXcoord(),s.getYcoord(),3);
				} else {
					g.drawPolygon(s.getXcoord(),s.getYcoord(),3);
				}
		}
	}
	

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public static void main(String[] args) {
        SwingTest test = new SwingTest();
        test.setBounds(50, 50, 500, 500);
        test.setVisible(true);
        test.setResizable(false);
    }
}