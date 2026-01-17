#include <iostream>

using namespace std;

namespace first_space {
    void func() {
        cout << "Inside first_space" << endl;
    }

    // nested namespace
    namespace inner_space {
        void func() {
            cout << "Inside inner_space" << endl;
        }
    }
}

using namespace first_space::inner_space;
int main() {
    func();
    return 0;
}
