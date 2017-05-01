package com.fii.pcd.service;

import com.fii.pcd.model.Subject;
import com.fii.pcd.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject getSubjectForSubjectId(int subjectId) {
        return subjectRepository.findOne(subjectId);
    }
}
