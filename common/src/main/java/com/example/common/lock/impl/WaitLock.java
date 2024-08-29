package com.example.common.lock.impl;

import com.example.common.lock.Lock;
import com.example.common.lock.LockInfo;
import com.example.common.lock.RedisLockCallable;
import com.example.common.utils.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Component
public class WaitLock implements Lock {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Override
    public Object proceed(ProceedingJoinPoint point, LockInfo lockInfo) throws Throwable  {
        String lockKey = lockInfo.getLockName();

        // 获取事务锁，如果在配置的时间内没有获取到事务锁，则抛出异常
        RedisLockCallable callable = new RedisLockCallable(stringRedisTemplate, lockInfo);
        try {
            Future<Boolean> future = taskExecutor.submit(callable);
            // 如果没有超时，则表示获取到事务锁了，其他线程已经执行完毕，可以执行回调处理了
            future.get(lockInfo.getWaitTime(), TimeUnit.SECONDS);
            try {
                // 处理业务
                return point.proceed();
            } catch (Exception e) {
                throw e;
            } finally {
                RedisHelper.expire(stringRedisTemplate, lockKey);
            }
        } catch (TimeoutException e) {
            //定义超时后的状态修改
            log.error("获取事务锁超时");
            throw new RuntimeException("get lock timeout");
        } catch (Exception e) {
            throw e;
        } finally {
            callable.shutdown();
        }
    }

}
