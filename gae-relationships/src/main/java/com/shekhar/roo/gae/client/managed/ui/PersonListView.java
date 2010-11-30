package com.shekhar.roo.gae.client.managed.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.text.client.DateTimeFormatRenderer;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;

import com.shekhar.roo.gae.client.scaffold.place.AbstractProxyListView;
import com.shekhar.roo.gae.client.managed.request.PersonProxy;
import java.util.Set;
import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.ui.AddressSetEditor;


import java.util.HashSet;
import java.util.Set;

/**
 * {@link AbstractProxyListView} specialized to {@link PersonKey}} values.
 */
public class PersonListView extends AbstractProxyListView<PersonProxy> {
	interface Binder extends UiBinder<HTMLPanel, PersonListView> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);
  
  private static PersonListView instance;

	@UiField CellTable<PersonProxy> table;
	@UiField Button newButton;
  
  private Set<String> paths = new HashSet<String>();

  public static PersonListView instance() {
    if (instance == null) {
      instance = new PersonListView();
    }
    
    return instance;
  }
  
	public PersonListView() {
		init(BINDER.createAndBindUi(this), table, newButton);
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
    
    paths.add("id");
    table.addColumn(new TextColumn<PersonProxy>() {
      Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {
        public String render(java.lang.String obj) {
          return obj == null ? "" : String.valueOf(obj);
        }
      };
      
      @Override
      public String getValue(PersonProxy object) {
        return renderer.render(object.getId());
      }
    }, "Id");
    paths.add("version");
    table.addColumn(new TextColumn<PersonProxy>() {
      Renderer<java.lang.Integer> renderer = new AbstractRenderer<java.lang.Integer>() {
        public String render(java.lang.Integer obj) {
          return obj == null ? "" : String.valueOf(obj);
        }
      };
      
      @Override
      public String getValue(PersonProxy object) {
        return renderer.render(object.getVersion());
      }
    }, "Version");
    paths.add("firstName");
    table.addColumn(new TextColumn<PersonProxy>() {
      Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {
        public String render(java.lang.String obj) {
          return obj == null ? "" : String.valueOf(obj);
        }
      };
      
      @Override
      public String getValue(PersonProxy object) {
        return renderer.render(object.getFirstName());
      }
    }, "First Name");
    paths.add("lastName");
    table.addColumn(new TextColumn<PersonProxy>() {
      Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {
        public String render(java.lang.String obj) {
          return obj == null ? "" : String.valueOf(obj);
        }
      };
      
      @Override
      public String getValue(PersonProxy object) {
        return renderer.render(object.getLastName());
      }
    }, "Last Name");
    paths.add("addresses");
    table.addColumn(new TextColumn<PersonProxy>() {
      Renderer<java.util.Set> renderer = com.shekhar.roo.gae.client.scaffold.place.CollectionRenderer.of(com.shekhar.roo.gae.client.managed.ui.AddressProxyRenderer.instance());
      
      @Override
      public String getValue(PersonProxy object) {
        return renderer.render(object.getAddresses());
      }
    }, "Addresses");
 	}

  public String[] getPaths() {
    return paths.toArray(new String[paths.size()]);
  }
}
