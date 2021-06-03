package cl.teamweichafe.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "username", unique = true)
	private String userName;
	
	private boolean enabled;
	
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	
	private String password;
	
	@JsonManagedReference
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"),
			uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
	private Set<Role> roles = new HashSet<Role>();
	
	@JsonBackReference
	@OneToOne(mappedBy = "user")
	private AdditionalInfo additionalInfo;
	
	@JsonBackReference
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<UserMeasure> userMeassures = new HashSet<UserMeasure>();
	
	@PrePersist
	public void prePersist() {
		this.createdOn = LocalDateTime.now();
		this.enabled = true;
	}

	public Integer getUserId() {
		return userId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		this.roles.add(role);
	}
	
	public void addUserMeassure(UserMeasure userMeassure) {
		this.userMeassures.add(userMeassure);
	}

	public AdditionalInfo getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(AdditionalInfo additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public Set<UserMeasure> getUserMeassures() {
		return userMeassures;
	}
}
