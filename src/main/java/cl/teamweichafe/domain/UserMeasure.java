package cl.teamweichafe.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "users_measures")
public class UserMeasure implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "measure_id")
	private Measure measures;
	
	private float value;
	
	@Column(name = "measure_date")
	private LocalDate measureDate;
	
	@PrePersist
	public void prePersist() {
		this.measureDate = LocalDate.now();
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

	public Integer getId() {
		return id;
	}

	public LocalDate getMeasureDate() {
		return measureDate;
	}

}
