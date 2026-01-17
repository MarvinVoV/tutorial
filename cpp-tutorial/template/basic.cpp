#include <iostream>

using namespace std;

template <typename T>
T add(T a, T b) {
    return a + b;
}

int main() {
    int resultInt = add(5,10);
    double resultDouble = add(3.5, 2.1);

    cout << resultInt << endl;
    cout << resultDouble << endl;
    return 0;
}