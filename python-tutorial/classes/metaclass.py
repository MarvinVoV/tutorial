# metaclass
class Meta(type):
    def __new__(cls, name, bases, dct):
        x = super().__new__(cls, name, bases, dct)
        x.attr = 100
        return x


class Foo(metaclass=Meta):
    pass


print(Foo.attr)


# decorator

def decorator(cls):
    class NewClass(cls):
        attr = 200

    return NewClass


@decorator
class Y:
    pass


@decorator
class X:
    pass


print(X.attr)
print(Y.attr)
