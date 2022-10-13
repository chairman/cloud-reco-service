package reco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import reco.core.*;
import javax.annotation.Resource;
import java.util.Map;

/**
 * 推荐服务的入口
 * @author Tommy.Z
 * @date 2022年10月13日 09:08
 */
@RestController
@RequestMapping("/reco/works/v1")
public class RecoController {

    @Resource
    private FlowEntry flowEntry;
    @Autowired
    StringRedisTemplate redisTemplate;

    @RequestMapping(value="/fetchIds2",method = RequestMethod.GET)
    @ResponseBody
    public RecoResponse fetchIds2(
            @RequestParam Map<String, Object> params
    ) {
        RecoRequest recoRequest = new RecoRequest();
        recoRequest.setParams(params);
        Context context = new Context(recoRequest,redisTemplate);
//        redisTemplate.opsForValue().set("zxj","222222");
        return flowEntry.process(context);
    }
}
