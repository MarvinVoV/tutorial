def even_only(nums):
    for num in nums:
        if num % 2 == 0:
            yield num


def sum_even_only(nums):
    total = 0
    for num in even_only(nums):
        total += num
    return total


print(sum_even_only([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]))
