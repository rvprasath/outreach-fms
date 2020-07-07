package com.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fms.entity.VolunteerEnrollmentUnregister;

@Repository
public interface ExcelFileToDatabaseBatchJobVolunteerEnrollmentUnregisterRepository
		extends JpaRepository<VolunteerEnrollmentUnregister, Integer> {

}
