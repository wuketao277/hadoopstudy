1、这是一个maven构建的hadoop项目，在pom.xml文件中添加2个依赖hadoop-common和hadoop-mapreduce-client-core。
2、为了让生成的jar包能够直接通过hadoop jar的方式运行，需要在pom.xml文件中增加插件maven-jar-plugin的配置，定义好主类。
3、代码中只需要分别继承Mapper、Reducer类，并重写map、reduce函数。
    3.1、map、reduce函数的参数都是key、value、context。其中key、value表示从文件或数据库中读取的输入；context表示数据输出的上下文。
    3.2、需要注意的是，在数据输出到context中时，需要转换为hadoop的数据结构。
    3.3、hadoop mapreduce框架是一个key-value作为输入、输出的框架。不论map还是reduce，输入和输出都是key-value。
4、在主类中定义好job的map类和reduce类