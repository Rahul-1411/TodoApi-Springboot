package com.example.TodoApiSpring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Todo> getTodos(){
        return todoList;
    }
    @PostMapping("/todos")
    public Todo CreateTodo(@RequestBody Todo newTodo){
        todoList.add(newTodo);
        return newTodo;
    }



}
