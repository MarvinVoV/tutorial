#include <iostream>

using namespace std;

class Line {
    public:
        // This is a constructor
        Line(double len);
        // This is a default constructor
        Line() = default;

        // copy constructor
        Line(const Line &obj);

        double getLength();
        void setLength(double len);


        // This is a destructor
        ~Line(); 

    private:
        double length;
        double *ptr;
};

Line::Line(double len) {
    cout << "Object is being created,length=" << length << endl;
    length = len;
    // allocate memory for the pointer 
    ptr = new double;
    *ptr = len;
}

// or Using Initialization Lists to Initialize fields
// Line::Line(double len) : length(len) {
//     cout << "Object is being created,length=" << length << endl;
// }

void Line::setLength(double len) {
    length = len;
}

double Line::getLength() {
    return *ptr;
}

Line::~Line() {
    cout << "Freeing memory!" << endl;
    delete ptr;
}

Line::Line(const Line &obj) {
    cout << "Copy constructor allocating ptr " << endl;
    ptr = new double;
    // copy the value
    *ptr = *obj.ptr;
}

int main() {
    Line line(10.0);
    cout << "Length of line : " << line.getLength() << endl;
    line.setLength(6.0);
    cout << "Length of line : " << line.getLength() << endl;
    return 0;
}