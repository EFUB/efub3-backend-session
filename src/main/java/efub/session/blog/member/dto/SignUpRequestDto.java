package efub.session.blog.member.dto;

import efub.session.blog.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class SignUpRequestDto {
    @NotBlank(message = "학번 기입은 필수입니다.")
    @Pattern(regexp = "^[0-9]{2,16}$",message = "7자리 학번을 입력해주세요.")
    private Long studentId;

    @NotBlank(message = "학교 기입은 필수입니다.")
    private String school;

    @NotBlank(message = "닉네임은 필수입니다.")
    private String nickname;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효하지 않은 이메일 형식입니다.",regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!.?,])[A-Za-z\\d!.?,]{2,16}$",
            message = "16자 이내의 영문자 및 숫자와 ?,!,., , 특수문자로 입력해주세요.")
    private String password;

    @Builder
    public SignUpRequestDto(Long studentId,String school,String nickname,String email,String password){
        this.studentId=studentId;
        this.school=school;
        this.nickname=nickname;
        this.email=email;
        this.password=password;
    }

    public Member toEntity(){
        return Member.builder()
                .studentId(this.studentId)
                .school(this.school)
                .nickname(this.nickname)
                .encodedPassword(this.password)
                .email(this.email)
                .bio("안녕하세요!")
                .build();
    }
}
