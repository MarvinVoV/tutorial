#include <iostream>
#include <vector>

using namespace std;

class Obj
{
private:
    static int i, j;

public:
    void f() const
    {
        cout << i++ << endl;
    }
    void g() const
    {
        cout << j++ << endl;
    }
};

// static member definition
int Obj::i = 10;
int Obj::j = 12;

// Implement a container for the above class
class ObjContainer
{
private:
    vector<Obj *> a;

public:
    void add(Obj *obj)
    {
        a.push_back(obj);
    }
    friend class SmartPointer;
};

class SmartPointer
{
private:
    ObjContainer oc;
    int index;

public:
    SmartPointer(ObjContainer &oc) : oc(oc), index(0) {}

    // return value indicates end of list
    bool operator++()
    {
        if (index >= oc.a.size())
        {
            return false;
        }
        if (oc.a[++index] == 0)
        {
            return false;
        }
        return true;
    }
    bool operator++(int)
    {
        return operator++();
    }

    // overload operator ->
    Obj *operator->() const
    {
        if (!oc.a[index])
        {
            cout << "Zero value";
            return (Obj *)0;
        }
        return oc.a[index];
    }
};

int main()
{
    const int size = 10;
    Obj o[size];
    ObjContainer oc;
    for (int i = 0; i < size; i++)
    {
        oc.add(&o[i]);
    }

    // create an iterator
    SmartPointer sp(oc);
    do
    {
        sp->f();
        sp->g();
    } while(sp++);
    return 0;
}