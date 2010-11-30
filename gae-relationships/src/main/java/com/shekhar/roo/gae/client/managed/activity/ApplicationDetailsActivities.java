package com.shekhar.roo.gae.client.managed.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

import com.shekhar.roo.gae.client.scaffold.place.ProxyPlace;
import com.shekhar.roo.gae.client.managed.request.ApplicationRequestFactory;
import com.shekhar.roo.gae.client.managed.request.ApplicationEntityTypesProcessor;
import com.shekhar.roo.gae.client.managed.request.PersonProxy;
import com.shekhar.roo.gae.client.managed.activity.PersonActivitiesMapper;
import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.activity.AddressActivitiesMapper;

/**
 * Instantiates detail activities.
 */
public class ApplicationDetailsActivities implements ActivityMapper {
  private final ApplicationRequestFactory requests;
  private final PlaceController placeController;

  @Inject
  public ApplicationDetailsActivities(ApplicationRequestFactory requestFactory, PlaceController placeController) {
    this.requests = requestFactory;
    this.placeController = placeController;
  }

  public Activity getActivity(Place place) {
    if (!(place instanceof ProxyPlace)) {
      return null;
    }

    final ProxyPlace proxyPlace = (ProxyPlace) place;

    return new ApplicationEntityTypesProcessor<Activity>() {
      @Override
			public void handlePerson(PersonProxy proxy) {
				setResult(new PersonActivitiesMapper(requests, placeController).getActivity(proxyPlace));
			}
      @Override
			public void handleAddress(AddressProxy proxy) {
				setResult(new AddressActivitiesMapper(requests, placeController).getActivity(proxyPlace));
			}
    }.process(proxyPlace.getProxyClass());
  }
}
