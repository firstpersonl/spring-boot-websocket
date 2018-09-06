package com.order_porint.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zsx on 2018-08-31.
 * testing order model
 */
public class HotelOrder implements Serializable {
    private String orderId;
    private String username;
    private String userPhone;
    private Date createTime;
    private String hotelId;

    public String getOrderId() {
        return orderId;
    }

    public String getUsername() {
        return username;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getHotelId() {
        return hotelId;
    }
    // set methods

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String toString() {
        return "HotelOrder{" +
                "orderId='" + orderId + '\'' +
                ", username='" + username + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", createTime=" + createTime +
                ", hotelId='" + hotelId + '\'' +
                '}';
    }
}
