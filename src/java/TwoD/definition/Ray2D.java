package TwoD.definition;

import IDefinition.ILine;
import IDefinition.IPoint;
import IDefinition.IRay;
import IDefinition.IVector;

public class Ray2D implements IRay {

    private Point2D startPoint;
    private Vector2D direction;

    public Ray2D(Point2D startPoint, Vector2D direction) {
        this.startPoint = startPoint;
        this.direction = direction;
    }

    public Ray2D(Point2D startPoint, Point2D passPoint){
        this.startPoint = startPoint;
        this.direction = new Vector2D(startPoint, passPoint);
    }

    public Point2D getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point2D startPoint) {
        this.startPoint = startPoint;
    }

    public void setDirection(Vector2D direction) {
        this.direction = direction;
    }

    @Override
    public boolean parallel(IRay ray) {
        return this.direction.parallel(ray.getDirection());
    }

    @Override
    public IPoint intersect(IRay ray) {
        //todo: finish
        return null;
    }

    @Override
    public IPoint intersect(ILine line) {
        //todo: finish
        return null;
    }

    @Override
    public double distance(IRay ray) {
        //todo: finish
        return 0;
    }

    @Override
    public double distance(IPoint point) {
        //todo: finish
        return 0;
    }

    @Override
    public double distance(ILine line) {
        //todo: finish
        return 0;
    }

    @Override
    public IVector getDirection() {
        return direction;
    }
}
