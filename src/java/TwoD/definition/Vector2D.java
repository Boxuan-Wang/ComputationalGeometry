package TwoD.definition;

import IDefinition.IVector;
import javax.naming.OperationNotSupportedException;

public class Vector2D implements IVector {

    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(@org.jetbrains.annotations.NotNull Point2D startPoint, @org.jetbrains.annotations.NotNull Point2D endPoint){
        this.x = endPoint.getX()- startPoint.getX();
        this.y = endPoint.getY() - startPoint.getY();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean parallel(IVector vector) {
        if(!(vector instanceof Vector2D))
            throw new IllegalArgumentException("Dimension not match");
        else
            return x* ((Vector2D) vector).getY() == y* ((Vector2D) vector).getX();
    }

    @Override
    public double dotProduct(IVector vector) {
        if(!(vector instanceof Vector2D))
            throw new IllegalArgumentException("Dimension not match");

        else
            return x* ((Vector2D) vector).getX()+y* ((Vector2D) vector).getY();
    }

    @Override
    public IVector uniform() throws OperationNotSupportedException {
        if(modular()==0)
            throw new OperationNotSupportedException("Zero vector.");
        else{
            return this.multiply(1/modular());
        }
    }

    @Override
    public double modular() {
        return Math.sqrt(x*x+y*y);
    }

    @Override
    public IVector add(IVector vector) {
        if(vector instanceof Vector2D){
            return new Vector2D(x + ((Vector2D) vector).x, y + ((Vector2D) vector).y);
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }

    @Override
    public IVector multiply(double a) {
        return new Vector2D(x*a, y*a);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj instanceof Vector2D)
            return x == ((Vector2D) obj).getX() && y == ((Vector2D) obj).getY();
        else return false;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(x)+Double.hashCode(y);
    }

    @Override
    public String toString() {
        return String.format("Vector2D (%f, %f)",x,y);
    }

    public Vector2D computePerpendicular(){
        return new Vector2D(getY(), -1*getX());
    }
}
