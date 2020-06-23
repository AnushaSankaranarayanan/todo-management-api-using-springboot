package com.anusha.projects.todomanagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.anusha.projects.todomanagement.Todo;
import com.anusha.projects.todomanagement.TodoManagementRepository;
import com.anusha.projects.todomanagement.TodoManagementService;

/**
 * The Class TodoManagementServiceTest.
 */
@RunWith(SpringRunner.class)
public class TodoManagementServiceTest {

	/**
	 * The Class TodoManagementServiceTestContextConfiguration.
	 */
	@TestConfiguration
	static class TodoManagementServiceTestContextConfiguration {

		/**
		 * Todo management service.
		 *
		 * @return the todo management service
		 */
		@Bean
		public TodoManagementService todoManagementService() {
			return new TodoManagementService();
		}
	}

	/** The todo management service. */
	@Autowired
	private TodoManagementService todoManagementService;

	/** The todo management repository. */
	@MockBean
	private TodoManagementRepository todoManagementRepository;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		// Set up behaviour .
		// Define a list with 2 items
		List<Todo> todoList = new ArrayList<>(Arrays.asList(
				new Todo("1", "Pick up Kid From DayCare", "Pick up Kid From DayCare", "2019-04-30", "15:00"),
				new Todo("2", "Meeting with HR Manager", "Meeting with HR Manager", "2019-04-19", "11:00")));

		Mockito.when(todoManagementRepository.findAll()).thenReturn(todoList);

		// Setup data for Save / Update
		Todo mockedTodoTask = new Todo("mockedId", "Pick up Kid From DayCare", "Pick up Kid From DayCare", "2018-04-20",
				"15:00");
		Mockito.when(todoManagementRepository.save(Mockito.any(Todo.class))).thenReturn(mockedTodoTask);

		// Do Nothing for delete. Will be verified in the test case.
		Mockito.doNothing().when(todoManagementRepository).delete(Mockito.any(String.class));

	}

	/**
	 * Test get todos.
	 */
	@Test
	public void testGetTodos() {
		assertThat(todoManagementService.getTodos().size()).isEqualTo(2);
	}

	/**
	 * Test get todos due for A day.
	 */
	@Test
	public void testGetTodosDueForADay() {
		assertThat(todoManagementService.getTodosDueForADay("2019-04-19").size()).isEqualTo(1);
	}

	/**
	 * Test get todos due in minutes.
	 */
	@Test
	public void testGetTodosDueInMinutes() {
		try {
			assertThat(todoManagementService.getTodosDueInMinutes(15).size()).isEqualTo(0);
		} catch (ParseException e) {
			fail("testGetTodosDueInMinutes failed due to" + e.getMessage());
		}
	}

	/**
	 * Test add todo.
	 */
	@Test
	public void testAddTodo() {
		assertThat(todoManagementService.addTodo(new Todo()).getId()).isEqualTo("mockedId");
	}

	/**
	 * Test update todo.
	 */
	@Test
	public void testUpdateToDo() {
		assertThat(todoManagementService.addTodo(new Todo()).getId()).isEqualTo("mockedId");
	}

	/**
	 * Test delete todo.
	 */
	@Test
	public void testDeleteTodo() {
		todoManagementService.deleteTodo("1");
		verify(todoManagementRepository, times(1)).delete("1");
		// assert that it has not deleted anything by quering the size
		assertThat(todoManagementService.getTodos().size()).isEqualTo(2);
	}

}
