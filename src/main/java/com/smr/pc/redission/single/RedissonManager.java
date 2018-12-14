package com.smr.pc.redission.single;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redisson
 *
 * @author qj
 * @date 2018/12/13
 */
@Configuration
public class RedissonManager {

    private final String REDIS_SINGLE_ADDRESS = "redis://127.0.0.1:6379";

    @Bean
    public RedissonClient getRedisson() throws Exception {

        RedissonClient redisson = null;
        Config config = new Config();
        // single
        config.useSingleServer()
                .setAddress(REDIS_SINGLE_ADDRESS)
                .setPassword(null);
        redisson = Redisson.create(config);


//        config.useClusterServers()
//                .setScanInterval(2000) // 集群状态扫描间隔时间，单位是毫秒
//                //可以用"rediss://"来启用SSL连接
//                .addNodeAddress("redis://192.168.123.83:6381")
//                .addNodeAddress("redis://192.168.123.83:6382")
//                .addNodeAddress("redis://192.168.123.83:6383");

        //success
        System.out.println(redisson.getConfig().toJSON().toString());

        return redisson;
    }

}
