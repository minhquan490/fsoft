var a = [10, 1, 6, 8, 4, 3, 45, 2, 9];

function selectionSort(inputArr) {
    let n = inputArr.length;

    for (let i = 0; i < n; i++) {
        let min = i;
        for (let j = i + 1; j < n; j++) {
            if (inputArr[j] < inputArr[min]) {
                min = j;
            }
        }
        if (min != i) {
            inputArr[i] *= inputArr[min];
            inputArr[min] = inputArr[i] / inputArr[min];
            inputArr[i] = inputArr[i] / inputArr[min];
        }
    }
    return inputArr;
}

a = selectionSort(a);
console.log(a);

function printEvenNumberAndSumIt(a) {
    let sum = 0;
    console.log("Even number: ");
    a.forEach(num => {
        if (num % 2 == 0) {
            console.log(num);
            sum += num;
        }
    });
    return sum;
}

let b = printEvenNumberAndSumIt(a);
console.log("Sum of them: ");
console.log(b);

function printOddNumberAndSumIt(a) {
    let sum = 0;
    console.log("Odd number: ");
    a.forEach(num => {
        if (num % 2 != 0) {
            console.log(num);
            sum += num;
        }
    });
    return sum;
}

let c = printOddNumberAndSumIt(a);
console.log("Sum of them: ");
console.log(c);

function getValue() {
    let m = document.getElementById('getValue').value;
    let mArray = m.split(",");
    a.forEach(ele => {
        if (ele >= mArray[0] && ele <= mArray[1]) {
            console.log(ele);
        }
    });
}