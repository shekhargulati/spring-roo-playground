package com.shekhar.roo.gae.client.managed.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

import com.shekhar.roo.gae.client.scaffold.place.ProxyListPlace;
import com.shekhar.roo.gae.client.managed.request.ApplicationRequestFactory;
import com.shekhar.roo.gae.client.managed.request.ApplicationEntityTypesProcessor;
import com.shekhar.roo.gae.client.scaffold.ScaffoldApp;
import com.shekhar.roo.gae.client.managed.activity.PersonListActivity;
import com.shekhar.roo.gae.client.managed.request.PersonProxy;
import com.shekhar.roo.gae.client.managed.ui.PersonListView;
import com.shekhar.roo.gae.client.managed.ui.PersonMobileListView;
import com.shekhar.roo.gae.client.managed.activity.AddressListActivity;
import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.ui.AddressListView;
import com.shekhar.roo.gae.client.managed.ui.AddressMobileListView;

/**
 * Instantiates master activities.
 */
public final class ApplicationMasterActivities implements ActivityMapper {
	private final ApplicationRequestFactory requests;
	private final PlaceController placeController;

	@Inject
	public ApplicationMasterActivities(ApplicationRequestFactory requests, PlaceController placeController) {
 		this.requests = requests;
		this.placeController = placeController;
	}

	public Activity getActivity(Place place) {
		if (!(place instanceof ProxyListPlace)) {
			return null;
		}

		ProxyListPlace listPlace = (ProxyListPlace) place;

		return new ApplicationEntityTypesProcessor<Activity>() {
			@Override
      public void handlePerson(PersonProxy isNull) {
        setResult(new PersonListActivity(requests,
          ScaffoldApp.isMobile() ? PersonMobileListView.instance() : PersonListView.instance(),
          placeController));
      }
      
			@Override
      public void handleAddress(AddressProxy isNull) {
        setResult(new AddressListActivity(requests,
          ScaffoldApp.isMobile() ? AddressMobileListView.instance() : AddressListView.instance(),
          placeController));
      }
      
		}.process(listPlace.getProxyClass());
	}
}
