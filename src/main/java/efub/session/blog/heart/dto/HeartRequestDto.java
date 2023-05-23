package efub.session.blog.heart.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeartRequestDto {
    @NotNull(message = "작성자는 필수")
    private Long accountId;

    @Builder
    public HeartRequestDto(Long accountId) {
        this.accountId = accountId;
    }

}
