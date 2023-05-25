package com.efub.blogsession.domain.follow.dto;

import com.efub.blogsession.domain.account.domain.Account;
import com.efub.blogsession.domain.follow.domain.Follow;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 어노테이션 추가
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowRequestDto {
    private Long followingId;

    public FollowRequestDto(Long followingId){
        this.followingId=followingId;
    }

    public Follow toEntity(Account follower, Account following){
        return Follow.builder()
                .follower(follower)
                .following(following)
                .build();
    }
}