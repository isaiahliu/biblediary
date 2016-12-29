//Cleaned
package org.trinity.biblediary.repository.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "from_chapter")
    private int fromChapter;

    @Column(name = "from_volume")
    private String fromVolume;

    private RecordStatus status;

    @Column(name = "to_chapter")
    private int toChapter;

    @Column(name = "to_volume")
    private String toVolume;

    // bi-directional many-to-one association to User
    @OneToMany(mappedBy = "plan")
    private List<User> users;

    public Plan() {
    }

    public User addUser(final User user) {
        getUsers().add(user);
        user.setPlan(this);

        return user;
    }

    public Date getDate() {
        return this.date;
    }

    public int getFromChapter() {
        return this.fromChapter;
    }

    public String getFromVolume() {
        return this.fromVolume;
    }

    public Long getId() {
        return this.id;
    }

    public RecordStatus getStatus() {
        return this.status;
    }

    public int getToChapter() {
        return this.toChapter;
    }

    public String getToVolume() {
        return this.toVolume;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public User removeUser(final User user) {
        getUsers().remove(user);
        user.setPlan(null);

        return user;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public void setFromChapter(final int fromChapter) {
        this.fromChapter = fromChapter;
    }

    public void setFromVolume(final String fromVolume) {
        this.fromVolume = fromVolume;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setStatus(final RecordStatus status) {
        this.status = status;
    }

    public void setToChapter(final int toChapter) {
        this.toChapter = toChapter;
    }

    public void setToVolume(final String toVolume) {
        this.toVolume = toVolume;
    }

    public void setUsers(final List<User> users) {
        this.users = users;
    }

}
