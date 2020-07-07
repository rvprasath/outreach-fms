package com.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fms.entity.EventInformation;

@Repository
public interface EventInformationRepository extends JpaRepository<EventInformation, Integer>{

	int countByEventIdIn(List<String> eventIds);

}
