package org.example.clients;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.generated.models.TodoItemDTO;

import java.util.List;

@RegisterRestClient(configKey = "todo-api")
@Path("/api/TodoItems")
public interface TodoClient {

    @GET
    List<TodoItemDTO> getAllTodoItems();

    @GET
    @Path("/{id}")
    TodoItemDTO getTodoItem(@PathParam("id") Long id);

    @POST
    TodoItemDTO createTodoItem(TodoItemDTO todoItemDTO);

    @PUT
    @Path("/{id}")
    void updateTodoItem(@PathParam("id") Long id, TodoItemDTO todoItemDTO);

    @DELETE
    @Path("/{id}")
    void deleteTodoItem(@PathParam("id") Long id);
}
