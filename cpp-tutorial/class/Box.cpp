#include <iostream>
using namespace std;

class Box {
    public:
        double length;
        double breadth;
        double height;

        // member function
        double getVolume() {
            return length * breadth * height;
        }

        // member function declared
        double getArea();
};

// deine the member function outside the class using the scope resolution operator
double Box::getArea(){
    return length * breadth;
};

int main() {
    // create an object
    Box box;
    box.breadth = 10;
    box.length = 20;
    box.height = 5;

    cout << box.getVolume() << endl;
    cout << box.getArea() << endl;
}
