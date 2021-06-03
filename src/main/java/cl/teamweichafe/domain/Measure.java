package cl.teamweichafe.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "measures")
public class Measure implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "measure_id")
	private Integer measeurId;
	
	private String unit;
	
	@Column(name = "measure_desc")
	private String measureDesc;
	
	@JsonBackReference
	@OneToMany(mappedBy = "measures", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<UserMeasure> userMeasures;

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMeasureDesc() {
		return measureDesc;
	}

	public void setMeasureDesc(String measureDesc) {
		this.measureDesc = measureDesc;
	}

	public Set<UserMeasure> getUserMeasures() {
		return userMeasures;
	}

	public void setUserMeasures(Set<UserMeasure> userMeasures) {
		this.userMeasures = userMeasures;
	}

	public Integer getMeaseurId() {
		return measeurId;
	}
}
