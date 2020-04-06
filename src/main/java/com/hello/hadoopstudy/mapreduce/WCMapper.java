package com.hello.hadoopstudy.mapreduce;

import com.google.common.base.Strings;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author wuketao
 * @date 2020/4/6
 * @Description
 */
public class WCMapper extends Mapper {

    @Override
    protected void map(Object key, Object value, Context context) throws IOException, InterruptedException {
        if (!Strings.isNullOrEmpty(value.toString())) {
            String[] words = value.toString().split(" ");
            for (String word : words) {
                context.write(new Text(word), new LongWritable(1));
            }
        }
    }
}
