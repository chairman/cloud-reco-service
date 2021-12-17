package reco.core;

public class Rule {
    private int id;
    private String description;
    private ParameterizedExecutor executor;
    private Selector selector;
    private int order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ParameterizedExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(ParameterizedExecutor executor) {
        this.executor = executor;
    }

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
