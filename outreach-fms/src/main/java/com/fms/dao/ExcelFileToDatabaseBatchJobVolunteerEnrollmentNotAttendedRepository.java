package com.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fms.entity.VolunteerEnrollmentNotAttended;

@Repository
public interface ExcelFileToDatabaseBatchJobVolunteerEnrollmentNotAttendedRepository
		extends JpaRepository<VolunteerEnrollmentNotAttended, Integer> {

}
