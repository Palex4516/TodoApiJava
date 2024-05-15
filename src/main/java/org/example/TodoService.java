package org.example;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.generated.ApiClient;
import org.example.generated.client.TodoItemsApi;

@ApplicationScoped
public class TodoService {

    private final TodoItemsApi todoApi;

    TodoService() {
        ApiClient apiClient = new ApiClient();
        apiClient.updateBaseUri("http://localhost:5130");
        todoApi = new TodoItemsApi(apiClient);
    }

    public TodoItemsApi getTodoApi() {
        return todoApi;
    }
}
