package com.korit.board_back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "meeting_groups")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MeetingGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String groupId;
}
