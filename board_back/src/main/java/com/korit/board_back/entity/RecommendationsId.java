package com.korit.board_back.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
* 복합키 정의
* : @Embeddable 클래스 정의
* */

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecommendationsId implements Serializable {
    private Integer groupId;
    private String userId;
}
