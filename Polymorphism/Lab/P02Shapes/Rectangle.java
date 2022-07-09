package com.company.Polymorphism.Lab.P02Shapes;

public class Rectangle extends Shape {
    private Double height;
    private Double width;

    public Rectangle(Double height, Double width) {
        this.height = height;
        this.width = width;
    }

    public final Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public final Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    @Override
    protected Double calculatePerimeter() {
        return 2 * this.height + 2 * this.width;
    }

    @Override
    protected Double calculateArea() {
        return this.height * this.width;
    }
}
