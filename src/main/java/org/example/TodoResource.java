package org.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.generated.ApiException;
import org.example.generated.models.TodoItemDTO;

import java.util.List;

@Path("/todo")
public class TodoResource {

    @Inject
    TodoService todoService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Din mamma";
    }

    @GET
    @Path("/item")
    @Produces(MediaType.APPLICATION_JSON)
    public TodoItemDTO todo() {
        return new TodoItemDTO().name("Din mamma").done(false);
    }


    @GET
    @Path("/tjosan")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TodoItemDTO> tjosan() throws ApiException {
        return todoService.getTodoApi().todoItemsGetAllTodoItems();
    }

    @POST
    @Path("/tjosan")
    @Produces(MediaType.APPLICATION_JSON)
    public TodoItemDTO tjosanPOST(TodoItemDTO todoItemDTO) throws ApiException {
        return todoService.getTodoApi().todoItemsPostTodoItem(todoItemDTO);
    }
}
