package Dgroup.test.repository;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import Dgroup.test.form.GetForm;
import Dgroup.test.model.Student;
 
 
@Repository
public class StudentDaoImpl implements StudentDao {
     
    private final NamedParameterJdbcTemplate jdbcTemplate;
 
    @Autowired
    public StudentDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
 
    @Override
    public List<Student> findList(GetForm form) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT d.id, d.category, d.title, d.content, TO_CHAR(d.date, 'YYYY/MM/DD') AS date, d.update_datetime, c.name "
                + "FROM diary AS d INNER JOIN category_code AS c ON d.category = c.cd "
                + "WHERE c.group_cd = '01'");
        
        // パラメータ設定用Map
        Map<Integer, Integer> param = new HashMap<>();
        // パラメータが存在した場合、where句にセット
        if(form.getENT_YEAR() != null && form.getENT_YEAR() != 0) {
            sqlBuilder.append(" AND c.cd = :cd");
            param.put("cd", form.getENT_YEAR());
        }
        if(form.getCLASS_NUM() != null && form.getCLASS_NUM() != "") {
            sqlBuilder.append(" AND TO_CHAR(d.date, 'YYYY/MM') = :date");
            param.put("date", form.getCLASS_NUM());
        }
        if(form.getIS_ATTEND() != null && form.getIS_ATTEND() !=  false) {
            sqlBuilder.append(" AND TO_CHAR(d.date, 'YYYY/MM') = :date");
            param.put("date", form.getCLASS_NUM());
        }
        String sql = sqlBuilder.toString();
 
        
        //タスク一覧をMapのListで取得
        List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, param);
        //return用の空のListを用意
        List<Student> list = new ArrayList<Student>();
 
        //データをDiaryにまとめる
        for(Map<String, Object> result : resultList) {
        	Student student = new Student();
        	student.setID((Long)result.get("id"));
        	student.setENT_YEAR((Integer)result.get("ent_year"));
        	student.setCLASS_NUM((String)result.get("class_num"));
        	student.setIS_ATTEND((Boolean)result.get("is_attend"));
            list.add(student);
        }
        return list;
    }
}