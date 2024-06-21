package sg.nus.iss.service.ca.Tianrui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.nus.iss.service.ca.Tianrui.entities.LeaveType;
import sg.nus.iss.service.ca.Tianrui.service.EmployeeService;
import sg.nus.iss.service.ca.Tianrui.service.LeaveTypeService;

@Controller
@RequestMapping("/leave-types")
public class LeaveTypeController {

    @Autowired
    private LeaveTypeService leaveTypeService;
    
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String listLeaveTypes(Model model) {
        model.addAttribute("leaveTypes", leaveTypeService.findAll());
        model.addAttribute("leaveType", new LeaveType());
        model.addAttribute("employees", employeeService.findAll()); // 添加员工列表
        return "leave-types/list";
    }

    @PostMapping
    public String saveLeaveType(@ModelAttribute("leaveType") LeaveType leaveType) {
        leaveTypeService.save(leaveType);
        return "redirect:/leave-types";
    }

    @GetMapping("/delete/{id}")
    public String deleteLeaveType(@PathVariable("id") Long id) {
        leaveTypeService.deleteById(id);
        return "redirect:/leave-types";
    }
}
