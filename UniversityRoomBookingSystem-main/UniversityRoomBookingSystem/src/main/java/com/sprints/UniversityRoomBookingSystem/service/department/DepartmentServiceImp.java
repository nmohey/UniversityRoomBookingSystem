package com.sprints.UniversityRoomBookingSystem.service.department;

import com.sprints.UniversityRoomBookingSystem.dto.DepartmentDto;
import com.sprints.UniversityRoomBookingSystem.model.Department;
import com.sprints.UniversityRoomBookingSystem.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImp implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImp(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    private DepartmentDto mapToDto(Department department) {
        return new DepartmentDto(
                department.getName(),
                department.getUsers()
        );
    }

    private Department mapToEntity(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setUsers(departmentDto.getUsers());
        return department;
    }

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        Department department = mapToEntity(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return mapToDto(savedDepartment);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department.map(this::mapToDto).orElse(null);
    }

    @Override
    public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) {
        Optional<Department> existingDepartment = departmentRepository.findById(id);
        if (existingDepartment.isPresent()) {
            Department department = existingDepartment.get();
            department.setName(departmentDto.getName());
            department.setUsers(departmentDto.getUsers());
            Department updatedDepartment = departmentRepository.save(department);
            return mapToDto(updatedDepartment);
        }
        return null;
    }

    @Override
    public String deleteDepartment(Long id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
            return "Department with id " + id + " deleted successfully.";
        }
        return "Department with id " + id + " not found.";
    }
}
