package com.security.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.security.template.model.SubModule;
import com.security.template.service.SubModuleService;

import java.util.List;

@RestController
@RequestMapping("/sub")
public class SubModuleController {

    @Autowired
    private SubModuleService subModuleService;

    @GetMapping
    public List<SubModule> getAllSubModules() {
        return subModuleService.getAllSubModules();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubModule> getSubModuleById(@PathVariable Long id) {
        return subModuleService.getSubModuleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{courseID}")
    public SubModule createSubModule(@RequestBody SubModule subModule, @PathVariable Long courseID) {
        return subModuleService.createSubModule(subModule, courseID);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubModule> updateSubModule(@PathVariable Long id, @RequestBody SubModule subModule) {
        return subModuleService.updateSubModule(id, subModule)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubModule(@PathVariable Long id) {
        if (subModuleService.deleteSubModule(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
