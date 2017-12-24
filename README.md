# calculator


## 开发环境
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3.5.0](http://mirrors.tuna.tsinghua.edu.cn/apache/maven/maven-3/3.5.0/binaries/apache-maven-3.5.0-bin.zip)

## 说明

- 配置类在Config.java中，配置选项包括
    1. "-n", "-number": 生成表达式的个数, 如 -n 10 表示生成10个表达式
    2. "-r", "-range": 控制题目中数值（自然数、真分数和真分数分母）的范围，该参数可以设置为1或其他自然数。该参数必须给定，否则程序报错并给出帮助信息
    3. "-hf": 有无分数
    4. "-md": 是否有乘除法
    5. "-hp": 是否有括号
    6. "-hn": 有无负数
    