package reco.core.excutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reco.core.Context;

public class UvExcutor extends AbstractExcutor{
    private final Logger logger = LoggerFactory.getLogger(UvExcutor.class);
    private String id;
    private String type;

    public UvExcutor(String id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public void process(Context context) {

    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }
}
