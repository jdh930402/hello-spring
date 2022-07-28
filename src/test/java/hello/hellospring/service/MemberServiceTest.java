package hello.hellospring.service;

import hello.hellospring.bean.MemberBean;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    private Logger logger = LoggerFactory.getLogger(MemberServiceTest.class);
    private MemberService memberService;
    private MemberRepository memberRepository;

    @BeforeEach
    private void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    private void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        MemberBean member = new MemberBean();
        member.setName("spring");

        Long id = memberService.join(member);

        MemberBean findMember = memberService.findOne(id).orElseGet(MemberBean::new);
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void duplJoin() {
        MemberBean member1 = new MemberBean();
        member1.setName("Spring");
        memberService.join(member1);

        MemberBean member2 = new MemberBean();
        member2.setName("Spring");

        logger.error(assertThrows(IllegalStateException.class, () -> memberService.join(member2)).getMessage());

       /* try{
            memberService.join(member2);
            fail("테스트 예외가 발생해야합니다.");
        }catch (IllegalStateException e){
            logger.error(e.getMessage());
        }*/

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}