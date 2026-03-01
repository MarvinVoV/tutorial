from typing import List, Tuple, Dict, Set, Callable


def process_items(items: List[int]) -> List[int]:
    return [item * 2 for item in items]


def look_up(coord: Tuple[float, float]) -> str:
    return f"{coord[0]}, {coord[1]}"


def count_words(text: str) -> Dict[str, int]:
    words = text.split()
    return {word: words.count(word) for word in words}


def apply_twice(func: Callable[[int], int], value: int) -> int:
    return func(func(value))


def square(x: int) -> int:
    return x * x


result = apply_twice(square, 3)
print(result)
