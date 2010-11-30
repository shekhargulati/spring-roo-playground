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
import com.shekhar.roo.gae.client.managed.request.PersonProxy;
import java.util.Set;
import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.ui.AddressSetEditor;

/**
 * Details view for PersonProxy.
 */
public class PersonDetailsView extends Composite implements ProxyDetailsView<PersonProxy> {
	interface Binder extends UiBinder<HTMLPanel, PersonDetailsView> {}

	private static final Binder BINDER = GWT.create(Binder.class);

  private static PersonDetailsView instance;

	PersonProxy proxy;
	
	@UiField SpanElement id;
	@UiField SpanElement version;
	@UiField SpanElement firstName;
	@UiField SpanElement lastName;
	@UiField SpanElement addresses;	
	
	@UiField SpanElement displayRenderer;
	@UiField HasClickHandlers edit;
	@UiField HasClickHandlers delete;

	private Delegate delegate;

  public static PersonDetailsView instance() {
    if (instance == null) {
      instance = new PersonDetailsView();
    }
    
    return instance;
  }

	public PersonDetailsView() {
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

	@UiHandler("edit")
	public void onEditClicked(ClickEvent e) {
		delegate.editClicked();
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

		displayRenderer.setInnerText(com.shekhar.roo.gae.client.managed.ui.PersonProxyRenderer.instance().render(proxy));
	}
}
