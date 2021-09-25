package IDefinition;

public interface ILine {
    boolean parallel(ILine l);

    double distance(ILine line);
    double distance(IPoint point);
    double distance(IRay ray);

    IPoint intersect(ILine line);
    IPoint intersect(IRay ray);

    IVector getDirection();
}
