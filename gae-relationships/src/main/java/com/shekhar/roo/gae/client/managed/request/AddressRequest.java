package com.shekhar.roo.gae.client.managed.request;

import com.google.gwt.requestfactory.shared.RequestContext;
import org.springframework.roo.addon.gwt.RooGwtMirroredFrom;
import com.shekhar.roo.gae.server.domain.Address;
import com.google.gwt.requestfactory.shared.Service;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.InstanceRequest;

@RooGwtMirroredFrom(Address.class)
@Service(Address.class)
public interface AddressRequest extends RequestContext {

    abstract Request<java.lang.Long> countAddresses();

    abstract Request<java.util.List<com.shekhar.roo.gae.client.managed.request.AddressProxy>> findAllAddresses();

    abstract Request<java.util.List<com.shekhar.roo.gae.client.managed.request.AddressProxy>> findAddressEntries(int firstResult, int maxResults);

    abstract Request<com.shekhar.roo.gae.client.managed.request.AddressProxy> findAddress(String id);

    abstract InstanceRequest<com.shekhar.roo.gae.client.managed.request.AddressProxy, java.lang.Void> remove();

    abstract InstanceRequest<com.shekhar.roo.gae.client.managed.request.AddressProxy, java.lang.Void> persist();
}
