package com.shekhar.roo.gae.client.managed.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import com.shekhar.roo.gae.client.scaffold.place.ProxyPlace;
import com.shekhar.roo.gae.client.scaffold.place.ProxyListPlace;
import com.shekhar.roo.gae.client.scaffold.place.ProxyEditView;
import com.shekhar.roo.gae.client.managed.request.PersonProxy;
import java.util.Set;
import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.ui.AddressSetEditor;
import com.shekhar.roo.gae.client.managed.request.ApplicationRequestFactory;
import com.shekhar.roo.gae.client.scaffold.activity.IsScaffoldMobileActivity;


import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/**
 * Wraps PersonProxy edit and create activities to manage extra portions of their
 * views, like value pickers.
 */
public class PersonEditActivityWrapper implements Activity,
    IsScaffoldMobileActivity {

  /**
   * The view used by this activity.
   * 
   * @param <V> the type of the ProxyEditView
   */
  public interface View<V extends ProxyEditView<PersonProxy, V>>
      extends ProxyEditView<PersonProxy, V> {
    
    
    void setAddressesPickerValues(Collection<AddressProxy> values);
    
  }

  private final EntityProxyId<PersonProxy> proxyId;
  private final ApplicationRequestFactory requests;
  private final View<?> view;
  private final Activity wrapped;

  public PersonEditActivityWrapper(ApplicationRequestFactory requests,
        View<?> view, Activity activity, EntityProxyId<PersonProxy> proxyId) {
    this.requests = requests;
    this.view = view;
    this.wrapped = activity;
    this.proxyId = proxyId;
  }

  public Place getBackButtonPlace() {
    return (proxyId == null) ? new ProxyListPlace(PersonProxy.class) :
      new ProxyPlace(proxyId, ProxyPlace.Operation.DETAILS);
  }

  public String getBackButtonText() {
    return "Cancel";
  }

  public Place getEditButtonPlace() {
    return null;
  }

  public String getTitleText() {
    return (proxyId == null) ? "New Person" : "Edit Person";
  }

  public boolean hasEditButton() {
    return false;
  }

  @Override
  public String mayStop() {
    return wrapped.mayStop();
  }

  @Override
  public void onCancel() {
    wrapped.onCancel();
  }

  @Override
  public void onStop() {
    wrapped.onStop();
  }

  @Override
  public void start(AcceptsOneWidget display, EventBus eventBus) {

    view.setAddressesPickerValues(Collections.<AddressProxy> emptyList());
    requests.addressRequest().findAddressEntries(0, 50).with(
        com.shekhar.roo.gae.client.managed.ui.AddressProxyRenderer.instance().getPaths()).fire(
            new Receiver<List<AddressProxy>>() {
                public void onSuccess(List<AddressProxy> response) {
                    List<AddressProxy> values = new ArrayList<AddressProxy>();
                    values.add(null);
                    values.addAll(response);
                    view.setAddressesPickerValues(values);
            }
    });
    
    wrapped.start(display, eventBus);
  }
}
