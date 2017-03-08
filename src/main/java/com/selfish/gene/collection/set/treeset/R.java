package com.selfish.gene.collection.set.treeset;

/**
 * Created by Administrator on 2017/3/8.
 */
public class R implements Comparable {

    int count;

    public R(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "R{" +
                "count=" + count +
                '}';
    }

    // 重写equals方法,根据count来判断是否相等
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj != null && obj.getClass() == R.class){
            R r = (R)obj;
            return r.count == this.count;
        }
        return false;
    }

    // 重写compareTo,根据count来比较大小
    @Override
    public int compareTo(Object o) {
        R r = (R) o;
        return count > r.count ? 1 :
                count < r.count ? -1 : 0;
    }
}
