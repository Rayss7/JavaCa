package sg.nus.iss.service.ca.Tianrui.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.nus.iss.service.ca.Tianrui.entities.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e WHERE e.id = :empId")
	Employee findManagerById(@Param("empId") long empId);
	
	@Query("SELECT e FROM Employee e WHERE e.id = :empId")
	Employee findCoveringEmployeeById(@Param("empId") long empId);
}
