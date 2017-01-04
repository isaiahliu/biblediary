//Cleaned
package org.trinity.biblediary.repository.business.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import org.trinity.biblediary.common.message.lookup.FlagStatus;
import org.trinity.biblediary.common.message.lookup.UserStatus;
import org.trinity.repository.entity.AbstractAuditableEntity;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User extends AbstractAuditableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "User_PK_IdGenerator")
	@TableGenerator(name = "User_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "User_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
	private Long id;

	private FlagStatus admin;

	private String cellphone;

	@Column(name = "nick_name")
	private String nickName;

	private String session;

	private UserStatus status;

	private String wechat;

	// bi-directional many-to-one association to SignOff
	@OneToMany(mappedBy = "user")
	private List<SignOff> signOffs;

	// bi-directional many-to-one association to Church
	@ManyToOne
	private Church church;

	// bi-directional many-to-many association to Plan
	@ManyToMany
	@JoinTable(name = "user_plan", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "plan_id") })
	private List<Plan> plans;

	public User() {
	}

	public SignOff addSignOff(final SignOff signOff) {
		getSignOffs().add(signOff);
		signOff.setUser(this);

		return signOff;
	}

	public FlagStatus getAdmin() {
		return this.admin;
	}

	public String getCellphone() {
		return this.cellphone;
	}

	public Church getChurch() {
		return this.church;
	}

	public Long getId() {
		return this.id;
	}

	public String getNickName() {
		return this.nickName;
	}

	public List<Plan> getPlans() {
		return this.plans;
	}

	public String getSession() {
		return this.session;
	}

	public List<SignOff> getSignOffs() {
		return this.signOffs;
	}

	public UserStatus getStatus() {
		return this.status;
	}

	public String getWechat() {
		return this.wechat;
	}

	public SignOff removeSignOff(final SignOff signOff) {
		getSignOffs().remove(signOff);
		signOff.setUser(null);

		return signOff;
	}

	public void setAdmin(final FlagStatus admin) {
		this.admin = admin;
	}

	public void setCellphone(final String cellphone) {
		this.cellphone = cellphone;
	}

	public void setChurch(final Church church) {
		this.church = church;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setNickName(final String nickName) {
		this.nickName = nickName;
	}

	public void setPlans(final List<Plan> plans) {
		this.plans = plans;
	}

	public void setSession(final String session) {
		this.session = session;
	}

	public void setSignOffs(final List<SignOff> signOffs) {
		this.signOffs = signOffs;
	}

	public void setStatus(final UserStatus status) {
		this.status = status;
	}

	public void setWechat(final String wechat) {
		this.wechat = wechat;
	}

}
