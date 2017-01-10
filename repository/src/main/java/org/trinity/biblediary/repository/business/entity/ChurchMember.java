//Cleaned
package org.trinity.biblediary.repository.business.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.trinity.biblediary.common.message.lookup.FlagStatus;
import org.trinity.biblediary.common.message.lookup.RecordStatus;
import org.trinity.repository.entity.AbstractAuditableEntity;

/**
 * The persistent class for the church_member database table.
 *
 */
@Entity
@Table(name = "church_member")
@NamedQuery(name = "ChurchMember.findAll", query = "SELECT c FROM ChurchMember c")
public class ChurchMember extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private FlagStatus admin;

    private RecordStatus status;

    // bi-directional many-to-one association to Church
    @ManyToOne
    private Church church;

    // bi-directional many-to-one association to User
    @ManyToOne
    private User user;

    public ChurchMember() {
    }

    public FlagStatus getAdmin() {
        return this.admin;
    }

    public Church getChurch() {
        return this.church;
    }

    public Long getId() {
        return this.id;
    }

    public RecordStatus getStatus() {
        return this.status;
    }

    public User getUser() {
        return this.user;
    }

    public void setAdmin(final FlagStatus admin) {
        this.admin = admin;
    }

    public void setChurch(final Church church) {
        this.church = church;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setStatus(final RecordStatus status) {
        this.status = status;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}
