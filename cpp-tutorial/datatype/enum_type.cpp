#include <iostream>
using namespace std;


enum Color {
    RED, GREEN=5,BLUE
};

int main() {

    Color c = GREEN;
    cout << "c.value is\t" << c << endl;


    return 0;
}