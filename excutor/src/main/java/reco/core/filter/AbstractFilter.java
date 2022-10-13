package reco.core.filter;

import reco.core.Context;
import reco.core.Filter;

/**
 * 过滤器模版（Template Method）
 * @author Tommy.Z
 * @date 2022年10月13日 09:08
 */
public abstract class AbstractFilter implements Filter {

    /**
     * 对公共资源的初始化操作，例如mysql\redis链接初始化
     * @param context
     */
    @Override
    public void init(Context context){

    }

    /**
     * 对公共资源的初始化操作，例如关闭mysql\redis链接
     * @param context
     */
    @Override
    public void cleanup(Context context){

    }

}
