/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cs.cpsc215.crazy_mail.mail;

import java.io.File;

/**
 *
 * @Author Kevin Jett
 * @AuthorEmmanuel John
 */
public final class EmailAttachment {
    private String name;
    private String path;
    private File file;
    private String description;

    public EmailAttachment(File file) {
        this.file = file;
        this.setName(file.getName());
        this.setPath(file.getPath());
    }
    
    public EmailAttachment(String name, String path) {
        this(new File(path));
        this.name = name;
        this.path = path;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
