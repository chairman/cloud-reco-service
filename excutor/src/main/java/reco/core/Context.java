package reco.core;

import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 上下文
 * @author Tommy.Z
 * @date 2022年10月13日 09:08
 */
public class Context {
    private State state;
    RecoRequest recoRequest;
    RecoResult recoResult;
    private Executor executor;
    StringRedisTemplate redisTemplate;

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public Context(RecoRequest recoRequest,StringRedisTemplate redisTemplate) {
        this.state = new State(recoRequest.params);
        this.recoRequest = recoRequest;
        this.redisTemplate = redisTemplate;
    }

    public void setState(State state) {
        this.state = state;
    }

    public RecoRequest getRecoRequest() {
        return recoRequest;
    }

    public void setRecoRequest(RecoRequest recoRequest) {
        this.recoRequest = recoRequest;
    }

    public RecoResult getRecoResult() {
        return recoResult;
    }

    public void setRecoResult(RecoResult recoResult) {
        this.recoResult = recoResult;
    }

    public State getState(){
        return state;
    }
}
