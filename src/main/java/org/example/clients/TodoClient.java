package org.example.clients;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.example.generated.ApiClient;
import org.example.generated.ApiException;
import org.example.generated.client.TodoItemsApi;
import org.example.generated.models.TodoItemDTO;
import org.example.models.TodoItemJava;

import java.util.List;

@ApplicationScoped
public class TodoClient {

    @ConfigProperty(name = "todo-api.url")
    String todoUrl;

    private TodoItemsApi todoApi;

    @PostConstruct
    void init() {
        ApiClient apiClient = new ApiClient();
        apiClient.updateBaseUri(todoUrl);
        todoApi = new TodoItemsApi(apiClient);
    }

    public List<TodoItemJava> getAllTodoItems() {
        try {
            return todoApi.todoItemsGetAllTodoItems().stream().map(this::toJava).toList();
        } catch (ApiException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public TodoItemJava getTodoItem(Long id) {
        try {
            return toJava(todoApi.todoItemsGetTodoItem(id));
        } catch (ApiException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public TodoItemJava createTodoItem(TodoItemJava todoItemJava) {
        try {
            return toJava(todoApi.todoItemsPostTodoItem(toDTO(todoItemJava)));
        } catch (ApiException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void updateTodoItem(Long id, TodoItemJava todoItemJava) {
        try {
            TodoItemDTO todoItemDTO = toDTO(todoItemJava);
            todoApi.todoItemsPutTodoItem(id, todoItemDTO);
        } catch (ApiException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public void deleteTodoItem(Long id) {
        try {
            todoApi.todoItemsDeleteTodoItem(id);
        } catch (ApiException e) {
            throw new RuntimeException(e.getMessage(), e);
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
