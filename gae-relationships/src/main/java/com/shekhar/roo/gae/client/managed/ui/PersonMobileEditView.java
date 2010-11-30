package com.shekhar.roo.gae.client.managed.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import com.google.gwt.requestfactory.shared.RequestFactory;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.LongBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.shekhar.roo.gae.client.scaffold.ui.*;

import com.shekhar.roo.gae.client.scaffold.place.ProxyEditView;
import com.shekhar.roo.gae.client.managed.request.PersonProxy;
import java.util.Set;
import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.ui.AddressSetEditor;
import com.shekhar.roo.gae.client.managed.activity.PersonEditActivityWrapper;


import java.util.Collection;
import java.util.List;

/**
 * Edit view for Person proxys.
 */
public class PersonMobileEditView extends Composite implements 
		PersonEditActivityWrapper.View<PersonMobileEditView> {

	interface Binder extends UiBinder<HTMLPanel, PersonMobileEditView> {
	}

	interface Driver extends
		RequestFactoryEditorDriver<PersonProxy, PersonMobileEditView> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	private static PersonMobileEditView instance;

	public static PersonMobileEditView instance() {
		if (instance == null) {
			instance = new PersonMobileEditView();
		}
		return instance;
	}

	
	@UiField TextBox firstName ;
	@UiField TextBox lastName ;
	@UiField AddressSetEditor addresses ;	
	@UiField Button save;
	@UiField DivElement errors;

	private Delegate delegate;

	public PersonMobileEditView() {
		initWidget(BINDER.createAndBindUi(this));
	}

	@Override
	public RequestFactoryEditorDriver<PersonProxy, PersonMobileEditView> createEditorDriver() {
		RequestFactoryEditorDriver<PersonProxy, PersonMobileEditView> driver = GWT.create(Driver.class);
		driver.initialize(this);
		return driver;
	}

	

	
		public void setAddressesPickerValues(Collection<AddressProxy> values) {
		addresses.setAcceptableValues(values);
	}

   
	public void setCreating(boolean creating) {
	}

	public void setDelegate(Delegate delegate) {
		this.delegate = delegate;
	}

	public void setEnabled(boolean enabled) {
		save.setEnabled(enabled);
	}

	public void showErrors(List<EditorError> errors) {
		SafeHtmlBuilder b = new SafeHtmlBuilder();
		for (EditorError error : errors) {
      b.appendEscaped(error.getPath()).appendEscaped(": ");
			b.appendEscaped(error.getMessage()).appendHtmlConstant("<br>");
		}
		this.errors.setInnerHTML(b.toSafeHtml().asString());
	}

	@UiHandler("save")
	void onSave(ClickEvent event) {
		delegate.saveClicked();
	}
}
