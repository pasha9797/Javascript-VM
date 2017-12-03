main("one");

var a;
a = 5;
b = 10;
c = a + b;
alert("a = " + a);
alert("b = " + b);
alert("c = a + b = " + c);

for (var i = 0; i < 4; i++) {
    alert("Loop i = " + i);
}

function test(arg1, arg2) {
    return prompt("Function test called. Write something" + arg1 + arg2, "default");
}

function main(args) {
    alert("main func called with arg: " + args);
}

myArr = [345, 567, 876];
alert("Array - " + myArr);
myArr[1] = 1;
alert("Element 1 changed: " + myArr);

if (a == 4 && c > 0) {
    var j = Math.abs(0 - 5);
    alert("abs of -5: " + j);
}

if (6 == 7 && 5 == 4 || 1)
    alert("Logical test - true!");
else
    alert("Logical test - false!");

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

function crazy(a){
    return
    [   1,
        2,
        [
            11,
            22,
            function anonym(){
                alert("ahahahahaha js");
            },
            44
        ],
        4
    ];
}
crazy("dfg")[2][2]();

alert("gg" == "gg");

test2 = test;
alert(test2(" here,", " please"));