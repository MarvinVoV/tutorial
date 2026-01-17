from enum import Enum


# The simplest form compares a subject value against one or more literals:
def http_error(status):
    match status:
        case 400:
            return "bad request"
        case 404:
            return "Not found"
        case 405 | 406 | 407:
            return "Server exception"
        case _:
            return "something is wrong"


print(http_error(400))


class Color(Enum):
    RED = 'red'
    BLUE = 'blue'


color = Color('red')
match color:
    case Color.RED:
        print('red!')
    case Color.BLUE:
        print('blue')
    case _:
        print('other')
