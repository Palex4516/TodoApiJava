package org.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.generated.ApiException;
import org.example.generated.models.TodoItemDTO;

import java.util.List;

@Path("/todo/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoResource {

    @Inject
    TodoService todoService;

    @GET
    public List<TodoItemDTO> getAllTodoItems() throws ApiException {
        return todoService.getTodoApi().todoItemsGetAllTodoItems();
    }


    @GET
    @Path("/{id}")
    public TodoItemDTO getTodoItem(Long id) throws ApiException {
        return todoService.getTodoApi().todoItemsGetTodoItem(id);
    }

    @POST
    public TodoItemDTO createTodoItem(TodoItemDTO todoItemDTO) throws ApiException {
        return todoService.getTodoApi().todoItemsPostTodoItem(todoItemDTO);
    }

    @PUT
    @Path("/{id}")
    public void updateTodoItem(Long id, TodoItemDTO todoItemDTO) throws ApiException {
        todoService.getTodoApi().todoItemsPutTodoItem(id, todoItemDTO);
    }

    @DELETE
    @Path("/{id}")
    public void deleteTodoItem(Long id) throws ApiException {
        todoService.getTodoApi().todoItemsDeleteTodoItem(id);
    }
}
