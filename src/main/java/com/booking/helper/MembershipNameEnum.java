package com.booking.helper;

public enum MembershipNameEnum {
    NONE(0),
    SILVER(0.05),
    GOLD(0.1);

    private final double DISCOUNT;

    MembershipNameEnum(double DISCOUNT){
        this.DISCOUNT = DISCOUNT;
    }

    public double getDISCOUNT() {
        return DISCOUNT;
    }
}
