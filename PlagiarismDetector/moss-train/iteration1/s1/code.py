# Python program to implementation of Bubble Sort
 
def bubble_sort(arr1):
    n = len(arr1)
 
    for i in range(n):
        for j in range(0, n-i-1):
            if arr1[j] > arr1[j+1] :
                arr1[j], arr1[j+1] = arr1[j+1], arr1[j]

arr1 = [5,7,8,9,1,2,3,0]

bubble_sort(arr1)

print ("Sorted array is:")
for i in range(len(arr1)):
    print ("%d" %arr1[i])
