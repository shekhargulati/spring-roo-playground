package com.shekhar.roo.gae.client.managed.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;


import com.shekhar.roo.gae.client.scaffold.place.ProxyDetailsView;
import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.request.PersonProxy;

/**
 * Details view for Address proxys.
 */
public class AddressMobileDetailsView extends Composite implements ProxyDetailsView<AddressProxy> {
	interface Binder extends UiBinder<HTMLPanel, AddressMobileDetailsView> {}

	private static final Binder BINDER = GWT.create(Binder.class);

	private static AddressMobileDetailsView instance;

	public static AddressMobileDetailsView instance() {
		if (instance == null) {
			instance = new AddressMobileDetailsView();
		}
		return instance;
	}

	AddressProxy proxy;
	
	@UiField Element id;
	@UiField Element version;
	@UiField Element streetAddress;
	@UiField Element city;
	@UiField Element state;
	@UiField Element zip;
	@UiField Element person;	
	@UiField HasClickHandlers delete;

	private Delegate delegate;

	public AddressMobileDetailsView() {
		initWidget(BINDER.createAndBindUi(this));
	}

  	public Widget asWidget() {
		return this;
	}
  
	public boolean confirm(String msg) {
		return Window.confirm(msg);
	}

	public AddressProxy getValue() {
		return proxy;
	}

	@UiHandler("delete")
	public void onDeleteClicked(ClickEvent e) {
		delegate.deleteClicked();
	}

	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	public void setValue(AddressProxy proxy) {
		this.proxy = proxy;
		id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
		version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
		streetAddress.setInnerText(proxy.getStreetAddress() == null ? "" : String.valueOf(proxy.getStreetAddress()));
		city.setInnerText(proxy.getCity() == null ? "" : String.valueOf(proxy.getCity()));
		state.setInnerText(proxy.getState() == null ? "" : String.valueOf(proxy.getState()));
		zip.setInnerText(proxy.getZip() == null ? "" : String.valueOf(proxy.getZip()));
		person.setInnerText(proxy.getPerson() == null ? "" : com.shekhar.roo.gae.client.managed.ui.PersonProxyRenderer.instance().render(proxy.getPerson()));
	}
}
