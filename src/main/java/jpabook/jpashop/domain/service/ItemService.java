package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Entity.Delivery.Delivery;
import jpabook.jpashop.domain.Entity.Items.Item;
import jpabook.jpashop.domain.Entity.Member.Member;
import jpabook.jpashop.domain.Entity.Order.Order;
import jpabook.jpashop.domain.Entity.Order.OrderItem;
import jpabook.jpashop.domain.Entity.Order.OrderStatus;
import jpabook.jpashop.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public List<Item> findItem(){
        return itemRepository.findAll();
    }

    public Item findOne(Long id){
        return itemRepository.findOne(id);
    }


}
