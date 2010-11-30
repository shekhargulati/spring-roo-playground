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
import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.request.PersonProxy;


import java.util.HashSet;
import java.util.Set;

/**
 * {@link AbstractProxyListView} specialized to {@link AddressKey}} values.
 */
public class AddressListView extends AbstractProxyListView<AddressProxy> {
	interface Binder extends UiBinder<HTMLPanel, AddressListView> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);
  
  private static AddressListView instance;

	@UiField CellTable<AddressProxy> table;
	@UiField Button newButton;
  
  private Set<String> paths = new HashSet<String>();

  public static AddressListView instance() {
    if (instance == null) {
      instance = new AddressListView();
    }
    
    return instance;
  }
  
	public AddressListView() {
		init(BINDER.createAndBindUi(this), table, newButton);
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.DISABLED);
    
    paths.add("id");
    table.addColumn(new TextColumn<AddressProxy>() {
      Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {
        public String render(java.lang.String obj) {
          return obj == null ? "" : String.valueOf(obj);
        }
      };
      
      @Override
      public String getValue(AddressProxy object) {
        return renderer.render(object.getId());
      }
    }, "Id");
    paths.add("version");
    table.addColumn(new TextColumn<AddressProxy>() {
      Renderer<java.lang.Integer> renderer = new AbstractRenderer<java.lang.Integer>() {
        public String render(java.lang.Integer obj) {
          return obj == null ? "" : String.valueOf(obj);
        }
      };
      
      @Override
      public String getValue(AddressProxy object) {
        return renderer.render(object.getVersion());
      }
    }, "Version");
    paths.add("streetAddress");
    table.addColumn(new TextColumn<AddressProxy>() {
      Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {
        public String render(java.lang.String obj) {
          return obj == null ? "" : String.valueOf(obj);
        }
      };
      
      @Override
      public String getValue(AddressProxy object) {
        return renderer.render(object.getStreetAddress());
      }
    }, "Street Address");
    paths.add("city");
    table.addColumn(new TextColumn<AddressProxy>() {
      Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {
        public String render(java.lang.String obj) {
          return obj == null ? "" : String.valueOf(obj);
        }
      };
      
      @Override
      public String getValue(AddressProxy object) {
        return renderer.render(object.getCity());
      }
    }, "City");
    paths.add("state");
    table.addColumn(new TextColumn<AddressProxy>() {
      Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {
        public String render(java.lang.String obj) {
          return obj == null ? "" : String.valueOf(obj);
        }
      };
      
      @Override
      public String getValue(AddressProxy object) {
        return renderer.render(object.getState());
      }
    }, "State");
    paths.add("zip");
    table.addColumn(new TextColumn<AddressProxy>() {
      Renderer<java.lang.String> renderer = new AbstractRenderer<java.lang.String>() {
        public String render(java.lang.String obj) {
          return obj == null ? "" : String.valueOf(obj);
        }
      };
      
      @Override
      public String getValue(AddressProxy object) {
        return renderer.render(object.getZip());
      }
    }, "Zip");
    paths.add("person");
    table.addColumn(new TextColumn<AddressProxy>() {
      Renderer<com.shekhar.roo.gae.client.managed.request.PersonProxy> renderer = com.shekhar.roo.gae.client.managed.ui.PersonProxyRenderer.instance();
      
      @Override
      public String getValue(AddressProxy object) {
        return renderer.render(object.getPerson());
      }
    }, "Person");
 	}

  public String[] getPaths() {
    return paths.toArray(new String[paths.size()]);
  }
}
