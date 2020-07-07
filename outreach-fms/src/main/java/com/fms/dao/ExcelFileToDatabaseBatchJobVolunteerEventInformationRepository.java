package com.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fms.entity.EventInformation;

@Repository
public interface ExcelFileToDatabaseBatchJobVolunteerEventInformationRepository extends JpaRepository<EventInformation, Integer> {

}
