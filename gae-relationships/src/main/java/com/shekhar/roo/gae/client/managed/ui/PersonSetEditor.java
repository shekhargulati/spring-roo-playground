package com.shekhar.roo.gae.client.managed.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.Widget;

import com.shekhar.roo.gae.client.scaffold.ui.CollectionRenderer;
import com.shekhar.roo.gae.client.managed.request.PersonProxy;
import java.util.Set;
import com.shekhar.roo.gae.client.managed.request.AddressProxy;
import com.shekhar.roo.gae.client.managed.ui.AddressSetEditor;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Displays an editor for a Set of PersonProxy objects.
 */
public class PersonSetEditor extends Composite
    implements ValueAwareEditor<Set<PersonProxy>>,
    LeafValueEditor<Set<PersonProxy>> {

  interface Binder extends UiBinder<Widget, PersonSetEditor> {

  }

  interface Driver extends RequestFactoryEditorDriver<List<PersonProxy>, //
      ListEditor<PersonProxy, NameLabel>> {

  }

  class NameLabel extends Composite implements ValueAwareEditor<PersonProxy> {

    final Label idEditor = new Label();

    public NameLabel() {
      this(null);
    }

    public NameLabel(final EventBus eventBus) {
      initWidget(idEditor);
    }

    public void flush() {
      // No-op
    }

    @Override
    public void onPropertyChange(String... strings) {

    }

    @Override
    public void setDelegate(
        EditorDelegate<PersonProxy> editorDelegate) {
    }

    @Override
    public void setValue(PersonProxy proxy) {
    }
  }

  interface Style extends CssResource {

    String editorPanelHidden();

    String editorPanelVisible();

    String viewPanelHidden();

    String viewPanelVisible();
  }

  /**
   * This is used by a ListEditor.
   */
  private class NameLabelSource extends EditorSource<NameLabel> {

    @Override
    public NameLabel create(int index) {
      NameLabel label = new NameLabel();
      container.insert(label, index);
      return label;
    }

    @Override
    public void dispose(NameLabel subEditor) {
      subEditor.removeFromParent();
    }

    @Override
    public void setIndex(NameLabel editor, int index) {
      container.insert(editor, index);
    }
  }

  @UiField
  FlowPanel container;

  @UiField(provided = true)
  @Editor.Ignore
  ValueListBox<PersonProxy> picker
      = new ValueListBox<PersonProxy>(
      com.shekhar.roo.gae.client.managed.ui.PersonProxyRenderer.instance(),
      new com.google.gwt.requestfactory.ui.client.EntityProxyKeyProvider<PersonProxy>());

  ;

  @UiField
  Button add;

  @UiField
  HTMLPanel editorPanel;

  @UiField
  Button clickToEdit;

  @UiField
  HTMLPanel viewPanel;

  @UiField
  Label viewLabel;

  @UiField
  Style style;

  boolean editing = false;

  private Set<PersonProxy> values;

  private final List<PersonProxy> displayedList;

  public PersonSetEditor() {

    // Create the UI
    initWidget(GWT.<Binder>create(Binder.class).createAndBindUi(this));

    // Create the driver which manages the data-bound widgets
    Driver driver = GWT.<Driver>create(Driver.class);

    // Use a ListEditor that uses our NameLabelSource
    ListEditor<PersonProxy, NameLabel> editor = ListEditor
        .of(new NameLabelSource());

    // Configure the driver
    ListEditor<PersonProxy, NameLabel> listEditor = editor;
    driver.initialize(listEditor);

    /*
     * Notice the backing list is essentially anonymous.
     */
    driver.display(new ArrayList<PersonProxy>());

    // Modifying this list triggers widget creation and destruction
    displayedList = listEditor.getList();

    editing = false;
  }

  @UiHandler("add")
  public void addClicked(ClickEvent e) {
    if (!displayedList.contains(picker.getValue())) {
      displayedList.add(picker.getValue());
      viewLabel.setText(makeFlatList(displayedList));
    }
  }

  @UiHandler("clickToEdit")
  public void clickToEditClicked(ClickEvent e) {
    toggleEditorMode();
  }

  @Override
  public void flush() {
  }

  @Override
  public Set<PersonProxy> getValue() {
    if (values == null && displayedList.size() == 0) {
      return null;
    }
    return new HashSet<PersonProxy>(displayedList);
  }

  public void onLoad() {
    makeEditable(false);
  }

  @Override
  public void onPropertyChange(String... strings) {
  }

  public void setAcceptableValues(Collection<PersonProxy> proxies) {
    picker.setAcceptableValues(proxies);
  }

  @Override
  public void setDelegate(
      EditorDelegate<Set<PersonProxy>> editorDelegate) {
  }

  @Override
  public void setValue(Set<PersonProxy> values) {
    this.values = values;
    makeEditable(editing = false);
    if (displayedList != null) {
      displayedList.clear();
    }

    if (values != null) {
      for (PersonProxy e : values) {
        displayedList.add(e);
      }
    }
    viewLabel.setText(makeFlatList(values));
  }

  private void makeEditable(boolean editable) {
    if (editable) {
      editorPanel.setStylePrimaryName(style.editorPanelVisible());
      viewPanel.setStylePrimaryName(style.viewPanelHidden());
      clickToEdit.setText("Done");
    } else {
      editorPanel.setStylePrimaryName(style.editorPanelHidden());
      viewPanel.setStylePrimaryName(style.viewPanelVisible());
      clickToEdit.setText("Edit");
    }
  }

  private String makeFlatList(Collection<PersonProxy> values) {
    return CollectionRenderer.of(com.shekhar.roo.gae.client.managed.ui.PersonProxyRenderer.instance())
        .render(values);
  }

  private void toggleEditorMode() {
    editing = !editing;
    makeEditable(editing);
  }
}
