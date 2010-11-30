package com.shekhar.roo.gae.client.managed.request;

import com.google.gwt.requestfactory.shared.EntityProxy;
import org.springframework.roo.addon.gwt.RooGwtMirroredFrom;
import com.shekhar.roo.gae.server.domain.Person;
import com.google.gwt.requestfactory.shared.ProxyFor;
import java.util.Set;
import com.shekhar.roo.gae.client.managed.request.AddressProxy;

@RooGwtMirroredFrom(Person.class)
@ProxyFor(Person.class)
public interface PersonProxy extends EntityProxy {

    abstract Set<com.shekhar.roo.gae.client.managed.request.AddressProxy> getAddresses();

    abstract String getFirstName();

    abstract String getId();

    abstract String getLastName();

    abstract Integer getVersion();

    abstract void setAddresses(Set<AddressProxy> addresses);

    abstract void setFirstName(String firstName);

    abstract void setId(String id);

    abstract void setLastName(String lastName);

    abstract void setVersion(Integer version);
}
