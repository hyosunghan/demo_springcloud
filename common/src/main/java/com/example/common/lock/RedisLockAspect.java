package com.example.common.lock;

import com.example.common.lock.impl.ExecuteLock;
import com.example.common.lock.impl.WaitLock;
import com.example.common.utils.AnnotationUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Aspect
@Component
public class RedisLockAspect {

    @Autowired
    private ExecuteLock executeLock;

    @Autowired
    private WaitLock waitLock;

    @Pointcut(value = "@annotation(com.example.common.lock.RedisLock)")
    public void pointCut() {}

    @Around(value = "pointCut() && @annotation(redisLock)")
    public Object around(ProceedingJoinPoint point, RedisLock redisLock) throws Throwable {
        LockInfo lockInfo = getLockInfo(point, redisLock);
        switch (redisLock.type()) {
            case EXECUTE:
                return executeLock.proceed(point, lockInfo);
            default:
                return waitLock.proceed(point, lockInfo);
        }
    }

    private LockInfo getLockInfo(ProceedingJoinPoint point, RedisLock redisLock) {
        LockInfo lockInfo = new LockInfo();
        lockInfo.setFrequency(redisLock.frequency());
        lockInfo.setLeaseTime(redisLock.leaseTime());
        lockInfo.setLockValue(redisLock.value());
        lockInfo.setWaitTime(redisLock.waitTime());
        lockInfo.setLockName(getKeyName(point, redisLock));
        return lockInfo;
    }

    private static String getKeyName(ProceedingJoinPoint joinPoint, RedisLock redisLock) {
        List<String> serviceKeys = AnnotationUtil.getSpElDefinitionKey(joinPoint, redisLock.keys());
        String listStr = StringUtils.collectionToDelimitedString(serviceKeys,"",":","");
        return redisLock.name() + listStr;
    }

}
