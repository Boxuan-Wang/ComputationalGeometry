import IDefinition.IShape;
import TwoD.definition.Point2D;
import TwoD.definition.Ray2D;
import TwoD.definition.Segment2D;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;
@RunWith(JUnit4.class)
public class SegmentRayIntersectionTest {
    @Test
    public void parallelNotIntersect(){
        //arrange
        Ray2D ray = new Ray2D(new Point2D(0,0), new Point2D(1,0));
        Segment2D seg = new Segment2D(new Point2D(0,1), new Point2D(1,1));
        //act
        IShape result1 = ray.intersect(seg);
        IShape result2 = seg.intersect(ray);

        //assert
        assertThat(result1).isNull();
        assertThat(result2).isNull();
    }

    @Test
    public void NotParallelNotIntersect(){
        //arrange
        Ray2D ray = new Ray2D(new Point2D(0,0), new Point2D(1,0));
        Segment2D seg = new Segment2D(new Point2D(0,1), new Point2D(1,2));
        //act
        IShape result1 = ray.intersect(seg);
        IShape result2 = seg.intersect(ray);
        //assert
        assertThat(result1).isNull();
        assertThat(result2).isNull();
    }

    @Test
    public void intersectOnEnd(){

        Ray2D ray = new Ray2D(new Point2D(0,0), new Point2D(1,0));
        Segment2D seg = new Segment2D(new Point2D(0,0), new Point2D(1,2));
        //act
        IShape result1 = ray.intersect(seg);
        IShape result2 = seg.intersect(ray);
        //assert
        assertThat(result1).isEqualTo(new Point2D(0,0));
        assertThat(result2).isEqualTo(new Point2D(0,0));
    }

    @Test
    public void intersectNormal(){
        //arrange
        Ray2D ray = new Ray2D(new Point2D(0,0), new Point2D(1,1));
        Segment2D seg = new Segment2D(new Point2D(0,0.5), new Point2D(2,1.5));
        //act
        IShape result1 = ray.intersect(seg);
        IShape result2 = seg.intersect(ray);
        //assert
        assertThat(result1).isEqualTo(new Point2D(1,1));
        assertThat(result2).isEqualTo(new Point2D(1,1));
    }

    @Test
    public void rayContainSegment(){
        //arrange
        Ray2D ray = new Ray2D(new Point2D(0,0), new Point2D(1,0));
        Segment2D seg = new Segment2D(new Point2D(0,0), new Point2D(1,0));
        //act
        IShape result1 = ray.intersect(seg);
        IShape result2 = seg.intersect(ray);
        //assert
        assertThat(result1).isEqualTo(new Segment2D(new Point2D(0,0),new Point2D(1,0)));
        assertThat(result2).isEqualTo(new Segment2D(new Point2D(0,0),new Point2D(1,0)));
    }

    @Test
    public void rayContainPartOfTheSegment(){
        //arrange
        Ray2D ray = new Ray2D(new Point2D(0,0), new Point2D(1,0));
        Segment2D seg = new Segment2D(new Point2D(-1,0), new Point2D(1,0));
        //act
        IShape result1 = ray.intersect(seg);
        IShape result2 = seg.intersect(ray);
        //assert
        assertThat(result1).isEqualTo(new Segment2D(new Point2D(0,0),new Point2D(1,0)));
        assertThat(result2).isEqualTo(new Segment2D(new Point2D(0,0),new Point2D(1,0)));
    }

}
