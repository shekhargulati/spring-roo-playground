package com.shekhar.roo.gae.client.managed.ui;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.client.DateTimeFormatRenderer;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.text.shared.Renderer;

import com.shekhar.roo.gae.client.managed.request.PersonProxy;
import java.util.Set;
import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.ui.AddressSetEditor;
import com.shekhar.roo.gae.client.scaffold.ui.MobileProxyListView;
import com.shekhar.roo.gae.client.scaffold.ScaffoldMobileApp;

import java.util.HashSet;
import java.util.Set;

/**
 * {@link MobileProxyListView} specialized to {@link PersonKey} values.
 */
public class PersonMobileListView extends MobileProxyListView<PersonProxy> {

	/**
	 * The renderer used to render cells.
	 */
	private static class CellRenderer extends
			AbstractSafeHtmlRenderer<PersonProxy> {
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
		public SafeHtml render(PersonProxy value) {
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
			if (value.getFirstName() != null) {
				sb.appendEscaped(secondaryRenderer.render(value.getFirstName()));
			}
			sb.appendHtmlConstant("</div>");

			// Date property.
			sb.appendHtmlConstant("<div class=\"" + dateStyle + "\">");
			
			sb.appendHtmlConstant("</div>");
			sb.appendHtmlConstant("</div>");

			return sb.toSafeHtml();
		}
	}

	private static PersonMobileListView instance;

	private final Set<String> paths = new HashSet<String>();

	public static PersonMobileListView instance() {
		if (instance == null) {
			instance = new PersonMobileListView();
		}

		return instance;
	}

	public PersonMobileListView() {
		super("New Person", new CellRenderer());
		
		paths.add("id");
		paths.add("firstName");
	}

	public String[] getPaths() {
		return paths.toArray(new String[paths.size()]);
	}
}
