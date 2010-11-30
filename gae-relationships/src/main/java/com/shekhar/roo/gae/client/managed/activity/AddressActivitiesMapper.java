package com.shekhar.roo.gae.client.managed.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.RequestContext;

import com.shekhar.roo.gae.client.scaffold.place.ProxyPlace;
import com.shekhar.roo.gae.client.scaffold.place.CreateAndEditProxy;
import com.shekhar.roo.gae.client.scaffold.place.FindAndEditProxy;
import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.request.PersonProxy;
import com.shekhar.roo.gae.client.managed.request.ApplicationRequestFactory;
import com.shekhar.roo.gae.client.scaffold.ScaffoldApp;
import com.shekhar.roo.gae.client.managed.activity.AddressDetailsActivity;
import com.shekhar.roo.gae.client.managed.activity.AddressEditActivityWrapper;
import com.shekhar.roo.gae.client.managed.ui.AddressListView;
import com.shekhar.roo.gae.client.managed.ui.AddressDetailsView;
import com.shekhar.roo.gae.client.managed.ui.AddressMobileDetailsView;
import com.shekhar.roo.gae.client.managed.ui.AddressEditView;
import com.shekhar.roo.gae.client.managed.ui.AddressMobileEditView;
import com.shekhar.roo.gae.client.managed.request.AddressRequest;

/**
 * Maps {@link ProxyPlace} instances to the {@link Activity} to run.
 */
public class AddressActivitiesMapper {
  private final ApplicationRequestFactory requests;
  private final PlaceController placeController;

  public AddressActivitiesMapper(ApplicationRequestFactory requests, PlaceController placeController) {
    this.requests = requests;
    this.placeController = placeController;
  }

  public Activity getActivity(ProxyPlace place) {
    switch (place.getOperation()) {
      case DETAILS:
        return new AddressDetailsActivity((EntityProxyId<AddressProxy>)place.getProxyId(), requests, 
          placeController, ScaffoldApp.isMobile() ? AddressMobileDetailsView.instance() : AddressDetailsView.instance());

      case EDIT:
        return makeEditActivity(place);

      case CREATE:
        return makeCreateActivity();
    }

    throw new IllegalArgumentException("Unknown operation "
        + place.getOperation());
  }

  @SuppressWarnings("unchecked")
  private EntityProxyId<AddressProxy> coerceId(ProxyPlace place) {
    return (EntityProxyId<AddressProxy>) place.getProxyId();
  }

  private Activity makeCreateActivity() {
    AddressEditView.instance().setCreating(true);
    final AddressRequest request = requests.addressRequest();
    Activity activity = new CreateAndEditProxy<AddressProxy>(
        AddressProxy.class, request,
        ScaffoldApp.isMobile() ? AddressMobileEditView.instance() : AddressEditView.instance(),
        placeController) {
      
      @Override
      protected RequestContext createSaveRequest(AddressProxy proxy) {
        request.persist().using(proxy);
        return request;
      }
    };

    return new AddressEditActivityWrapper(requests,
        ScaffoldApp.isMobile() ? AddressMobileEditView.instance() : AddressEditView.instance(),
        activity, null);
  }

  private Activity makeEditActivity(ProxyPlace place) {
    AddressEditView.instance().setCreating(false);
    EntityProxyId<AddressProxy> proxyId = coerceId(place);
    Activity activity = new FindAndEditProxy<AddressProxy>(proxyId,
        requests,
        ScaffoldApp.isMobile() ? AddressMobileEditView.instance() : AddressEditView.instance(),
        placeController) {
      
      @Override
      protected RequestContext createSaveRequest(AddressProxy proxy) {
        AddressRequest request = requests.addressRequest();
        request.persist().using(proxy);
        return request;
      }
    };

    return new AddressEditActivityWrapper(requests,
        ScaffoldApp.isMobile() ? AddressMobileEditView.instance() : AddressEditView.instance(),
        activity, proxyId);
  }
}
