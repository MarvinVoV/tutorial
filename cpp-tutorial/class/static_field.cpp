#include <iostream>

using namespace std;

class Box {
    private:
        double length;
        double breadth;
        double height;
    public:
        static int objectCount;
        
        // Constructor definition
        Box(double l = 2.0, double b= 2.0, double h =2.0) {
            cout << "Constructor called." << endl;
            length = l;
            breadth = b;
            height = h;
            objectCount++;
        }

        double volume() {
            return length * breadth * height;
        }

        static int getCount() {
            return objectCount;
        }
};

// Initialize static member of class
int Box::objectCount = 0;

int main() {
    Box Box1(3.3, 1.2, 1.5);
    Box Box2(8.5, 6.0, 2.0);
    cout << "Total objects created: " << Box::objectCount << endl;
    cout << "Total objects created: " << Box::getCount() << endl;
    return 0;
}