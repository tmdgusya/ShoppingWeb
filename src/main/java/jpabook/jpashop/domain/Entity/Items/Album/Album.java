package jpabook.jpashop.domain.Entity.Items.Album;

import jpabook.jpashop.domain.Entity.Items.Item;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("album")
@Getter
@Setter
public class Album extends Item {

    private String artist;
    private String etc;
}
