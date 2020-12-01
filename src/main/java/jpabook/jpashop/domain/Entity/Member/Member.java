package jpabook.jpashop.domain.Entity.Member;

import jpabook.jpashop.domain.Entity.Order.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // Order Class 에 있는 member Field => mappedBy 선언하면 readonly 로 변환
    private List<Order> orders = new ArrayList<>();



}
