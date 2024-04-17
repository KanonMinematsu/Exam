

package Dgroup.test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long ID;
	
	@Column(length = 10, nullable = true)
	private String NO;
	
	@Column(length = 10, nullable = true)
	private String NAME;

	@Column(name="ENT_YEAR",length = 10,nullable = true)
	private Integer entYear;

	@Column(name="CLASS_NUM",length = 3, nullable = true)
	private String classNum;
	
	@Column(name="IS_ATTEND",nullable = true)
	private Boolean isAttend;
	
	@Column(length = 3, nullable = true)
	private String SCHOOL_CD;

	

	
	
	
	
	
	
	
	
	
	
	
	
	

}