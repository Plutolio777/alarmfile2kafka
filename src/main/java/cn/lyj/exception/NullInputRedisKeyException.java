package cn.lyj.exception;

public class NullInputRedisKeyException extends Exception{
    public NullInputRedisKeyException() {
    }

    public NullInputRedisKeyException(String message) {
        super(message);
    }
}
