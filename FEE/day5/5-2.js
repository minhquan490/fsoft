function display() {
    let fullName = document.getElementById("name").value;
    let birthDate = document.getElementById("birthDate").value.split("-");
    let gender = document.querySelector('input[name="gender"]:checked').value;
    let nowYear = new Date(Date.now()).getFullYear();
    let age = parseInt(nowYear) - parseInt(birthDate[0]);

    document.getElementById("fullName").innerText = 'Full name: ' + fullName
    document.getElementById("birth-date").innerText = 'Birth Date: ' + birthDate[2] + '/' + birthDate[1] + '/' + birthDate[0];
    document.getElementById("gen").innerText = 'Gender: ' + gender;
    document.getElementById("age").innerText = 'Age: ' + age;
}

function display2() {
    let fullName = $("input[id=name]").value;
    let birthDate = $("#birthDate").val().split("-");
    let gender = $("#")
}