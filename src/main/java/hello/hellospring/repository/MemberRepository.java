package hello.hellospring.repository;


import hello.hellospring.bean.MemberBean;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    MemberBean save(MemberBean member);
    Optional<MemberBean> findById(Long id);
    Optional<MemberBean> findByName(String name);
    List<MemberBean> findAll();
    void clearStore();
}
