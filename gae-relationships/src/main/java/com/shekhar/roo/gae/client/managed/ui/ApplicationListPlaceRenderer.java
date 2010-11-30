package com.shekhar.roo.gae.client.managed.ui;

import com.google.gwt.text.shared.AbstractRenderer;

import com.shekhar.roo.gae.client.scaffold.place.ProxyListPlace;
import com.shekhar.roo.gae.client.managed.request.ApplicationEntityTypesProcessor;
import com.shekhar.roo.gae.client.managed.request.PersonProxy;
import com.shekhar.roo.gae.client.managed.request.AddressProxy;

/**
 * Renders {@link ProxyListPlace}s for display to users.
 */
public class ApplicationListPlaceRenderer extends AbstractRenderer<ProxyListPlace> {

	public String render(ProxyListPlace object) {
		return new ApplicationEntityTypesProcessor<String>() {

			@Override
      public void handlePerson(PersonProxy isNull) {
        setResult("Persons");
      }
			@Override
      public void handleAddress(AddressProxy isNull) {
        setResult("Addresss");
      }
		}.process(object.getProxyClass());
	}
}
