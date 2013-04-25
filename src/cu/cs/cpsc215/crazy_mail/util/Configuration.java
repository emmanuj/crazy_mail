
package cu.cs.cpsc215.crazy_mail.util;

import java.io.Serializable;

/**
 *
 * @author Emmanuel John
 * Maintains configuration information about the user's system
 * 
 */
public class Configuration implements Serializable {
    private boolean auth = true;

	private static final long serialVersionUID = 1228598296735827634L;

    private Protocol outgoingMail;
    private Protocol incomingMail;
    private String host;
    private boolean debug = true;
    private int port = 465;
    private boolean useTLS = true;
    private boolean useSSL = true;
    private String inHost;

    public String getInHost() {
        return inHost;
    }

    public void setInHost(String inHost) {
        this.inHost = inHost;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public Protocol getOutgoingMail() {
        return outgoingMail;
    }

    public void setOutgoingMail(Protocol outgoingmail) {
        this.outgoingMail = outgoingmail;
    }

    public Protocol getIncomingMail() {
        return incomingMail;
    }

    public void setIncomingMail(Protocol incomingMail) {
        this.incomingMail = incomingMail;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isUseTLS() {
        return useTLS;
    }

    public void setUseTLS(boolean useTLS) {
        this.useTLS = useTLS;
    }

    public boolean isUseSSL() {
        return useSSL;
    }

    public void setUseSSL(boolean useSSL) {
        this.useSSL = useSSL;
    }
    
    @Override  
    public boolean equals(Object obj)
    {
    	if (obj == null) { 
    		return false;
    	}  
        if (getClass() != obj.getClass()) { 
        	return false;
        }  
        Configuration other = (Configuration)obj;
        
    	boolean protocolsEqual,sslTlsEqual;
    	protocolsEqual = ( outgoingMail.equals(other.getOutgoingMail()) && incomingMail.equals(other.getIncomingMail()));
    	sslTlsEqual = (useTLS == other.isUseTLS()) && (useSSL == other.isUseSSL());
    	
    	return(host.equals(other.getHost()) && port == other.getPort() && protocolsEqual && sslTlsEqual);

    }
    
    
    
}
