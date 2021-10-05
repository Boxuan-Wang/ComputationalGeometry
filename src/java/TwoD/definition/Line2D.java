package TwoD.definition;

import IDefinition.*;
import org.jetbrains.annotations.NotNull;

import javax.sound.sampled.Line;

public class Line2D implements ILine {
    private final Vector2D direction;
    private final Point2D passPoint;

    public Line2D(@NotNull Vector2D direction, @NotNull Point2D passPoint) {
        if(direction.isZeroVector())
            throw new IllegalArgumentException("Try to initialise from a zero vector.");
        this.direction = direction;
        this.passPoint = passPoint;
    }

    public Line2D(@NotNull Point2D p1, @NotNull Point2D p2){
        if(p1.equals(p2))
            throw new IllegalArgumentException("Try to initialise a ray from two same points.");
        this.direction = new Vector2D(p1,p2);
        this.passPoint = p1;
    }

    @Override
    public Point2D getOnePassPoint() {
        return passPoint;
    }

    @Override
    //when l == this, return false
    public boolean parallel(ILine l) {
        if(l instanceof Line2D){
            return (!l.equals(this)) && ((Line2D)l).direction.parallel(direction);
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }

    @Override
    public boolean pass(IPoint point) {
        if(point instanceof Point2D){
            return new Vector2D(passPoint, (Point2D) point).parallel(direction);
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }

    @Override
    public IShape intersect(ILine line){
        if(line instanceof Line2D){
            if(((Line2D) line).direction.parallel(direction)){
                if(!this.equals(line))
                    return null;
                else
                    return this;
            }
            else{
                double v_x1 = this.direction.getX();
                double v_y1 = this.direction.getY();

                double v_x2 = ((Line2D) line).direction.getX();
                double v_y2 = ((Line2D) line).direction.getY();

                double a1 = v_y1*passPoint.getX() - v_x1*passPoint.getY();
                double a2 = v_y2*((Line2D)line).passPoint.getX() - v_x2*((Line2D)line).passPoint.getY();

                double d0 = v_x1*v_y2 - v_y1*v_x2;
                double dx = a2*v_x1 - a1*v_x2;
                double dy = v_y1*a2 - v_y2*a1;

                double x = dx/d0;
                double y = dy/d0;

                return new Point2D(x,y);
            }
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }

    @Override
    public IShape intersect(IRay ray) {
        if(ray instanceof Ray2D){
            if(ray.intersect(new Ray2D(passPoint,direction))!=null ||
                    ray.intersect(new Ray2D(passPoint, direction.multiply(-1)))!=null){
                return this.intersect(
                        new Line2D(
                                ((Ray2D) ray).getDirection(),
                                ((Ray2D) ray).getStartPoint()));
            }
            else return null;
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }


    @Override
    public IShape intersect(ISegment segment) {
        if(segment instanceof Segment2D){
            boolean intersect = segment.intersect(new Ray2D(passPoint,direction))!=null ||
                    segment.intersect(new Ray2D(passPoint, direction.multiply(-1)))!=null;
            if(intersect){
                if(this.pass(((Segment2D) segment).getP1())&&this.pass(((Segment2D) segment).getP2()))
                    return (Segment2D)segment;
                else return this.intersect(
                        new Line2D(
                                (Vector2D) segment.getDirection(),
                                (Point2D) segment.getEnds()[0]));
            }
            else return null;
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }

    @Override
    public Vector2D getDirection() {
        return direction;
    }

    @Override
    public int hashCode() {
        return this.direction.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj instanceof Line2D){
            return ((Line2D) obj).direction.parallel(this.direction) && this.pass(((Line2D) obj).getOnePassPoint());
        }
        else return false;
    }

    @Override
    public String toString(){
        return String.format("Line2D %f x %+f y = %f",
                direction.getY(),
                direction.getX(),
                direction.getX()*passPoint.getY()-direction.getY()*passPoint.getX());
    }

    @Override
    public double distance(IShape shape) {
        if(shape instanceof Line2D line){
            if(this.intersect(line)!=null)
                return 0;
            else{
                Vector2D v = new Vector2D(this.passPoint, line.getOnePassPoint());
                Vector2D p = this.direction.computePerpendicular();

                return Math.abs(v.dotProduct(p.multiply(1/p.modular())));
            }
        }
        else if (shape instanceof Ray2D){

            //todo: finish
            return 0;
        }
        else if(shape instanceof Point2D point){
            Vector2D v = new Vector2D(point,passPoint);
            Vector2D p = this.direction.computePerpendicular();
            return Math.abs(v.dotProduct(p.multiply(1/p.modular())));
        }

        else if(shape instanceof Segment2D){
            return 0;
            //todo: finish
        }
        else throw new IllegalArgumentException("Dimension not match or shape not supported.");
    }
}
