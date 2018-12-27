package com.bwei.day02;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Test {
    @Id(autoincrement = true)
    private long id;
    private String image;
    private String title;
    private double price;
    @Generated(hash = 694704730)
    public Test(long id, String image, String title, double price) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.price = price;
    }
    @Generated(hash = 372557997)
    public Test() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
