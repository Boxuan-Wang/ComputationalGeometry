package TwoD.definition;

import IDefinition.*;

public class Segment2D implements ISegment {

    private final Point2D p1;
    private final Point2D p2;
    private final Vector2D v;

    public Segment2D(Point2D p1, Point2D p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.v = new Vector2D(p1,p2);
    }


    @Override
    public IShape intersect(ISegment segment) {
        if(segment instanceof Segment2D){
//            boolean intersect =
//                    segment.pass(p1)
//                    ||segment.pass(p2)
//                    ||this.pass(((Segment2D) segment).getP1())
//                    ||this.pass(((Segment2D) segment).getP2())
//                    ||(
//                            v.crossProductMod(new Vector2D(p1, ((Segment2D) segment).getP1()))*
//                                    v.crossProductMod(new Vector2D(p1, ((Segment2D) segment).getP2()))<0
//                            && ((Segment2D)segment).getDirection().crossProductMod(new Vector2D(((Segment2D) segment).getP1(), p1))*
//                                    ((Segment2D)segment).getDirection().crossProductMod(new Vector2D(((Segment2D) segment).getP1(), p2))<0
//                            );
            boolean onEnd = segment.pass(p1)
                    ||segment.pass(p2)
                    ||this.pass(((Segment2D) segment).getP1())
                    ||this.pass(((Segment2D) segment).getP2());
            boolean intersect =
                    v.crossProductMod(new Vector2D(p1, ((Segment2D) segment).getP1()))*
                        v.crossProductMod(new Vector2D(p1, ((Segment2D) segment).getP2()))<0
                    && ((Segment2D)segment).getDirection().crossProductMod(new Vector2D(((Segment2D) segment).getP1(), p1))*
                        ((Segment2D)segment).getDirection().crossProductMod(new Vector2D(((Segment2D) segment).getP1(), p2))<0;
            if (intersect)
                return new Line2D(p1,p2).intersect(new Line2D(((Segment2D) segment).getP1(), ((Segment2D) segment).getP2()));
            else if(onEnd && v.parallel(((Segment2D) segment).v)){
                if(this.pass(((Segment2D) segment).p1)&&this.pass(((Segment2D) segment).p2)){
                    return segment;
                }
                else if (((Segment2D)segment).pass(p1)&&((Segment2D)segment).pass(p2)){
                    return this;
                }
                else{
                    Point2D interPoint1 = this.pass(((Segment2D) segment).getP1()) ?
                            ((Segment2D) segment).getP1() :
                            ((Segment2D) segment).getP2();
                    Point2D interPoint2 = ((Segment2D) segment).pass(p1) ? p1 : p2;
                    return new Segment2D(interPoint1,interPoint2);
                }

            }
            else return null;
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }

    @Override
    public IShape intersect(ILine line) {
        if(line instanceof Line2D){
            return line.intersect(this);
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }

    @Override
    public IShape intersect(IRay ray) {
        if (ray instanceof Ray2D){
            return ((Ray2D)ray).intersect(this);
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }

    @Override
    public Point2D[] getEnds() {
        return new Point2D[]{p1,p2};
    }

    @Override
    public double getLength() {
        return new Vector2D(p1,p2).modular();
    }



    @Override
    public Vector2D getDirection() {
        return v;
    }

    @Override
    public boolean pass(IPoint point) {
        if(point instanceof Point2D){
            return  ((Point2D) point).getX() <=Math.max(p1.getX(),p2.getX())
                    && ((Point2D) point).getX() >= Math.min(p1.getX(),p2.getX())
                    && new Vector2D((Point2D) point,p1).parallel(v);
        }
        else throw new IllegalArgumentException("Dimension not match.");
    }

    public Point2D getP1() {
        return p1;
    }

    public Point2D getP2() {
        return p2;
    }

    @Override
    public String toString() {
       return "Segment2D from " + p1 + " to " + p2;
    }

    @Override
    public double distance(IShape shape) {
        return 0;
    }

    @Override
    public boolean equals(Object o){
        if(this==o)
            return true;
        if(o instanceof Segment2D){
            return p1.equals(((Segment2D) o).p1)&&p2.equals(((Segment2D) o).p2)||
                    p1.equals(((Segment2D) o).p2)&&p2.equals(((Segment2D) o).p1);
        }
        else
            return false;
    }

    @Override
    public int hashCode(){
        return p1.hashCode() + p2.hashCode();
    }
}
