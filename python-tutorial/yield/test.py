def reverse(data):
    for index in range(len(data) - 1, -1, -1):
        yield data[index]


for char in reverse('golf'):
    print(char)

# Anything that can be done with generators can also be done with class-based iterators.
# What makes generators so compact is that the __iter()__ and __next()__ methods are created automatically
len()