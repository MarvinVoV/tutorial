#include <iostream>
#include <stdio.h>

using namespace std;

int main() {

    int x = 10;
    //  声明一个整型指针变量
    int *p;
    // 将指针变量指向x的地址
    p = &x;
    
    // 将声明和赋值合并到一起，等同于这样
    // int *p = &x;

    // 获取指针指向的正数x的值
    printf("Value of x: %d\n", *p);

    // 获取变量x的内存地址
    printf("Address of x: %p\n", &x);

    // %p用于格式化字符串中输出指针的地址。强制类型转换为 (void*) 是一个良好的实践，以匹配 printf 函数的参数期望类型
    printf("Address of x: %p\n", (void *)p);

    printf("sizeof x %d bytes\n", sizeof(x));

    printf("sizeof p %d bytes\n", sizeof(p));

    return 0;
}