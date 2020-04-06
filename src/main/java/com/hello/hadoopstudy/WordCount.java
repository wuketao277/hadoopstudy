package com.hello.hadoopstudy;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.hello.hadoopstudy.mapreduce.WCMapper;
import com.hello.hadoopstudy.mapreduce.WCReduce;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author wuketao
 * @date 2020/4/6
 * @Description
 */
public class WordCount {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length == 3) {
            System.out.println("job begin");
            // 创建job对象
            Job job = Job.getInstance(new Configuration());
            // 指定程序的入口
            if ("WordCount".equals(args[0])) {
                job.setJarByClass(WordCount.class);

                // 指定自定义的Mapper阶段的任务处理类
                job.setMapperClass(WCMapper.class);
                job.setMapOutputKeyClass(Text.class);
                job.setMapOutputValueClass(LongWritable.class);
                // 数据HDFS文件服务器读取数据路径
                FileInputFormat.setInputPaths(job, new Path(args[1]));

                // 指定自定义的Reducer阶段的任务处理类
                job.setReducerClass(WCReduce.class);
                // 设置最后输出结果的Key和Value的类型
                job.setOutputKeyClass(Text.class);
                job.setOutputValueClass(LongWritable.class);
                // 将计算的结果上传到HDFS服务
                FileOutputFormat.setOutputPath(job, new Path(args[2]));

                // 执行提交job方法，直到完成，参数true打印进度和详情
                job.waitForCompletion(true);
            } else {
                System.out.println("progressentry error");
            }
            System.out.println("job end");
        } else {
            System.out.println("job args:" + StringUtils.join(args, " "));
            System.out.println("job format: hadoop jar xxxx.jar progressentry inputpath outputpath");
        }

    }
}
