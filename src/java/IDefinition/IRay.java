package IDefinition;

public interface IRay {
    boolean parallel(IRay ray);

    IPoint intersect(IRay ray);
    IPoint intersect(ILine line);

    double distance(IRay ray);
    double distance(IPoint point);
    double distance(ILine line);

    IVector getDirection();


}
