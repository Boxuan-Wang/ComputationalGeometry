package IDefinition;

import java.util.List;

public interface ISegment extends IShape{

    IShape intersect(ISegment segment);
    IShape intersect(ILine line);
    IShape intersect(IRay ray);

    IPoint[] getEnds();
    double getLength();

    IVector getDirection();

    boolean pass(IPoint point);
}
