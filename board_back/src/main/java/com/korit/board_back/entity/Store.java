package com.korit.board_back.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stores")
@NoArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name; // 가게 이름 필드

    @Column(length = 255)
    private String address; // 가게 주소 필드

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
    }
}