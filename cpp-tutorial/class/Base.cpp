#include <iostream>
using namespace std;

class Base {
    // By default all the members of as class would be private
    private:
        double width;
        double length;
    public:
        void setWidth(double w);
        double getWidth(); 

};

double Base::getWidth() {
    return this->width;
}

void Base::setWidth(double w) {
    this->width = w;
}

int main() {
    Base base;
    base.setWidth(5);
    cout << base.getWidth() << endl;

    return 0;
}