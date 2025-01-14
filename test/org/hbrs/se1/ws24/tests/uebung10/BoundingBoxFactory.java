package org.hbrs.se1.ws24.tests.uebung10;

public class BoundingBoxFactory {
	public static MyPrettyRectangle boundingBox(MyPrettyRectangle[] rects) {
		if (rects == null) {
			return null;
		}

		double min_x1 = 0;
		double min_y1 = 0;
		double max_x2 = 0;
		double max_y2 = 0;
		if (rects.length > 0) {
			// first item initializes the bounding box
			min_x1 = rects[0].x1();
			min_y1 = rects[0].y1();
			max_x2 = rects[0].x2();
			max_y2 = rects[0].y2();
		}
		for (MyPrettyRectangle rect : rects) {
			if (rect.x1() < min_x1) {
				min_x1 = rect.x1();
			}
			if (rect.y1() < min_y1) {
				min_y1 = rect.y1();
			}
			if (rect.x2() > max_x2) {
				max_x2 = rect.x2();
			}
			if (rect.y2() > max_y2) {
				max_y2 = rect.y2();
			}
		}
		return new MyPrettyRectangle(min_x1, min_y1, max_x2, max_y2);
	}
}
