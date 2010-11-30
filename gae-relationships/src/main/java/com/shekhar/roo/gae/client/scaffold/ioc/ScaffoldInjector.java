package com.shekhar.roo.gae.client.scaffold.ioc;

import com.google.gwt.inject.client.Ginjector;
import com.shekhar.roo.gae.client.scaffold.ScaffoldApp;

public interface ScaffoldInjector extends Ginjector{

    ScaffoldApp getScaffoldApp();
}
