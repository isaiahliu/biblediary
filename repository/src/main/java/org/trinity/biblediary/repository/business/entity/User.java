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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import org.trinity.biblediary.common.message.lookup.TimeZone;
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

    private String cellphone;

    @Column(name = "nick_name")
    private String nickName;

    private String session;

    @Column(name = "time_zone")
    private TimeZone timeZone;

    private UserStatus status;

    private String wechat;

    // bi-directional many-to-one association to SignOff
    @OneToMany(mappedBy = "user")
    private List<SignOff> signOffs;

    // bi-directional many-to-many association to Plan
    @ManyToMany
    @JoinTable(name = "user_plan", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "plan_id") })
    private List<Plan> plans;

    // bi-directional many-to-one association to UserChurchApplication
    @OneToMany(mappedBy = "user")
    private List<UserChurchApplication> applications;

    // bi-directional many-to-one association to ChurchMember
    @OneToMany(mappedBy = "user")
    private List<ChurchMember> churchMembers;

    public User() {
    }

    public UserChurchApplication addApplication(final UserChurchApplication application) {
        getApplications().add(application);
        application.setUser(this);

        return application;
    }

    public ChurchMember addChurchMember(final ChurchMember churchMember) {
        getChurchMembers().add(churchMember);
        churchMember.setUser(this);

        return churchMember;
    }

    public SignOff addSignOff(final SignOff signOff) {
        getSignOffs().add(signOff);
        signOff.setUser(this);

        return signOff;
    }

    public List<UserChurchApplication> getApplications() {
        return this.applications;
    }

    public String getCellphone() {
        return this.cellphone;
    }

    public List<ChurchMember> getChurchMembers() {
        return this.churchMembers;
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

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public String getWechat() {
        return this.wechat;
    }

    public UserChurchApplication removeApplication(final UserChurchApplication application) {
        getApplications().remove(application);
        application.setUser(null);

        return application;
    }

    public ChurchMember removeChurchMember(final ChurchMember churchMember) {
        getChurchMembers().remove(churchMember);
        churchMember.setUser(null);

        return churchMember;
    }

    public SignOff removeSignOff(final SignOff signOff) {
        getSignOffs().remove(signOff);
        signOff.setUser(null);

        return signOff;
    }

    public void setApplications(final List<UserChurchApplication> applications) {
        this.applications = applications;
    }

    public void setCellphone(final String cellphone) {
        this.cellphone = cellphone;
    }

    public void setChurchMembers(final List<ChurchMember> churchMembers) {
        this.churchMembers = churchMembers;
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

    public void setTimeZone(final TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public void setWechat(final String wechat) {
        this.wechat = wechat;
    }
}
