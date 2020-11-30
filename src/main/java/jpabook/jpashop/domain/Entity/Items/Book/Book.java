package jpabook.jpashop.domain.Entity.Items.Book;

import jpabook.jpashop.domain.Entity.Items.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("book")
@Getter
@Setter
public class Book extends Item {

    private String author;
    private String isbn;
}
