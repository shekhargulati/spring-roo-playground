package com.shekhar.roo.gae.client.scaffold.ioc;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.shekhar.roo.gae.client.scaffold.ScaffoldDesktopApp;
import com.shekhar.roo.gae.client.scaffold.ScaffoldMobileApp;

@GinModules(value = {ScaffoldModule.class})
public interface MobileInjector extends ScaffoldInjector {

    ScaffoldMobileApp getScaffoldApp();
}
