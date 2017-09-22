package com.cp;


/**
 * @author zenghui<410486047@qq.com>
 * @date 2017/9/22
 */
public class Application {
    public static void main(String[] args) {
        new Generator().generate(Config.create(args));
    }
}
