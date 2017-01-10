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
import org.trinity.repository.entity.AbstractAuditableEntity;

/**
 * The persistent class for the user_church_application database table.
 *
 */
@Entity
@Table(name = "user_church_application")
@NamedQuery(name = "UserChurchApplication.findAll", query = "SELECT u FROM UserChurchApplication u")
public class UserChurchApplication extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private RecordStatus status;

    @Column(name = "time_zone")
    private String timeZone;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    // bi-directional many-to-one association to Church
    @ManyToOne
    private Church church;

    // bi-directional many-to-one association to User
    @ManyToOne
    private User user;

    public UserChurchApplication() {
    }

    public Church getChurch() {
        return this.church;
    }

    public String getContent() {
        return content;
    }

    public Long getId() {
        return this.id;
    }

    public RecordStatus getStatus() {
        return this.status;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public User getUser() {
        return this.user;
    }

    public void setChurch(final Church church) {
        this.church = church;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setStatus(final RecordStatus status) {
        this.status = status;
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setTimeZone(final String timeZone) {
        this.timeZone = timeZone;
    }

    public void setUser(final User user) {
        this.user = user;
    }

}
