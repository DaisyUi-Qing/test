package com.daisy.demo.service.impl;

import com.daisy.demo.asyn.GPerson;
import com.daisy.demo.dao.UserMapper;
import com.daisy.demo.dto.UserDTO;
import com.daisy.demo.entity.User;
import com.daisy.demo.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author dxm
 */
@Service
public record UserServiceImpl(UserMapper userMapper) implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void add() {
        long start = System.currentTimeMillis();
        int pool = Runtime.getRuntime().availableProcessors();
        logger.info("可用核心：{}",pool);
        ExecutorService service = Executors.newFixedThreadPool(pool);
        GPerson gPerson = new GPerson();
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 1000000; i+= 10000) {
            int a=i;
            Callable<List<User>> task = () -> {
                List<User> subUser = new ArrayList<>();
                for (int j = a; j < a + 10000; j++) {
                    subUser.add(gPerson.getPerson());
                }
                return subUser;
            };
            Future<List<User>> submit = service.submit(task);
            try {
                userList.addAll(submit.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

        }
        service.shutdown();
        try {
            service.awaitTermination(1,TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        拆分数据 多线程插入
        ExecutorService service1 = Executors.newFixedThreadPool(40);
        int i = userList.size() / 40;

        List<List<User>> sub= new ArrayList<>();
        for (int j = 0; j < 40; j++) {
            int fromIndex = j * i;
            int toIndex = (j == 40 - 1) ? userList.size() : (j + 1) * i;
            sub.add(userList.subList(fromIndex, toIndex));
        }

        CountDownLatch latch = new CountDownLatch(40);

        for (List<User> users : sub) {
            service1.execute(()->{
                userMapper.insertBatch(users);
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        service1.shutdown();
        long end = System.currentTimeMillis();
        logger.info("耗时：{} 秒",(end-start)/1000);
        logger.info("执行完成");

    }

    @Override
    public List<User> all(UserDTO user) {
       return userMapper.all(user);
    }

    @Override
    public int count(UserDTO user) {
        return userMapper.count(user);
    }
}
