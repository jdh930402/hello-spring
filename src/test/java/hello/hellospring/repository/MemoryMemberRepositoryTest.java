package hello.hellospring.repository;

import hello.hellospring.bean.MemberBean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        MemberBean member = new MemberBean();
        member.setName("spring");

        repository.save(member);

        MemberBean result = repository.findById(member.getId()).get();

        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        MemberBean member1 = new MemberBean();
        member1.setName("spring1");
        repository.save(member1);

        MemberBean member2 = new MemberBean();
        member2.setName("spring2");
        repository.save(member2);

        MemberBean result = repository.findByName("spring1").get();
        assertThat(member1).isEqualTo(result);
    }

    @Test
    public void findAll() {
        MemberBean member1 = new MemberBean();
        member1.setName("spring1");
        repository.save(member1);

        MemberBean member2 = new MemberBean();
        member2.setName("spring2");
        repository.save(member2);

        assertThat(repository.findAll().size()).isEqualTo(2);

    }

}
