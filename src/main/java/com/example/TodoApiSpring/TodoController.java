package com.example.TodoApiSpring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
// Two @ controller + @RequestBody == Restcontroller
// Controller -> Entry point that collects request
// usage : collecting the request and returning the response
// does not have any business logic here.
// written the response in http response body.
public class TodoController {
    private static List<Todo> todoList;
    public TodoController(){
        todoList = new ArrayList<>();
        todoList.add(new Todo(1,false , "Todo -1",1));
        todoList.add(new Todo(2,true,"Todo-2",2));

    }
    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodos(){
        return ResponseEntity.status(HttpStatus.CREATED).body(todoList);
    }
    @PostMapping("/todos")
    // different way to handle 201 request -> Response status
    // we can use this annotation to set up  @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Todo> CreateTodo(@RequestBody Todo newTodo){
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }
}
