package hello.hellospring.service;

import hello.hellospring.bean.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    private Logger logger = LoggerFactory.getLogger(MemberServiceIntegrationTest.class);

    @Autowired
    private MemberService memberService;

    @Test
    void join() {
        Member member = new Member();
        member.setName("spring4");

        Long id = memberService.join(member);

        Member findMember = memberService.findOne(id).orElseGet(Member::new);
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void duplJoin() {
        Member member1 = new Member();
        member1.setName("Spring");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("Spring");

        logger.error(assertThrows(IllegalStateException.class, () -> memberService.join(member2)).getMessage());

       /* try{
            memberService.join(member2);
            fail("테스트 예외가 발생해야합니다.");
        }catch (IllegalStateException e){
            logger.error(e.getMessage());
        }*/

    }

}