package cl.teamweichafe.dto;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

/**
 * 
 * @author italohonorato
 *
 */
public class LevelDto extends RepresentationModel<LevelDto> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer levelId;
	private String levelName;
	private String levelDesc;

	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
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
