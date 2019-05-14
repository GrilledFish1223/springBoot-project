/**
 * unisinsight.com
 * Copyright (C) 2018-2019 All Rights Reserved.
 */
package com.ping.test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @version $Id MyTest.java, v 1.0 2019-05-07 14:08 zsp $$
 * @author: zhangsp
 */

public class MyTest {
    public static Set<Long> result = new HashSet<>();

    private static final long SERVICE_NUM = 0;
    private static final long NODE_NUM = 6;
    //Node  编号 12bit
    private static final Integer SIXONE = 61;
    private static final Integer FOURNIGHT = 49;
    private static final Integer ONESEVEN = 17;
    private static final Integer THOUSAND = 1000;
    private static final Integer ONE_ONE = 131_071;

    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static long getRecordIdPrimaryKey(long time) {
        long recordId = 0;
        recordId = recordId | (SERVICE_NUM << SIXONE);
        recordId = recordId | (NODE_NUM << FOURNIGHT);

        long timeStamp = time / THOUSAND;
        recordId = recordId | (timeStamp << ONESEVEN);

        long randomCode = getSeqNum();
        recordId = recordId | randomCode;
        return recordId;
    }

    private static int getSeqNum() {
        int currInt = atomicInteger.getAndIncrement();
        while (currInt > ONE_ONE) {
            synchronized (MyTest.class) {
                if (atomicInteger.get() > ONE_ONE) {
                    atomicInteger = new AtomicInteger();
                }
            }
            currInt = atomicInteger.getAndIncrement();
        }
        return currInt;
    }

    public static void main(String[] args) {
//        long recordId = getRecordIdPrimaryKey(new Date().getTime());
//        System.out.println(recordId);
        for (int i = 0; i < 100; i++) {
            new Thread(new ThreadRecordId()).start();
        }
    }
}
