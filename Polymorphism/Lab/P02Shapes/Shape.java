package com.company.Polymorphism.Lab.P02Shapes;

public abstract class Shape {
    private Double perimeter;
    private Double area;

    protected abstract Double calculatePerimeter();

    protected abstract Double calculateArea();

    public Double getPerimeter() {
        return perimeter;
    }

    protected void setPerimeter(Double perimeter) {
        this.perimeter = this.calculatePerimeter();
    }

    public Double getArea() {
        return area;
    }

    protected void setArea(Double area) {
        this.area = this.calculateArea();
    }
}
