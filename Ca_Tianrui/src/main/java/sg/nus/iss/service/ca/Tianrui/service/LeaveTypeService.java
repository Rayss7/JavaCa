package sg.nus.iss.service.ca.Tianrui.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.service.ca.Tianrui.entities.LeaveType;
import sg.nus.iss.service.ca.Tianrui.repositories.LeaveTypeRepository;

@Service
public class LeaveTypeService {

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;

    public List<LeaveType> findAll() {
        return leaveTypeRepository.findAll();
    }

    public LeaveType save(LeaveType leaveType) {
        return leaveTypeRepository.save(leaveType);
    }

    public void deleteById(Long id) {
        leaveTypeRepository.deleteById(id);
    }
}