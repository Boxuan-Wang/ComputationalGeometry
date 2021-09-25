package IDefinition;

public interface IPolygon {
    int getEdges();

    double getPerimeter();
    double getArea();

    IPolygon intersect(IPolygon polygon);

    double isSimplePolygon();
    double isConvexPolygon();
}
