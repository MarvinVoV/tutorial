def counter():
    value = 0

    def _counter():
        # Nonlocal is used to mark variables from the upper scope. if not marked, the inner function will not be able to directly modify the outer function variable.
        nonlocal value

        value += 1
        return value

    return _counter


c = counter()

print(c())  # 1
print(c())  # 2

c2 = counter()
print(c2())  # 1
