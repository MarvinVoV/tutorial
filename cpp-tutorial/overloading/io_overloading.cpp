#include <iostream>

using namespace std;

class Distance {
    private:
        int feet;
        int inches;
    public:
        Distance() : feet(0), inches(0) {}
        Distance(int f, int i) : feet(f), inches(i) {}
        friend ostream &operator << (ostream &output, const Distance &d) {
            output << "F: " << d.feet << ",I: " << d.inches;
            return output;
        }

        friend istream &operator >> (istream &input, Distance &d) {
            input >> d.feet >> d.inches;
            return input;
        }

        // overload function call
        Distance operator()(int a, int b, int c) {
            Distance d;
            d.feet = a + c + 10;
            d.inches = b + c + 100;
            return d;
        }
        
        void  display() {
            cout << "F: " << feet << " I: " << inches << endl;
        }
};

int main() {
    Distance d1(11, 10), d2(5,11), d3;
    cout << "Enter the value of object:" << endl;
    cin >> d3;
    cout << "First Distance: " << d1 << endl;
    cout << "Second Distance: " << d2 << endl;
    cout << "Third Distance: " << d3 << endl;

    // invoke operator()
    d3 = d1(10, 10, 10); 
    d3.display();
    return 0;

}