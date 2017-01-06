//Cleaned
package org.trinity.biblediary.repository.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.trinity.biblediary.common.message.lookup.RecordStatus;
import org.trinity.repository.entity.AbstractAuditableEntity;

/**
 * The persistent class for the plan_progress database table.
 *
 */
@Entity
@Table(name = "plan_progress")
@NamedQuery(name = "PlanProgress.findAll", query = "SELECT p FROM PlanProgress p")
public class PlanProgress extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private RecordStatus status;

    // bi-directional many-to-one association to Plan
    @ManyToOne
    private Plan plan;

    // bi-directional many-to-one association to PlanProgressDetail
    @OneToMany(mappedBy = "progress")
    private List<PlanProgressDetail> details;

    public PlanProgress() {
    }

    public PlanProgressDetail addDetail(final PlanProgressDetail detail) {
        getDetails().add(detail);
        detail.setProgress(this);

        return detail;
    }

    public Date getDate() {
        return this.date;
    }

    public List<PlanProgressDetail> getDetails() {
        return this.details;
    }

    public Long getId() {
        return this.id;
    }

    public Plan getPlan() {
        return this.plan;
    }

    public RecordStatus getStatus() {
        return this.status;
    }

    public PlanProgressDetail removeDetail(final PlanProgressDetail detail) {
        getDetails().remove(detail);
        detail.setProgress(null);

        return detail;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public void setDetails(final List<PlanProgressDetail> details) {
        this.details = details;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setPlan(final Plan plan) {
        this.plan = plan;
    }

    public void setStatus(final RecordStatus status) {
        this.status = status;
    }

}
