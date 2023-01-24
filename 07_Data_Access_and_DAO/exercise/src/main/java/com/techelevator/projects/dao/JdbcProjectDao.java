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

		}
		return project;
	}

	@Override
	public List<Project> getAllProjects() {
		List<Project> allProjects = new ArrayList<>();

		String sqlGetAllProjects = "SELECT project_id, name, from_date,to_date FROM project";
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
		String deleteTimesheetSql = "DELETE FROM timesheet WHERE project_id = ?;";
		int numberOfRowsDeleted = jdbcTemplate.update(deleteTimesheetSql, projectId);

		String deleteProjectEmployeeSql = "DELETE FROM project_employee WHERE project_id = ?;";
		numberOfRowsDeleted = jdbcTemplate.update(deleteProjectEmployeeSql, projectId);

		String deleteProjectSql = "DELETE FROM project WHERE project_id = ?;";
		 numberOfRowsDeleted = jdbcTemplate.update(deleteProjectSql, projectId);

				if( numberOfRowsDeleted == 1){
			System.out.println("Project Was Deleted");
		}
				else {
			System.out.println("Project Delete Failed.");
		}

	}

	public Project mapRowToProject(SqlRowSet rowset) {
		Project project = new Project();
		project.setId(rowset.getInt("project_id"));
		project.setName(rowset.getString("name"));
		if (rowset.getDate("from_date") !=null){
			project.setFromDate(rowset.getDate("from_date").toLocalDate());
		}
		if (rowset.getDate("to_date") !=null){
			project.setToDate(rowset.getDate("to_date").toLocalDate());
		}
		return project;
	}

}
