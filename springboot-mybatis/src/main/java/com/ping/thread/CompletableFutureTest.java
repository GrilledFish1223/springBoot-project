package com.ping.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CompletableFutureTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompletableFutureTest.class);
    Random random = new Random();

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CompletableFutureTest(String name) {
        this.name = name;
    }

    public double getPrice(String product) {
        return calculaterice(product);
    }

    /**
     * 同步计算价格
     */
    private double calculaterice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 模拟计算
     */
    private static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOGGER.error("线程中断异常{}" + e.getMessage());
        }
    }

    /**
     * 异步计算价格
     */
    private Future<Double> getPriceAsync(String product) {
       /* CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(()-> {
           try {
               double price = calculaterice(product);
               futurePrice.complete(price);
           } catch (Exception e) {
               //否则就抛出异常,完成这次future操作
               futurePrice.completeExceptionally(e);
           }
        }).start();
        return futurePrice;*/
        return CompletableFuture.supplyAsync(() -> calculaterice(product));
    }

    private static List<CompletableFutureTest> shops = Arrays.asList(
            new CompletableFutureTest("A"),
            new CompletableFutureTest("B"),
            new CompletableFutureTest("C"),
            new CompletableFutureTest("D"));

    private static List<String> findPrice(String product) {
        return shops.stream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    /**
     * 使用平行流对请求进行并行操作
     *
     * @param product
     * @return
     */
    private static List<String> parallelFindPrices(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    /**
     * 最佳价格查询器(异步调用实现)
     *
     * @param product
     * @return
     */
    public static List<String> asyncFindPrices(String product) {
        List<CompletableFuture<String>> priceFuture = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))))
                .collect(Collectors.toList());

        return priceFuture.stream().map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100));

    /**
     * 最佳价格查询器(异步调用实现,自定义执行器)
     *
     * @param product
     * @return
     */
    public static List<String> asyncFindpricesThread(String product) {
        List<CompletableFuture<String>> priceFuture = shops
                .stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product), executor))
                .collect(Collectors.toList());

        return priceFuture.stream().map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
       /* CompletableFutureTest shop = new CompletableFutureTest("Base Product");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("product");
        long incocationTime = (System.nanoTime() -start)/1_1000_000;
        System.out.println("执行时间:" + incocationTime + " msesc");
        try {
            Double price = futurePrice.get();
            System.out.printf("Price is %.2f%n",price);
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("获取价格线程异常{}" + e.getMessage());
        }
        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("retrievalTime:" + retrievalTime + " msecs");*/

        long start = System.nanoTime();
        System.out.println(asyncFindpricesThread("myPhones27s"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

}
