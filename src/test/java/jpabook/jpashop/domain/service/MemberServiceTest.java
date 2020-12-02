package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Entity.Member.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @org.junit.jupiter.api.Test
    public void 회원가입()throws Exception{
        Member member = new Member();
        member.setName("Jung");

        Long join_id = memberService.join(member);

        assertEquals(member, memberRepository.findOne(join_id));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        Member member1 = new Member();
        member1.setName("Kim");
        Member member2 = new Member();
        member2.setName("Kim");

        memberService.join(member1);
        try{
            memberService.join(member2);
        }catch (IllegalStateException e){
            return ;
        }
        fail(" 중복 예외가 발생해야 한다..! ");
    }
}