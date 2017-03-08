package com.selfish.gene.collection.set.hashset;

/**
 * Created by Administrator on 2017/3/8.
 */
public class R {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
        {
            R r = (R) o;
            return count == r.count;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.count;
    }
}
