import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.LineBorder;

public class SwingTest extends JFrame implements ActionListener {
    private JPanel panel;
    private JPanel subpanel;
    private JPanel subpanel2;
    private JComboBox<MyShape> jcomboShape;
    private JComboBox<MyColor> jcomboColor;
    private JCheckBox checkbox;
	private JScrollPane scrollpane;
    private JButton button;
	private JSlider slider;
    private MyShape[] shape;
    private MyColor[] colors;


    public SwingTest() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,550);
		this.setLayout(null);
		
		colors=new MyColor[5];
        colors[0]=new MyColor("red", Color.red);
        colors[1]=new MyColor("white", Color.white);
        colors[2]=new MyColor("magenta", Color.magenta);
        colors[3]=new MyColor("black", Color.black);
        colors[4]=new MyColor("yellow", Color.yellow);
		
		jcomboColor=new JComboBox<MyColor>(colors);	
		jcomboColor.setMaximumSize(new Dimension(120,50));
		jcomboColor.setAlignmentX(CENTER_ALIGNMENT);

        subpanel = new JPanel();
		subpanel.setBorder(LineBorder.createBlackLineBorder());
        subpanel.setBackground(Color.gray);
		scrollpane=new JScrollPane(subpanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setBounds(5,5,475,300);
		this.add(scrollpane);

		shape = new MyShape[2];
        Shape shape1 = new Rectangle(200, 100);
		shape[0]=new MyShape("Rectangle",shape1);
		Shape shape2 = new Polygon();
        shape[1] = new MyShape("Triangle",shape2);	
		
		jcomboShape = new JComboBox<MyShape>(shape);
		jcomboShape.setMaximumSize(new Dimension(120,50));
		jcomboShape.setAlignmentX(CENTER_ALIGNMENT);
			
        subpanel2 = new JPanel();
		subpanel2.setBounds(5,305,475,205);
		subpanel2.setAlignmentX(CENTER_ALIGNMENT);
        subpanel2.setLayout(new BoxLayout(subpanel2, BoxLayout.Y_AXIS));
		slider=new JSlider(JSlider.HORIZONTAL,-50,300,0);
		slider.setMajorTickSpacing(50);
		slider.setMinorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setAlignmentX(CENTER_ALIGNMENT);
		slider.addChangeListener(new SliderListener());
        subpanel2.add(Box.createRigidArea(new Dimension(0, 10)));
		subpanel2.add(slider);
		subpanel2.add(Box.createRigidArea(new Dimension(0,5)));
        subpanel2.add(jcomboColor);
        subpanel2.add(Box.createRigidArea(new Dimension(0, 5)));
        subpanel2.add(jcomboShape);
        subpanel2.add(Box.createRigidArea(new Dimension(0, 5)));
        checkbox = new JCheckBox("fill out");
		checkbox.setAlignmentX(CENTER_ALIGNMENT);
        subpanel2.add(checkbox);
        subpanel2.add(Box.createRigidArea(new Dimension(0, 5)));
        button = new JButton("Button");
		button.setAlignmentX(CENTER_ALIGNMENT);
        button.addActionListener(this);
        subpanel2.add(button);
        this.add(subpanel2);
		
		int[]x ={(int)(scrollpane.getBounds().getWidth()/2),(int)(scrollpane.getBounds().getWidth()/3),(int)(scrollpane.getBounds().getWidth()*2/3)};
		int[]y ={(int)(scrollpane.getBounds().getHeight()/3)+35,(int)(scrollpane.getBounds().getHeight()*2/3)+35,(int)(scrollpane.getBounds().getHeight()*2/3)+35};

		shape[1].init(x,y);

    }

    public void paint(Graphics g) {
        super.paint(g);

        MyColor selectedItem = (MyColor)jcomboColor.getSelectedItem();
        g.setColor(selectedItem.getColor());

        MyShape s = (MyShape) jcomboShape.getSelectedItem();
		
		if (s.toString().equals("Rectangle")){
			int sum=(int) (subpanel.getHeight()) / 2 -(int) (s.getHeight()/2);
			System.out.println("Subpanel height="+subpanel.getHeight()+" Rectangle height="+s.getHeight()+" Form="+sum);
			System.out.println("Subpanel coords : x="+subpanel.getBounds().getX()+" y="+subpanel.getBounds().getY());
			if (checkbox.isSelected()) {
				g.fillRect((int) (scrollpane.getBounds().getWidth()) / 2 - (int) (s.getWidth() / 2)+12, (int) (scrollpane.getBounds().getHeight()) / 2 -(int) (s.getHeight()/2.5-4), (int) s.getWidth(), (int) s.getHeight());
				} else {
					g.drawRect((int) (scrollpane.getBounds().getWidth()) / 2 - (int) (s.getWidth() / 2)+12, (int) (scrollpane.getBounds().getHeight()) / 2-(int) (s.getHeight()/2.5-4), (int) s.getWidth(), (int) s.getHeight());
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
		
	class SliderListener implements ChangeListener{
		public void stateChanged(ChangeEvent e){
			for (int i=0;i<shape.length;i++){
				//System.out.println(s.toString());
				if (shape[i].toString().equals("Rectangle")){
					//System.out.println("Slider value="+slider.getValue()+" X="+shape[i].getWidth()+" Y="+shape[i].getHeight());
					shape[i].setSize(shape[i].getInitWidth()+slider.getValue(),shape[i].getInitHeight()+slider.getValue());
					repaint();
				} else {
					shape[i].setSize(slider.getValue());
				}
			}
		}		
	}		

    public static void main(String[] args) {
        SwingTest test = new SwingTest();
        test.setVisible(true);
		test.setLocationRelativeTo(null);
        test.setResizable(false);
    }
}