package com.selfish.gene.keywords.final_;

/**
 * Created by Administrator on 2017/5/17.
 */
public class CacheImmutableTest {

    private static int MAX_SIZE = 16;
    // 使用数组来缓存已有的实例
    private static CacheImmutableTest[] cache = new CacheImmutableTest[MAX_SIZE];
    // 记录缓存实例在缓存中的位置,cache[pos-1]是最新缓存的实例
    private static int pos = 0;

    private final String name;//不可变类设计规则第1点

    private CacheImmutableTest(String name){//不可变类设计规则第2点
        this.name = name;
    }

    public String getName() {//不可变类设计规则第3点
        return name;
    }

    public static CacheImmutableTest valueOf(String name)
    {
        // 遍历已缓存的对象，
        for(int i = 0; i < MAX_SIZE; i++){
            // 如果已有相同实例，直接返回该缓存的实例
            if(cache[i] != null && cache[i].getName().equals(name))
            {
                return cache[i];
            }
        }
        // 如果缓存池已满
        if(pos == MAX_SIZE)
        {
            // 把缓存的第一个对象覆盖，即把刚刚生成的对象放在缓存池的最开始位置。
            cache[0] = new CacheImmutableTest(name);
            // 把pos设为1
            pos = 1;
        }
        else
        {
            // 把新创建的对象缓存起来，pos加1
            cache[pos++] = new CacheImmutableTest(name);
        }
        return cache[pos - 1];
    }

    //不可变类设计规则第4点
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
        {
            return true;
        }
        if(obj != null && obj.getClass() == CacheImmutableTest.class)
        {
            CacheImmutableTest ci = (CacheImmutableTest) obj;
            return name.equals(ci.getName());
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        CacheImmutableTest c1 = CacheImmutableTest.valueOf("hello");
        CacheImmutableTest c2 = CacheImmutableTest.valueOf("hello");
        System.out.println(c1 == c2);   //true
    }
}
