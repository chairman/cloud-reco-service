package reco.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reco.common.utils.R;
@RestController
@RequestMapping("/reco/exector/v1/oss")
public class OssController {


    @RequestMapping("/getPolicy")
    public R getPolicy() {
        return R.ok().put("data", "");
    }
}
