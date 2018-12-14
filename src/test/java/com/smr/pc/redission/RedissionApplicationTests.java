package com.smr.pc.redission;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedissionApplicationTests {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void redisson() throws Exception {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        Runnable task = () -> {

            try {

                System.out.println(Thread.currentThread().getName() + "-> run start");

                RLock rLock = redissonClient.getLock("testLock");

                if (rLock.tryLock(5, TimeUnit.SECONDS)) {

                    System.out.println(Thread.currentThread().getName() + "-> get lock");

                    rLock.unlock();

                    Thread.sleep(10 * 1000);
                }

                System.out.println(Thread.currentThread().getName() + "-> run over");

            } catch (Exception e) {
                e.printStackTrace();
            }
        };


        for (int i = 0; i < 2; i++) {
            executor.submit(task);
        }

        Thread.sleep(100 * 1000);
    }

}

