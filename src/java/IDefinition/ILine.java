package IDefinition;

public interface ILine extends IShape{
    boolean parallel(ILine l);

    boolean pass(IPoint point);

    IShape intersect(ILine line) throws Exception;
    IShape intersect(IRay ray);
    IShape intersect(ISegment segment);

    IPoint getOnePassPoint();

    IVector getDirection();
}
