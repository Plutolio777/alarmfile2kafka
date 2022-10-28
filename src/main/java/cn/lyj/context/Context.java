package cn.lyj.context;

import java.util.concurrent.atomic.AtomicBoolean;

public class Context {
    private volatile AtomicBoolean testSwitch = new AtomicBoolean();
}
