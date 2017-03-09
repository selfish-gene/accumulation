package com.selfish.gene.collection.queue;

import java.util.LinkedList;

/**
 * Created by Administrator on 2017/3/10.
 */
public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList tools = new LinkedList();
        // 将字符串元素加入队列的尾部
        tools.offer("everything");
        // 将一个字符串元素加入栈的顶部
        tools.push("idea");
        // 将字符串元素添加到队列的头部（相当于栈的顶部）
        tools.offerFirst("xmind");
        System.out.println(tools);
        // 访问、并不删除栈顶的元素
        System.out.println(tools.peekFirst());
        // 访问、并不删除队列的最后一个元素
        System.out.println(tools.peekLast());
        // 将栈顶的元素弹出“栈”
        System.out.println(tools.pop());
        // 下面输出将看到队列中第一个元素被删除
        System.out.println(tools);
        // 访问、并删除队列的最后一个元素
        System.out.println(tools.pollLast());
        System.out.println(tools);
    }
}
