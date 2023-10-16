package com.employee.service;

import java.util.List;

import com.employee.dto.EmployeeDto;


public interface EmployeeService {
	
	List<EmployeeDto> getAllEmployees();
	
	String addEmployee(EmployeeDto empDto);
	
	EmployeeDto getEmployeeById(Long empId);
	
	String updateEmployeeDetails(EmployeeDto empDto, Long empId);
	
	String deleteEmployeeById(Long empId);
	

}
