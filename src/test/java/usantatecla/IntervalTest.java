package usantatecla;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class IntervalTest {

  private Point left = new Point(-2.2);
  private Point moreLeft = new Point(-3.3);
  private Point right = new Point(4.4);
  private Point moreRight = new Point(5.5);
  private IntervalBuilder intervalBuilder;

  @BeforeEach
  public void before(){
    this.left = new Point(-2.2);
    this.moreLeft = new Point(-3.3);
    this.right = new Point(4.4);
    this.moreRight = new Point(5.5);
    this.intervalBuilder = new IntervalBuilder();
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWithIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertFalse(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));
    assertTrue(interval.include(right.getLess()));
    assertFalse(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenInc3ludeWithIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).open(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertTrue(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertFalse(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWit3hIncludedValueThenTrue() {
    Interval interval = this.intervalBuilder.open(left.getEquals()).closed(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertFalse(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertTrue(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void givenIntervaOpenOpenlwhenIncludeWithInclude5dValueThenTrue() {
    Interval interval = this.intervalBuilder.closed(left.getEquals()).closed(right.getEquals()).build();
    assertFalse(interval.include(left.getLess()));
    assertTrue(interval.include(left.getEquals()));
    assertTrue(interval.include(left.getGreater()));

    assertTrue(interval.include(right.getLess()));
    assertTrue(interval.include(right.getEquals()));
    assertFalse(interval.include(right.getGreater()));
  }

  @Test
  public void getIntersectionOfIntervalContainsThis() {
    Interval intersection = this.intervalBuilder.open(left.getEquals()).open(right.getEquals()).build()
            .getIntersection(
                    new IntervalBuilder().open(moreLeft.getEquals()).open(moreRight.getEquals()).build()
            );
    assertFalse(intersection.include(left.getEquals()));
    assertTrue(intersection.include(left.getGreater()));
    assertFalse(intersection.include(moreLeft.getGreater()));
    assertFalse(intersection.include(right.getEquals()));
    assertTrue(intersection.include(right.getLess()));
    assertFalse(intersection.include(moreRight.getLess()));
  }

  @Test
  public void getIntersectionOfThisContainsInterval() {
    Interval intersection = this.intervalBuilder.open(moreLeft.getEquals()).open(moreRight.getEquals()).build()
            .getIntersection(
                    new IntervalBuilder().closed(left.getEquals()).closed(right.getEquals()).build()
            );
    assertTrue(intersection.include(left.getEquals()));
    assertTrue(intersection.include(left.getGreater()));
    assertFalse(intersection.include(moreLeft.getGreater()));
    assertTrue(intersection.include(right.getEquals()));
    assertTrue(intersection.include(right.getLess()));
    assertFalse(intersection.include(moreRight.getLess()));
  }

}