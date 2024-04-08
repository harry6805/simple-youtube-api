package com.inrech.jobs.youtube.database;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface SubscribeRepository extends CrudRepository<Subscribe, Integer> {
    @Query("SELECT s FROM Subscribe s WHERE s.user.id=?1")
    Iterable<Subscribe> getAllSubscribes(int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Subscribe s WHERE s.user.id=?1 AND s.channelId=?2")
    void deleteChannel(int userId, String channelId);
}
