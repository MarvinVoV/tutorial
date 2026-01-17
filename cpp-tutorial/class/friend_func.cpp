#include <iostream>

using namespace std;

class Box {
    double width;

    public:
        double length;
        friend void printWidth(Box box);
        void setWidth(double w);
};

void Box::setWidth(double w) {
    width = w;
}


// Note : printWidth() is not a member function of any class;
void printWidth(Box box) {
    /*
        Because printWidth() is a friend of Box, it Can directly access the private members of Box 
    */

    cout << "Width of box: " << box.width << endl;
}

int main() {
    Box box;
    box.setWidth(10.0);
    printWidth(box);
    return 0;
}