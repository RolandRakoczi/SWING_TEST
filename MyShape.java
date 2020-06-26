import java.awt.*;

public class MyShape {
    private String name;
    private Shape shape;
	private int width;
	private int height;
	private int[] xcoord;
	private int[] ycoord;

    public MyShape(String name, Shape shape) {
        this.name = name;
        this.shape = shape;
		
		if (name.equals("Rectangle")){
			this.width=(int)shape.getBounds().getWidth();
			this.height=(int)shape.getBounds().getHeight();
		}
    }


    public Shape getShape() {
        return this.shape;
    }
	
	public void setSize(int[] x,int[] y){
		xcoord = new int[3];
		ycoord = new int[3];
		for (int i=0; i<3; i++){
			this.xcoord[i]=x[i];
			this.ycoord[i]=y[i];
		}
	}
	
	public int[] getXcoord(){
		return this.xcoord;
	}
	
	public int[] getYcoord(){
		return this.ycoord;
	}	

    public String toString() {
        return this.name;
    }
}