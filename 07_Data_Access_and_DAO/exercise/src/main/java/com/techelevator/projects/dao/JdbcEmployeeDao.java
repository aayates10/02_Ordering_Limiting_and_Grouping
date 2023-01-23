package com.techelevator.projects.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.techelevator.projects.model.Department;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.projects.model.Employee;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcEmployeeDao implements EmployeeDao {

	private final JdbcTemplate jdbcTemplate;

	public JdbcEmployeeDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employeeList = new ArrayList<>();

		String sqlGetAllEmployees = "SELECT employee_id, department_id, first_name, last_name, birth_date, hire_date FROM employee;";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllEmployees);
		while (results.next()){
			employeeList.add(mapRowToEmployee(results));

		}

		return  employeeList;
	}

	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch) {
		List<Employee> employeeNameList = new ArrayList<>();

		String sqlGetNameList = "SELECT * FROM employee WHERE first_name LIKE ? AND last_name LIKE ?;";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetNameList,"%"+firstNameSearch,"%"+lastNameSearch);
		while (results.next()){
			Employee employeeName = mapRowToEmployee(results);
			employeeNameList.add(employeeName);

		}

		return  employeeNameList;
	}

	@Override
	public List<Employee> getEmployeesByProjectId(int projectId) {
		List<Employee> employeeProjectList = new ArrayList<Employee>();

		String sqlGetAllProjects = "SELECT department_id, first_name, last_name, birth_date, hire_date, employee.employee_id,project_id FROM employee JOIN project_employee ON employee.employee_id = project_employee.employee_id  WHERE project_id =?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllProjects,projectId);
		while (results.next()){
			Employee employeeProjectId = mapRowToEmployee(results);
			employeeProjectList.add(employeeProjectId);

		}

		return  employeeProjectList;
	}

	@Override
	public void addEmployeeToProject(int projectId, int employeeId) {
	}

	@Override
	public void removeEmployeeFromProject(int projectId, int employeeId) {
		String sql = "DELETE FROM project_employee WHERE project_id = ? AND employee_id = ?;";
		jdbcTemplate.update(sql,projectId,employeeId);
	}


	@Override
	public List<Employee> getEmployeesWithoutProjects() {
		List<Employee> EmployeeNoproject = new ArrayList<>();
		String sqlGetWithoutProjects = "SELECT employee.employee_id, employee.department_id, first_name, last_name, birth_date, hire_date, project_employee.project_id From employee LEFT JOIN project_employee ON project_employee.employee_id = employee.employee_id WHERE project_employee.project_id IS NULL";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetWithoutProjects);
		while (results.next()){
			EmployeeNoproject.add(mapRowToEmployee(results));
		}

		return EmployeeNoproject;
	}


	private Employee mapRowToEmployee(SqlRowSet rowSet) {
		Employee employee = new Employee();
		employee.setId(rowSet.getInt("employee_id"));
		employee.setDepartmentId(rowSet.getInt("department_id"));
		employee.setFirstName(rowSet.getString("first_name"));
		employee.setLastName(rowSet.getString("last_name"));
		employee.setBirthDate(rowSet.getDate("birth_date").toLocalDate());
		employee.setHireDate(rowSet.getDate("hire_date").toLocalDate());
		return employee;
	}
}
