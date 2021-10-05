package TwoD.definition;

import IDefinition.IPoint;
import IDefinition.IShape;

import java.util.List;

public class Point2D implements IPoint {
    private final double x;
    private final double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public List<Double> toList() {
        return List.of(x,y);
    }

    @Override
    public double distance(IShape shape) {
        if(shape instanceof Point2D point) {
            return Math.sqrt(Math.pow(point.getX()-x,2)+Math.pow(point.getY()-y,2));
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj instanceof Point2D){
            return x == ((Point2D) obj).getX() && y == ((Point2D) obj).getY();
        }
        else return false;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(x)+Double.hashCode(y);
    }

    @Override
    public String toString() {
        return String.format("Point2D (%f, %f)",x,y);
    }
}
