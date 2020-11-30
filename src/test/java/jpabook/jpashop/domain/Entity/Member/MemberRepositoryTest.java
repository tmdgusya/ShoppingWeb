package jpabook.jpashop.domain.Entity.Member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional // 여기 써있으면 Test가 끝난 뒤 DB RollBack
    public void testMember() throws Exception{
        Member member = new Member();
        member.setName("memberA");
        Long save = memberRepository.save(member);
        Member findMember = memberRepository.find(save);
        Assertions.assertThat(member.getId()).isEqualTo(findMember.getId());
    }

}