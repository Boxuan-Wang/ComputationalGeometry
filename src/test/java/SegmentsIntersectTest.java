import IDefinition.IShape;
import TwoD.definition.Point2D;
import TwoD.definition.Segment2D;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class SegmentsIntersectTest {
    @Test
    public void SegmentNotIntersect() {
        //arrange
        Segment2D s1 = new Segment2D(new Point2D(0, 0), new Point2D(1, 0));
        Segment2D s2 = new Segment2D(new Point2D(1, 1), new Point2D(2, 1));
        //act
        IShape result = s1.intersect(s2);
        //assert
        assertThat(result).isNull();
    }

    @Test
    public void SegmentIntersectOnEnd() {
        //arrange
        Segment2D s1 = new Segment2D(new Point2D(0, 0), new Point2D(2, 0));
        Segment2D s2 = new Segment2D(new Point2D(1, 0), new Point2D(1, 1));
        //act
        IShape result = s1.intersect(s2);
        //assert
        assertThat(result).isEqualTo(new Point2D(1, 0));
    }

    @Test
    public void SegmentIntersectOnTheOtherEnd() {
        //arrange
        Segment2D s2 = new Segment2D(new Point2D(0, 0), new Point2D(2, 0));
        Segment2D s1 = new Segment2D(new Point2D(1, 0), new Point2D(1, 1));
        //act
        IShape result = s1.intersect(s2);
        //assert
        assertThat(result).isEqualTo(new Point2D(1, 0));
    }

    @Test
    public void SegmentIntersectNormal1() {
        //arrange
        Segment2D s2 = new Segment2D(new Point2D(0, 0), new Point2D(2, 0));
        Segment2D s1 = new Segment2D(new Point2D(1, -1), new Point2D(1, 1));
        //act
        IShape result = s1.intersect(s2);
        //assert
        assertThat(result).isEqualTo(new Point2D(1, 0));
    }

    @Test
    public void SegmentIntersectNormal2() {
        //arrange
        Segment2D s2 = new Segment2D(new Point2D(0, 0), new Point2D(2, 0));
        Segment2D s1 = new Segment2D(new Point2D(0, -2), new Point2D(2, 2));
        //act
        IShape result = s1.intersect(s2);
        //assert
        assertThat(result).isEqualTo(new Point2D(1, 0));
    }

    @Test
    public void SegmentContainIntersect() {
        //arrange
        Segment2D s2 = new Segment2D(new Point2D(0, 0), new Point2D(2, 0));
        Segment2D s1 = new Segment2D(new Point2D(0, 0), new Point2D(1, 0));
        //act
        IShape result = s1.intersect(s2);
        //assert
        assertThat(result).isEqualTo(new Segment2D(new Point2D(0, 0), new Point2D(1, 0)));
    }

    @Test
    public void SegmentOnTheSameLineIntersect() {
        //arrange
        Segment2D s2 = new Segment2D(new Point2D(-1, 0), new Point2D(2, 0));
        Segment2D s1 = new Segment2D(new Point2D(0, 0), new Point2D(1, 0));
        //act
        IShape result = s1.intersect(s2);
        //assert
        assertThat(result).isEqualTo(new Segment2D(new Point2D(0, 0), new Point2D(1, 0)));

    }
}
