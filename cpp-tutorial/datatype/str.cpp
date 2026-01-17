#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string>
using namespace std;

int main()
{

    /* C-style character */
    // char greeting[] = "hello";
    char greeting[6] = {'H', 'e', 'l', 'l', 'o', '\0'};
    cout << greeting << endl;

    char str1[10] = "Hello";
    char str2[10] = "Word";
    char str3[10];
    int len;
    strcpy(str3, str1);
    cout << "strcpy" << str3 << endl;

    strcat(str1, str2);
    cout << "strcat(str1, str2)" << str1 << endl;

    len = strlen(str1);
    cout << "strlen(str1)" << len << endl;

    /* string class */

    string a1 = "Hello";
    string a2 = "World";
    string a3 ;
    // copy a1 into a3
    a3 = a1;
    cout << "a3: " << a3 <<endl;

    // concatenates a1 and a2
    a3 = a1 + a2;
    cout << "a1+a2 :" << a3 << endl;

    len = a3.size();
    cout << "a3.size(): " << len << endl;

    return 0;
}