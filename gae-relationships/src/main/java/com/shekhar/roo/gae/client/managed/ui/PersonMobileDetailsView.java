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
import com.shekhar.roo.gae.client.managed.request.PersonProxy;
import java.util.Set;
import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.ui.AddressSetEditor;

/**
 * Details view for Person proxys.
 */
public class PersonMobileDetailsView extends Composite implements ProxyDetailsView<PersonProxy> {
	interface Binder extends UiBinder<HTMLPanel, PersonMobileDetailsView> {}

	private static final Binder BINDER = GWT.create(Binder.class);

	private static PersonMobileDetailsView instance;

	public static PersonMobileDetailsView instance() {
		if (instance == null) {
			instance = new PersonMobileDetailsView();
		}
		return instance;
	}

	PersonProxy proxy;
	
	@UiField Element id;
	@UiField Element version;
	@UiField Element firstName;
	@UiField Element lastName;
	@UiField Element addresses;	
	@UiField HasClickHandlers delete;

	private Delegate delegate;

	public PersonMobileDetailsView() {
		initWidget(BINDER.createAndBindUi(this));
	}

  	public Widget asWidget() {
		return this;
	}
  
	public boolean confirm(String msg) {
		return Window.confirm(msg);
	}

	public PersonProxy getValue() {
		return proxy;
	}

	@UiHandler("delete")
	public void onDeleteClicked(ClickEvent e) {
		delegate.deleteClicked();
	}

	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	public void setValue(PersonProxy proxy) {
		this.proxy = proxy;
		id.setInnerText(proxy.getId() == null ? "" : String.valueOf(proxy.getId()));
		version.setInnerText(proxy.getVersion() == null ? "" : String.valueOf(proxy.getVersion()));
		firstName.setInnerText(proxy.getFirstName() == null ? "" : String.valueOf(proxy.getFirstName()));
		lastName.setInnerText(proxy.getLastName() == null ? "" : String.valueOf(proxy.getLastName()));
		addresses.setInnerText(proxy.getAddresses() == null ? "" : com.shekhar.roo.gae.client.scaffold.place.CollectionRenderer.of(com.shekhar.roo.gae.client.managed.ui.AddressProxyRenderer.instance()).render(proxy.getAddresses()));
	}
}
