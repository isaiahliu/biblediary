package org.trinity.biblediary.common.message.dto.domain;

import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.LookupDto;

public class UserDto extends AbstractBusinessDto {
    private LookupDto admin;

    private String cellphone;

    private String nickName;

    private String wechat;

    private ChurchDto church;

    private PlanDto plan;

    private String session;

    public LookupDto getAdmin() {
        return admin;
    }

    public String getCellphone() {
        return cellphone;
    }

    public ChurchDto getChurch() {
        return church;
    }

    public String getNickName() {
        return nickName;
    }

    public PlanDto getPlan() {
        return plan;
    }

    public String getSession() {
        return session;
    }

    public String getWechat() {
        return wechat;
    }

    public void setAdmin(final LookupDto admin) {
        this.admin = admin;
    }

    public void setCellphone(final String cellphone) {
        this.cellphone = cellphone;
    }

    public void setChurch(final ChurchDto church) {
        this.church = church;
    }

    public void setNickName(final String nickName) {
        this.nickName = nickName;
    }

    public void setPlan(final PlanDto plan) {
        this.plan = plan;
    }

    public void setSession(final String session) {
        this.session = session;
    }

    public void setWechat(final String wechat) {
        this.wechat = wechat;
    }

}
