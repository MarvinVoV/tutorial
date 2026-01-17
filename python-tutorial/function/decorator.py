import time
import random
from functools import wraps, update_wrapper


# Example 1: simple decorators
def my_decorator(func):
    def wrapper():
        print("Something is happening before the function is called.")
        func()
        print("Something is happening after the function is called.")

    return wrapper


def say_whee():
    print("Whee!")


say_whee = my_decorator(say_whee)
say_whee()


# Syntacic Sugar: pie-syntax. https://peps.python.org/pep-0318/#background
def my_decorator1(func):
    def wrapper():
        print("Something is happening before the function is called.")
        func()
        print("Something is happening after the function is called.")

    return wrapper


# equivalent to `say_whee = my_decorator1(say_whee1) `
@my_decorator1
def say_whee1():
    print("Whee!")


say_whee1()


# Example 2: decorators with arguments
def timer(func):
    @wraps(func)
    def decorated(*args, **kwargs):
        st = time.perf_counter()
        ret = func(*args, **kwargs)
        print("time cost:{} seconds".format(time.perf_counter() - st))
        return ret

    return decorated


@timer
def random_sleep():
    time.sleep(random.random())


random_sleep()

print(random_sleep.__name__)


# Example 4: decorators with arguments
def repeat(_func=None, *, num_times=2):
    def decorator_repeat(func):
        @wraps(func)
        def wrapper_repeat(*args, **kwargs):
            for _ in range(num_times):
                value = func(*args, **kwargs)
            return value

        return wrapper_repeat

    if _func is None:
        return decorator_repeat
    else:
        return decorator_repeat(_func)


# equivalent to: greet = repeat(3)(great)
@repeat
def say_whee():
    print("Whee!")


@repeat(num_times=3)
def greet(name):
    print(f"Hello {name}")


say_whee()
greet("Penny")


# Example 4: decorator class
class CountCalls:
    def __init__(self, func):
        update_wrapper(self, func)
        self.func = func
        self.num_calls = 0

    def __call__(self, *args, **kwargs):
        self.num_calls += 1
        print(f"Call {self.num_calls} of {self.func.__name__!r}")
        return self.func(*args, **kwargs)


@CountCalls
def say_whee():
    print("Whee!")


say_whee()


# Example 5: Create singleton

def singleton(cls):
    """Make a class a Singleton class (only one instance)"""

    @wraps(cls)
    def wrapper_singleton(*args, **kwargs):
        if not wrapper_singleton.instance:
            wrapper_singleton.instance = cls(*args, **kwargs)
        return wrapper_singleton.instance

    wrapper_singleton.instance = None
    return wrapper_singleton


@singleton
class TheOne:
    pass


first_one = TheOne()
another_one = TheOne()
print(first_one is another_one)
