package reco.core;

public class ReturnType<T> {
    public static final ReturnType<String> OK = new ReturnType<String>(0,"OK");
    private int retCd;
    private String retMsg;
    private T data;
    public ReturnType(T data){
        this.retCd = 0;
        this.retMsg = "OK";
        this.data = data;
    }

    public ReturnType(int retCd,String retMsg){
        this.retCd = retCd;
        this.retMsg = retMsg;
    }

    public ReturnType(int retCd,String retMsg,T data){
        this.retCd = retCd;
        this.retMsg = retMsg;
        this.data = data;
    }

    public int getRetCd() {
        return retCd;
    }

    public void setRetCd(int retCd) {
        this.retCd = retCd;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
