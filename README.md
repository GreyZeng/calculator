[题目要求](http://www.cnblogs.com/jiel/p/4810756.html)

## update 2018-04-12

- 增加测试功能(Beta)

输入四则运算的规则和表达式，测试程序可以自动判断表达式是否符合规则

输入输出示例：

输入：
```json
{
  "config": {
    "numberOfExpression": 30,
    "range": 30,
    "hasFraction": false,
    "hasMultipleAndDivide": true,
    "hasParentheses": true
  },
  "expressions": {
    "3 × ( 3 + 3 )": "18",
    "( 3 + 3 )": "6"
  }
}
```
输入参数说明：
在config中：
- numberOfExpression: 表达式的个数
- range: 表达式中操作数的范围
- hasFraction: 是否包含分数（true:是，false:否）
- hasMultipleAndDivide: 是否包含乘除法（true:是，false:否）
- hasParentheses: 是否包含括号（true:是，false:否）

在expressions中
- key 表示 表达式
- value 表示表达式的值

输出：
```json
{
  "config": {
    "numberOfExpression": 30,
    "range": 30,
    "hasFraction": false,
    "hasMultipleAndDivide": true,
    "hasParentheses": true,
    "hasNegative": false,
    "answer": false,
    "maxNumberOfOperation": 3
  },
  "results": [
    {
      "3 × ( 3 + 3 )": {
        "true": "符合要求的表达式"
      }
    },
    {
      "( 3 - 1 )": {
        "false": "题目中的乘除法不符合要求"
      }
    }
  ],
  "enough": false
}

```
输出参数说明：

- config：和输入的config一样
- results：里面对每个表达式正确与否进行了说明，key为表达式，value中的key为正确与否，value中说明了表达式不满足哪个条件
- enough: 表示是否生成了足够多满足条件的表达式

[RESTful接口地址(试用版)](http://116.196.74.137:9000/checking)，

- 接口方法类型：POST
- 接口参数名：answer
- 参数值：见输入示例


## update 2018-04-06
- 增加多语言处理
    - 英语
    - 简体中文
- 结果页面优化
- 增加是否显示答案选项

## update 2018-04-04

- 新增dev分支，用于平时开发，待测试稳定后merge到master分支上，目前服务器上运行的是master分支的内容
- dev分支更新
    - 更新到Spring boot 2.0.0
    - 增加日志处理
    - 增加异常处理
    
    



## 开发环境
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3.5.0](http://mirrors.tuna.tsinghua.edu.cn/apache/maven/maven-3/3.5.0/binaries/apache-maven-3.5.0-bin.zip)
- [IntelliJ IDEA](http://www.jetbrains.com/idea/)

## 说明

[运行地址](http://116.196.74.137:8888/index.html)

[接口地址](http://116.196.74.137:8888/services/CalculateService?wsdl)

### 生成表达式接口方法
```java
String generate(String configs) 
```

入参,字符串类型，可配置项见配置参数：
```json
{
  "numberOfExpression": "10",
  "range": "10",
  "hasFraction": "false",
  "hasMultipleAndDivide": "true",
  "hasParentheses": "false",
  "hasNegative": "true",
  "answer": "true"
}
```
返回：
```
[8 - 5 ÷ 6 × 9 = 0.5, 7 + 8 = 15.0, 9 × 9 = 81.0, 7 × 6 + 4 ÷ 7 = 42.57142857142857, 5 ÷ 7 - 4 = -3.2857142857142856, 2 - 9 - 1 + 1 = -7.0, 3 ÷ 2 × 6 = 9.0, 1 × 0 = 0.0, 4 × 3 = 12.0, 3 - 7 - 5 = -9.0]
```
### 答题（目前支持一题）接口方法
```java
String answer(String expression) 
```
入参：
```
4 × ( 9'1/7 - 5 ) ÷ 3
```

返回：
```
4 × ( 9'1/7 - 5 ) ÷ 3 = 5.523809523809524
```
### 获取默认的配置项接口方法
```java
String retrieveDefaultTemplate() 
```
返回默认的JSON配置：
```json
{
  "numberOfExpression": "10",
  "range": "10",
  "hasFraction": "false",
  "hasMultipleAndDivide": "true",
  "hasParentheses": "false",
  "hasNegative": "true",
  "answer": "true"
}
```

### 配置参数

- -n 或 -number： 生成表达式个数
- -r 或 -range: 控制题目中数值（自然数、真分数和真分数分母）的范围，该参数可以设置为1或其他自然数。该参数必须给定，否则程序报错并给出帮助信息
- -hf: 有无分数
- -md: 是否有乘除法
- -hp: 是否有括号
- -hn: 有无负数
- -as: 是否生成答案
- 至少一个运算符，最多三个运算符

注： 解析命令行参数采用了[JCommander](http://www.jcommander.org/)进行解析。

### 生成表达式逻辑
最初想法，其实是很简单粗暴的，把要生成的运算式看成字符串，这个字符串中：
- 运算符个数 = 运算数的个数 - 1 => 运算数 = 运算符个数 + 1
- 开辟一个(运算符个数 + 运算数个数)大小的数组，遍历数组中的每个元素，偶数位置插入运算数，奇数位置插入运算符。
- 如果有分数，则先随机找一个分数可插入的位置，其他的运算数插入位置可插分数或者整数。
- 判断是否有乘法和除法，如果有，则从{"+","-","x","÷"}中随机找一个插入运算符位置中，如果没有乘法和除法，
则从{"+","-"}中随机找一个插入运算符位置中。
- 如果运算符为除法且后面紧接的运算数为0，则将0重新替换成一个不为0的运算数。
- 如果有括号，则判断从第0个操作数到第n-1个操作数中随机选一个操作数之前拼接一个"(", 假设拼接"("是第m个操作数，然后从第m+1个操作数到第n个操作数中随机选一个操作数后面拼接")"

### 去重的逻辑
- 将字符串表达式（中缀表达式）转换成二叉树，
然后对这个二叉树的左右子树进行自定义排序，不管左右子树如何转换，它们最终都会被有序排序
如果两个表达式生成的二叉树排序以后的字符串序列一致，则说明两个式子是重复的。


### 计算逻辑
- 将中缀表达式转换为后缀表达式，然后对后缀表达式进行计算
- 计算过程中，设置一个栈，操作数压栈，遇到操作符，出栈两个操作数进行计算结果再压栈，最后栈剩下一个元素即为结果。



### [代码](https://github.com/GreyZeng/calculator/tree/master)

## 参考文献
- [How to use Log4j 2 with Spring Boot](https://www.callicoder.com/spring-boot-log4j-2-example/)
- [Guide to Spring Boot REST API Error Handling](https://www.toptal.com/java/spring-boot-rest-api-error-handling)
- [Spring Boot中Web应用的统一异常处理](http://blog.didispace.com/springbootexception/)
- [A Guide To Spring Redirects](http://www.baeldung.com/spring-redirect-and-forward)
- [Spring MVC and Thymeleaf: how to access data from templates](https://www.thymeleaf.org/doc/articles/springmvcaccessdata.html)
- [A quick guide to Internationalization in Spring Boot](https://phraseapp.com/blog/posts/spring-boot-internationalization/)