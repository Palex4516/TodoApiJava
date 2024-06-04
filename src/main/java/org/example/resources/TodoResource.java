package org.example.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.clients.TodoClient;
import org.example.generated.models.TodoItemDTO;
import org.example.models.TodoItemJava;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/todo/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoResource {

    @RestClient
    TodoClient todoClient;

    @GET
    @APIResponse(responseCode = "200", description = "Returns a List of TodoItems")
    public RestResponse<List<TodoItemJava>> getAllTodoItems() {
        return RestResponse.ok(todoClient.getAllTodoItems().stream().map(this::toJava).toList());
    }

    @GET
    @Path("/{id}")
    @APIResponse(responseCode = "200", description = "Returns a TodoItem")
    @APIResponse(responseCode = "404", description = "If Not Found")
    public RestResponse<TodoItemJava> getTodoItem(Long id) {
        try {
            return RestResponse.ok(toJava(todoClient.getTodoItem(id)));
        } catch (ClientWebApplicationException e) {
            if (e.getResponse().getStatus() == RestResponse.StatusCode.NOT_FOUND) {
                return RestResponse.notFound();
            }
            throw e;
        }
    }

    @POST
    @APIResponse(responseCode = "201", description = "TodoItem Created", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = TodoItemJava.class))
    })
    public RestResponse<TodoItemJava> createTodoItem(TodoItemJava todoItemJava) {
        return RestResponse.status(RestResponse.Status.CREATED, toJava(todoClient.createTodoItem(toDTO(todoItemJava))));
    }

    @PUT
    @Path("/{id}")
    @APIResponse(responseCode = "204", description = "Returns No Content")
    @APIResponse(responseCode = "400", description = "If Request is Bad")
    @APIResponse(responseCode = "404", description = "If Not Found")
    public RestResponse<Object> updateTodoItem(Long id, TodoItemJava todoItemJava) {
        if (!id.equals(todoItemJava.getId())) {
            return RestResponse.status(RestResponse.Status.BAD_REQUEST);
        }
        try {
            todoClient.updateTodoItem(id, toDTO(todoItemJava));
            return RestResponse.noContent();
        } catch (ClientWebApplicationException e) {
            if (e.getResponse().getStatus() == RestResponse.StatusCode.NOT_FOUND) {
                return RestResponse.notFound();
            }
            throw e;
        }
    }

    @DELETE
    @Path("/{id}")
    @APIResponse(responseCode = "204", description = "Returns No Content")
    @APIResponse(responseCode = "404", description = "If Not Found")
    public RestResponse<Object> deleteTodoItem(Long id) {
        try {
            todoClient.deleteTodoItem(id);
            return RestResponse.noContent();
        } catch (ClientWebApplicationException e) {
            if (e.getResponse().getStatus() == RestResponse.StatusCode.NOT_FOUND) {
                return RestResponse.notFound();
            }
            throw e;
        }
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
