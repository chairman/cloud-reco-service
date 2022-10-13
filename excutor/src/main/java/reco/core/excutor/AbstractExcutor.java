package reco.core.excutor;

import reco.core.Context;
import reco.core.Executor;

/**
 * 执行器的模版（Template Method）
 * @author Tommy.Z
 * @date 2022年10月13日 09:08
 */
public abstract class AbstractExcutor implements Executor {
    /**
     * 对公共资源的初始化操作，例如资源的初始化
     * @param context
     */
    @Override
    public void init(Context context){

    }

    /**
     * 对公共资源的清理，例如资源的关闭操作
     * @param context
     */
    @Override
    public void cleanup(Context context){

    }
}
