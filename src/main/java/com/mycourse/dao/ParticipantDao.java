package com.mycourse.dao;

import com.mycourse.entity.Participant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ParticipantDao extends CrudRepository<Participant, String> {

    List<Participant> findAllByCourseId(String id);

}
