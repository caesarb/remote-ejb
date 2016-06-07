package de.bk.testejb;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Singleton
@Startup
public class TestBean {

	private Context context = null;

	@PostConstruct
	public void invokeOnBean() {
		try {
			final Hashtable props = new Hashtable();
			// setup the ejb: namespace URL factory
			props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

			// if the following props are disabled the jboss-ejb-client.xml ref
			// should be used, but thats not working
			props.put("org.jboss.ejb.client.scoped.context", "true");
			props.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", false);
			props.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", false);
			props.put("remote.connections", "default");
			props.put("remote.connection.default.host", "localhost");
			props.put("remote.connection.default.port", "18080");
			props.put("remote.connection.default.username", "ejb");
			props.put("remote.connection.default.password", "test");

			context = new InitialContext(props);

			final Greeter bean = (Greeter) context.lookup("ejb:/server-ejb-1.0-SNAPSHOT/GreeterBean!"
					+ Greeter.class.getName());

			// invoke on the bean
			final String greeting = bean.greet("Benny");

			System.out.println("Received greeting: " + greeting);

		} catch (Exception e) {
			closeCtx();
			throw new RuntimeException(e);
		}
		closeCtx();
	}

	private void closeCtx() {
		try {
			context.close();
		} catch (NamingException e1) {
		}
	}
}
