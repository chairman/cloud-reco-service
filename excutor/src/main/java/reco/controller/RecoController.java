package reco.controller;

import org.springframework.web.bind.annotation.*;
import reco.core.*;
import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/reco/works/v1")
public class RecoController {

    @Resource
    private FlowEntry flowEntry;

    @RequestMapping(value="/fetchIds",method = RequestMethod.POST)
    @ResponseBody
    public RecoResponse fetchIds(
            @RequestBody Map<String, Object> params
    ) {
        RecoRequest recoRequest = new RecoRequest();
        recoRequest.setParams(params);
        Context context = new Context(recoRequest);
        return flowEntry.process(context);
    }

    @RequestMapping(value="/fetchIds2",method = RequestMethod.GET)
    @ResponseBody
    public RecoResponse fetchIds2(
            @RequestParam Map<String, Object> params
    ) {
        RecoRequest recoRequest = new RecoRequest();
        recoRequest.setParams(params);
        Context context = new Context(recoRequest);
        return flowEntry.process(context);
    }
}
