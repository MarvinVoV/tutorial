#include <iostream>

using namespace std;

int main() {
    for (char c; cin >> c;) {
        cout << "The value of " << c << " is " << int{c} << endl;
    }
    return 0;
}