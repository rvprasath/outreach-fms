package com.fms.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fms.entity.EventSummary;

@Repository
public interface EventSummaryRepository extends JpaRepository<EventSummary, Integer> {

	List<EventSummary> findByPocId(int employeeId);

	Page<EventSummary> findByPocId(int employeeId, Pageable paging);

	EventSummary findByEventId(String eventId);

}
