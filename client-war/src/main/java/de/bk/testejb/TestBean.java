package de.bk.testejb;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.Context;

@Singleton
@Startup
public class TestBean {

	@PostConstruct
	public void invokeOnBean() {
		try {
			final Hashtable props = new Hashtable();
			// setup the ejb: namespace URL factory
			props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			// create the InitialContext
			final Context context = new javax.naming.InitialContext(props);

			// Lookup the Greeter bean using the ejb: namespace syntax which is
			// explained here
			// https://docs.jboss.org/author/display/AS71/EJB+invocations+from+a+remote+client+using+JNDI
			final Greeter bean =  (Greeter) context.lookup("ejb:/server-ejb-1.0-SNAPSHOT/GreeterBean!"+ Greeter.class.getName());

			// invoke on the bean
			final String greeting = bean.greet("Benny");

			System.out.println("Received greeting: " + greeting);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
