package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {

  @Autowired
  MemberService memberService;

  @Autowired
  MemberRepository memberRepository;

  @Test
  public void 회원가입() throws Exception {
    //given
    Member member = new Member();
    member.setName("An");

    //when
    Long saveId = memberService.join(member);

    //then
    assertEquals(member, memberRepository.findById(saveId).get());

  }

  @Test
  public void 중복_회원_예외() throws Exception {
    //given
    Member member1 = new Member();
    member1.setName("An");

    Member member2 = new Member();
    member2.setName("An");

    //when
    memberService.join(member1);
//    memberService.join(member2);

    //then
    assertThrows(IllegalStateException.class, () -> memberService.join(member2));
  }


}
