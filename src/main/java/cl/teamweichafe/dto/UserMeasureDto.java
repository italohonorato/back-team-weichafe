package cl.teamweichafe.dto;

import java.io.Serializable;
import java.time.LocalDate;

import cl.teamweichafe.domain.Measure;
import cl.teamweichafe.domain.User;

/**
 * 
 * @author italohonorato
 *
 */
public class UserMeasureDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private User user;
	private Measure measures;
	private float value;
	private LocalDate measureDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Measure getMeasures() {
		return measures;
	}
	public void setMeasures(Measure measures) {
		this.measures = measures;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public LocalDate getMeasureDate() {
		return measureDate;
	}
	public void setMeasureDate(LocalDate measureDate) {
		this.measureDate = measureDate;
	}

}
