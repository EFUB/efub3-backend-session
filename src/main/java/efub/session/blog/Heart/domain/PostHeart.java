package efub.session.blog.domain.post.domain;

import efub.session.blog.domain.account.domain.Account;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostHeart {
    // 게시글 좋아요 id 작성

    // 게시글 - Post 참조

    // 작성자 - writer 참조

    // Builder 패턴을 통해 post, account 자동 생성
}
