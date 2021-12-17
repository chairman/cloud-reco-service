package reco.schedules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reco.core.FlowEntry;

import javax.annotation.Resource;

@Component
@EnableScheduling
public class SamplerFeedTask {
    private Logger logger = LoggerFactory.getLogger(SamplerFeedTask.class);

    @Resource
    private FlowEntry flowEntry;

    @Scheduled(cron = "0 2/10 * * * ?")
    public void sampler(){

    }
}
