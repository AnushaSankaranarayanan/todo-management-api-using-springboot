package com.anusha.projects.todomanagement;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class TodoManagementController.
 */
@RestController
public class TodoManagementController {

	/** The todo management service. */
	@Autowired
	private TodoManagementService todoManagementService;

	/**
	 * Gets the list of todos from the DB using call to the service.
	 *
	 * @return the todos
	 */
	@RequestMapping("/todos")
	public List<Todo> getTodos() {
		return todoManagementService.getTodos();
	}

	/**
	 * Gets the todos for the given day from the DB using call to the service.
	 *
	 * @param day
	 *            the day
	 * @return the todos for A day
	 * todos?getAll
	 * todos?getForId=id
	 * todos?getForDay=yyyy
	 * todos?getDueInMinutes=30
	 * 
	 */
	@RequestMapping("/todos/{day}")
	public List<Todo> getTodosForADay(@PathVariable String day) {
		return todoManagementService.getTodosDueForADay(day);
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
	@RequestMapping("/todos/remind/{minutes}")
	public List<Todo> getTodosDueInMinutes(@PathVariable int minutes) throws ParseException {
		return todoManagementService.getTodosDueInMinutes(minutes);
	}

	/**
	 * Adds the todo to the DB using the service.
	 *
	 * @param todo
	 *            the todo
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/todos")
	public @ResponseBody Todo addTodo(@RequestBody Todo todo) {
		return todoManagementService.addTodo(todo);
	}

	/**
	 * Update todo of the id passed in the DB using the service.
	 *
	 * @param todo
	 *            the todo
	 * @param id
	 *            the id
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/todos/{id}")
	public @ResponseBody Todo  updateTodo(@RequestBody Todo todo, @PathVariable String id) {
		return todoManagementService.updateToDo(todo, id);
	}

	/**
	 * Delete of the id passed from the DB using the service.
	 *
	 * @param id
	 *            the id
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/todos/{id}")
	public void deleteTodo(@PathVariable String id) {
		todoManagementService.deleteTodo(id);
	}
}
