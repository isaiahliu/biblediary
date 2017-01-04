//Cleaned
package org.trinity.biblediary.repository.business.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.trinity.biblediary.common.message.lookup.RecordStatus;
import org.trinity.repository.entity.AbstractAuditableEntity;

/**
 * The persistent class for the sign_off database table.
 * 
 */
@Entity
@Table(name = "sign_off")
@NamedQuery(name = "SignOff.findAll", query = "SELECT s FROM SignOff s")
public class SignOff extends AbstractAuditableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "exceed_days")
	private int exceedDays;

	@Column(name = "override_exceed_days")
	private int overrideExceedDays;

	@Temporal(TemporalType.DATE)
	@Column(name = "sign_off_date")
	private Date signOffDate;

	private RecordStatus status;

	// bi-directional many-to-one association to PlanProgress
	@ManyToOne
	@JoinColumn(name = "plan_progress_id")
	private PlanProgress progress;

	// bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public SignOff() {
	}

	public int getExceedDays() {
		return this.exceedDays;
	}

	public Long getId() {
		return this.id;
	}

	public int getOverrideExceedDays() {
		return this.overrideExceedDays;
	}

	public PlanProgress getProgress() {
		return this.progress;
	}

	public Date getSignOffDate() {
		return this.signOffDate;
	}

	public RecordStatus getStatus() {
		return this.status;
	}

	public User getUser() {
		return this.user;
	}

	public void setExceedDays(final int exceedDays) {
		this.exceedDays = exceedDays;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setOverrideExceedDays(final int overrideExceedDays) {
		this.overrideExceedDays = overrideExceedDays;
	}

	public void setProgress(final PlanProgress progress) {
		this.progress = progress;
	}

	public void setSignOffDate(final Date signOffDate) {
		this.signOffDate = signOffDate;
	}

	public void setStatus(final RecordStatus status) {
		this.status = status;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
