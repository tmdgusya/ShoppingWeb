package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.CustomException.NotEnoughStockException;
import jpabook.jpashop.domain.Entity.Items.Book.Book;
import jpabook.jpashop.domain.Entity.Items.Item;
import jpabook.jpashop.domain.Entity.Member.Address;
import jpabook.jpashop.domain.Entity.Member.Member;
import jpabook.jpashop.domain.Entity.Order.Order;
import jpabook.jpashop.domain.Entity.Order.OrderStatus;
import jpabook.jpashop.domain.repository.OrderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        Member member = new Member();
        member.setName("회원1");
        Address address = new Address("Seoul", "Gyeongi", "123");
        em.persist(member);

        member.setAddress(address);
        Item book = new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);

        Long order = orderService.order(member.getId(), book.getId(), 2);

        Order getOrder = orderRepository.findOne(order);

        Assert.assertEquals("상품주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
        assertEquals("주문가격은 가격 * 수량이다.", 10000*2, getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, book.getStockQuantity());
    }

    @Test
    public void 주문취소() throws Exception {
        Member member = new Member();
        member.setName("회원1");
        Address address = new Address("Seoul", "Gyeongi", "123");
        em.persist(member);

        member.setAddress(address);
        Item book = new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);

        Long order = orderService.order(member.getId(), book.getId(), 2);

        Order getOrder = orderRepository.findOne(order);
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, book.getStockQuantity());
        getOrder.cancel();
        assertEquals("주문 취소시 상품의 주문의 상태가 취소가된다.", OrderStatus.CANCLE, getOrder.getStatus());
        assertEquals("주문 수량만큼 재고가 복구되야 한다.", 10, book.getStockQuantity());
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {
        Member member = new Member();
        member.setName("회원1");
        Address address = new Address("Seoul", "Gyeongi", "123");
        em.persist(member);

        member.setAddress(address);
        Item book = new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        try {
            Long order = orderService.order(member.getId(), book.getId(), 12);
        }catch (NotEnoughStockException e){
            return;
        }
        fail("이전에 NotEnoughStockException 가 발생해야");
    }

}