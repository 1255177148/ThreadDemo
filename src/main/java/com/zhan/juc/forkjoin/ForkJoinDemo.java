package com.zhan.juc.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @Author Zhanzhan
 * @Date 2020/12/21 20:30
 */
public class ForkJoinDemo extends RecursiveTask<Integer> {

    private static final long serialVersionUID = 1L;

    private static final long THRESHOLD = 1000; // 临界值，达到此值就拆分任务

    private int start; // 子任务开始的边界
    private int end; // 子任务结束的边界

    public ForkJoinDemo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - start) <= THRESHOLD) { // 进行计算
            int sum = 0;
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else { // 拆分任务
            int mid = (start + end) / 2; // 将边界值一分为二，其实和归并排序的思想类似，也是种递归
            ForkJoinDemo task1 = new ForkJoinDemo(start, mid);
            ForkJoinDemo task2 = new ForkJoinDemo(mid + 1, end);
            invokeAll(task1, task2);
            return task1.join() + task2.join();
        }
    }
}
