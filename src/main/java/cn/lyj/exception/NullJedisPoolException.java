package cn.lyj.exception;

public class NullJedisPoolException extends Exception{
    public NullJedisPoolException() {
    }

    public NullJedisPoolException(String message) {
        super(message);
    }
}
