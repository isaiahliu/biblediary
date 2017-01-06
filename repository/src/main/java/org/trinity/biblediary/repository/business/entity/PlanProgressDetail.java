//Cleaned
package org.trinity.biblediary.repository.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.trinity.biblediary.common.message.lookup.BibleVolume;
import org.trinity.biblediary.common.message.lookup.RecordStatus;
import org.trinity.repository.entity.AbstractAuditableEntity;

/**
 * The persistent class for the plan_progress_detail database table.
 *
 */
@Entity
@Table(name = "plan_progress_detail")
@NamedQuery(name = "PlanProgressDetail.findAll", query = "SELECT p FROM PlanProgressDetail p")
public class PlanProgressDetail extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_chapter")
    private int fromChapter;

    @Column(name = "from_verse")
    private int fromVerse;

    @Column(name = "from_volume")
    private BibleVolume fromVolume;

    private int part;

    private RecordStatus status;

    @Column(name = "to_chapter")
    private int toChapter;

    @Column(name = "to_verse")
    private int toVerse;

    @Column(name = "to_volume")
    private BibleVolume toVolume;

    // bi-directional many-to-one association to PlanProgress
    @ManyToOne
    @JoinColumn(name = "plan_progress_id")
    private PlanProgress progress;

    public PlanProgressDetail() {
    }

    public int getFromChapter() {
        return this.fromChapter;
    }

    public int getFromVerse() {
        return fromVerse;
    }

    public BibleVolume getFromVolume() {
        return this.fromVolume;
    }

    public Long getId() {
        return this.id;
    }

    public int getPart() {
        return this.part;
    }

    public PlanProgress getProgress() {
        return this.progress;
    }

    public RecordStatus getStatus() {
        return this.status;
    }

    public int getToChapter() {
        return this.toChapter;
    }

    public int getToVerse() {
        return toVerse;
    }

    public BibleVolume getToVolume() {
        return this.toVolume;
    }

    public void setFromChapter(final int fromChapter) {
        this.fromChapter = fromChapter;
    }

    public void setFromVerse(final int fromVerse) {
        this.fromVerse = fromVerse;
    }

    public void setFromVolume(final BibleVolume fromVolume) {
        this.fromVolume = fromVolume;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setPart(final int part) {
        this.part = part;
    }

    public void setProgress(final PlanProgress progress) {
        this.progress = progress;
    }

    public void setStatus(final RecordStatus status) {
        this.status = status;
    }

    public void setToChapter(final int toChapter) {
        this.toChapter = toChapter;
    }

    public void setToVerse(final int toVerse) {
        this.toVerse = toVerse;
    }

    public void setToVolume(final BibleVolume toVolume) {
        this.toVolume = toVolume;
    }

}
