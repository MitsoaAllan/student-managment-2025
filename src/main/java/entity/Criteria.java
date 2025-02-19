package entity;

public class Criteria {
    private String column;
    private String value;
    private boolean asc;
    private String orderBy;

    public Criteria(String column, String value, String orderBy, boolean asc) {
        this.column = column;
        this.value = value;
        this.asc = asc;
        this.orderBy = orderBy;
    }

    public boolean isAsc() {
        return asc;
    }

    public void setAsc(boolean asc) {
        this.asc = asc;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
