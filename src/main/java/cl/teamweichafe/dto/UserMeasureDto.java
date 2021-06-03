package cl.teamweichafe.dto;

import java.io.Serializable;
import java.time.LocalDate;

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
	private UserDto user;
	private MeasureDto measure;
	private float value;
	private LocalDate measureDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public MeasureDto getMeasure() {
		return measure;
	}

	public void setMeasure(MeasureDto measure) {
		this.measure = measure;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((measure == null) ? 0 : measure.hashCode());
		result = prime * result + ((measureDate == null) ? 0 : measureDate.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + Float.floatToIntBits(value);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserMeasureDto other = (UserMeasureDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (measure == null) {
			if (other.measure != null)
				return false;
		} else if (!measure.equals(other.measure))
			return false;
		if (measureDate == null) {
			if (other.measureDate != null)
				return false;
		} else if (!measureDate.equals(other.measureDate))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value))
			return false;
		return true;
	}

}
