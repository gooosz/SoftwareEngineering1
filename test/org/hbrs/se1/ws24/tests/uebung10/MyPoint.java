package org.hbrs.se1.ws24.tests.uebung10;

public record MyPoint(double x, double y) {
	@Override
	public boolean equals(Object o) {
		if (o instanceof MyPoint other) {
			return this.x() == other.x() && this.y() == other.y();
		}
		return false;
	}
}