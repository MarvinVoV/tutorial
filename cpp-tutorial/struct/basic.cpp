#include <iostream>
#include <cstring>

using namespace std;


struct Book {
    char title[50];
    char author[50];
    char subject[100];
    int book_id;
};

void printBook(Book book) {
    cout << "Book title : " << book.title << endl;
    cout << "Book author : " << book.author << endl;
    cout << "Book subject : " << book.subject << endl;
    cout << "Book book_id : " << book.book_id << endl;
}

// pass struct as a pointer
void printBook2(Book *book)  {
    cout << "Book title : " << book->title << endl;
    cout << "Book author : " << book->author << endl;
    cout << "Book subject : " << book->subject << endl;
    cout << "Book book_id : " << book->book_id << endl;
}

int main() {
    Book book1;

    strcpy(book1.title, "C++");
    strcpy(book1.author, "Bjarne Stroustrup");
    strcpy(book1.subject, "C++ Programming");    
    book1.book_id = 456;
    printBook(book1);
    printBook2(&book1);
    return 0;
}