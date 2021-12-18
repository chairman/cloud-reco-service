package reco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reco.common.utils.R;
@RestController
@RequestMapping("/reco/exector/v1/oss")
public class OssController {

    @Autowired
    public MongoTemplate mongoTemplate;

    @RequestMapping("/getPolicy")
    public R getPolicy() {
        User user = new User();
        user.setId(100022L);
        user.setUserName("zhencw");
        user.setPassWord("zzzz");
        mongoTemplate.save(user);
        return R.ok().put("data", "");
    }
}
