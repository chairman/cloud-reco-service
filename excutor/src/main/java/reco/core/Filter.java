package reco.core;

public interface Filter {
    boolean filter(Object id);
    boolean[] preFilter(Object[] id);
    void init(Context context);
    String getId();
    String getType();
    void cleanup(Context context);
}
