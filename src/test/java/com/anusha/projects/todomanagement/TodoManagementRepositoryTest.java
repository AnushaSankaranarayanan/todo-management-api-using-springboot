package com.anusha.projects.todomanagement;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.anusha.projects.todomanagement.Todo;
import com.anusha.projects.todomanagement.TodoManagementRepository;

/**
 * The Class TodoManagementRepositoryTest.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoManagementRepositoryTest {

    /** The entity manager. */
    @Autowired
    private TestEntityManager entityManager;
    
    /** The todo management repository. */
    @Autowired
    private TodoManagementRepository todoManagementRepository;
    
    /**
     * Test find all create update and delete.
     */
    @Test
    public void testFindAllCreateUpdateAndDelete() {
    	todoManagementRepository.save(new Todo("1", "Pick up Kid From DayCare", "Pick up Kid From DayCare", "2019-04-30", "15:00"));
    }
	
	/**
	 * Test find all.
	 */
	@Test
	public void testFindAll() {
		entityManager.persist(new Todo("1", "Pick up Kid From DayCare", "Pick up Kid From DayCare", "2019-04-30", "15:00"));
		entityManager.persist(new Todo("2", "Meeting with HR Manager", "Meeting with HR Manager", "2019-04-19", "11:00"));
		assertThat(todoManagementRepository.findAll()).size().isEqualTo(2);
	}

	/**
	 * Test save S.
	 */
	@Test
	public void testSaveS() {
		Todo addedTodo = todoManagementRepository.save(new Todo("1", "Pick up Kid From DayCare", "Pick up Kid From DayCare", "2019-04-30", "15:00"));
		assertThat(addedTodo.getId()).isEqualTo("1");
	}

	/**
	 * Test delete ID.
	 */
	@Test
	public void testDeleteID() {
		//Persist 2 entries - Deleyte 1 . Assert for the list size as 1
		entityManager.persist(new Todo("1", "Pick up Kid From DayCare", "Pick up Kid From DayCare", "2019-04-30", "15:00"));
		entityManager.persist(new Todo("2", "Meeting with HR Manager", "Meeting with HR Manager", "2019-04-19", "11:00"));
		
		todoManagementRepository.delete("1");
		assertThat(todoManagementRepository.findAll()).size().isEqualTo(1);
	}

}
