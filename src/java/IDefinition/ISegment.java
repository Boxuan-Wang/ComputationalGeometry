package IDefinition;

import java.util.List;

public interface ISegment {

    IPoint intersect(ISegment segment);
    IPoint intersect(ILine line);
    IPoint intersect(IRay ray);

    IPoint[] getEnds();
    double getLength();

    double distance(ISegment segment);
    double distance(ILine line);
    double distance(IRay ray);

    IVector getDirection();

    boolean pass(IPoint point);
}
