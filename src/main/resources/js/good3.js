function crazy(a){
    return
    [
        1,
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

function test(arg1, arg2) {
    return prompt("Function test called. Write something" + arg1 + arg2, "default");
}

test2 = test;
alert(test2(" here,", " please"));