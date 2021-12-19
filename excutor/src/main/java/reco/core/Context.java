package reco.core;

public class Context {
    private State state;
    RecoRequest recoRequest;
    RecoResult recoResult;
    private Executor executor;

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public Context(RecoRequest recoRequest) {
        this.state = new State(recoRequest.params);
        this.recoRequest = recoRequest;
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
