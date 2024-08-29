package com.example.common.lock;

import org.aspectj.lang.ProceedingJoinPoint;

public interface Lock {

    Object proceed(ProceedingJoinPoint point, LockInfo lockInfo) throws Throwable ;

}
