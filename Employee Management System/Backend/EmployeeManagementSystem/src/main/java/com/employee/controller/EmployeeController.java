package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.ApiResponse;
import com.employee.dto.EmployeeDto;
import com.employee.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/employees")
	public ResponseEntity<?> getAllEmployees(){
		try {
			System.out.println("in get all available employees ");
			return new ResponseEntity<>(employeeService.getAllEmployees(),HttpStatus.OK);
		}
		catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PostMapping("/employees")
	public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto empDto){
	          
//		SubjectDto createdSubjectDto =  this.subjectService.addSubject(subjectDto, professorId);
//		return  new ResponseEntity<SubjectDto>(createdSubjectDto,HttpStatus.CREATED);
		try {
			System.out.println("in add new Employee" + empDto);
			return new ResponseEntity<>(new ApiResponse(employeeService.addEmployee(empDto)),HttpStatus.CREATED);
		}
		catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@GetMapping("/employees/{empId}")
	public ResponseEntity<?> getEmployeeById(@PathVariable Long empId) 
	{
//	    SubjectDto subjectDto = this.subjectService.getSubjectById(subjectId);
//		return new ResponseEntity<SubjectDto>(subjectDto,HttpStatus.OK);
		try {
			System.out.println("in get employee by id");
			return new ResponseEntity<>(employeeService.getEmployeeById(empId),HttpStatus.OK);
		}
		catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
	
	@PutMapping("/employees/{employeeId}")
	public String updateSubject(@RequestBody EmployeeDto employeeDto ,@PathVariable Long employeeId)
	{
//		SubjectDto updateSubject = this.subjectService.updateSubject(subjectDto,subjectId);
//		return new ResponseEntity<SubjectDto>(updateSubject, HttpStatus.OK);
		
		return employeeService.updateEmployeeDetails(employeeDto, employeeId);
	}


	@DeleteMapping("/employees/{employeeId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId )
	{
//		this.subjectService.deleteSubject(subjectId);
//	    return new ApiResponse("Subject is successfully deleted");
		try {
			System.out.println("in delete Employee by id");
			return new ResponseEntity<>(employeeService.deleteEmployeeById(employeeId),HttpStatus.OK);
		}
		catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage()));
		}
	}
}
