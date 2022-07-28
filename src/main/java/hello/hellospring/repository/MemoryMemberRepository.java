package hello.hellospring.repository;

import hello.hellospring.bean.MemberBean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, MemberBean> store = new ConcurrentHashMap<Long, MemberBean>();

    private static AtomicLong sequnce = new AtomicLong();


    @Override
    public MemberBean save(MemberBean member) {
        member.setId(sequnce.getAndIncrement());
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<MemberBean> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<MemberBean> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<MemberBean> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
