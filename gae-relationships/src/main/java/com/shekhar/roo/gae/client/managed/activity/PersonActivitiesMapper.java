package com.shekhar.roo.gae.client.managed.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.RequestContext;

import com.shekhar.roo.gae.client.scaffold.place.ProxyPlace;
import com.shekhar.roo.gae.client.scaffold.place.CreateAndEditProxy;
import com.shekhar.roo.gae.client.scaffold.place.FindAndEditProxy;
import com.shekhar.roo.gae.client.managed.request.PersonProxy;
import java.util.Set;
import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.ui.AddressSetEditor;
import com.shekhar.roo.gae.client.managed.request.ApplicationRequestFactory;
import com.shekhar.roo.gae.client.scaffold.ScaffoldApp;
import com.shekhar.roo.gae.client.managed.activity.PersonDetailsActivity;
import com.shekhar.roo.gae.client.managed.activity.PersonEditActivityWrapper;
import com.shekhar.roo.gae.client.managed.ui.PersonListView;
import com.shekhar.roo.gae.client.managed.ui.PersonDetailsView;
import com.shekhar.roo.gae.client.managed.ui.PersonMobileDetailsView;
import com.shekhar.roo.gae.client.managed.ui.PersonEditView;
import com.shekhar.roo.gae.client.managed.ui.PersonMobileEditView;
import com.shekhar.roo.gae.client.managed.request.PersonRequest;

/**
 * Maps {@link ProxyPlace} instances to the {@link Activity} to run.
 */
public class PersonActivitiesMapper {
  private final ApplicationRequestFactory requests;
  private final PlaceController placeController;

  public PersonActivitiesMapper(ApplicationRequestFactory requests, PlaceController placeController) {
    this.requests = requests;
    this.placeController = placeController;
  }

  public Activity getActivity(ProxyPlace place) {
    switch (place.getOperation()) {
      case DETAILS:
        return new PersonDetailsActivity((EntityProxyId<PersonProxy>)place.getProxyId(), requests, 
          placeController, ScaffoldApp.isMobile() ? PersonMobileDetailsView.instance() : PersonDetailsView.instance());

      case EDIT:
        return makeEditActivity(place);

      case CREATE:
        return makeCreateActivity();
    }

    throw new IllegalArgumentException("Unknown operation "
        + place.getOperation());
  }

  @SuppressWarnings("unchecked")
  private EntityProxyId<PersonProxy> coerceId(ProxyPlace place) {
    return (EntityProxyId<PersonProxy>) place.getProxyId();
  }

  private Activity makeCreateActivity() {
    PersonEditView.instance().setCreating(true);
    final PersonRequest request = requests.personRequest();
    Activity activity = new CreateAndEditProxy<PersonProxy>(
        PersonProxy.class, request,
        ScaffoldApp.isMobile() ? PersonMobileEditView.instance() : PersonEditView.instance(),
        placeController) {
      
      @Override
      protected RequestContext createSaveRequest(PersonProxy proxy) {
        request.persist().using(proxy);
        return request;
      }
    };

    return new PersonEditActivityWrapper(requests,
        ScaffoldApp.isMobile() ? PersonMobileEditView.instance() : PersonEditView.instance(),
        activity, null);
  }

  private Activity makeEditActivity(ProxyPlace place) {
    PersonEditView.instance().setCreating(false);
    EntityProxyId<PersonProxy> proxyId = coerceId(place);
    Activity activity = new FindAndEditProxy<PersonProxy>(proxyId,
        requests,
        ScaffoldApp.isMobile() ? PersonMobileEditView.instance() : PersonEditView.instance(),
        placeController) {
      
      @Override
      protected RequestContext createSaveRequest(PersonProxy proxy) {
        PersonRequest request = requests.personRequest();
        request.persist().using(proxy);
        return request;
      }
    };

    return new PersonEditActivityWrapper(requests,
        ScaffoldApp.isMobile() ? PersonMobileEditView.instance() : PersonEditView.instance(),
        activity, proxyId);
  }
}
