package com.shekhar.roo.gae.client.managed.ui;

import com.google.gwt.requestfactory.ui.client.ProxyRenderer;

import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.request.PersonProxy;

/**
 * Renders {@link AddressProxy for display to the user. Requires the
 *  properties to have been fetched.
 */
public class AddressProxyRenderer extends ProxyRenderer<AddressProxy> {
  private static AddressProxyRenderer INSTANCE;

  public static AddressProxyRenderer instance() {
    if (INSTANCE == null) {
      INSTANCE = new AddressProxyRenderer();
    }

    return INSTANCE;
  }

  protected AddressProxyRenderer() {
    super(new String[] {"id"});
  }

  public String render(AddressProxy object) {
    if (object == null) {
      return "";
    }
    return object.getId() + " (" + object.getId() + ")";
  }
}
