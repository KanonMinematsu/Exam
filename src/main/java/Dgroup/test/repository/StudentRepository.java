package Dgroup.test.repository;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Dgroup.test.model.Student;

 
//@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

	List<Student> findByEntYearAndClassNum(Integer entYear,String classNum);

	//List<Student> findByIsAttendAndClassNumAndEntYear(Boolean IS_ATTEND);

	//List<Student> findByIS_ATTEND(Boolean iS_ATTEND);

  
}