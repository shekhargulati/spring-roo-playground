package com.shekhar.roo.gae.client.managed.ui;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.client.DateTimeFormatRenderer;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.text.shared.Renderer;

import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.request.PersonProxy;
import com.shekhar.roo.gae.client.scaffold.ui.MobileProxyListView;
import com.shekhar.roo.gae.client.scaffold.ScaffoldMobileApp;

import java.util.HashSet;
import java.util.Set;

/**
 * {@link MobileProxyListView} specialized to {@link AddressKey} values.
 */
public class AddressMobileListView extends MobileProxyListView<AddressProxy> {

	/**
	 * The renderer used to render cells.
	 */
	private static class CellRenderer extends
			AbstractSafeHtmlRenderer<AddressProxy> {
		private final String dateStyle = ScaffoldMobileApp.getMobileListResources().cellListStyle().dateProp();
		private final String secondaryStyle = ScaffoldMobileApp.getMobileListResources().cellListStyle().secondaryProp();
		
		private final Renderer<java.lang.String> primaryRenderer = new AbstractRenderer<java.lang.String>() {
        public String render(java.lang.String obj) {
          return obj == null ? "" : String.valueOf(obj);
        }
      };
		private final Renderer<java.lang.String> secondaryRenderer = new AbstractRenderer<java.lang.String>() {
        public String render(java.lang.String obj) {
          return obj == null ? "" : String.valueOf(obj);
        }
      };

		@Override
		public SafeHtml render(AddressProxy value) {
			if (value == null) {
				return SafeHtmlUtils.EMPTY_SAFE_HTML;
			}

			// Primary property.
			SafeHtmlBuilder sb = new SafeHtmlBuilder();
			if (value.getId() != null) {
				sb.appendEscaped(primaryRenderer.render(value.getId()));
			}

			// Secondary property.
			sb.appendHtmlConstant("<div style=\"position:relative;\">");
			sb.appendHtmlConstant("<div class=\"" + secondaryStyle + "\">");
			if (value.getStreetAddress() != null) {
				sb.appendEscaped(secondaryRenderer.render(value.getStreetAddress()));
			}
			sb.appendHtmlConstant("</div>");

			// Date property.
			sb.appendHtmlConstant("<div class=\"" + dateStyle + "\">");
			
			sb.appendHtmlConstant("</div>");
			sb.appendHtmlConstant("</div>");

			return sb.toSafeHtml();
		}
	}

	private static AddressMobileListView instance;

	private final Set<String> paths = new HashSet<String>();

	public static AddressMobileListView instance() {
		if (instance == null) {
			instance = new AddressMobileListView();
		}

		return instance;
	}

	public AddressMobileListView() {
		super("New Address", new CellRenderer());
		
		paths.add("id");
		paths.add("streetAddress");
	}

	public String[] getPaths() {
		return paths.toArray(new String[paths.size()]);
	}
}
