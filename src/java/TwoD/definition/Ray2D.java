package TwoD.definition;

import IDefinition.*;

public class Ray2D implements IRay {

    private final Point2D startPoint;
    private final Vector2D direction;

    public Ray2D(Point2D startPoint, Vector2D direction) {
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

    @Override
    public boolean pass(IPoint point) {
        if(point instanceof Point2D){
            return point.equals(startPoint)||this.equals(new Ray2D(startPoint,(Point2D) point));
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }

    @Override
    public Point2D intersect(IRay ray) {
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
            if(intersect)
                return new Line2D(direction,startPoint).intersect(new Line2D(((Ray2D) ray).direction, ((Ray2D) ray).getStartPoint()));
            else return null;
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }

    @Override
    public Point2D intersect(ISegment segment) {
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
            if(intersect)
                return new Line2D(direction,startPoint).intersect(new Line2D(((Segment2D) segment).getP1(), ((Segment2D) segment).getP2()));
            else return null;
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }

    @Override
    public Point2D intersect(ILine line) {
        if(line instanceof Line2D){
            return ((Line2D) line).intersect(this);
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }

    @Override
    public double distance(IRay ray) {
        //todo: finish
        return 0;
    }

    @Override
    public double distance(IPoint point) {
        //todo: finish
        return 0;
    }

    @Override
    public double distance(ILine line) {
        //todo: finish
        return 0;
    }

    @Override
    public Vector2D getDirection() {
        return direction;
    }
}
