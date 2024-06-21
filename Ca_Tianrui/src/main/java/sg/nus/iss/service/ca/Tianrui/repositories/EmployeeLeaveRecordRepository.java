package sg.nus.iss.service.ca.Tianrui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.nus.iss.service.ca.Tianrui.entities.EmployeeLeaveRecord;

@Repository
public interface EmployeeLeaveRecordRepository extends JpaRepository<EmployeeLeaveRecord, Long> {
	
	@Query("SELECT e FROM EmployeeLeaveRecord e WHERE e.leaveId = :id")
	public EmployeeLeaveRecord findEmployeeLeaveRecordById(@Param("id") Long id);
}
