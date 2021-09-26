package IDefinition;

public interface IRay {
    boolean parallel(IRay ray);
    boolean pass(IPoint point);

    IPoint intersect(IRay ray);
    IPoint intersect(ISegment segment);
    IPoint intersect(ILine line);
    IPoint getStartPoint();

    double distance(IRay ray);
    double distance(IPoint point);
    double distance(ILine line);

    IVector getDirection();


}
