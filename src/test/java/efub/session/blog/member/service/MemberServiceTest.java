package efub.session.blog.member.service;

import efub.session.blog.member.MemberRepository;
import efub.session.blog.member.domain.Member;
import efub.session.blog.member.dto.MemberUpdateRequestDto;
import efub.session.blog.member.dto.SignUpRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
//@Transactional
class MemberServiceTest {
    /*
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void beforeEach(){
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("create member")
    public void testSignUp() {
        // given
        String school="00대학교";
        String nickname="닉네임";
        String email = "email@naver.com";

        SignUpRequestDto requestDto = SignUpRequestDto.builder()
                .school(school)
                .nickname(nickname)
                .email(email)
                .build();

        // when
        Long memberId = memberService.signUp(requestDto);

        // then
        Member member = memberRepository.findById(memberId).orElse(null);
        assertNotNull(member);
        assertEquals(requestDto.getSchool(), member.getSchool());
        assertEquals(requestDto.getNickname(), member.getNickname());
        assertEquals(requestDto.getEmail(), member.getEmail());
    }

    @Test
    @DisplayName("update member")
    public void testUpdate() {
        // given
        Member member = Member.builder()
                .school("00대학교")
                .nickname("닉네임")
                .email("email@naver.com")
                .bio("hi")
                .encodedPassword("Password!")
                .build();
        memberRepository.save(member);
        Long memberId = member.getMemberId();

        String bio = "hello!";
        String nickname = "newNickname";
        MemberUpdateRequestDto requestDto = MemberUpdateRequestDto.builder()
                .bio(bio)
                .nickname(nickname)
                .build();

        // when
        Long updatedMemberId = memberService.update(memberId, requestDto);

        // then
        assertEquals(memberId, updatedMemberId);
        Member updatedMember = memberRepository.findById(memberId).orElse(null);
        assertNotNull(updatedMember);
        assertEquals(requestDto.getNickname(), updatedMember.getNickname());
    }



    @Test
    @DisplayName("withdraw member")
    public void testWithdraw() {
        // given
        Member member = Member.builder()
                .school("00대학교")
                .nickname("닉네임")
                .email("email@naver.com")
                .encodedPassword("Password!")
                .bio("hi")
                .build();
        memberRepository.save(member);
        Long memberId = member.getMemberId();

        // when
        memberService.withdraw(memberId);

        // then
        Member deletedMember = memberRepository.findById(memberId).orElse(null);
        assertNotNull(deletedMember);

    }

*/
}