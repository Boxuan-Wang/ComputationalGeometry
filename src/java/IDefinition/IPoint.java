package IDefinition;

import java.util.List;

public interface IPoint {
    List<Double> getValues();
    double distance(IPoint p);
    boolean samePoint(IPoint p);
}
