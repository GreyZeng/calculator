package com.hui.calculator.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author zenghui
 */
public class BossRunnable implements Runnable {
    private static final Logger logger = LogManager.getLogger(BossRunnable.class);
    private CountDownLatch countDownLatch;

    public BossRunnable(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            // 主线程等待所有子线程执行完成，再向下执行
            countDownLatch.await(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            logger.error("timeout to generate the expression {}",e.getMessage());
        }
    }
}