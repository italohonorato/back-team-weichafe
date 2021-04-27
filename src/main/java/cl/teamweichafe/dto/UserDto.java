package cl.teamweichafe.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author italohonorato
 *
 */
public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;
	private String userName;
	private boolean enabled;
	private LocalDateTime createdOn;
	private String password;
	private List<RoleDto> roles = new ArrayList<RoleDto>();
	private AdditionalInfoDto additionalInfo;
	private List<UserMeasureDto> userMeassures = new ArrayList<UserMeasureDto>();

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}

	public AdditionalInfoDto getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(AdditionalInfoDto additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public List<UserMeasureDto> getUserMeassures() {
		return userMeassures;
	}

	public void setUserMeassures(List<UserMeasureDto> userMeassures) {
		this.userMeassures = userMeassures;
	}

}
