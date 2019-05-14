/**
 * unisinsight.com
 * Copyright (C) 2018-2019 All Rights Reserved.
 */
package com.ping.test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @version $Id ThreadRecordId.java, v 1.0 2019-05-07 16:48 zsp $$
 * @author: zhangsp
 */

public class ThreadRecordId implements Runnable {

    private static final int COUNTS = 100000;

    @Override
    public void run() {
        for (int i = 0; i < COUNTS; i++) {

            long recordIdPrimaryKey = MyTest.getRecordIdPrimaryKey(new Date().getTime());
            MyTest.result.add(recordIdPrimaryKey);
//            System.out.println("Thread.currentThread: " + Thread.currentThread().getName()+ ",recordId: " + MyTest.getRecordIdPrimaryKey(new Date().getTime()));
        }
    }
}
