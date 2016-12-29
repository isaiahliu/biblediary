//Cleaned
package org.trinity.biblediary.repository.business.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.trinity.biblediary.common.message.lookup.RecordStatus;
import org.trinity.biblediary.common.message.lookup.SystemAttributeKey;
import org.trinity.repository.entity.AbstractAuditableEntity;

/**
 * The persistent class for the system_attribute database table.
 *
 */
@Entity
@Table(name = "system_attribute")
@NamedQuery(name = "SystemAttribute.findAll", query = "SELECT s FROM SystemAttribute s")
public class SystemAttribute extends AbstractAuditableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private SystemAttributeKey id;

	private RecordStatus status;

	private String value;

	public SystemAttribute() {
	}

	public SystemAttributeKey getId() {
		return this.id;
	}

	public RecordStatus getStatus() {
		return this.status;
	}

	public String getValue() {
		return this.value;
	}

	public void setId(final SystemAttributeKey id) {
		this.id = id;
	}

	public void setStatus(final RecordStatus status) {
		this.status = status;
	}

	public void setValue(final String value) {
		this.value = value;
	}

}
