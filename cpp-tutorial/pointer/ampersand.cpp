#include <iostream>
using namespace std;

int main() {

    /* 引用类型声明 */
    int original = 42;
    int &ref = original; // 创建整型引用ref，关联到original
    cout << ref << endl;

    ref = 100; // 修改ref实际上也修改了original
    cout << ref << endl;
    cout << original << endl;

    return 0;

}