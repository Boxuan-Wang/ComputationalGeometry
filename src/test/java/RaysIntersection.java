import IDefinition.ISegment;
import IDefinition.IShape;
import TwoD.definition.Point2D;
import TwoD.definition.Ray2D;

import TwoD.definition.Segment2D;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class RaysIntersection {
    @Test
    public void SameRayIntersect(){
        //arrange
        Ray2D r1 = new Ray2D(new Point2D(0,0), new Point2D(1,0));
        Ray2D r2 = new Ray2D(new Point2D(0,0), new Point2D(2,0));
        //act
        IShape result = r1.intersect(r2);
        //assert
        assertThat(result).isEqualTo(r1);
    }

    @Test
    public void parallelNotSameDirectionReturnStartPoint(){
        //arrange
        Ray2D r1 = new Ray2D(new Point2D(0,0), new Point2D(1,0));
        Ray2D r2 = new Ray2D(new Point2D(0,0), new Point2D(-2,0));
        //act
        IShape result = r1.intersect(r2);
        //assert
        assertThat(result).isEqualTo(new Point2D(0,0));
    }

    @Test
    public void parallelNotSameDirectionReturnSegment(){
        //arrange
        Ray2D r1 = new Ray2D(new Point2D(0,0), new Point2D(1,0));
        Ray2D r2 = new Ray2D(new Point2D(1,0), new Point2D(-2,0));
        //act
        IShape result = r1.intersect(r2);
        //assert
        assertThat(result).isEqualTo(new Segment2D(new Point2D(0,0), new Point2D(1,0)));
    }

    @Test
    public void parallelNotIntersect(){
        //arrange
        Ray2D r1 = new Ray2D(new Point2D(0,0), new Point2D(1,0));
        Ray2D r2 = new Ray2D(new Point2D(0,1), new Point2D(-2,1));
        //act
        IShape result = r1.intersect(r2);
        //assert
        assertThat(result).isNull();
    }

    @Test
    public void NotParallelNotIntersect(){
        //arrange
        Ray2D r1 = new Ray2D(new Point2D(0,0), new Point2D(1,-1));
        Ray2D r2 = new Ray2D(new Point2D(0,1), new Point2D(1,1));
        //act
        IShape result = r1.intersect(r2);
        //assert
        assertThat(result).isNull();
    }

    @Test
    public void NormalIntersect1(){
        //arrange
        Ray2D r1 = new Ray2D(new Point2D(0,0), new Point2D(2,2));
        Ray2D r2 = new Ray2D(new Point2D(0,2), new Point2D(2,0));
        //act
        IShape result = r1.intersect(r2);
        //assert
        assertThat(result).isEqualTo(new Point2D(1,1));
    }

    @Test
    public void NormalIntersect2(){
        //arrange
        Ray2D r1 = new Ray2D(new Point2D(0,0), new Point2D(2,2));
        Ray2D r2 = new Ray2D(new Point2D(0,0), new Point2D(2,0));
        //act
        IShape result = r1.intersect(r2);
        //assert
        assertThat(result).isEqualTo(new Point2D(0,0));
    }
}
