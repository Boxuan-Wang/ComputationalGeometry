package IDefinition;

public interface ILine {
    boolean parallel(ILine l);

    boolean pass(IPoint point);

    double distance(ILine line);
    double distance(IPoint point);
    double distance(IRay ray);

    IPoint intersect(ILine line);
    IPoint intersect(IRay ray);
    IPoint intersect(ISegment segment);

    IPoint getOnePassPoint();

    IVector getDirection();
}
