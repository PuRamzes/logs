package by.puramzes.objects;

import java.util.Date;

/**
 *
 * @author pure
 */
public class Query {

    private String name;
    private Date date;
    private String patternMessage;

    public String getName() {
        return name;
    }

    public boolean isNameExist() {
        return name != null && name.length() > 0;
    }

    public boolean isDateExist() {
        return date != null;
    }

    public boolean isPatternMessageExist() {
        return patternMessage != null && patternMessage.length() > 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPatternMessage() {
        return patternMessage;
    }

    public void setPatternMessage(String patternMessage) {
        this.patternMessage = patternMessage;
    }

}
