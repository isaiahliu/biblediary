//Cleaned
package org.trinity.biblediary.repository.business.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import org.trinity.biblediary.common.message.lookup.JoinMethod;
import org.trinity.biblediary.common.message.lookup.RecordStatus;
import org.trinity.repository.entity.AbstractAuditableEntity;

/**
 * The persistent class for the church database table.
 *
 */
@Entity
@NamedQuery(name = "Church.findAll", query = "SELECT c FROM Church c")
public class Church extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    // bi-directional many-to-one association to ChurchMember
    @OneToMany(mappedBy = "church")
    private List<ChurchMember> members;
    @Column(name = "join_method")
    private JoinMethod joinMethod;

    // bi-directional many-to-one association to UserChurchApplication
    @OneToMany(mappedBy = "church")
    private List<UserChurchApplication> applications;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Church_PK_IdGenerator")
    @TableGenerator(name = "Church_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "Church_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
    private Long id;

    private String description;

    private String name;

    private RecordStatus status;

    public Church() {
    }

    public UserChurchApplication addApplication(final UserChurchApplication application) {
        getApplications().add(application);
        application.setChurch(this);

        return application;
    }

    public ChurchMember addMember(final ChurchMember member) {
        getMembers().add(member);
        member.setChurch(this);

        return member;
    }

    public List<UserChurchApplication> getApplications() {
        return this.applications;
    }

    public String getDescription() {
        return this.description;
    }

    public Long getId() {
        return this.id;
    }

    public JoinMethod getJoinMethod() {
        return this.joinMethod;
    }

    public List<ChurchMember> getMembers() {
        return this.members;
    }

    public String getName() {
        return this.name;
    }

    public RecordStatus getStatus() {
        return this.status;
    }

    public UserChurchApplication removeApplication(final UserChurchApplication application) {
        getApplications().remove(application);
        application.setChurch(null);

        return application;
    }

    public ChurchMember removeMember(final ChurchMember member) {
        getMembers().remove(member);
        member.setChurch(null);

        return member;
    }

    public void setApplications(final List<UserChurchApplication> applications) {
        this.applications = applications;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setJoinMethod(final JoinMethod joinMethod) {
        this.joinMethod = joinMethod;
    }

    public void setMembers(final List<ChurchMember> members) {
        this.members = members;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setStatus(final RecordStatus status) {
        this.status = status;
    }
}
