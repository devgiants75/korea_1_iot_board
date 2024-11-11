package com.korit.board_back.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "recommendations")
public class Recommendation {

    @EmbeddedId
    private RecommendationsId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private MeetingGroup meetingGroup;
}
