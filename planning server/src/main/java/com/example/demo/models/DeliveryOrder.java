package com.example.demo.models;

import lombok.Data;

@Data
public class DeliveryOrder {
    Order order;
    Delivery delivery;
    public DeliveryOrder(Delivery delivery, Order order){
        this.delivery = delivery;
        this.order = order;
    }

    public DeliveryOrder() {
        delivery = new Delivery();
        order = new Order();
    }

    @Override
    public String toString() {
        return "DeliveryOrder{" +
                "order=" + order +
                ", delivery=" + delivery +
                '}';
    }
}