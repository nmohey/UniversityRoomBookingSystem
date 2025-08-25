package com.sprints.UniversityRoomBookingSystem.service.department;

import com.sprints.UniversityRoomBookingSystem.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    public DepartmentDto addDepartment(DepartmentDto departmentDto);

    public List<DepartmentDto> getAllDepartments();

    public DepartmentDto getDepartmentById(Long id);

    public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto);

    public String deleteDepartment(Long id);
}
