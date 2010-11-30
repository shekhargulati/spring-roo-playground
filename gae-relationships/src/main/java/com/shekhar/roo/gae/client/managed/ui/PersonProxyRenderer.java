package com.shekhar.roo.gae.client.managed.ui;

import com.google.gwt.requestfactory.ui.client.ProxyRenderer;

import com.shekhar.roo.gae.client.managed.request.PersonProxy;
import java.util.Set;
import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.ui.AddressSetEditor;

/**
 * Renders {@link PersonProxy for display to the user. Requires the
 *  properties to have been fetched.
 */
public class PersonProxyRenderer extends ProxyRenderer<PersonProxy> {
  private static PersonProxyRenderer INSTANCE;

  public static PersonProxyRenderer instance() {
    if (INSTANCE == null) {
      INSTANCE = new PersonProxyRenderer();
    }

    return INSTANCE;
  }

  protected PersonProxyRenderer() {
    super(new String[] {"id"});
  }

  public String render(PersonProxy object) {
    if (object == null) {
      return "";
    }
    return object.getId() + " (" + object.getId() + ")";
  }
}
