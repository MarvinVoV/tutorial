#include <fstream>
#include <iostream>

using namespace std;

int main() {
    char data[100];

    // open a file in write mode
    ofstream outfile;
    outfile.open("text");

    cout << "Writing to the file" << endl;
    cout << "Enter your name: ";
    cin.getline(data, 100);

    // write inputted data into the file
    outfile << data << endl;

    cout << "Enter your age: ";
    cin >> data;
    cin.ignore();

    // again write inputted data into the file
    outfile << data << endl;

    outfile.close();

    // open a file in read mode
    ifstream infile;
    infile.open("text");
    cout << "Reading from the file" << endl;
    infile >> data;

    // write the data of the file
    cout << data << endl;

    // again read the data from the file and display it
    infile >> data;
    cout << data << endl;
    infile.close();
    return 0;
}