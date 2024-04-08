package com.inrech.jobs.youtube.database;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Map;

@Entity
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String country;
    private int viewCount;
    private int subscriberCount;
    private int videoCount;
    private boolean hiddenSubscriberCount;
    private String publishedAt;
    private String thumbnails;
    private String channelTitle;
    private String channelId;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public String getChannelTitle() {
        return channelTitle;
    }

    public String getChannelId() {
        return channelId;
    }

    public User getUser() {
        return user;
    }
}
