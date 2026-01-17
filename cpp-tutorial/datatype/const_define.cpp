#include <iostream>
using namespace std;

// Using #define preprocessor
#define WIDTH 30
#define LENGTH 20

// Using const
const char NEWLINE = '\n';

constexpr int MAX_BUF = 1024;

// initialize every char to 0
char buf[MAX_BUF] {};

enum class MyEnum : int
{
    MY_CONST = 42
};

int main()
{

    int area;
    area = WIDTH * LENGTH;

    cout << "area=" << area << endl;

    cout << NEWLINE;

    cout << static_cast<int>(MyEnum::MY_CONST) << endl;


    return 0;
}