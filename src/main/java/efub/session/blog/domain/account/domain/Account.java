package efub.session.blog.domain.account.domain;

<<<<<<< HEAD
import efub.session.blog.domain.comment.domain.Comment;
import efub.session.blog.global.entity.BaseTimeEntity;
=======
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseTimeEntity {
=======

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Account {
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", updatable = false)
    private Long accountId;

<<<<<<< HEAD
    @Column(nullable = false, length = 60)
=======
    @Column(nullable = false, length=60)
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
    private String email;

    @Column(nullable = false)
    private String encodedPassword;

<<<<<<< HEAD
    @Column(nullable = false, updatable = false, length = 16)
=======
    @Column(nullable = false, updatable = false, length=60)
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
    private String nickname;

    private String bio;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

<<<<<<< HEAD
    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Account(Long accountId, String email, String password, String nickname, String bio) {
        this.accountId = accountId;
=======
    @Builder
    public Account(String email, String password, String nickname, String bio){
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
        this.email = email;
        this.encodedPassword = password;
        this.nickname = nickname;
        this.bio = bio;
<<<<<<< HEAD
        this.status = AccountStatus.REGISTERED;
    }

    public void updateAccount(String bio, String nickname) {
        this.bio = bio;
        this.nickname = nickname;
    }

    public void withdrawAccount() {
        this.status = AccountStatus.UNREGISTERED;
=======
>>>>>>> 7cbec439bd161968cd18dfe3c5a9b5fb2073bc49
    }
}
