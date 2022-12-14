package hello.hellospring.service;

import hello.hellospring.bean.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    private Logger logger = LoggerFactory.getLogger(MemberServiceTest.class);
    private MemberService memberService;
    private MemoryMemberRepository memberRepository;

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
        Member member = new Member();
        member.setName("spring");

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

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}