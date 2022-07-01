package com.company.WorkingWithAbstraction.Lab.PointInRectangle;

public class Rectangle {
    private Point a;
    private Point b;

    public Rectangle(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    public boolean contains(Point x) {
        return x.isGreaterOrEqual(a) && x.isLessOrEqual(b);
    }
}
