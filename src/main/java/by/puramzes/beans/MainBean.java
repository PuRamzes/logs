package by.puramzes.beans;

import by.puramzes.objects.Filter;
import by.puramzes.objects.Log;
import by.puramzes.objects.LogManager;
import by.puramzes.objects.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author pure
 */
@Named
@SessionScoped
public class MainBean implements Serializable {

    private static final Integer typeDate = 1;
    private static final Integer typeName = 2;
    private static final Integer typeMessage = 3;

    private static Logger log = Logger.getLogger(MainBean.class.getName());
    private LogManager logManager = new LogManager();
    private Query query = new Query();
    private List<Log> data = new ArrayList<>();

    public MainBean() {
    }

    @PostConstruct
    public void start() {
        log.info("start");
        //logManager.getFile("logs/log1.txt");
        logManager.getAllLogs("logs");
        //SessionFactory dm = HibernateUtil.getSessionFactory();
    }

    public void load() {
        Stack st = new Stack();
        if (query.isDateExist()) {
            st.push(new Filter(query.getDate(), typeDate));
        }
        if (query.isNameExist()) {
            st.push(new Filter(query.getName(), typeName));
        }
        if (query.isPatternMessageExist()) {
            st.push(new Filter(query.getPatternMessage(), typeMessage));
        }

        for (int i = 1; !st.isEmpty(); i++) {
            if (i == 1) {
                filterData(logManager.getListLog(), st);
            }else{
              filterData(data, st);  
            }
        }

        addMessage();
    }

    private void filterData(Collection<Log> collect, Stack st) {
        Filter filter = (Filter) st.pop();
        switch (filter.getType()) {
            case 1:
                filterList(collect, t -> query.getDate().equals(t.getDate()));
                break;
            case 2:
                filterList(collect, t -> query.getName().toUpperCase().equals(t.getUserName().toUpperCase()));
                break;
            case 3:
                filterList(collect, t -> t.getMessage().toUpperCase().contains(query.getPatternMessage().toUpperCase()));
                break;
        }
    }

    private void filterList(Collection<Log> collect, Predicate<Log> pred) {
        data = collect.stream().filter(pred).collect(Collectors.toList());
    }

    public void addMessage() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful", "Load completed"));
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List<Log> getData() {
        return data;
    }

}
