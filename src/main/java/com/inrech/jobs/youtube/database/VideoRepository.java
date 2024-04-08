package com.inrech.jobs.youtube.database;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface VideoRepository extends CrudRepository<Video, Integer> {
    @Query("SELECT v FROM Video v WHERE v.user.id=?1")
    Iterable<Video> findAllVideos(int userId);

    @Query("SELECT v FROM Video v WHERE v.user.id=?1 AND v.videoId=?2")
    Video findByVideoId(int userId, String videoId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Video v WHERE v.user.id=?1 AND v.videoId=?2")
    void deleteVideo(int userId, String videoId);

    @Transactional
    @Modifying
    @Query("UPDATE Video v SET v.favored = ?3 WHERE v.user.id=?1 AND v.videoId=?2")
    void updateVideoFavorite(int userId, String videoId, boolean favored);

    @Transactional
    @Modifying
    @Query("UPDATE Video v SET v.resumeAt = ?3 WHERE v.user.id=?1 AND v.videoId=?2")
    void updateVideoPlaybackPosition(int userId, String videoId, int position);
}
