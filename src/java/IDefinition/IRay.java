package IDefinition;

public interface IRay extends IShape{
    boolean parallel(IRay ray);
    boolean pass(IPoint point);

    IShape intersect(IRay ray);
    IShape intersect(ISegment segment);
    IShape intersect(ILine line);

    IPoint getStartPoint();

    IVector getDirection();


}
