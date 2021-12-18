package reco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reco.common.utils.R;
import reco.core.rule.dao.IdsRepository;

import javax.annotation.Resource;

@RestController
@RequestMapping("/reco/exector/v1/oss")
public class OssController {

    @Autowired
    public MongoTemplate mongoTemplate;

    @Resource
    private IdsRepository idsRepository;

    @RequestMapping("/getPolicy")
    public R getPolicy() {
        User user = new User();
        user.setId(100022L);
        user.setUserName("zhencw");
        user.setPassWord("zzzz");
        mongoTemplate.save(user);
        idsRepository.getNext("t_base_rules");
        return R.ok().put("data", "");
    }
}
