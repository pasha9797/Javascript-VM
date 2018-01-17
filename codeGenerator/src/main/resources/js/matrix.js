matr1=[
        [1,2],
        [3,4]
];

matr2=[
    [3,2],
    [1,4]
];

alert(matr1);
alert(matr2);

matr3=[[0,0],[0,0]];

for(var i=0;i<2;i++){
    for(var j=0;j<2;j++){
        for(var k=0;k<2;k++){
            matr3[i][j]=matr3[i][j]+matr1[i][k]*matr2[k][j];
        }
    }
}
alert(matr3);