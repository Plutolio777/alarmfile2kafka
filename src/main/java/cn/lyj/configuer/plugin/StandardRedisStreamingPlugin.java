package cn.lyj.configuer.plugin;

import cn.lyj.exception.NullInputRedisKeyException;
import cn.lyj.exception.NullJedisPoolException;
import cn.lyj.redis.RedisSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.ArrayList;

public class StandardRedisStreamingPlugin extends ContextPlugin implements RedisSupport {
    private static final Logger logger = LogManager.getLogger(StandardRedisStreamingPlugin.class);
    private String inputKey="test";
    private JedisPool pool = new JedisPool();


    private ArrayList<DataProcessPlugin> formatList;

    @Override
    public void work() {
        //
        System.out.println("StandardRedisStreamingPlugin start working");
        ArrayList<String> content = listenRedis();
        System.out.println("get data:" + content);
        System.out.println("StandardRedisStreamingPlugin ending working");
    }



    public void necessityCheck() throws Exception {
        System.out.println("======================执行检查=============================");
        if (getPool()==null){
            System.out.println("未配置redis,请重试");
            throw new NullJedisPoolException(" [Please configure Redis connection pool]");
        }else if(inputKey==null){
            throw new NullInputRedisKeyException(" [Please set Redis input key]");
        }
        try{
            Jedis jedis = pool.getResource();
        }catch (JedisConnectionException exception){
            throw new JedisConnectionException(" [Could not get a resource from the pool]");
        }
    }

    @Override
    public ArrayList<String> listenRedis() {
        ArrayList<String> list = new ArrayList<>();
        String content = null;
        try (Jedis jedis = pool.getResource()) {
            if (jedis.exists(inputKey)) {
                while (jedis.llen(inputKey) > 0) {
                    content = jedis.rpop(inputKey);
                    if (content!=null) {
                        list.add(content);
                    }
                }

            }
        } catch (Exception e) {
            logger.error(e.getMessage());

        }
        return list;
    }





    public String getInputKey() {
        return inputKey;
    }

    public void setInputKey(String inputKey) {
        this.inputKey = inputKey;
    }

    public JedisPool getPool() {
        return pool;
    }

    public void setPool(JedisPool pool) {
        this.pool = pool;
    }

    public ArrayList<DataProcessPlugin> getFormatList() {
        return formatList;
    }

    public void setFormatList(ArrayList<DataProcessPlugin> formatList) {
        this.formatList = formatList;
    }


}
