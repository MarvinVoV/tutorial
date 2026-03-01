def func(a: int, b: str) -> str:
    return f"{a} {b}"


print(func.__annotations__)


def my_decorator(func):
    def wrapper(*args, **kwargs):
        print("Before function call")
        result = func(*args, **kwargs)
        print("After function call")
        return result

    return wrapper


@my_decorator
def say_hello(name: str) -> str:
    return f"Hello {name}"


print(say_hello("World"))


def reapter(n: int):
    def decorator(func):
        def wrapper(*args, **kwargs):
            for _ in range(n):
                result = func(*args, **kwargs)
            return result

        return wrapper

    return decorator

@reapter(3)
def greet(name:str) -> str:
    print(f"Hello {name}")


greet("Penny")
