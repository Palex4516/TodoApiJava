package org.example.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.clients.TodoClient;
import org.example.models.TodoItemJava;

import java.util.List;

@Path("/todo/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoResource {

    @Inject
    TodoClient todoClient;

    @GET
    public List<TodoItemJava> getAllTodoItems() {
        return todoClient.getAllTodoItems();
    }

    @GET
    @Path("/{id}")
    public TodoItemJava getTodoItem(Long id) {
        return todoClient.getTodoItem(id);
    }

    @POST
    public TodoItemJava createTodoItem(TodoItemJava todoItemJava) {
        return todoClient.createTodoItem(todoItemJava);
    }

    @PUT
    @Path("/{id}")
    public void updateTodoItem(Long id, TodoItemJava todoItemJava) {
        todoClient.updateTodoItem(id, todoItemJava);
    }

    @DELETE
    @Path("/{id}")
    public void deleteTodoItem(Long id) {
        todoClient.deleteTodoItem(id);
    }
}
