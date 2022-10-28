package cn.lyj.configuer.plugin;

import cn.lyj.context.Context;

public class ContextPlugin extends RunnablePlugin{

    protected Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
