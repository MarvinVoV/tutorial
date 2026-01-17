#include <iostream>
using namespace std;

class Box {
    protected:
        double width;
    public:
        void setWidth(double w);
};

void Box::setWidth(double w) {
    this->width = w;
}

class SmallBox : public Box {
    public:
        double getVolume() {
            return width * width * width;
        }
};

int main() {
    SmallBox box;
    box.setWidth(5);
    cout << box.getVolume() << endl;
}