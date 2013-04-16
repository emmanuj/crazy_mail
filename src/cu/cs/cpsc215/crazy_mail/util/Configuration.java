
package cu.cs.cpsc215.crazy_mail.util;

import cu.cs.cpsc215.crazy_mail.mail.Protocol;
import java.io.Serializable;

/**
 *
 * @author Emmanuel John
 * Maintains configuration information about the user's system
 * 
 */
public class Configuration implements Serializable {
    private boolean auth;
    private Protocol outgoingmail;
    private Protocol incomingMail;
    private String host;
    private boolean debug;
    private int port;
    private boolean useTLS;
    private boolean useSSL;

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
