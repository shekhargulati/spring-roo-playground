package com.shekhar.roo.gae.client.scaffold;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.shekhar.roo.gae.client.scaffold.ioc.DesktopInjector;
import com.shekhar.roo.gae.client.scaffold.ioc.DesktopInjectorWrapper;
import com.shekhar.roo.gae.client.scaffold.ioc.InjectorWrapper;
import com.shekhar.roo.gae.client.scaffold.ioc.ScaffoldInjector;

/**
 * Application for browsing entities.
 */
public class Scaffold implements EntryPoint {
    final private InjectorWrapper injectorWrapper = GWT.create(DesktopInjectorWrapper.class);

    public void onModuleLoad() {
        /* Get and run platform specific app */

        injectorWrapper.getInjector().getScaffoldApp().run();
    }
}