package efub.session.blog.member.service;

import efub.session.blog.member.MemberRepository;
import efub.session.blog.member.domain.Member;
import efub.session.blog.member.dto.MemberUpdateRequestDto;
import efub.session.blog.member.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long signUp(SignUpRequestDto requestDto){
        if(existByEmail(requestDto.getEmail())){
            throw new IllegalArgumentException("이미 존재하는 email입니다. " + requestDto.getEmail());
        }
        Member member=memberRepository.save(requestDto.toEntity());
        return member.getMemberId();
    }

    @Transactional(readOnly = true)
    public boolean existByEmail(String email){
        return memberRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public Member findMemberById(Long id){
        return memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 id 를 가진 Member를 찾을 수 없습니다. id ="+id));
    }

    public Long update(Long memberId, MemberUpdateRequestDto requestDto){
        Member member=findMemberById(memberId);
        member.updateMember(requestDto.getBio(),requestDto.getNickname());
        return member.getMemberId();
    }
    @Transactional
    public void withdraw(Long memberId){
        Member member=findMemberById(memberId);
        member.withdrawMember();
    }

    public void delete(Long memberId){
        Member member=findMemberById(memberId);
        memberRepository.delete(member);
    }
}
