package com.company.Polymorphism.Lab.P02Shapes;

public class Circle extends Shape {
    private Double radius;

    public Circle(Double radius) {
        this.radius = radius;
    }

    public final Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    protected Double calculatePerimeter() {
        return 2 * Math.PI * this.radius;
    }

    @Override
    protected Double calculateArea() {
        return Math.PI * this.radius * this.radius;
    }
}
