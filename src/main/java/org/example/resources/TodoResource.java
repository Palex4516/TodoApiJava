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
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.clients.TodoClient;
import org.example.generated.models.TodoItemDTO;
import org.example.models.TodoItemJava;

import java.util.List;

@Path("/todo/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoResource {

    @RestClient
    TodoClient todoClient;

    @GET
    public List<TodoItemJava> getAllTodoItems() {
        return todoClient.getAllTodoItems().stream().map(this::toJava).toList();
    }

    @GET
    @Path("/{id}")
    public TodoItemJava getTodoItem(Long id) {
        return toJava(todoClient.getTodoItem(id));
    }

    @POST
    public TodoItemJava createTodoItem(TodoItemJava todoItemJava) {
        return toJava(todoClient.createTodoItem(toDTO(todoItemJava)));
    }

    @PUT
    @Path("/{id}")
    public void updateTodoItem(Long id, TodoItemJava todoItemJava) {
        todoClient.updateTodoItem(id, toDTO(todoItemJava));
    }

    @DELETE
    @Path("/{id}")
    public void deleteTodoItem(Long id) {
        todoClient.deleteTodoItem(id);
    }

    private TodoItemDTO toDTO(TodoItemJava todoItemJava) {
        return new TodoItemDTO()
                .id(todoItemJava.getId())
                .name(todoItemJava.getName())
                .done(todoItemJava.isDone());
    }

    private TodoItemJava toJava(TodoItemDTO todoItemDTO) {
        return new TodoItemJava(todoItemDTO.getId(), todoItemDTO.getName(), todoItemDTO.getDone());
    }
}
