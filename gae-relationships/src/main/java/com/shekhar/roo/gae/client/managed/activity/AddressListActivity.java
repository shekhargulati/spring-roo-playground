package com.shekhar.roo.gae.client.managed.activity;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.view.client.Range;

import com.shekhar.roo.gae.client.scaffold.place.AbstractProxyListActivity;
import com.shekhar.roo.gae.client.scaffold.place.ProxyListView;
import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.request.PersonProxy;
import com.shekhar.roo.gae.client.scaffold.ScaffoldMobileApp;
import com.shekhar.roo.gae.client.scaffold.activity.IsScaffoldMobileActivity;
import com.shekhar.roo.gae.client.managed.request.ApplicationRequestFactory;


import java.util.List;

/**
 * Activity that requests and displays all AddressProxy.
 */
public class AddressListActivity extends AbstractProxyListActivity<AddressProxy>
		implements IsScaffoldMobileActivity {

	private final ApplicationRequestFactory requests;

	public AddressListActivity(ApplicationRequestFactory requests, 
      ProxyListView<AddressProxy> view, PlaceController placeController) {
   	super(placeController, view, AddressProxy.class);
   	this.requests = requests;
	}

	public Place getBackButtonPlace() {
		return ScaffoldMobileApp.ROOT_PLACE;
	}

	public String getBackButtonText() {
		return "Entities";
	}

	public Place getEditButtonPlace() {
		return null;
	}

	public String getTitleText() {
		return "Addresses";
	}

	public boolean hasEditButton() {
		return false;
	}

	protected Request<List<AddressProxy>> createRangeRequest(Range range) {
		return requests.addressRequest().findAddressEntries(range.getStart(), range.getLength());
	}

	protected void fireCountRequest(Receiver<Long> callback) {
		requests.addressRequest().countAddresses().fire(callback);
	}
}
