from __future__ import print_function

def heapify(unsorted, index, heap_size):
    large = index
    left_index = 2 * index + 1
    right_index = 2 * index + 2
    if left_index < heap_size and unsorted[left_index] > unsorted[large]:
        large = left_index

    if right_index < heap_size and unsorted[right_index] > unsorted[large]:
        largest = right_index

    if largest != index:
        unsorted[large], unsorted[index] = unsorted[index], unsorted[large]
        heapify(unsorted, large, heap_size)


def heap_sorter(unsorted):
    
    arrlen = len(unsorted)
    for i in range(arrlen // 2 - 1, -1, -1):
        heapify(unsorted, i, arrlen)
    for i in range(arrlen - 1, 0, -1):
        unsorted[0], unsorted[i] = unsorted[i], unsorted[0]
        heapify(unsorted, 0, i)
    return unsorted


user_input = raw_input('Enter numbers separated by a comma:\n').strip()
unsorted = [int(item) for item in user_input.split(',')]
print(heap_sorter(unsorted))
