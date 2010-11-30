package com.shekhar.roo.gae.client.managed.request;

import com.google.gwt.requestfactory.shared.EntityProxy;
import org.springframework.roo.addon.gwt.RooGwtMirroredFrom;
import com.shekhar.roo.gae.server.domain.Address;
import com.google.gwt.requestfactory.shared.ProxyFor;
import com.shekhar.roo.gae.client.managed.request.PersonProxy;

@RooGwtMirroredFrom(Address.class)
@ProxyFor(Address.class)
public interface AddressProxy extends EntityProxy {

    abstract String getCity();

    abstract String getId();

    abstract PersonProxy getPerson();

    abstract String getState();

    abstract String getStreetAddress();

    abstract Integer getVersion();

    abstract String getZip();

    abstract void setCity(String city);

    abstract void setId(String id);

    abstract void setPerson(PersonProxy person);

    abstract void setState(String state);

    abstract void setStreetAddress(String streetAddress);

    abstract void setVersion(Integer version);

    abstract void setZip(String zip);
}
