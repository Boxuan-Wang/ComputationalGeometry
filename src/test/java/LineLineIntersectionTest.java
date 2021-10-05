import IDefinition.IShape;
import TwoD.definition.Line2D;
import TwoD.definition.Point2D;
import TwoD.definition.Vector2D;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class LineLineIntersectionTest {
    @Test
    public void SameLineIntersection(){
        //arrange
        Line2D l1 = new Line2D(new Vector2D(1,0), new Point2D(0,0));
        Line2D l2 = new Line2D(new Vector2D(1,0), new Point2D(1,0));
        //act
        IShape result = l1.intersect(l2);
        //assert
        assertThat(result).isEqualTo(l1);
    }

    @Test
    public void parallelLineIntersect(){
        //arrange
        Line2D l1 = new Line2D(new Vector2D(1,0), new Point2D(0,0));
        Line2D l2 = new Line2D(new Vector2D(1,0), new Point2D(1,1));
        //act
        IShape result = l1.intersect(l2);
        //assert
        assertThat(result).isNull();
    }

    @Test
    public void normalIntersection(){
        //arrange
        Line2D l1 = new Line2D(new Vector2D(1,1), new Point2D(0,0));
        Line2D l2 = new Line2D(new Vector2D(1,-1), new Point2D(0,2));
        //act
        IShape result = l1.intersect(l2);
        //assert
        assertThat(result).isEqualTo(new Point2D(1,1));
    }

    @Test
    public void normalIntersection2(){
        //arrange
        Line2D l1 = new Line2D(new Vector2D(1,1), new Point2D(1,1));
        Line2D l2 = new Line2D(new Vector2D(1,-1), new Point2D(0,2));
        //act
        IShape result = l1.intersect(l2);
        //assert
        assertThat(result).isEqualTo(new Point2D(1,1));
    }

    @Test
    public void normalIntersection3(){
        //arrange
        Line2D l1 = new Line2D(new Vector2D(1,2), new Point2D(1,0));
        Line2D l2 = new Line2D(new Vector2D(2,1), new Point2D(0,1));
        //act
        IShape result = l1.intersect(l2);
        //assert
        assertThat(result).isEqualTo(new Point2D(2,2));
    }

    @Test
    public void normalIntersection4(){
        //arrange
        Line2D l1 = new Line2D(new Vector2D(1,0), new Point2D(1,0));
        Line2D l2 = new Line2D(new Vector2D(0,1), new Point2D(0,1));
        //act
        IShape result = l1.intersect(l2);
        //assert
        assertThat(result).isEqualTo(new Point2D(0,0));
    }

    @Test
    public void normalIntersection5(){
        //arrange
        Line2D l1 = new Line2D(new Vector2D(1,1), new Point2D(1,1));
        Line2D l2 = new Line2D(new Vector2D(0,1), new Point2D(5,0));
        //act
        IShape result = l1.intersect(l2);
        //assert
        assertThat(result).isEqualTo(new Point2D(5,5));
    }
}
