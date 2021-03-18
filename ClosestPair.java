
import java.util.*;
 
public class ClosestPair
{
  public static class Point
  {
    public final double x;
    public final double y;
 
    public Point(double x, double y)
    {
      this.x = x;
      this.y = y;
    }
 
    public String toString()
    {  return "(" + x + ", " + y + ")";  }
  }
 
  public static class Pair
  {
    public Point point1 = null;
    public Point point2 = null;
    public double distance = 0.0;
 
    public Pair()
    {  }
 
    public Pair(Point point1, Point point2)
    {
      this.point1 = point1;
      this.point2 = point2;
      calcDistance();
    }
 
    public void update(Point point1, Point point2, double distance)
    {
      this.point1 = point1;
      this.point2 = point2;
      this.distance = distance;
    }
 
    public void calcDistance()
    {  this.distance = distance(point1, point2);  }
 
    public String toString()
    {  return point1 + "-" + point2 + " : " + distance;  }
  }
 
  public static double distance(Point p1, Point p2)
  {
    double xdist = p2.x - p1.x;
    double ydist = p2.y - p1.y;
    return Math.hypot(xdist, ydist);
  }
 
  public static Pair bruteForce(List<? extends Point> points)
  {
    int numPoints = points.size();
    if (numPoints < 2)
      return null;
    Pair pair = new Pair(points.get(0), points.get(1));
    if (numPoints > 2)
    {
      for (int i = 0; i < numPoints - 1; i++)
      {
        Point point1 = points.get(i);
        for (int j = i + 1; j < numPoints; j++)
        {
          Point point2 = points.get(j);
          double distance = distance(point1, point2);
          if (distance < pair.distance)
            pair.update(point1, point2, distance);
        }
      }
    }
    return pair;
  }
 
  public static void sortByX(List<? extends Point> points)
  {
    Collections.sort(points, new Comparator<Point>() {
        public int compare(Point point1, Point point2)
        {
          if (point1.x < point2.x)
            return -1;
          if (point1.x > point2.x)
            return 1;
          return 0;
        }
      }
    );
  }
 
  public static void sortByY(List<? extends Point> points)
  {
    Collections.sort(points, new Comparator<Point>() {
        public int compare(Point point1, Point point2)
        {
          if (point1.y < point2.y)
            return -1;
          if (point1.y > point2.y)
            return 1;
          return 0;
        }
      }
    );
  }
 
  public static Pair divideAndConquer(List<? extends Point> points)
  {
    List<Point> pointsSortedByX = new ArrayList<Point>(points);
    sortByX(pointsSortedByX);
    List<Point> pointsSortedByY = new ArrayList<Point>(points);
    sortByY(pointsSortedByY);
    return divideAndConquer(pointsSortedByX, pointsSortedByY);
  }
 
  private static Pair divideAndConquer(List<? extends Point> pointsSortedByX, List<? extends Point> pointsSortedByY)
  {
    int numPoints = pointsSortedByX.size();
    if (numPoints <= 3)
      return bruteForce(pointsSortedByX);
 
    int dividingIndex = numPoints >>> 1;
    List<? extends Point> leftOfCenter = pointsSortedByX.subList(0, dividingIndex);
    List<? extends Point> rightOfCenter = pointsSortedByX.subList(dividingIndex, numPoints);
 
    List<Point> tempList = new ArrayList<Point>(leftOfCenter);
    sortByY(tempList);
    Pair closestPair = divideAndConquer(leftOfCenter, tempList);
 
    tempList.clear();
    tempList.addAll(rightOfCenter);
    sortByY(tempList);
    Pair closestPairRight = divideAndConquer(rightOfCenter, tempList);
 
    if (closestPairRight.distance < closestPair.distance)
      closestPair = closestPairRight;
 
    tempList.clear();
    double shortestDistance =closestPair.distance;
    double centerX = rightOfCenter.get(0).x;
    for (Point point : pointsSortedByY)
      if (Math.abs(centerX - point.x) < shortestDistance)
        tempList.add(point);
 
    for (int i = 0; i < tempList.size() - 1; i++)
    {
      Point point1 = tempList.get(i);
      for (int j = i + 1; j < tempList.size(); j++)
      {
        Point point2 = tempList.get(j);
        if ((point2.y - point1.y) >= shortestDistance)
          break;
        double distance = distance(point1, point2);
        if (distance < closestPair.distance)
        {
          closestPair.update(point1, point2, distance);
          shortestDistance = distance;
        }
      }
    }
    return closestPair;
  }
 
  public static void main(String[] args)
  {
      
    Point p1 = new Point(94,5);
        Point p2 = new Point(96,-79);
        Point p3 = new Point(20,73);
        Point p4 = new Point(8,-50);
        Point p5 = new Point(78,2);
        Point p6 = new Point(100,63);
        Point p7 = new Point(-14,-69);
        Point p8 = new Point(99,-8);
        Point p9 = new Point(-11, -7);
        Point p10 = new Point(-78,-46);
        List<Point> list = new ArrayList<>();
        list.add(p1);  list.add(p2); list.add(p3); list.add(p4); list.add(p5); list.add(p6); list.add(p7); list.add(p8); list.add(p9); list.add(p10);
    long startTime = System.nanoTime();
    Pair bruteForceClosestPair = bruteForce(list);
    long elapsedTime = System.nanoTime() - startTime;
    System.out.println("Brute force (" + elapsedTime/1000000.0f + " ms): " + bruteForceClosestPair);
    startTime = System.nanoTime();
    Pair dqClosestPair = divideAndConquer(list);
    elapsedTime = System.nanoTime() - startTime;
    System.out.println("Divide and conquer (" + elapsedTime/1000000.0f + " ms): " + dqClosestPair);
    if (bruteForceClosestPair.distance != dqClosestPair.distance)
      System.out.println("MISMATCH");
  }
}