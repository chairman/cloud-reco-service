package reco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reco.core.BuilderParam;
import reco.core.ReturnType;
import reco.core.rule.dao.IdsRepository;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 规则配置获取接口
 * @author Tommy.Z
 * @date 2022年10月13日 09:08
 */
@RestController
@RequestMapping("/reco/config/v1")
public class ConfigController {

    @Resource
    private IdsRepository userRepository;
    @Autowired
    public MongoTemplate mongoTemplate;

    @RequestMapping(value="/executors",method = RequestMethod.GET)
    @ResponseBody
    public ReturnType<Map<String, List<BuilderParam>>> executors() {
        try {
            return new ReturnType<Map<String, List<BuilderParam>>>(-1,null,null);
        }catch (Exception e){
            return new ReturnType<Map<String, List<BuilderParam>>>(-1,e.getMessage());
        }
    }

    @RequestMapping(value="/selectors",method = RequestMethod.GET)
    @ResponseBody
    public ReturnType<Map<String, List<BuilderParam>>> selectors() {
        try {
            return new ReturnType<Map<String, List<BuilderParam>>>(-1,null,null);
        }catch (Exception e){
            return new ReturnType<Map<String, List<BuilderParam>>>(-1,e.getMessage());
        }
    }
}
