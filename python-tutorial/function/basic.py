def shop(kind, *arguments, **kwarg):
    print(kind)
    print("-" * 40)
    for arg in arguments:
        print(arg)
    print("-" * 40)
    for key in kwarg:
        print(key, ":", kwarg[key])


shop("Lim", "very", " runny", client="John", keeper="Micheal")


def standard_arg(arg):
    print(arg)


def pos_only_arg(arg, /):
    print(arg)


def kw_only_arg(*, arg):
    print(arg)


def combined_example(pos_only, /, standard, *, kwd_only):
    print(pos_only, standard, kwd_only)


standard_arg("hello")
standard_arg(arg="hello")
kw_only_arg(arg=3)
combined_example(1, 2, kwd_only=3)
combined_example(1, standard=2, kwd_only=3)


def foo(name, /, **kwargs):
    return 'name' in kwargs


foo(1, **{'name': 2})


def concat(*args, sep='/'):
    return sep.join(args)


print(concat('earch', 'mars', 'venus'))
print(concat('earch', 'mars', 'venus', sep='.'))

args = [3, 6]
print(list(range(*args)))

d = {'name': 'tom', 'age': 20}


def info(name, age=0):
    print(name, age)


info(**d)


def incr(n):
    return lambda x: x + n


f = incr(10)
print(f(22))
pairs = [(1, 'one'), (2, 'two'), (3, 'three'), (4, 'four')]
pairs.sort(key=lambda pair: pair[1])
print(pairs)



