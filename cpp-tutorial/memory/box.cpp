#include <iostream>
using namespace std;

class Box {
    public:
        Box() {
            cout << "Constructor called" << endl;
        }
        ~Box() {
            cout << "Destructor called" << endl;
        }
};

int main() {
    Box *boxArray = new Box[4];

    delete [] boxArray; // delete array

    return 0;
}