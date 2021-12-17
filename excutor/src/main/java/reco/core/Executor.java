package reco.core;

public interface Executor {
    void init(Context context);
    void process(Context context);
    String getId();
    String getType();
    void cleanup(Context context);
}
