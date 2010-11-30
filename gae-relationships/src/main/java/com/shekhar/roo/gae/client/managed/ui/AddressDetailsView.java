package com.shekhar.roo.gae.client.managed.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
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
import com.shekhar.roo.gae.client.scaffold.place.ProxyListView;
import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.request.PersonProxy;

/**
 * Details view for AddressProxy.
 */
public class AddressDetailsView extends Composite implements ProxyDetailsView<AddressProxy> {
	interface Binder extends UiBinder<HTMLPanel, AddressDetailsView> {}

	private static final Binder BINDER = GWT.create(Binder.class);

  private static AddressDetailsView instance;

	AddressProxy proxy;
	
	@UiField SpanElement id;
	@UiField SpanElement version;
	@UiField SpanElement streetAddress;
	@UiField SpanElement city;
	@UiField SpanElement state;
	@UiField SpanElement zip;
	@UiField SpanElement person;	
	
	@UiField SpanElement displayRenderer;
	@UiField HasClickHandlers edit;
	@UiField HasClickHandlers delete;

	private Delegate delegate;

  public static AddressDetailsView instance() {
    if (instance == null) {
      instance = new AddressDetailsView();
    }
    
    return instance;
  }

	public AddressDetailsView() {
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

	@UiHandler("edit")
	public void onEditClicked(ClickEvent e) {
		delegate.editClicked();
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

		displayRenderer.setInnerText(com.shekhar.roo.gae.client.managed.ui.AddressProxyRenderer.instance().render(proxy));
	}
}
