package com.fii.pcd.security;

import com.fii.pcd.model.Professor;
import com.fii.pcd.model.Student;
import com.fii.pcd.repository.ProfessorRepository;
import com.fii.pcd.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public CustomUserDetailsService(ProfessorRepository professorRepository,
                                    StudentRepository studentRepository) {
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Professor professor = professorRepository.findByName(username);
        Student student = studentRepository.findByName(username);
        if (professor != null && student != null) {
            throw new IllegalStateException("There is a professor and a student with same name");
        }
        if (professor != null) {
            return new CustomUserDetails(
                    professor.getName(),
                    professor.getPassword(),
                    professor.getId(),
                    true,
                    true,
                    true,
                    true,
                    getGrantedAuthorities("PROF"));
        }
        return new CustomUserDetails(
                student.getName(),
                student.getPassword(),
                student.getId(),
                true,
                true,
                true,
                true,
                getGrantedAuthorities("STUD"));
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
}
