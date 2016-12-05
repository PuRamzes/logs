package by.puramzes.objects;

/**
 *
 * @author pure
 */
public class Filter {

    private Object obj;
    private Integer type;

    public Filter(Object obj, Integer type) {
        this.obj = obj;
        this.type = type;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
