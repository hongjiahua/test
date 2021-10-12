package org.gnnu.test;

import java.util.*;

class ListNode {
    int val;
    ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 删除链表的倒数第 n 个结点，并且返回链表的头结点
     *
     * @param n int整型 倒数第n个
     * @return ListNode类
     */
    public ListNode removeNthFromEnd(ListNode node, int n) {
        // write code here

        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Date(System.currentTimeMillis()));
        Solution solution = new Solution();
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        ListNode result = solution.removeNthFromEnd(node, 2);
        System.out.println(new Date(System.currentTimeMillis()));
        System.out.println(result);
    }
}