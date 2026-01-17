#include <iostream>

using namespace std;

inline int Max(int x, int y) {
    return (x > y) ? x : y;
}

int main() {
    int a = 100;
    int b = 200;
    int ret;
    ret = Max(a, b);
    cout << ret << endl;
    return 0;
}