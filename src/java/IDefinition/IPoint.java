package IDefinition;

import java.util.List;

public interface IPoint {
    List<Double> getValues();
    double distance(IPoint p);
}
