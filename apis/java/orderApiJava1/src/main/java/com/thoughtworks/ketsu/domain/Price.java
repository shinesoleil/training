package com.thoughtworks.ketsu.domain;

import java.util.Date;

public class Price {
    private long id;
    private Date timeStamp;
    private double price;

    public Price(long id, Date timeStamp, double price) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.price = price;
    }

    public long getId() {
        return id;
    }
}
