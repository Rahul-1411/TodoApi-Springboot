package com.example.TodoApiSpring;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
// adding prefix to route -> @Request Mapping
// Two @ controller + @RequestBody == Restcontroller
// Controller -> Entry point that collects request
// usage : collecting the request and returning the response
// does not have any business logic here.
// written the response in http response body.
@RequestMapping("/api/v1/todos")
public class TodoController {
    private static List<Todo> todoList;
    private static  final String TODO_NOT_FOUND = "Todo not found";
    public TodoController(){
        todoList = new ArrayList<>();
        todoList.add(new Todo(1,false , "Todo -1",1));
        todoList.add(new Todo(2,true,"Todo-2",2));

    }
    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false,defaultValue = "true") boolean iscompleted){
        System.out.println("Incoming query params "+ iscompleted);
        return ResponseEntity.status(HttpStatus.CREATED).body(todoList);
    }
    @PostMapping
    // different way to handle 201 request -> Response status
    // we can use this annotation to set up  @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Todo> CreateTodo(@RequestBody Todo newTodo){
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }
    @GetMapping("/{todoId}")
    public ResponseEntity<?> getTodobyId( @PathVariable Long todoId)
    {
        for(Todo todo : todoList)
        {
            if(todo.getId() == todoId)
            {
                return ResponseEntity.ok(todo);
            }
        }
        // Along with 404 send a json like message todo not found.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);

    }


}
