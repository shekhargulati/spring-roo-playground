package com.shekhar.roo.gae.client.managed.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.EntityProxyId;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import com.shekhar.roo.gae.client.scaffold.place.ProxyPlace;
import com.shekhar.roo.gae.client.scaffold.place.ProxyDetailsView;
import com.shekhar.roo.gae.client.scaffold.place.ProxyListPlace;
import com.shekhar.roo.gae.client.scaffold.place.ProxyPlace.Operation;
import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.request.PersonProxy;
import com.shekhar.roo.gae.client.managed.request.ApplicationRequestFactory;
import com.shekhar.roo.gae.client.scaffold.activity.IsScaffoldMobileActivity;

import java.util.Set;

/**
 * An {@link com.google.gwt.activity.shared.Activity Activity} that requests and
 * displays detailed information on a given AddressProxy.
 */
public class AddressDetailsActivity extends AbstractActivity
		implements ProxyDetailsView.Delegate, IsScaffoldMobileActivity {

	private final ApplicationRequestFactory requests;
	private final PlaceController placeController;
	private final ProxyDetailsView<AddressProxy> view;
  private final EntityProxyId<AddressProxy> proxyId;
	private AcceptsOneWidget display;

	public AddressDetailsActivity(EntityProxyId<AddressProxy> proxyId, ApplicationRequestFactory requests, PlaceController placeController, ProxyDetailsView<AddressProxy> view) {
		this.placeController = placeController;
		this.proxyId = proxyId;
		this.requests = requests;
		view.setDelegate(this);
		this.view = view;
	}

	public void deleteClicked() {
		if (!view.confirm("Really delete this entry? You cannot undo this change.")) {
			return;
		}
   		
		requests.addressRequest().remove().using(view.getValue()).fire(new Receiver<Void>() {
			public void onSuccess(Void ignore) {
				if (display == null) {
					// This activity is dead
					return;
				}

				// Go back to the previous place.
				placeController.goTo(getBackButtonPlace());
			}
		});
	}

	public void editClicked() {
		placeController.goTo(getEditButtonPlace());
	}

	public Place getBackButtonPlace() {
		return new ProxyListPlace(AddressProxy.class);
	}

	public String getBackButtonText() {
		return "Back";
	}

	public Place getEditButtonPlace() {
		return new ProxyPlace(view.getValue().stableId(), Operation.EDIT);
	}

	public String getTitleText() {
		return "View Address";
	}

	public boolean hasEditButton() {
		return true;
	}

	public void onCancel() {
		onStop();
	}

	public void onStop() {
		display = null;
	}

	public void start(AcceptsOneWidget displayIn, EventBus eventBus) {
   	this.display = displayIn;
   	Receiver<EntityProxy> callback = new Receiver<EntityProxy>() {
      public void onSuccess(EntityProxy proxy) {
        if (display == null) {
   	  		return;
   			}
  		  view.setValue((AddressProxy) proxy);
        display.setWidget(view);
      }
    };

		requests.find(proxyId).with("person").fire(callback);
	}
}
