import datetime
from string import Template

print('{0}, {1}, {2}'.format('a', 'b', 'c'))

print('{} {} {}'.format('a', 'b', 'c'))

# unpacking argument sequence
print('{2}, {1}, {0}'.format(*'abc'))

print('{0}, {1}, {2}'.format(*('a', 'b', 'c')))

a = 'a', 'b', 'c'
# unpacking tuple
print('{0}, {1}, {2}'.format(*a))

# arguments' indices can be repeated
print('{0}, {1}, {0}'.format('a', 'b'))

# Accessing arguments by name
print('{latitude}, {longitude}'.format(latitude='37.24N', longitude='-115.8W'))

coord = {'latitude': '37.2', 'longitude': '-115.8'}
print('{latitude}, {longitude}'.format(**coord))

# Accessing arguments' attributes
c = 3 - 5j
print('The complex number {0} is formed from the real part {0.real} '
      'and the imaginary part {0.imag}.'.format(c))


class Point:
    def __init__(self, x, y):
        self.x, self.y = x, y

    def __str__(self):
        return 'Point({self.x}, {self.y})'.format(self=self)


print(str(Point(5, 2)))

# Accessing arguments' items
coord = (3, 5)
print('X: {0[0]}; Y: {0[1]}'.format(coord))

# Replacing %s and %r
print("repr() show quotes: {!r}; str() doesn't: {!s}".format('test1', 'test2'))

print('{0!r}'.format('test1'))

# Aligning the text and specifying a width
print('{:>30}'.format('right aligned'))
print('{:<30}'.format('left aligned'))
print('{:^30}'.format('centered'))
print('{:*^30}'.format('centered'))  # use '*' as fill char

# Converting the value to different bases
print("int {0:d}; hex {0:x}; oct {0:o}; bin {0:b}".format(42))

# with 0x, 0o, or 0b as prefix
print("int {0:d}; hex {0:#x}; oct {0:#o}; bin {0:#b}".format(42))

# Using the comma as a thousands separator
print('{:,}'.format(12345567))

# Expressing a percentage
print('Correct answer: {:.2%}'.format(19 / 22))

d = datetime.datetime(2019, 10, 4, 12, 15, 58)
print('{:%Y-%m-%d %H:%M:%S}'.format(d))

s = Template('$who likes $what')
print(s.substitute(who='tim', what='Tom'))
print()
