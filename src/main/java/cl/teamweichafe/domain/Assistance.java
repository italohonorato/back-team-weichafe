package cl.teamweichafe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "assistances")
public class Assistance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private AssistancePk id;
	
	@Column(name = "assistance_list")
	private String assistanceList;

	public AssistancePk getId() {
		return id;
	}

	public void setId(AssistancePk id) {
		this.id = id;
	}

	public String getAssistanceList() {
		return assistanceList;
	}

	public void setAssistanceList(String assistanceList) {
		this.assistanceList = assistanceList;
	}

}
