package reco.core;

import java.util.Map;

public class RecoRequest {
    Map<String,Object> params;
    public RecoRequest() {
    }
    public RecoRequest(Map<String, Object> params) {
        this.params = params;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
