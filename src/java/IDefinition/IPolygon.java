package IDefinition;

public interface IPolygon {
    int getEdges();

    double getPerimeter();
    double getArea();

    IShape intersect(IPolygon polygon);

    double isSimplePolygon();
    double isConvexPolygon();
}
