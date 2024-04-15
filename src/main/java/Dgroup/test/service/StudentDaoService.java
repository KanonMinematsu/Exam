package Dgroup.test.service;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Dgroup.test.form.GetForm;
import Dgroup.test.model.Student;
import Dgroup.test.repository.StudentDao;
 
@Service
@Transactional
public class StudentDaoService {
 
    private final StudentDao dao;
 
    @Autowired
    public StudentDaoService(StudentDao dao) {
        this.dao = dao;
    }
 
    public List<Student> findList(GetForm form) {
        return dao.findList(form);
 }
}