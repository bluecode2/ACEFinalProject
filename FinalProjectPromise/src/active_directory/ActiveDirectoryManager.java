package active_directory;
import javax.naming.Context;

import java.util.*;

import javax.naming.AuthenticationException;
import javax.naming.NamingException;
import javax.naming.InitialContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchResult;
import javax.naming.directory.SearchControls;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;




import common.CommonFunction;
import common.Constant;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;

public final class ActiveDirectoryManager {
	private static final String INITIAL_CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
	private static final String SECURITY_AUTHENTICATION = "simple";

	private String provideURL /* = "ldap://ace-router" */;
	private String domain /* = "nu-ace" */;
	private String schema;

	public ActiveDirectoryManager() {

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(CommonFunction.getGeneralParameterValue(Constant.GeneralParameter.APPLICATION_PROPERTIES_PATH) + "application.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.provideURL = properties.getProperty("ad.provideURL");
		this.domain = properties.getProperty("ad.domain");
		this.schema = properties.getProperty("ad.schema");

//		this.provideURL = "ldap://ace-router";
//		this.domain = "nu-ace.ad-ins.com";
//		this.schema = "DC=nu-ace,DC=ad-ins,DC=com";

	}

	private Hashtable init(String provideURL, String principal,
			String credential) {
		Hashtable env = new Hashtable(10, 1f);
		env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
		env.put(Context.PROVIDER_URL, provideURL);
		env.put(Context.SECURITY_AUTHENTICATION, SECURITY_AUTHENTICATION);

		env.put(Context.SECURITY_PRINCIPAL, principal);
		env.put(Context.SECURITY_CREDENTIALS, credential);

		env.put(Context.REFERRAL, "follow");
		return env;
	}

	public boolean isAuthenticated(String principal, String credential) {
		boolean valid = false;
		principal = principal + "@" + this.domain;
//		System.out.println("UserId: " + principal);
//		System.out.println("Domain: " + this.domain);

		try {
			Hashtable env = this.init(this.provideURL, principal, credential);
			Context ctx = null;
			try {
				ctx = new InitialContext(env);
			}
			finally {
				if (ctx != null)
					ctx.close();
			}
			valid = true;
		} catch (NamingException ne) {

			valid = false;
		}
		return valid;
	}

	public boolean checkValidUser(String userCheck, String userAdmin, String passAdmin) {
		boolean hasil = false;
		String username = userCheck;
		String password = passAdmin;	
		Hashtable<String, String> environment = new Hashtable<String, String>();
		environment.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		environment.put(Context.PROVIDER_URL, this.provideURL);
		environment.put(Context.SECURITY_AUTHENTICATION, "simple");
		String user = userAdmin + "@"+this.domain;
		environment.put(Context.SECURITY_PRINCIPAL, user);
		environment.put(Context.SECURITY_CREDENTIALS, password);
		try {
			DirContext context = new InitialDirContext(environment);

			String searchBase = this.schema;
			String FILTER = "(&(objectClass=user)(objectCategory=person)((sAMAccountName="
					+ username + ")))";
			SearchControls ctls = new SearchControls();
			ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			NamingEnumeration<SearchResult> answer = context.search(searchBase,
					FILTER, ctls);
			SearchResult result = answer.next();
			Attribute email = result.getAttributes().get("mail");
			Attribute cn = result.getAttributes().get("cn");
			System.out.println(cn + " : " + email);
			context.close();
			hasil = true;
		} catch (AuthenticationException a) {
//			System.out.println("Authentication failed: " + a.getExplanation());
			hasil = false;

		} catch (NamingException e) {
//			System.out.println("Failed to bind to LDAP: " + e.getExplanation());
//			e.printStackTrace();
			hasil = false;
		}
		return hasil;
	}

	public static void main(String args[]) {
		ActiveDirectoryManager adm = new ActiveDirectoryManager();
		System.out.println(adm.isAuthenticated("bonaventura.aap", "Ace2015")); //utk login
		System.out.println();
		System.out.println(adm.checkValidUser("dedy.suwandi", "bonaventura.aap", "Ace2015")); //utk cek user
	}

}