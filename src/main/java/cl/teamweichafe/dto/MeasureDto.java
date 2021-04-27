package cl.teamweichafe.dto;

import java.io.Serializable;
/**
 * 
 * @author italohonorato
 *
 */
public class MeasureDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer measeurId;
	private String unit;
	private String measureDesc;

	public Integer getMeaseurId() {
		return measeurId;
	}

	public void setMeaseurId(Integer measeurId) {
		this.measeurId = measeurId;
	}

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

}
