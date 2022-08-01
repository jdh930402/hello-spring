package hello.hellospring.service;

import hello.hellospring.bean.MemberBean;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(MemberBean member){
        // 같은 이름이 있는 회원 중복X
        this.validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 중복 아이디 체크
     * @param member
     */
    public void validateDuplicateMember(MemberBean member){
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조히
     * @return
     */
    public List<MemberBean> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 사용자 id로 조회
     * @param Id
     * @return
     */
    public Optional<MemberBean> findOne(Long id){
        return memberRepository.findById(id);
    }
}
