package cn.lyj.configuer.plugin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class RunnablePlugin implements Runnable,Plugin{
    private static final Logger logger = LogManager.getLogger(RunnablePlugin.class);

    private ArrayList<ExceptionHandlePlugin> exceptionHandlers = new ArrayList<>();

    @Override
    public void work() {
        System.out.println("======================工作=============================");
    }

    @Override
    public void run() {
        try{
            initWork();
            necessityCheck();
            while (true){
                preWork();
                check();
                work();
                postWork();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        }catch (Exception exception){
            exceptionHandle(exception);
        }

    }

    public void necessityCheck() throws Exception{
    }

    public void exceptionHandle(Exception exception) {
        if (exceptionHandlers.size() == 0) {
            ExceptionHandlePlugin defaultExceptionHandler = new DefautltExceptionHandlePlugin();
            exceptionHandlers.add(defaultExceptionHandler);
            defaultExceptionHandler.handlerException(exception);
        }
    }

    public void initWork() {

        System.out.println("======================初始化工作=============================");
    }

    public void preWork() {
        System.out.println("======================前置处理=============================");
    }

    public void check() throws Exception {
        System.out.println("======================执行检查=============================");
    }

    public void postWork() {
        System.out.println("======================后置处理=============================");

    }
}
