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
import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.request.PersonProxy;
import com.shekhar.roo.gae.client.managed.request.ApplicationRequestFactory;
import com.shekhar.roo.gae.client.scaffold.activity.IsScaffoldMobileActivity;


import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/**
 * Wraps AddressProxy edit and create activities to manage extra portions of their
 * views, like value pickers.
 */
public class AddressEditActivityWrapper implements Activity,
    IsScaffoldMobileActivity {

  /**
   * The view used by this activity.
   * 
   * @param <V> the type of the ProxyEditView
   */
  public interface View<V extends ProxyEditView<AddressProxy, V>>
      extends ProxyEditView<AddressProxy, V> {
    
    
    void setPersonPickerValues(Collection<PersonProxy> values);
    
  }

  private final EntityProxyId<AddressProxy> proxyId;
  private final ApplicationRequestFactory requests;
  private final View<?> view;
  private final Activity wrapped;

  public AddressEditActivityWrapper(ApplicationRequestFactory requests,
        View<?> view, Activity activity, EntityProxyId<AddressProxy> proxyId) {
    this.requests = requests;
    this.view = view;
    this.wrapped = activity;
    this.proxyId = proxyId;
  }

  public Place getBackButtonPlace() {
    return (proxyId == null) ? new ProxyListPlace(AddressProxy.class) :
      new ProxyPlace(proxyId, ProxyPlace.Operation.DETAILS);
  }

  public String getBackButtonText() {
    return "Cancel";
  }

  public Place getEditButtonPlace() {
    return null;
  }

  public String getTitleText() {
    return (proxyId == null) ? "New Address" : "Edit Address";
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

    view.setPersonPickerValues(Collections.<PersonProxy> emptyList());
    requests.personRequest().findPersonEntries(0, 50).with(
        com.shekhar.roo.gae.client.managed.ui.PersonProxyRenderer.instance().getPaths()).fire(
            new Receiver<List<PersonProxy>>() {
                public void onSuccess(List<PersonProxy> response) {
                    List<PersonProxy> values = new ArrayList<PersonProxy>();
                    values.add(null);
                    values.addAll(response);
                    view.setPersonPickerValues(values);
            }
    });
    
    wrapped.start(display, eventBus);
  }
}
