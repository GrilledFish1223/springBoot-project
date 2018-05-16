package com.ping.springboot.xml.noScan;

import org.springframework.stereotype.Service;

/**
 * @author: Zhangsp
 * @param:
 * @date: 2018/5/16 15:14
 */
@Service
public class NoScanService {
    public NoScanService() {
        System.out.println("I am NoScanService,i can not be scan");
    }
}
