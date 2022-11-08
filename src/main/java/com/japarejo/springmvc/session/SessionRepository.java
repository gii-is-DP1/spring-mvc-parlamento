package com.japarejo.springmvc.session;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<Session,Long> {
    @Query("SELECT s FROM Session s WHERE s.sala.description=:name")
    List<Session> findByRoomDescription(@Param("name") String room);
}
