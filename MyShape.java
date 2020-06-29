import java.awt.*;

public class MyShape {
    private String name;
    private Shape shape;
	private int width;
	private int initWidth;
	private int height;
	private int initHeight;
	private int[] xcoord;
	private int[] initXcoord;
	private int[] ycoord;
	private int[] initYcoord;

    public MyShape(String name, Shape shape) {
        this.name = name;
        this.shape = shape;
		if (name.equals("Rectangle")){
			this.width=(int)shape.getBounds().getWidth();
			this.height=(int)shape.getBounds().getHeight();
			this.initWidth=this.width;
			this.initHeight=this.height;
		}
    }

    public Shape getShape() {
        return this.shape;
    }
	
	public void init(int[] x,int[] y){
		initXcoord=new int[3];
		initYcoord=new int[3];
		for (int i=0; i<3;i++){
			this.initXcoord[i]=x[i];
			this.initYcoord[i]=y[i];
		}	
		setSize(initXcoord,initYcoord);
	}
	
	public void setSize(int[] x,int[] y){
		
		xcoord = new int[3];
		ycoord = new int[3];
		for (int i=0; i<3; i++){
			this.xcoord[i]=x[i];
			this.ycoord[i]=y[i];
		}
	}
	
	public void setSize(int x){
			this.xcoord[0]=this.initXcoord[0];
			this.ycoord[0]=this.initYcoord[0]-x;
			this.xcoord[1]=this.initXcoord[1]-x;
			this.ycoord[1]=this.initYcoord[1]+x;
			this.xcoord[2]=this.initXcoord[2]+x;
			this.ycoord[2]=this.initYcoord[2]+x;
				
	}
	
	public void setSize(int x,int y){
		this.width=x;
		this.height=y;
	}
	
	public int getInitWidth(){
		return this.initWidth;
	}
	
	public int getInitHeight(){
		return this.initHeight;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public int[] getInitXcoord(){
		return initXcoord;
	}
	
	public int[] getInitYcoord(){
		return initYcoord;
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