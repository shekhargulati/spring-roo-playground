package com.shekhar.roo.gae.client.managed.request;

import com.google.gwt.requestfactory.shared.LoggingRequest;
import com.google.gwt.requestfactory.shared.RequestFactory;
import com.google.gwt.requestfactory.shared.UserInformationRequest;


public interface ApplicationRequestFactory extends RequestFactory {

	PersonRequest personRequest();

	AddressRequest addressRequest();
 	
  UserInformationRequest userInformationRequest();
  LoggingRequest loggingRequest();
}
