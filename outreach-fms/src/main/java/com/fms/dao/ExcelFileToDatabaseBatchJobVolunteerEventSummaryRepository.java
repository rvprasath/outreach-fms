package com.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fms.entity.EventSummary;

@Repository
public interface ExcelFileToDatabaseBatchJobVolunteerEventSummaryRepository extends JpaRepository<EventSummary, Integer> {

}
