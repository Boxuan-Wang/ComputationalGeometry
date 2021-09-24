package IDefinition;

public interface ILine {
    boolean sameLine(ILine l);
    boolean parallel(ILine l);
    boolean distance(ILine line);
    IPoint intersect(ILine line);
}
