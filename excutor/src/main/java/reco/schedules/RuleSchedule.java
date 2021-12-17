package reco.schedules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reco.core.Rules;

@Component
public class RuleSchedule {

    @Autowired
    private Rules rules;

    @Scheduled(cron = "*/10 * * * * ?")
    public void run(){
        rules.update();
    }

}
