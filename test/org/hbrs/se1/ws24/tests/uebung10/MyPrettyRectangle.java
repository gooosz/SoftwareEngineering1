package org.hbrs.se1.ws24.tests.uebung10;

public class MyPrettyRectangle {
	private double x1, y1; // Punkt links unten
	private double x2, y2; // Punkt rechts oben

	public MyPrettyRectangle(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public double x1() {
		return x1;
	}
	public double y1() {
		return y1;
	}
	public double x2() {
		return x2;
	}
	public double y2() {
		return y2;
	}

	// returns true if this contains other completely
	public boolean contains(MyPrettyRectangle other) {
		return this.x1 <= other.x1 && this.y1 <= other.y2
			&& this.x2 >= other.x2 && this.y2 >= other.y2;
	}

	// returns center point
	public MyPoint getCenter() {
		// center is in the middle of diagonal line between (x1,y1), (x2,y2)
		return new MyPoint((x1+x2)/2, (y1+y2)/2);
	}

	// returns area of rectangle
	public double getArea() {
		// length * width
		return (x2-x1) * (y2-y1);
	}

	// returns perimeter of rectangle
	public double getPerimeter() {
		// (lenth + width) * 2
		return ((x2-x1) + (y2-y1)) * 2;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof MyPrettyRectangle other) {
			return this.x1() == other.x1() && this.y1() == other.y1()
				&& this.x2() == other.x2() && this.y2() == other.y2();
		}
		return false;
	}

	@Override
	public String toString() {
		return "(" + x1() + ", " + y1() + ", " + x2() + ", " + y2() + ")";
	}

}
