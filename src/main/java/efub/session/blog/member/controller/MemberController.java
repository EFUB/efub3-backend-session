package efub.session.blog.member.controller;

import efub.session.blog.member.domain.Member;
import efub.session.blog.member.dto.MemberResponseDto;
import efub.session.blog.member.dto.MemberUpdateRequestDto;
import efub.session.blog.member.dto.SignUpRequestDto;
import efub.session.blog.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MemberResponseDto signUp(@RequestBody @Valid final SignUpRequestDto requestDto){
        Long id=memberService.signUp(requestDto);
        Member findMember=memberService.findMemberById(id);
        return MemberResponseDto.from(findMember);
    }

    @GetMapping("/{memberId}")
    @ResponseStatus(value=HttpStatus.OK)
    public MemberResponseDto getMember(@PathVariable Long memberId)
    {
        Member findMember = memberService.findMemberById(memberId);
        return MemberResponseDto.from(findMember);
    }

    @PatchMapping("/profile/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public MemberResponseDto update(@PathVariable final Long memberId,@RequestBody @Valid final MemberUpdateRequestDto requestDto){
        Long id=memberService.update(memberId,requestDto);
        Member findMember=memberService.findMemberById(id);
        return MemberResponseDto.from(findMember);
    }

    @DeleteMapping("/{memberId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String delete(@PathVariable long memberId){
        memberService.delete(memberId);
        return "성공적으로 탈퇴가 완료되었습니다.";
    }
}
