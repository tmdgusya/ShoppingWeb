package jpabook.jpashop.controller;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "이름은 반드시 입력해야합니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
