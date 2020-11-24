package area;

public class Area {

	public static void main(String[] args) {
		double area;
		System.out.println("AREA CALCULATOR ");
		System.out.println("There will be a chosen shape to calculate its area :");
		System.out.println("1-rectangle");
		System.out.println("2-square");
		System.out.println("3-circle");
		if(args.length==2 || args.length==3)
		{  String shape = args[0]; 
			if(shape.compareTo("1")== 0) {
			System.out.println("the chosen shape is rectangle:");
			double width= Double.parseDouble(args[1]);
			System.out.println("the width is :"+ args[1]);
			double length= Double.parseDouble(args[2]);
			System.out.println("the length is :"+ args[2]);
			area=width*length;
			System.out.println("the rectangle area is :"+area);
			}
			else if(shape.compareTo("2")== 0) {
				    System.out.println("the chosen shape is square:");
				    double side= Double.parseDouble(args[1]);
					System.out.println("the side length is  :"+ args[1]);
					area=side*side;
					System.out.println("the square area is :"+area);
					}
			 else if(shape.compareTo("3")== 0)
		     {      System.out.println("the chosen shape is circle:");
				    double pi=3.14;
		    	    double r= Double.parseDouble(args[1]);
					System.out.println("the raduis length is  :"+ args[1]);
					area=pi*r*r;
					System.out.println("the circle area is :"+area);}
			 else {
				 System.out.println("ERROR");
			 }
					

		     }
		else {System.out.println("WRONG ENTRY , TRY AGAIN");}
		
					
			

	}

}
