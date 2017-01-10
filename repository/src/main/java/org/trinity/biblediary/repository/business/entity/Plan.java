//Cleaned
package org.trinity.biblediary.repository.business.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.trinity.biblediary.common.message.lookup.PlanName;
import org.trinity.biblediary.common.message.lookup.RecordStatus;
import org.trinity.repository.entity.AbstractAuditableEntity;

/**
 * The persistent class for the plan database table.
 *
 */
@Entity
@NamedQuery(name = "Plan.findAll", query = "SELECT p FROM Plan p")
public class Plan extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private PlanName name;

    private RecordStatus status;

    // bi-directional many-to-one association to PlanProgress
    @OneToMany(mappedBy = "plan")
    private List<PlanProgress> progresses;

    // bi-directional many-to-many association to User
    @ManyToMany(mappedBy = "plans")
    private List<User> users;

    public Plan() {
    }

    public PlanProgress addProgress(final PlanProgress progress) {
        getProgresses().add(progress);
        progress.setPlan(this);

        return progress;
    }

    public Long getId() {
        return this.id;
    }

    public PlanName getName() {
        return this.name;
    }

    public List<PlanProgress> getProgresses() {
        return this.progresses;
    }

    public RecordStatus getStatus() {
        return this.status;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public PlanProgress removeProgress(final PlanProgress progress) {
        getProgresses().remove(progress);
        progress.setPlan(null);

        return progress;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final PlanName name) {
        this.name = name;
    }

    public void setProgresses(final List<PlanProgress> progresses) {
        this.progresses = progresses;
    }

    public void setStatus(final RecordStatus status) {
        this.status = status;
    }

    public void setUsers(final List<User> users) {
        this.users = users;
    }

}
