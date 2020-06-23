package com.anusha.projects.todomanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Class TodoManagementService.
 */
@Service
public class TodoManagementService {

	/** The logger. */
	Logger logger = Logger.getLogger(this.getClass());

	/*
	 * private List<Todo> todoList = new ArrayList<>( Arrays.asList(new Todo("id1",
	 * "name1", "desc1", "2018-04-18", "11:30"), new Todo("id2", "name2", "desc2",
	 * "2018-04-19", "13:45")));
	 */

	/** The todo management repository. */
	@Autowired
	private TodoManagementRepository todoManagementRepository;

	/**
	 * Gets all the todos from DB.
	 *
	 * @return the todos
	 */
	public List<Todo> getTodos() {
		List<Todo> todoList = new ArrayList<>();
		todoManagementRepository.findAll().forEach(todoList::add);
		return todoList;
	}

	/**
	 * Gets the todos due for A day. Retrieves whole list first and then filter
	 * based on the day. Only Pending Todos are retrieved as a best practice.
	 *
	 * @param day
	 *            the day
	 * @return the todos due for A day
	 */
	public List<Todo> getTodosDueForADay(String day) {
		List<Todo> todoList = new ArrayList<>();
		List<Todo> todosForTheDay = new ArrayList<>();

		todoManagementRepository.findAll().forEach(todoList::add);

		todoList.stream().filter(t -> t.getDate().equals(day) && t.getStatus().equalsIgnoreCase("PENDING"))
				.forEach(todosForTheDay::add);
		return todosForTheDay;
	}

	/**
	 * Gets the todos due in minutes specified. All the previous pending todos will
	 * also be returned as a standard practice.
	 * 
	 * @param minutes
	 *            the minutes
	 * @return the todos due in minutes
	 * @throws ParseException
	 *             the parse exception
	 */
	public List<Todo> getTodosDueInMinutes(int minutes) throws ParseException {

		// Retrieve all Todos from the DB
		List<Todo> todoList = new ArrayList<>();
		todoManagementRepository.findAll().forEach(todoList::add);

		List<Todo> todosDueInMinutes = new ArrayList<>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		/**
		 * add the passed in minutes here. Iterate all tasks from the list above and
		 * pick up tasks which are due before this time
		 */
		cal.add(Calendar.MINUTE, minutes);//
		Date systemDate = cal.getTime();

		for (Todo todo : todoList) {
			Date dateFromList = sdf.parse(todo.getDate() + " " + todo.getTime());
			if (dateFromList.compareTo(systemDate) < 0 && todo.getStatus().equalsIgnoreCase("PENDING")) {
				System.out.println("dateFromList is before systemDate and status is pending");
				todosDueInMinutes.add(todo);
			}
		}
		return todosDueInMinutes;
	}

	/**
	 * Adds the todo to the DB as PENDING by default.
	 *
	 * @param todo
	 *            the todo
	 */
	public Todo addTodo(Todo todo) {
		if (null == todo.getStatus()) {
			todo.setStatus("PENDING");
		}
		logger.info("Saved to DB: "+ todo.toString());
		return todoManagementRepository.save(todo);
	}

	/**
	 * Update the Todo in DB. Check the status and force it to PENDING if not
	 * specified.
	 *
	 * @param todo
	 *            the todo
	 * @param id
	 *            the id
	 */
	public Todo updateToDo(Todo todo, String id) {
		if (null == todo.getStatus()) {
			todo.setStatus("PENDING");
		}
		// save will do a save or update if there are changes to the Todo.
		
		logger.info("Updated in DB: "+ todo.toString());
		return todoManagementRepository.save(todo);
	}

	/**
	 * Delete todo from the DB.
	 *
	 * @param id
	 *            the id
	 */
	public void deleteTodo(String id) {
		todoManagementRepository.delete(id);
		logger.info("Deleted from DB: "+ id);
	}

}
