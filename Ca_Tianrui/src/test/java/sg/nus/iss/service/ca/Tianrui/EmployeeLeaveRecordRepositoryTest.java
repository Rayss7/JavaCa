package sg.nus.iss.service.ca.Tianrui;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sg.nus.iss.service.ca.Tianrui.entities.Employee;
import sg.nus.iss.service.ca.Tianrui.entities.EmployeeLeaveRecord;
import sg.nus.iss.service.ca.Tianrui.entities.LeaveEntitlement;
import sg.nus.iss.service.ca.Tianrui.repositories.EmployeeLeaveRecordRepository;
import sg.nus.iss.service.ca.Tianrui.repositories.EmployeeRepository;
import sg.nus.iss.service.ca.Tianrui.repositories.LeaveEntitlementRepository;

@SpringBootTest
public class EmployeeLeaveRecordRepositoryTest {
    @Autowired
    private EmployeeLeaveRecordRepository employeeLeaveRecordRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private LeaveEntitlementRepository leaveEntitlementRepository;

    @Test
    public void testSaveEntity() {
        employeeLeaveRecordRepository.deleteAll();
        employeeRepository.deleteAll();
        leaveEntitlementRepository.deleteAll();

        LeaveEntitlement leaveAdmin = new LeaveEntitlement("Administrative", 20, 30);
        LeaveEntitlement leaveProfessional = new LeaveEntitlement("Professional", 10, 5);
        leaveEntitlementRepository.save(leaveAdmin);
        leaveEntitlementRepository.save(leaveProfessional);

        
        Employee ray = new Employee("Ray", "Sun", leaveAdmin, Date.valueOf("2014-06-15"), "Active", null, "password123");
        employeeRepository.save(ray);

        Employee zeyu = new Employee("Zeyu", "Lin", leaveProfessional, Date.valueOf("2014-06-15"), "Active", ray, "password123");
        employeeRepository.save(zeyu);

        Employee joyish = new Employee("Joyish", "Zhao", leaveProfessional, Date.valueOf("2014-06-15"), "Active", ray, "password123");
        employeeRepository.save(joyish);

        EmployeeLeaveRecord elr = new EmployeeLeaveRecord();
        elr.setLeaveQty(1.0);
        elr.setLeaveDate(Date.valueOf("2014-06-15"));
        elr.setEmployee(joyish);
        elr.setLeaveType("Dayoff");
        elr.setStatus("Approval");
        elr.setApproveManager(ray);
        elr.setCoveringEmployee(zeyu);
        elr.setAdditionalReason("Personal reasons");
        elr.setOverseas(false);
        elr.setContactDetail("123-456-7890");
        employeeLeaveRecordRepository.save(elr);

        EmployeeLeaveRecord elrRepo = employeeLeaveRecordRepository.findEmployeeLeaveRecordById(elr.getLeaveId());

        assertThat(elrRepo.getLeaveId()).isNotNull();
        assertThat(elrRepo).isNotNull();
        assertEquals(elr.getLeaveId(), elrRepo.getLeaveId());
        assertEquals(1.0, elrRepo.getLeaveQty());
        assertThat(elrRepo.getLeaveDate()).isNotNull();
        assertEquals(joyish.toString(), elrRepo.getEmployee().toString());
        assertEquals("Dayoff", elrRepo.getLeaveType());
        assertEquals("Approval", elrRepo.getStatus());
        assertEquals(ray.toString(), elrRepo.getApproveManager().toString());
        assertEquals(zeyu.toString(), elrRepo.getCoveringEmployee().toString());
        assertEquals("Personal reasons", elrRepo.getAdditionalReason());
        assertEquals(false, elrRepo.isOverseas());
        assertEquals("123-456-7890", elrRepo.getContactDetail());
    }
}