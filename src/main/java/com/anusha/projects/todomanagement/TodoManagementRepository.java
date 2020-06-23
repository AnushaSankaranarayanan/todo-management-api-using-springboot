package com.anusha.projects.todomanagement;

import org.springframework.data.repository.CrudRepository;

/**
 * The Interface TodoManagementRepository.
 * Brings along handy methods from the Super class, to be used directly from the Service layer.
 * Will be handy for Simple operations like CRUD and at the same time, custom methods can be defined.
 */
public interface TodoManagementRepository extends CrudRepository<Todo, String> {


}