//Cleaned
package org.trinity.biblediary.repository.business.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.trinity.biblediary.common.message.lookup.RecordStatus;
import org.trinity.biblediary.common.message.lookup.TimeZone;
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
    @Column(name = "for_date")
    private Date forDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "local_timestamp")
    private Date localTimestamp;

    @Column(name = "local_time_zone")
    private TimeZone localTimeZone;

    private RecordStatus status;

    // bi-directional many-to-one association to User
    @ManyToOne
    private User user;

    public SignOff() {
    }

    public int getExceedDays() {
        return this.exceedDays;
    }

    public Date getForDate() {
        return this.forDate;
    }

    public Long getId() {
        return this.id;
    }

    public Date getLocalTimestamp() {
        return localTimestamp;
    }

    public int getOverrideExceedDays() {
        return this.overrideExceedDays;
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

    public void setForDate(final Date forDate) {
        this.forDate = forDate;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setLocalTimestamp(final Date localTimestamp) {
        this.localTimestamp = localTimestamp;
    }

    public void setOverrideExceedDays(final int overrideExceedDays) {
        this.overrideExceedDays = overrideExceedDays;
    }

    public void setStatus(final RecordStatus status) {
        this.status = status;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public TimeZone getLocalTimeZone() {
        return localTimeZone;
    }

    public void setLocalTimeZone(TimeZone localTimeZone) {
        this.localTimeZone = localTimeZone;
    }

}
