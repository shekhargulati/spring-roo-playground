package com.shekhar.roo.gae.client.managed.request;

import com.google.gwt.requestfactory.shared.EntityProxy;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A helper class for dealing with proxy types. Subclass it and override the
 * various handle methods for type specific handling of proxy objects or
 * classes, then call {@link #process(Class)} or {@link #process(Object)}.
 * Optionally use {#setResult} to set the return value of the {@link #process}
 * call.
 
 * <p>
 * Use {@link #getAll} for a set of all proxy types.
 * 
 * @param <T> the type to filter to
 */
public abstract class ApplicationEntityTypesProcessor<T> {

	/**
	 * Return a set of all proxy types available to this application.
	 */
	public static Set<Class<? extends EntityProxy>> getAll() {
		Set<Class<? extends EntityProxy>> rtn = new HashSet<Class<? extends EntityProxy>>();

		rtn.add(PersonProxy.class);
		rtn.add(AddressProxy.class);
  
		return Collections.unmodifiableSet(rtn);
	}

	private static void process(ApplicationEntityTypesProcessor<?> processor, Class<?> clazz) {
		if (PersonProxy.class.equals(clazz)) {
			processor.handlePerson((PersonProxy) null);
			return;
		}
		if (AddressProxy.class.equals(clazz)) {
			processor.handleAddress((AddressProxy) null);
			return;
		} 	
		processor.handleNonProxy(null);
	}

	private static void process(ApplicationEntityTypesProcessor<?> processor, Object proxy) {
		if (proxy instanceof PersonProxy) {
			processor.handlePerson((PersonProxy) proxy);
			return;
		}
		if (proxy instanceof AddressProxy) {
			processor.handleAddress((AddressProxy) proxy);
			return;
		}
		processor.handleNonProxy(proxy);
	}

	private final T defaultValue;
	private T result;

	/**
	 * Create an instance with a null default value.
	 */
	public ApplicationEntityTypesProcessor() {
		defaultValue = null;
	}

	/**
	 * Create an instance with the given default value.
	 * 
	 * @param the value that will be returned by {@link #process} if {@link #setResult} is not called.
	 */
	public ApplicationEntityTypesProcessor(T defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * Called if {@link #process} is called with a non-proxy object. This default
	 * implementation does nothing.
	 */
	public void handleNonProxy(Object object) {
	}

	public abstract void handlePerson(PersonProxy proxy);		
	public abstract void handleAddress(AddressProxy proxy);		

   	/**
	 * Call the handle method of the appropriate type, with a null argument. Note
	 * that this will not work as expected on the class objects returned by the
	 * {@link #getClass()} method of a proxy object, due to limitations of GWT's
	 * metadata. It will only work with against class objects in the set returned
	 * by {@link #getAll()}, or returned by
	 * {@link com.google.gwt.requestfactory.shared.RequestFactory#getClass(Proxy)}
	 * or
	 * {@link com.google.gwt.requestfactory.shared.RequestFactory#getClass(String)}.
 	 * 
	 * @param clazz the proxy type to resolve
	 * @return the value provided via {@link #setResult}, or the default value
	 */
	public T process(Class<?> clazz) {
		setResult(defaultValue);
		ApplicationEntityTypesProcessor.process(this, clazz);
		return result;
	}

	/**
	 * Process a proxy object
	 * 
	 * @param proxy the proxy to process
	 * @return the value provided via {@link #setResult}, or the default value
	 */
	public T process(Object proxy) {
		setResult(defaultValue);
		ApplicationEntityTypesProcessor.process(this, proxy);
		return result;
	}

	/**
	 * Set the value to return from a call to {@link #process(Class)} or
	 * {@link #process(Object)}.
	 * 
	 * @param result the value to return
	 */
	protected void setResult(T result) {
		this.result = result;
	}
}
