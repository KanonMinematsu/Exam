package Dgroup.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import Dgroup.test.model.Student;
import Dgroup.test.repository.StudentRepository;
import jakarta.transaction.Transactional;
 

@Service
@Transactional
public class StudentService {
 
	@Autowired
	private StudentRepository repository;
 
	/**
	 * アドレス帳一覧の取得
	 * @return List<>
	 */
	public List<Student> getstudentList() {
	    List<Student> entityList = this.repository.findAll();
	    return entityList;
	}
 
	/**
	 * 詳細データの取得
	 * @param @NonNull Long index
	
	 */
	public Student get(@NonNull Long index) {
		Student students = this.repository.findById(index).orElse(new Student());
		return students;
	}
 
	
	public void save(@NonNull Student students) {
		//初期値の設定
        students.setIS_ATTEND(true);
		this.repository.save(students);
	}
 
	public void delete(@NonNull Long student) {
		this.repository.deleteById(student);
	}
	
	 // 受け取ったidからデータを取得して、Formを返却する
    public Student getOneBook(Long id) {
        // idを指定して本の情報を取得する
        Student student = repository.findById(id).orElseThrow();
        // 画面返却用のFormに値を設定する
        return student;
    }
    // 本を更新する
    public void update(Student editstudent) {
		
        // データベースに登録する値を保持するインスタンスの作成
    	
    	//System.out.println(editstudent);
        //Student student = new Student();
		
        //初期値の設定
        //editstudent.setIS_ATTEND(true);
       
		
        // データベースを更新する
        repository.save(editstudent);
    }
	
}