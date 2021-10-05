package TwoD.definition;

import IDefinition.*;

import javax.naming.OperationNotSupportedException;

public class Ray2D implements IRay {

    private final Point2D startPoint;
    private final Vector2D direction;

    public Ray2D(Point2D startPoint, Vector2D direction) {
        if(direction.equals(new Vector2D(0,0)))
            throw new IllegalArgumentException("Zero vector cannot be a direction.");
        this.startPoint = startPoint;
        this.direction = direction;
    }

    public Ray2D(Point2D startPoint, Point2D passPoint){
        if(startPoint.equals(passPoint))
            throw new IllegalArgumentException("Try to initialise a ray from two same points.");
        this.startPoint = startPoint;
        this.direction = new Vector2D(startPoint, passPoint);
    }

    @Override
    public Point2D getStartPoint() {
        return startPoint;
    }

    @Override
    public boolean parallel(IRay ray) {
        return this.direction.parallel(ray.getDirection());
    }


    public boolean sameDirection(IRay ray) throws OperationNotSupportedException {
        return parallel(ray) && this.direction.uniform().equals(ray.getDirection().uniform());
    }

    @Override
    public boolean pass(IPoint point) {
        if(point instanceof Point2D){
            return point.equals(startPoint)||this.equals(new Ray2D(startPoint,(Point2D) point));
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }

    @Override
    public IShape intersect(IRay ray){
        if(ray instanceof Ray2D){
            boolean intersect =
                    this.pass(((Ray2D) ray).getStartPoint())
                    || ray.pass(this.startPoint)
                    ||(
                            this.getDirection().crossProductMod(new Vector2D(this.startPoint, ((Ray2D) ray).getStartPoint()))*
                                    this.getDirection().crossProductMod(((Ray2D) ray).getDirection())<0
                            && ((Ray2D)ray).getDirection().crossProductMod(new Vector2D(((Ray2D) ray).startPoint, this.startPoint))*
                                    ((Ray2D)ray).getDirection().crossProductMod(this.direction)<0
                            );
            if(intersect) {
                boolean sameDirection = false;
                try{
                    if (this.sameDirection(ray)) sameDirection = true;
                } catch (OperationNotSupportedException e) {
                    e.printStackTrace();
                }
                if(this.direction.parallel(((Ray2D) ray).direction) && sameDirection){
                    Point2D interP =
                            this.pass(((Ray2D) ray).startPoint)?((Ray2D) ray).startPoint:this.startPoint;
                    return new Ray2D(interP, this.direction);
                }
                else if(this.direction.parallel(((Ray2D) ray).direction) && !sameDirection &&
                        this.startPoint.equals(((Ray2D) ray).startPoint)){
                    return this.startPoint;
                }
                else if(this.direction.parallel(((Ray2D) ray).direction) && !sameDirection &&
                        !this.startPoint.equals(((Ray2D) ray).startPoint)){
                    return new Segment2D(this.startPoint, ((Ray2D) ray).startPoint  );
                }
                else
                    return new Line2D(direction, startPoint).intersect(new Line2D(((Ray2D) ray).direction, ((Ray2D) ray).getStartPoint()));
            }
            else return null;
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }

    @Override
    public IShape intersect(ISegment segment){
        if(segment instanceof Segment2D){
            boolean intersect =
                    this.pass(((Segment2D)segment).getP1())
                    ||this.pass(((Segment2D)segment).getP2())
                    || segment.pass(startPoint)
                    ||(
                            this.direction.crossProductMod(new Vector2D(startPoint, ((Segment2D) segment).getP1()))*
                                    this.direction.crossProductMod(new Vector2D(startPoint, ((Segment2D) segment).getP2()))<0
                            && ((Segment2D)segment).getDirection().crossProductMod(new Vector2D(((Segment2D) segment).getP1(), startPoint))*
                                    ((Segment2D)segment).getDirection().crossProductMod(direction)<0

                    );

            if(intersect){
                if(this.pass(((Segment2D)segment).getP1())&&this.pass(((Segment2D) segment).getP2())) {
                    return segment;
                }
                else if(this.direction.parallel(segment.getDirection())){
                    Point2D interP =
                            this.pass(((Segment2D)segment).getP1())?((Segment2D)segment).getP1(): ((Segment2D) segment).getP2();
                    if(interP.equals(this.startPoint)) {
                        return interP;
                    }
                    else {
                        return new Segment2D(interP, this.startPoint);
                    }
                }
                else {
                    return new Line2D(direction, startPoint).
                            intersect(new Line2D(((Segment2D) segment).getP1(), ((Segment2D) segment).getP2()));
                }
            }
            else return null;
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }

    @Override
    public IShape intersect(ILine line) {
        if(line instanceof Line2D){
            return ((Line2D) line).intersect(this);
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }


    @Override
    public Vector2D getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "Ray2D from" + startPoint.toString() + "-> Direction:" + direction.toString();
    }

    @Override
    public double distance(IShape shape) {
        return 0;
    }

    @Override
    public boolean equals(Object obj){
        if(this==obj)
            return true;
        if(obj instanceof Ray2D)
            return this.startPoint.equals(((Ray2D) obj).startPoint)&&
                    this.direction.parallel(((Ray2D) obj).direction)&&
                    this.direction.getX()*((Ray2D) obj).direction.getX()>=0&&
                    this.direction.getY()*((Ray2D) obj).direction.getY()>=0;
        else return false;
    }

    @Override
    public int hashCode(){
        try {
            return this.startPoint.hashCode() + this.direction.uniform().hashCode();
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();
            return this.startPoint.hashCode();
        }
    }
}
