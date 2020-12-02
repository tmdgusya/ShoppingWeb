package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Entity.Order.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;
}
