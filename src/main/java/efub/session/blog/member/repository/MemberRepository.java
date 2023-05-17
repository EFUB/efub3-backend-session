package efub.session.blog.member;

import efub.session.blog.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Boolean existsByEmail(String email);
}
