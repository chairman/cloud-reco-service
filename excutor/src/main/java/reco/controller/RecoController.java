package reco.controller;

import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reco.core.BuilderParam;
import reco.core.FlowEntry;
import reco.core.RecoRequest;
import reco.core.ReturnType;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reco/novel/v1")
public class RecoController {

    @Resource
    private FlowEntry flowEntry;

    @RequestMapping(value="/fetchIds",method = RequestMethod.POST)
    @ResponseBody
    public ReturnType<Map<String, List<BuilderParam>>> fetchIds(
            @RequestBody RecoRequest recoRequest,
            ServletServerHttpResponse response
    ) {
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
