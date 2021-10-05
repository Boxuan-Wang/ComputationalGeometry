import TwoD.definition.*;
import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.sound.sampled.Line;

import static com.google.common.truth.Truth.assertThat;
@RunWith(JUnit4.class)
public class BasicElementInitialisationTest {
    @Test
    public void Point2DCreate(){
        //arrange
        Point2D p = new Point2D(0,0);
        //act
        String s = p.toString();
        //assert
        assertThat(s).startsWith("Point2D");
        assertThat(p.getX()).isEqualTo(0);
        assertThat(p.getY()).isEqualTo(0);
        assertThat(p.toList()).containsExactly(0.0,0.0);
    }

    @Test
    public void Ray2DCreateStartAndDirection(){
        //arrange
        Point2D start = new Point2D(0,0);
        Vector2D v = new Vector2D(1,0);
        //act
        Ray2D r = new Ray2D(start,v);
        //assert
        assertThat(r.toString()).startsWith("Ray2D");
        System.out.println(r);
        assertThat(r.getDirection()).isEqualTo(v);
        assertThat(r.getStartPoint()).isEqualTo(start);
    }

    @Test
    public void Ray2DCreateFromTwoPoints(){
        //arrange
        Point2D start = new Point2D(0,0);
        Point2D pass = new Point2D(1,0);
        //act
        Ray2D r = new Ray2D(start,pass);
        //assert
        assertThat(r.toString()).startsWith("Ray2D");
        System.out.println(r);
        assertThat(r.getDirection().parallel(new Vector2D(1,0))).isTrue();
        assertThat(r.getStartPoint()).isEqualTo(start);
    }

    @Test
    public void SegmentCreate(){
        //arrange
        Point2D p1 = new Point2D(0,0);
        Point2D p2 = new Point2D(0,1);
        //act
        Segment2D seg = new Segment2D(p1,p2);
        //assert
        assertThat(seg.toString()).startsWith("Segment2D");
        System.out.println(seg);
        assertThat(seg.getP1()).isEqualTo(p1);
        assertThat(seg.getP2()).isEqualTo(p2);
        assertThat(seg.getDirection()).isEqualTo(new Vector2D(p1,p2));
    }

    @Test
    public void LineCreateFromTwoPoints(){
        //arrange
        Point2D p1 = new Point2D(0,0);
        Point2D p2 = new Point2D(0,1);
        //act
        Line2D line = new Line2D(p1,p2);
        //assert
        assertThat(line.toString()).startsWith("Line2D");
        System.out.println(line);
        assertThat(line.getDirection().parallel(new Vector2D(p1,p2))).isTrue();
        assertThat(line.pass(line.getOnePassPoint())).isTrue();
        assertThat(line.pass(p1)).isTrue();
        assertThat(line.pass(p2)).isTrue();
    }

    @Test
    public void LineCreateFromDirectionAndPoint(){
        //arrange
        Point2D p = new Point2D(0,0);
        Vector2D v = new Vector2D(0,1);
        //act
        Line2D line = new Line2D(v,p);
        //assert
        assertThat(line.toString()).startsWith("Line2D");
        System.out.println(line);
        assertThat(line.getDirection().parallel(v)).isTrue();
        assertThat(line.pass(p)).isTrue();
        assertThat(line.pass(line.getOnePassPoint())).isTrue();
    }
}
