package com.nmdsolucoesdigitais.todolist.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Task")
public class TaskController {

    @Autowired
    private ITaskRepository repository;

    @PostMapping("/create")
    public TaskModel TaskCreate(@RequestBody TaskModel taskModel){

       var task =  repository.save(taskModel);

       return task;

    }
}
