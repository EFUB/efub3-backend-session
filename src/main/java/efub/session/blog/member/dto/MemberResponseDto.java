package efub.session.blog.member.dto;

import efub.session.blog.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {
    private Long memberId;
    private Long studentId;
    private String school;
    private String nickname;
    private String email;
    private String bio;

    public MemberResponseDto(Long memberId, Long studentId, String school, String nickname, String email,  String bio) {
        this.memberId = memberId;
        this.studentId=studentId;
        this.school=school;
        this.nickname = nickname;
        this.email = email;
        this.bio = bio;
    }
    public static MemberResponseDto from(Member member) {
        return new MemberResponseDto(member.getMemberId(),
                member.getStudentId(),
                member.getSchool(),
                member.getNickname(),
                member.getEmail(),
                member.getBio());
    }
}
