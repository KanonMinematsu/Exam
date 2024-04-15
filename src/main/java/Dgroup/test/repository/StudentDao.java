package Dgroup.test.repository;

import java.util.List;

import Dgroup.test.form.GetForm;
import Dgroup.test.model.Student;

public interface StudentDao {

	  public List<Student> findList(GetForm form);
	  ;
}
