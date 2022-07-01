package com.company.WorkingWithAbstraction.Lab.HotelReservation;

public enum Discount {
    VIP(0.8),
    SECOND_VISIT(0.9),
    NONE(1);

    private double priceReduction;

    Discount(double priceReduction) {
        this.priceReduction = priceReduction;
    }


    public double getPriceReduction() {
        return priceReduction;
    }

    public static Discount parse(String s) {
        switch (s) {
            case "VIP":
                return VIP;
            case "SecondVisit":
                return SECOND_VISIT;
            case "None":
                return NONE;
            default:
                throw new IllegalArgumentException("Unknown enum value " + s);
        }
    }

}
