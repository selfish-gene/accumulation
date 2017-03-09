package com.selfish.gene.collection.queue;

import java.util.PriorityQueue;

/**
 * Created by Administrator on 2017/3/10.
 */
public class PriorityQueueDemo {
    public static void main(String[] args) {
        // PriorityQueue的排序规则基本与TreeSet相似
        PriorityQueue pq = new PriorityQueue();
        pq.offer(5);
        pq.offer(-3);
        pq.offer(2);
        pq.offer(10);
        // 输出pq队列，并不是按元素的加入顺序排列
        System.out.println(pq);
        // 访问队列第一个元素并删除，其实就是队列中最小的元素：-3，而不是第一个添加的元素5
        System.out.println(pq.poll());
        System.out.println(pq);
    }
}
