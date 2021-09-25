package TwoD.definition;

import IDefinition.*;

public class Line2D implements ILine {
    private Vector2D direction;
    private Point2D passPoint;

    @Override
    public Point2D getOnePassPoint() {
        return passPoint;
    }

    @Override
    public boolean parallel(ILine l) {
        if(l instanceof Line2D){
            return ((Line2D) l).direction.parallel(direction);
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
    public double distance(ILine line) {
        if(this.intersect(line)!=null)
            return 0;
        else{
            Vector2D v = new Vector2D(this.passPoint, ((Line2D)line).getOnePassPoint());
            Vector2D p = this.direction.computePerpendicular();

            return Math.abs(v.dotProduct(p.multiply(1/p.modular())));
        }
    }

    @Override
    public double distance(IPoint point) {
        if(point instanceof Point2D){
            Vector2D v = new Vector2D((Point2D) point,passPoint);
            Vector2D p = this.direction.computePerpendicular();
            return Math.abs(v.dotProduct(p.multiply(1/p.modular())));
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }

    @Override
    public double distance(IRay ray) {
        //todo: finish
        return 0;
    }

    @Override
    public IPoint intersect(ILine line) {
        if(line instanceof Line2D){
            if(((Line2D) line).direction.parallel(direction)){
                return null;
            }
            else{
                //todo: finish, has intersection
                return null;
            }
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }

    @Override
    public IPoint intersect(IRay ray) {
        if(ray instanceof Ray2D){
            if(ray.intersect(new Ray2D(passPoint,direction))!=null ||
                    ray.intersect(new Ray2D(passPoint,(Vector2D) direction.multiply(-1)))!=null){
                //todo: finish, has intersection
                return null;
            }
            else return null;
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }

    @Override
    public IPoint intersect(ISegment segment) {
        return null;
    }

    @Override
    public IVector getDirection() {
        return direction;
    }
}
