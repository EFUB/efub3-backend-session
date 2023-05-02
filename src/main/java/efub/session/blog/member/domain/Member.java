package efub.session.blog.member.domain;

import efub.session.blog.global.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static efub.session.blog.member.domain.MemberStatus.REGISTERED;
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id",updatable = false)
    private Long memberId;

    @Column(nullable = false,length = 16)
    private Long studentId;

    @Column(nullable = false,length = 60)
    private String school;

    @Column(nullable = false,updatable = false,length=16)
    private String nickname;

    @Column(nullable = false,length=60)
    private String email;

    @Column(nullable = false)
    private String encodedPassword;

    private String bio;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Builder
    public Member(Long studentId, String school, String nickname, String email, String encodedPassword,String bio){
        this.studentId=studentId;
        this.school=school;
        this.nickname=nickname;
        this.email=email;
        this.encodedPassword=encodedPassword;
        this.bio=bio;
        this.status=REGISTERED;
    }

    public void updateMember(String bio,String nickname){
        this.bio=bio;
        this.nickname=nickname;
    }

    public void withdrawMember(){
        this.status=MemberStatus.UNRESISTERED;
    }
}
