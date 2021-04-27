package cl.teamweichafe.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class AssistancePk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "assistance_id")
	private Integer assistanceId;
	
	@OneToOne
	@JoinColumn(name = "level_id")
	private Level level;

	@Column(name = "assistance_date")
	private LocalDate assistanceDate;
	
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Integer getAssistanceId() {
		return assistanceId;
	}

	public void setAssistanceId(Integer assistanceId) {
		this.assistanceId = assistanceId;
	}

	public LocalDate getAssistanceDate() {
		return assistanceDate;
	}

	public void setAssistanceDate(LocalDate assistanceDate) {
		this.assistanceDate = assistanceDate;
	}
	
}
