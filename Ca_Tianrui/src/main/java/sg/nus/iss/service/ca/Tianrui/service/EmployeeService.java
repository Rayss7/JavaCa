package sg.nus.iss.service.ca.Tianrui.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.service.ca.Tianrui.entities.Employee;
import sg.nus.iss.service.ca.Tianrui.repositories.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public void setActive(Long id, boolean active) {
        Employee employee = findById(id);
        if (employee != null) {
            employee.setEmpStatus(active ? "Active" : "Inactive");
            save(employee);
        }
    }
}

