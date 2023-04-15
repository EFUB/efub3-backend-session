package efub.session.blog.global.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate//엔티티의 변경이 있으면 해당 시간을 저장한다.
    @Column(insertable=false) // 해당 컬럼은 DB 삽입에서 제외한다. 처음에는 DB에 넣을 때는 아직 수정하기 전이므로 비워져 있어야 한다.
    private LocalDateTime modifiedDate;
}
