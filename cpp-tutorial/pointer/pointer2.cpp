#include <iostream>
#include <stdlib.h>
#include <stdio.h>

int main() {
    int *a;
    int length = 0;

    printf("Enter a length: ");
    scanf("%d", &length);

    a = (int *)malloc(length * sizeof(int));

    if (a == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return 1;
    }
    for (int i = 0; i < length; i++) {
        a[i] = i;
    }

    for (int i = 0; i < length; i++) {
        printf("a[%d]=%d\n", i, a[i]);
    }
    free(a);

    return 0;
}