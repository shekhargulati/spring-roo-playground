package com.shekhar.roo.gae.client.scaffold.place;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.inject.Inject;

import com.shekhar.roo.gae.client.managed.request.ApplicationRequestFactory;

public class PlaceHistoryFactory {
	
	private final ProxyListPlace.Tokenizer proxyListPlaceTokenizer;
    private final ProxyPlace.Tokenizer proxyPlaceTokenizer;

    @Inject
    public PlaceHistoryFactory(ApplicationRequestFactory requestFactory) {
        this.proxyListPlaceTokenizer = new ProxyListPlace.Tokenizer(requestFactory);
        this.proxyPlaceTokenizer = new ProxyPlace.Tokenizer(requestFactory);
    }

    public PlaceTokenizer<ProxyListPlace> getProxyListPlaceTokenizer() {
        return proxyListPlaceTokenizer;
    }

    public PlaceTokenizer<ProxyPlace> getProxyPlaceTokenizer() {
        return proxyPlaceTokenizer;
    }

}
