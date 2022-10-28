package cn.lyj.configuer.plugin;

public class DefautltExceptionHandlePlugin extends ExceptionHandlePlugin{
    @Override
    public void handlerException(Exception exception) {
        exception.printStackTrace();
    }
}
