package com.techelevator.projects.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Project;

public class JdbcProjectDao implements ProjectDao {

	private final JdbcTemplate jdbcTemplate;

	public JdbcProjectDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Project getProject(int projectId) {
		Project project = null;
		String sql = "SELECT project_id , name, from_date,to_date FROM project WHERE project_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, projectId);
		if (results.next()) {
			project = mapRowToProject(results);
			//return new Department(id, results.getString("name"));

			return new Project(1, "Not Implemented Yet", null, null);
		}
		return null;
	}

	@Override
	public List<Project> getAllProjects() {
		List<Project> allProjects = new ArrayList<>();

		String sqlGetAllProjects = "SELECT project_id, name, from_date,to_date FROM project";

		//" Join timesheet ON project_employee.project_id = timesheet.project_id";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllProjects);
		while (results.next()) {
			allProjects.add(mapRowToProject(results));

		}

		return allProjects;

	}

	@Override
	public Project createProject(Project newProject) {
		String sql = "INSERT INTO project (name,from_date,to_date) VALUES (?,?,?) RETURNING project_id;";
		int projectId = jdbcTemplate.queryForObject(sql, int.class, newProject.getName(), newProject.getFromDate(), newProject.getToDate());
		return getProject(projectId);
	}




	@Override
	public void deleteProject(int projectId) {
		Project project = new Project();
		String sql = "DELETE FROM project_employee,project WHERE project_id IS NULL;";
		int numberofProjectDeleted = jdbcTemplate.update(sql, project.getId());
				if( numberofProjectDeleted==0){
			System.out.println("Project Was Deleted");
		}
				else{
			System.out.println("Project Delete Failed.");
		}
		jdbcTemplate.update(sql, projectId);


	}

	private Project mapRowToProject(SqlRowSet rowSet) {
		Project project = new Project();
		project.setId(rowSet.getInt("project_id"));
		project.setName(rowSet.getString("name"));


		if (rowSet.getDate("from_date") !=null){
			project.setFromDate((rowSet.getDate("from_date")).toLocalDate());
		}
		if (rowSet.getDate("to_date") !=null){
			project.setFromDate(rowSet.getDate("to_date").toLocalDate());
		}
		return project;
	}

}
