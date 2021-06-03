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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((measeurId == null) ? 0 : measeurId.hashCode());
		result = prime * result + ((measureDesc == null) ? 0 : measureDesc.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
		MeasureDto other = (MeasureDto) obj;
		if (measeurId == null) {
			if (other.measeurId != null)
				return false;
		} else if (!measeurId.equals(other.measeurId))
			return false;
		if (measureDesc == null) {
			if (other.measureDesc != null)
				return false;
		} else if (!measureDesc.equals(other.measureDesc))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}

}
