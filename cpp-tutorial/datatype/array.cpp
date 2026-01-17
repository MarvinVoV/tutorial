#include <iostream>
using namespace std;

#include <iomanip>
using std::setw;

int main() {
    int n[10];
    for (int i = 0; i < 10; i++) {
        n[i] = i + 100;
    }
    cout << "Element" << setw(13) << "Value" << endl;
    for (int j = 0; j < 10; j++) {
        cout << setw(7) << j << setw(13) << n[j] << endl;
    }


    int a[2][4] = {
        {0,1,2,3},
        {4,5,6,7}
    };

    cout << "a[1][1]=" << a[1][1] << endl;
    return 0;
}