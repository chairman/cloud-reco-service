package reco.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlowEntry {
    private final Logger logger = LoggerFactory.getLogger(FlowEntry.class);

    @Autowired
    private Rules rules;

    public RecoResponse process(Context context){
        try {

            return null;
        }catch (Exception e){
            return null;
        }
    }
}
