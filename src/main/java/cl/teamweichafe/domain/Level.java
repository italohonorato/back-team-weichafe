package cl.teamweichafe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "levels")
public class Level extends RepresentationModel<Level> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "level_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer levelId;
	
	@NotEmpty
	@Column(name = "level_name", unique = true)
	private String levelName;
	
	@Column(name = "level_desc")
	private String levelDesc;

	public Integer getLevelId() {
		return levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getLevelDesc() {
		return levelDesc;
	}

	public void setLevelDesc(String levelDesc) {
		this.levelDesc = levelDesc;
	}

}
