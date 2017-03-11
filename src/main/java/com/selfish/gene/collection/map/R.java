package com.selfish.gene.collection.map;

/**
 * Created by Administrator on 2017/3/11.
 */
public class R  implements Comparable{

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
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (obj != null && obj.getClass() == R.class){
            R r = (R)obj;
            return this.count == r.count;
        }
        return false;
    }


    @Override
    public int compareTo(Object o) {
        R r = (R) o;
        return count > r.count ? 1 :count < r.count ? -1 :0;
    }
}
