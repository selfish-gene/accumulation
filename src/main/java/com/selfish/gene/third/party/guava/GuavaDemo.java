package com.selfish.gene.third.party.guava;

import com.beust.jcommander.internal.Nullable;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import com.selfish.gene.models.User;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/3/27.
 */
public class GuavaDemo {

    public static final ImmutableSet<String> COLOR_NAMES = ImmutableSortedSet.of("red", "orange", "yellow", "green", "blue");

    @Test
    public void test() throws Exception{
        Optional<Integer> possible = Optional.of(5);
        assertTrue(possible.isPresent());
        assertThat(possible.get(), is(5));
    }

    public int compareTo(User user1, User user2){
        // ComparisonChain执行一种懒比较：它执行比较操作直至发现非零的结果，在那之后的比较输入将被忽略
        return ComparisonChain.start()
                .compare(user1.getName(), user2.getName())
                .compare(user1.getAge(), user2.getAge())
                .result();
    }

    @Test
    public void testOrdering() throws Exception{
        Ordering<String> byLengthOrdering = new Ordering<String>() {
            @Override
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };
        assertThat(byLengthOrdering.compare("aaaaa", "bb"), is(1));
        assertThat(byLengthOrdering.compare("aa", "bbhhh"), is(-1));

        Ordering<Foo> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, String>() {
            @Override
            public String apply(Foo foo) {
                return foo.sortedBy;
            }
        });
        Foo f1 = new Foo();
        f1.setSortedBy(null);
        Foo f2 = new Foo();
        f2.setSortedBy("a");
        assertThat(ordering.compare(f1, f2), is(-1));
    }

    @Test
    public void testMultiMap() throws Exception{
        Multimap<String, StudentScore> multimapList = ArrayListMultimap.create();
        for (int i = 0; i < 20; i++){
            StudentScore studentScore = new StudentScore();
            studentScore.courseId = 100 + i;
            studentScore.score = 100 - i;
            multimapList.put("name", studentScore);
        }
        System.out.println(multimapList);
        System.out.println(multimapList.size());
        System.out.println(multimapList.keys());


        System.out.println("*************************************");
        Multimap<String, StudentScore> multimapSet = HashMultimap.create();
        for (int i = 0; i < 10; i++){
            StudentScore studentScore = new StudentScore();
            studentScore.courseId = 100 + i;
            studentScore.score = 100 - i;
            multimapSet.put("name", studentScore);
        }
        System.out.println(multimapSet);
        System.out.println(multimapSet.size());
        System.out.println(multimapSet.keys());

        Collection<StudentScore> name = multimapList.get("name");
        name.clear();
        System.out.println(name);
    }

    @Test
    public void testMultiset() throws Exception{
        String s = "abtsbfacsaadfcd";
        char[] chars = s.toCharArray();
        List<Character> list = new ArrayList<>();
        for (char c: chars) {
            list.add(c);
        }
        Multiset<Character> multiset = HashMultiset.create();
        multiset.addAll(list);
        multiset.elementSet().stream().forEach(c ->  System.out.println(c + " = " + multiset.count(c)));
        multiset.add('r', 10);
        System.out.println(multiset);
        multiset.setCount('b', 5);
        System.out.println(multiset);
        multiset.remove('r', 3);
        System.out.println(multiset);
    }

    class StudentScore{
        int courseId;
        int score;
    }

    class Foo{

        Set<Bar> bars;
        Foo(){
        }

        Foo(Set<Bar> bars){
            this.bars = ImmutableSet.copyOf(bars);
        }

        @Nullable String sortedBy;
        int notSortedBy;

        public String getSortedBy() {
            return sortedBy;
        }

        public void setSortedBy(String sortedBy) {
            this.sortedBy = sortedBy;
        }

        public int getNotSortedBy() {
            return notSortedBy;
        }

        public void setNotSortedBy(int notSortedBy) {
            this.notSortedBy = notSortedBy;
        }

        @Override
        public String toString() {
            return "Foo{" +
                    "sortedBy='" + sortedBy + '\'' +
                    ", notSortedBy=" + notSortedBy +
                    '}';
        }
    }

    class Bar{

    }
}
