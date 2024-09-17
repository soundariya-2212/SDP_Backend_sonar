package com.security.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.template.model.Course;
import com.security.template.model.SubModule;
import com.security.template.repo.SubModuleRepo;

import java.util.List;
import java.util.Optional;

@Service
public class SubModuleService {

    @Autowired
    private SubModuleRepo subModuleRepository;

    @Autowired
    private CourseService cs;

    public List<SubModule> getAllSubModules() {
        return subModuleRepository.findAll();
    }

    public Optional<SubModule> getSubModuleById(Long id) {
        return subModuleRepository.findById(id);
    }

    public SubModule createSubModule(SubModule subModule, Long courseID) {
        Course obj = cs.getCourseById(courseID);
        subModule.setCourse(obj);
        return subModuleRepository.save(subModule);
    }

    public Optional<SubModule> updateSubModule(Long id, SubModule subModule) {
        if (subModuleRepository.existsById(id)) {
            subModule.setId(id);
            return Optional.of(subModuleRepository.save(subModule));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteSubModule(Long id) {
        if (subModuleRepository.existsById(id)) {
            subModuleRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
