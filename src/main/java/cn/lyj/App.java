package cn.lyj;

import cn.lyj.configuer.plugin.StandardRedisStreamingPlugin;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        StandardRedisStreamingPlugin p = new StandardRedisStreamingPlugin();
        Thread t = new Thread(p);
        t.start();
        System.out.println(111);
    }
}
