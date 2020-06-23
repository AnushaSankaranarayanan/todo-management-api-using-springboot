package com.anusha.projects.todomanagement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.anusha.projects.todomanagement.Todo;
import com.anusha.projects.todomanagement.TodoManagementController;
import com.anusha.projects.todomanagement.TodoManagementService;

/**
 * The Class TodoManagementControllerTest.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = TodoManagementController.class, secure = false)
public class TodoManagementControllerTest {

	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;

	/** The todo management service mock. */
	@MockBean
	private TodoManagementService todoManagementServiceMock;

	/** The todos url. */
	private String todosUrl = "/todos";
	
	/** The get todos for A day url. */
	private String getTodosForADayUrl = "/todos/2019-04-19";
	
	/** The get todos due in minutes url. */
	private String getTodosDueInMinutesUrl = "/todos/remind/30";
	
	/** The update todo url. */
	private String updateTodoUrl = "/todos/1";
	
	/** The delete todo url. */
	private String deleteTodoUrl = "/todos/1";

	/**
	 * Test get todos.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetTodos() throws Exception {
		List<Todo> todoList = new ArrayList<>(Arrays.asList(
				new Todo("1", "Pick up Kid From DayCare", "Pick up Kid From DayCare", "2019-04-30", "15:00"),
				new Todo("2", "Meeting with HR Manager", "Meeting with HR Manager", "2019-04-19", "11:00")));

		// Define Behaviors
		Mockito.when(todoManagementServiceMock.getTodos()).thenReturn(todoList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(todosUrl).accept(MediaType.APPLICATION_JSON);

		// Invoke the method
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "[{\"id\":\"1\",\"name\":\"Pick up Kid From DayCare\",\"description\":\"Pick up Kid From DayCare\",\"date\":\"2019-04-30\",\"time\":\"15:00\",\"status\":\"PENDING\"},{\"id\":\"2\",\"name\":\"Meeting with HR Manager\",\"description\":\"Meeting with HR Manager\",\"date\":\"2019-04-19\",\"time\":\"11:00\",\"status\":\"PENDING\"}]\n"
				+ "";
		// Assert response JSON
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);

	}

	/**
	 * Test get todos for A day.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetTodosForADay() throws Exception {
		List<Todo> todoList = new ArrayList<>(Arrays.asList(
				new Todo("1", "Pick up Kid From DayCare", "Pick up Kid From DayCare", "2019-04-19", "15:00"),
				new Todo("2", "Meeting with HR Manager", "Meeting with HR Manager", "2019-04-19", "11:00")));

		// Define Behaviors
		Mockito.when(todoManagementServiceMock.getTodosDueForADay(Mockito.anyString())).thenReturn(todoList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getTodosForADayUrl)
				.accept(MediaType.APPLICATION_JSON);
		// Invoke the method
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "[{\"id\":\"1\",\"name\":\"Pick up Kid From DayCare\",\"description\":\"Pick up Kid From DayCare\",\"date\":\"2019-04-19\",\"time\":\"15:00\",\"status\":\"PENDING\"},{\"id\":\"2\",\"name\":\"Meeting with HR Manager\",\"description\":\"Meeting with HR Manager\",\"date\":\"2019-04-19\",\"time\":\"11:00\",\"status\":\"PENDING\"}]\n"
				+ "";
		// Assert response JSON
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	}

	/**
	 * Test get todos due in minutes.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testGetTodosDueInMinutes() throws Exception {
		List<Todo> todoList = new ArrayList<>(Arrays.asList(
				new Todo("1", "Pick up Kid From DayCare", "Pick up Kid From DayCare", "2019-04-19", "15:00"),
				new Todo("2", "Meeting with HR Manager", "Meeting with HR Manager", "2019-04-19", "11:00")));

		// Define Behaviors
		Mockito.when(todoManagementServiceMock.getTodosDueInMinutes(30)).thenReturn(todoList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(getTodosDueInMinutesUrl)
				.accept(MediaType.APPLICATION_JSON);

		// Invoke the method
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "[{\"id\":\"1\",\"name\":\"Pick up Kid From DayCare\",\"description\":\"Pick up Kid From DayCare\",\"date\":\"2019-04-19\",\"time\":\"15:00\",\"status\":\"PENDING\"},{\"id\":\"2\",\"name\":\"Meeting with HR Manager\",\"description\":\"Meeting with HR Manager\",\"date\":\"2019-04-19\",\"time\":\"11:00\",\"status\":\"PENDING\"}]\n"
				+ "";
		// Assert response JSON
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	}

	/**
	 * Test add todo.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testAddTodo() throws Exception {
		Todo todoTask = new Todo("1", "Pick up Kid From DayCare", "Pick up Kid From DayCare", "2019-04-19", "15:00");

		// Define Behaviors
		Mockito.when(todoManagementServiceMock.addTodo(Mockito.any(Todo.class))).thenReturn(todoTask);

		// pass in any JSON
		String exampleTodoJson = "{\"id\":\"1\"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(todosUrl).accept(MediaType.APPLICATION_JSON)
				.content(exampleTodoJson).contentType(MediaType.APPLICATION_JSON);

		// Invoke the Method
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String expected = "{\"id\":\"1\",\"name\":\"Pick up Kid From DayCare\",\"description\":\"Pick up Kid From DayCare\",\"date\":\"2019-04-19\",\"time\":\"15:00\",\"status\":\"PENDING\"}";
		// Assert Response JSON
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);

	}

	/**
	 * Test update todo.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testUpdateTodo() throws Exception {
		Todo todoTask = new Todo("1", "Pick up Kid From DayCare", "Pick up Kid From DayCare", "2019-04-19", "15:00");

		// Define Behaviors
		Mockito.when(todoManagementServiceMock.updateToDo(Mockito.any(Todo.class), Mockito.any(String.class)))
				.thenReturn(todoTask);

		// pass in any JSON
		String exampleTodoJson = "{\"id\":\"1\"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(updateTodoUrl).accept(MediaType.APPLICATION_JSON)
				.content(exampleTodoJson).contentType(MediaType.APPLICATION_JSON);

		// Invoke the Method
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String expected = "{\"id\":\"1\",\"name\":\"Pick up Kid From DayCare\",\"description\":\"Pick up Kid From DayCare\",\"date\":\"2019-04-19\",\"time\":\"15:00\",\"status\":\"PENDING\"}";
		// Assert Response JSON
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
	}

	/**
	 * Test delete todo.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDeleteTodo() throws Exception {
		// Do Nothing for delete. Will be verified in the test case.
		Mockito.doNothing().when(todoManagementServiceMock).deleteTodo(Mockito.any(String.class));

		// pass in any JSON
		String exampleTodoJson = "{\"id\":\"1\"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(deleteTodoUrl).accept(MediaType.APPLICATION_JSON)
				.content(exampleTodoJson).contentType(MediaType.APPLICATION_JSON);

		// Invoke the Method
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		// Verify that Mock has been called
		verify(todoManagementServiceMock, times(1)).deleteTodo("1");

		// Assert for status
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

}
