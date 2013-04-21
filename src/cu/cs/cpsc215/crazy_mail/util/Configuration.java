
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

    private Protocol outgoingmail;
    private Protocol incomingMail;
    private String host;
    private boolean debug = true;
    private int port = 465;
    private boolean useTLS = true;
    private boolean useSSL = true;

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public Protocol getOutgoingmail() {
        return outgoingmail;
    }

    public void setOutgoingmail(Protocol outgoingmail) {
        this.outgoingmail = outgoingmail;
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
    
    
    
}
