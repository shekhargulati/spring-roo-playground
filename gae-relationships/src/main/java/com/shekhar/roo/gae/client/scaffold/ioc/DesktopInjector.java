package com.shekhar.roo.gae.client.scaffold.ioc;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.shekhar.roo.gae.client.scaffold.ScaffoldApp;
import com.shekhar.roo.gae.client.scaffold.ScaffoldDesktopApp;

@GinModules(value = {ScaffoldModule.class})
public interface DesktopInjector extends ScaffoldInjector {
	
	ScaffoldDesktopApp getScaffoldApp();
}