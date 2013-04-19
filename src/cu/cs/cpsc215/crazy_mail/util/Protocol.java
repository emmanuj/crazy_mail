/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cs.cpsc215.crazy_mail.util;

/**
 *
 * @author Hermoine
 */
public enum Protocol {
    /**
     * fields for holding protocol values
     */
    SMTP("smtp"),SMTPS("smtps"),IMAP("imap"), IMAPS("imaps"), POP3("pop3"), POP3S("pop3s");
    private String protocol;
    private Protocol(String protocol){
        this.protocol = protocol;
    }
    public String value(){
        return this.protocol;
    }
}
