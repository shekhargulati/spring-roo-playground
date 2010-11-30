package com.shekhar.roo.gae.client.managed.request;

import com.google.gwt.requestfactory.shared.RequestContext;
import org.springframework.roo.addon.gwt.RooGwtMirroredFrom;
import com.shekhar.roo.gae.server.domain.Person;
import com.google.gwt.requestfactory.shared.Service;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.InstanceRequest;

@RooGwtMirroredFrom(Person.class)
@Service(Person.class)
public interface PersonRequest extends RequestContext {

    abstract Request<java.lang.Long> countPeople();

    abstract Request<java.util.List<com.shekhar.roo.gae.client.managed.request.PersonProxy>> findAllPeople();

    abstract Request<java.util.List<com.shekhar.roo.gae.client.managed.request.PersonProxy>> findPersonEntries(int firstResult, int maxResults);

    abstract Request<com.shekhar.roo.gae.client.managed.request.PersonProxy> findPerson(String id);

    abstract InstanceRequest<com.shekhar.roo.gae.client.managed.request.PersonProxy, java.lang.Void> remove();

    abstract InstanceRequest<com.shekhar.roo.gae.client.managed.request.PersonProxy, java.lang.Void> persist();
}
