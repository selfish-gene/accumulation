package com.selfish.gene.collection.queue;

import java.util.ArrayDeque;

/**
 * Created by Administrator on 2017/3/10.
 */
public class ArrayDequeDemo {
    public static void main(String[] args) {
        // 作为stack使用
        ArrayDeque stack = new ArrayDeque();
        stack.push("Java");
        stack.push("Maven");
        stack.push("Git");
        stack.push("Bazel");
        // 采用push则是后进先出
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);

        System.out.println("******************");

        // 作为queue使用
        ArrayDeque queue = new ArrayDeque();
        queue.offer("Java");
        queue.offer("Maven");
        queue.offer("Git");
        queue.offer("Bazel");
        // 采用offer则是先进先出
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue);
    }
}
