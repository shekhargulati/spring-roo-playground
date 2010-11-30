package com.shekhar.roo.gae.client.managed.activity;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.view.client.Range;

import com.shekhar.roo.gae.client.scaffold.place.AbstractProxyListActivity;
import com.shekhar.roo.gae.client.scaffold.place.ProxyListView;
import com.shekhar.roo.gae.client.managed.request.PersonProxy;
import java.util.Set;
import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.ui.AddressSetEditor;
import com.shekhar.roo.gae.client.scaffold.ScaffoldMobileApp;
import com.shekhar.roo.gae.client.scaffold.activity.IsScaffoldMobileActivity;
import com.shekhar.roo.gae.client.managed.request.ApplicationRequestFactory;


import java.util.List;

/**
 * Activity that requests and displays all PersonProxy.
 */
public class PersonListActivity extends AbstractProxyListActivity<PersonProxy>
		implements IsScaffoldMobileActivity {

	private final ApplicationRequestFactory requests;

	public PersonListActivity(ApplicationRequestFactory requests, 
      ProxyListView<PersonProxy> view, PlaceController placeController) {
   	super(placeController, view, PersonProxy.class);
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
		return "People";
	}

	public boolean hasEditButton() {
		return false;
	}

	protected Request<List<PersonProxy>> createRangeRequest(Range range) {
		return requests.personRequest().findPersonEntries(range.getStart(), range.getLength());
	}

	protected void fireCountRequest(Receiver<Long> callback) {
		requests.personRequest().countPeople().fire(callback);
	}
}
