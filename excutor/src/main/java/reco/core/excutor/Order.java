package reco.core.excutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reco.core.Context;
import reco.core.Executor;

public class Order extends AbstractExcutor{
    private final Logger logger = LoggerFactory.getLogger(Order.class);

    private Executor[] executors;

    private String id;

    private String type;

    public Order(String id,String type,Executor[] executors) {
        this.id = id;
        this.type = type;
        this.executors = executors;
    }

    @Override
    public void init(Context context){
        if(executors!=null){
            for (Executor executor:executors){
                executor.init(context);
            }
        }
    }

    @Override
    public void cleanup(Context context){
        if(executors!=null){
            for (Executor executor:executors){
                executor.cleanup(context);
            }
        }
    }

    @Override
    public void process(Context context) {
        if(executors!=null){
            for (Executor executor:executors){
                executor.process(context);
            }
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    static {
    }
}
