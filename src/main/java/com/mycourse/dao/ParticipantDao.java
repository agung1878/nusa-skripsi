package com.mycourse.dao;

import com.mycourse.entity.Participant;
import com.mycourse.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipantDao extends CrudRepository<Participant, String> {

    List<Participant> findAllByCourseId(String id);
    List<Participant> findByUser(User user);

}
