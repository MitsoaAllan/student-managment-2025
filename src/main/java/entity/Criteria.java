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

    public String getOrderBy() {
        return orderBy;
    }

    public String getColumn() {
        return column;
    }

    public String getValue() {
        return value;
    }
}
