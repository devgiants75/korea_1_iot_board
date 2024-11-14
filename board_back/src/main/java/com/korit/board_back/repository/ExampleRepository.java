package com.korit.board_back.repository;

import com.korit.board_back.dto.RatingStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExampleRepository extends JpaRepository {
    /*
        @Query 구문
        : Spring Data JPA에서
            JPQL(Java Persistence Query Language)을 사용하여 직접 SQL 쿼리를 작성하는 방식

        1. 사용방법
        @Query 어노테이션
        : JPA Repository 메서드에 직접적인 JPQL 사용 시 작성

        @Query("절 단위로 SQL 문법 작성")

        EX) 특정 가게의 각 평점별 리뷰 수 반환
    */

    @Query("SELECT r.rating, COUNT(r.id) AS reviewCount " +
            "FROM Review r " +
            "JOIN Order o ON r.order.id = o.id " +
            "WHERE o.store.id = :storeId " +
            "GROUP BY r.rating")
    List<Object[]> findReviewStatisticsByStoreId(@Param("storeId") Long storeId);

    /*
        SQL 쿼리문의 경우
        각 절(SELECT, FROM, JOIN, WHERE 등)을 기준으로 문자열로 나눔
        또한, 테이블명은 Entity 명과 맾핑되며(동일하게 작성) Entity 내에서 @Table 어노테이션으로 테이블명 맾핑
        해당 테이블에서 가져오는 필드는 Entity 내에 반드시 존재해야 함.

        @Param() 내에 작성되는 문자열 필드값은 해당 메서드 사용 시 service 로직에서 가져올 데이터 값
            사용은 ':필드명' 으로 작성
    */
}
