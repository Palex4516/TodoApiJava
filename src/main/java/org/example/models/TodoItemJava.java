package org.example.models;

public class TodoItemJava {
    private long id;
    private String name;
    private boolean done;

    public TodoItemJava() {
    }

    public TodoItemJava(long id, String name, boolean done) {
        this.id = id;
        this.name = name;
        this.done = done;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
