myArr = [345, 567, 876];
alert("Array - " + myArr);
myArr[1] = 1;
alert("Element 1 changed: " + myArr);

var array;
array = [4, 5, 6, "str", [55, 66]];
alert("going to print array elements");
i = 0;
while (i <= 4) {
    alert(array[i]);
    i++;
}

alert("Array was: " + array);

var a = [34, 203, 3, 746, 200, 984, 198, 764, 9];
alert(a);
alert("going to sort");
function bubbleSort(a,length) {
    var swapped;
    do {
        swapped = false;
        for (var i = 0; i < length - 1; i++) {
            if (a[i] > a[i + 1]) {
                var temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
                swapped = true;
            }
        }
    } while (swapped)
}
bubbleSort(a, 9);
alert(a);