package com.hello.hadoopstudy.mapreduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author wuketao
 * @date 2020/4/6
 * @Description
 */
public class WCReduce extends Reducer {
    @Override
    protected void reduce(Object key, Iterable values, Context context) throws IOException, InterruptedException {
        Integer sum = 0;
        for (Object o : values) {
            sum += Integer.valueOf(o.toString());
        }
        context.write(new Text(key.toString()), new LongWritable(sum));
    }
}
