package com.nmdsolucoesdigitais.todolist.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/Task")
public class TaskController {

    @Autowired
    private ITaskRepository repository;

    @PostMapping("/create")
    public ResponseEntity TaskCreate(@RequestBody TaskModel taskModel, HttpServletRequest request){
        
        var IdUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID)IdUser);

        var currentData = LocalDateTime.now();

        // if(currentData.isAfter(taskModel.getStartAt() )|| currentData.isAfter(taskModel.getEndAt() )){
        //     return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        //     .body("Data de inicio/ Data de Término deve ser marior que a data atual");
        // }
        
        // if(taskModel.getStartAt().isAfter(taskModel.getEndAt())){
        //     return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        //     .body("Data de inicio dever ser meno que data Término");
        // }
       var task =  repository.save(taskModel);

       return ResponseEntity.status(HttpStatus.OK).body(task);

    }

    @GetMapping("/findAll")
    public ResponseEntity<List<TaskModel>> getTaskAll( UUID IdUser ,HttpServletRequest request) {
        IdUser = (UUID) request.getAttribute("idUser");
        var tasks = this.repository.findByIdUser(IdUser);
        return ResponseEntity.ok(tasks);     
    }


    @PutMapping("/atualizar/{id}")
    public ResponseEntity Update( @PathVariable UUID id, @RequestBody TaskModel taskModel, HttpServletRequest request ){
         var idUser = (UUID) request.getAttribute("idUser");

        taskModel = repository.getReferenceById(id);
        taskModel.setIdUser(idUser);
        repository.save(taskModel);
         return ResponseEntity.ok(taskModel);
        
    }
    
}
