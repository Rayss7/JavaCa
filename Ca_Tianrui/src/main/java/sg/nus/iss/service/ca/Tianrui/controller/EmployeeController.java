package sg.nus.iss.service.ca.Tianrui.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.nus.iss.service.ca.Tianrui.entities.Employee;
import sg.nus.iss.service.ca.Tianrui.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "employees/list";
    }

    @GetMapping("/new")
    public String showNewEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/form";
    }

    @PostMapping
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        // 设置加入日期
        employee.setJoinDate(new Date(System.currentTimeMillis()));

     // 设置经理
        if (employee.getManager() != null) {
            Employee manager = employeeService.findById(employee.getManager().getEmpId());
            if (manager != null) {
                employee.setManager(manager);
            } else {
                employee.setManager(null);
            }
        }

        employeeService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }

    @GetMapping("/set-active/{id}/{active}")
    public String setActiveEmployee(@PathVariable("id") Long id, @PathVariable("active") boolean active) {
        employeeService.setActive(id, active);
        return "redirect:/employees";
    }
}
