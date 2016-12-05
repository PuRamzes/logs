package by.puramzes.objects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author pure
 */
public class Log {

    private String userName;
    private Date date;
    private String message;

    public Log() {
    }

    public Log(String userName, Date date, String message) {
        this.userName = userName;
        this.date = date;
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @param date
     * @param pattern (MM/dd/yyyy hh:mm:ss)
     * @return
     */
    public String getDateToString(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

}
