package com.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.dto.EmployeeDto;
import com.employee.exception.ResourceNotFoundException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
//import org.modelmapper.ModelMapper;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<EmployeeDto> getAllEmployees() {
		// TODO Auto-generated method stub
		//return null;
		
		List<Employee> allEmployee = this.empRepo.findAll();
		//List<SubjectDto> subDtos = allSubjects.stream().map((subject) -> this.modelMapper.map(subject, SubjectDto.class)).collect(Collectors.toList());
		List<EmployeeDto> employeeDtoList = new ArrayList<EmployeeDto>();
		for(Employee employee : allEmployee) {
			EmployeeDto employeeDto = modelMapper.map(employee,EmployeeDto.class);
			//employeeDto.setDepartmentName(employee.getDepartment().getDepartmentName());
			employeeDtoList.add(employeeDto);
		}
		return employeeDtoList;
	}
	
	public String addEmployee(EmployeeDto empDto) {
		// TODO Auto-generated method stub
		
		Employee emp = this.modelMapper.map(empDto,Employee.class);
		
		Employee newEmployee = this.empRepo.save(emp);
		
		return newEmployee.getFirstName()+" "+ newEmployee.getLastName()+" added successfully ";
	}

	@Override
	public EmployeeDto getEmployeeById(Long empId) {
		
		 Employee emp = empRepo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Id not found "));
		// TODO Auto-generated method stub
		//return null;
		EmployeeDto employeeDto = modelMapper.map(emp,EmployeeDto.class);
		
		return employeeDto;

	}

	@Override
	public String updateEmployeeDetails(EmployeeDto employeeDto, Long empId) {
		// TODO Auto-generated method stub
		Employee emp = empRepo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Invalid Id"));
		this.empRepo.save(emp);
		modelMapper.map(employeeDto, emp);
		
		
 		return emp.getFirstName()+" "+emp.getLastName()+" successfully Updated ";
		
	}

	@Override
	public String deleteEmployeeById(Long empId) {
		// TODO Auto-generated method stub
         Employee emp = this.empRepo.findById(empId).orElseThrow(() -> new ResourceNotFoundException("employee Id was not found"));
		
		List<Employee> employeeList = empRepo.findAll();
		employeeList.remove(emp);
		empRepo.delete(emp);
		empRepo.saveAll(employeeList);
		return emp.getFirstName()+" "+emp.getLastName() + " removed";
	}
	
	

}
