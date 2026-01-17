#include <iostream>
#include <exception>
using namespace std;

struct MyException : public exception {
    const char *what() const throw() {
        return "C++ Exception";
    }
};

int main() {
    try {
        throw MyException();
    } catch(MyException &e) {
        cout << "MyException caught" << endl;
        cerr << e.what() << endl;
    } catch (exception &e) {
        cout << "other error" << endl;
    }
    return 0;
}