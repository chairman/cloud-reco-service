package reco.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reco.common.utils.R;
import reco.core.BuilderParam;
import reco.core.ReturnType;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reco/config/v1")
public class ConfigController {

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
