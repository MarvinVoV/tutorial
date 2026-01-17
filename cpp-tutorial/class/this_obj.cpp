#include <iostream>

using namespace std;

class Box {

    private:
        double length;
        double breadth;
        double height;
    public:
        Box(double l =2.0, double b=2.0, double h=2.0) {
            cout << "Constructor called." << endl;
            length = l;
            breadth = b;
            height = h;
        }

        double volume() {
            return length * breadth * height;
        }
};

int main() {
    Box Box1(3.3, 1.2, 1.5);
    Box Box2(8.5, 6.0, 2.0);
    // Declare pointer to a class
    Box *ptrBox;

    // Save the address of first object
    ptrBox = &Box1;

    // Now try to access a member using member access operator
    cout << "Volume of Box1: " << ptrBox->volume() << endl;

    // Save the address of second object
    ptrBox = &Box2;

    cout << "Volume of Box2: " << ptrBox->volume() << endl;
    return 0;
}