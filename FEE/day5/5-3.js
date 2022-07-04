var users = [
    { id: 1, first_name: "Eamon", last_name: "Harhoff", email: "eharhoff0@imageshack.us", gender: "Male", age: 76, salary: 18888 },
    { id: 2, first_name: "Laney", last_name: "Whittam", email: "lwhittam1@issuu.com", gender: "Female", age: 42, salary: 15018 },
    { id: 3, first_name: "Lynett", last_name: "Twinberrow", email: "ltwinberrow2@gov.uk", gender: "Female", age: 99, salary: 13343 },
    { id: 4, first_name: "Eeof", last_name: "Twinberrow", email: "ltwinberrow2@gov.uk", gender: "Male", age: 23, salary: 13343 }
];

function isYoungMan(user) {
    if (user.gender == "Male" && user.age < 40) {
        return true;
    }
    return false;
}

const list = document.getElementById("list");
users.forEach(ele => {
    list.innerHTML += '<li>ID: ' + ele.id + ', Name: ' + ele.first_name + ' ' + ele.last_name + ', Email: ' + ele.email + ', Gender: ' + ele.gender + ', Age: ' + ele.age + ', Salary: ' + ele.salary + '</li>';
});

const listYoung = document.getElementById("listYoung");
var youngs = users.filter(isYoungMan);
youngs.forEach(ele => {
    listYoung.innerHTML += '<li>ID: ' + ele.id + ', Name: ' + ele.first_name + ' ' + ele.last_name + ', Email: ' + ele.email + ', Gender: ' + ele.gender + ', Age: ' + ele.age + ', Salary: ' + ele.salary + '</li>';
});

let average = 0;
users.forEach(ele => average += ele.age);
document.getElementById("average").innerHTML = 'Average age: ' + average / users.length;