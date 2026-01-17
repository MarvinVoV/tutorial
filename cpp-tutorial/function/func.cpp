#include <iostream>
using namespace std;

// passed as by value
int add(int x, int y)
{
    return x + y;
}

// call by pointer
// 参数中的 int *x 是整型指针,存储整型变量的内存地址。 在函数内部通过操作指针，间接访问或修改原始数据。
void swap(int *x, int *y) {
    int temp;
    // 这里的 *x表示对指针x指向的内存位置进行解引用。它表示取出指针x指向的实际正数值。
    temp = *x;
    // 将指针 x 所指向的内存位置的值设置为指针 y 所指向的内存位置的值。通过这个操作实现了将 x 指向的值和 y 指向的值进行交换
    *x = *y;
    *y = temp;
}

// call by reference
// int &x 是整型的引用相当于是整型变量的别名 在函数内部，通过引用直接访问和修改原始数据，不需要使用指针操作
void swap2(int &x, int &y) {
    int temp = x;
    x = y;
    y = temp;
}

int main() {
    int result = add(1, 2);
    cout << "result: " << result << endl;


    int a = 100, b = 200;
    swap(&a, &b);

    cout << "After swap value of a: " << a << endl;
    cout << "After swap value of b: " << b << endl;

    int c = 5, d = 2;
    swap2(c, d);

    cout << "After swap value of c: " << c << endl;
    cout << "After swap value of d: " << d << endl;

    return 0;
}