import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.LineBorder;

public class SwingTest extends JFrame implements ActionListener {
	
    private MyPaintingPanel paintingPanel;
    private JPanel panel;
    private JComboBox<MyShape> jcomboShape;
    private JComboBox<MyColor> jcomboColor;
    private JCheckBox checkbox;
	private final JScrollPane scrollpane;
    private JButton button;
	private JSlider slider;
    private MyShape[] shape;
    private MyColor[] colors;


    public SwingTest() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,550);
		this.setLayout(null);
		getContentPane();
		
		colors=new MyColor[5];
        colors[0]=new MyColor("red", Color.red);
        colors[1]=new MyColor("white", Color.white);
        colors[2]=new MyColor("magenta", Color.magenta);
        colors[3]=new MyColor("black", Color.black);
        colors[4]=new MyColor("yellow", Color.yellow);
		
		jcomboColor=new JComboBox<MyColor>(colors);	
		jcomboColor.setMaximumSize(new Dimension(120,50));
		jcomboColor.setAlignmentX(CENTER_ALIGNMENT);

		/*SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				paintingPanel=new MyPaintingPanel(5,5,475,300);
			}
		});*/
		paintingPanel=new MyPaintingPanel(5,5,475,300);
		//Thread t=new Thread(paintingPanel);
		
        //paintingPanel = new MyPaintingPanel(5,5,475,300);
		//paintingPanel.setBorder(LineBorder.createBlackLineBorder());
        //paintingPanel.setBackground(Color.gray);
		scrollpane=new JScrollPane(paintingPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
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
			
        panel = new JPanel();
		panel.setBounds(5,305,475,205);
		panel.setAlignmentX(CENTER_ALIGNMENT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		slider=new JSlider(JSlider.HORIZONTAL,-50,300,0);
		slider.setMajorTickSpacing(50);
		slider.setMinorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setAlignmentX(CENTER_ALIGNMENT);
		slider.addChangeListener(new SliderListener());
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(slider);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
        panel.add(jcomboColor);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(jcomboShape);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        checkbox = new JCheckBox("fill out");
		checkbox.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(checkbox);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        button = new JButton("Button");
		button.setAlignmentX(CENTER_ALIGNMENT);
        button.addActionListener(this);
        panel.add(button);
        this.add(panel);
		
		int[]x ={(int)(scrollpane.getBounds().getWidth()/2),(int)(scrollpane.getBounds().getWidth()/3),(int)(scrollpane.getBounds().getWidth()*2/3)};
		int[]y ={(int)(scrollpane.getBounds().getHeight()/3)+35,(int)(scrollpane.getBounds().getHeight()*2/3)+35,(int)(scrollpane.getBounds().getHeight()*2/3)+35};

		shape[1].init(x,y);
		
        this.setVisible(true);
		this.setLocationRelativeTo(null);
        this.setResizable(false);
		
		//t.run();
    }

    public void actionPerformed(ActionEvent e) {
		MyShape myShape=(MyShape)jcomboShape.getSelectedItem();
		MyColor myColor=(MyColor)jcomboColor.getSelectedItem();
		paintingPanel.setParameters(checkbox.isSelected(),myShape,myColor);
        paintingPanel.repaint();
    }
		
	class SliderListener implements ChangeListener{
		public void stateChanged(ChangeEvent e){
			for (int i=0;i<shape.length;i++){
				//System.out.println(s.toString());
				if (shape[i].toString().equals("Rectangle")){
					//System.out.println("Slider value="+slider.getValue()+" X="+shape[i].getWidth()+" Y="+shape[i].getHeight());
					shape[i].setSize(shape[i].getInitWidth()+slider.getValue(),shape[i].getInitHeight()+slider.getValue());	
					MyShape myShape=(MyShape)jcomboShape.getSelectedItem();
					MyColor myColor=(MyColor)jcomboColor.getSelectedItem();
					paintingPanel.setParameters(checkbox.isSelected(),myShape,myColor);
					paintingPanel.repaint();
				} else {
					shape[i].setSize(slider.getValue());
					MyShape myShape=(MyShape)jcomboShape.getSelectedItem();
					MyColor myColor=(MyColor)jcomboColor.getSelectedItem();
					paintingPanel.setParameters(checkbox.isSelected(),myShape,myColor);
					paintingPanel.repaint();
				}
			}
		}		
	}		

    public static void main(String[] args) {
        SwingTest test = new SwingTest();

    }
}