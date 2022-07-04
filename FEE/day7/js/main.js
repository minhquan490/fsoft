$(document).ready(function () {
    render(users);
    addItem();
});

function addItem() {
    $("#btnSubmit").on("click", function () {
        // event.preventDefault();
        console.log("here");
        if ($("#form").valid()) {
            let id;
            if (users.length > 0) {
                id = users[users.length - 1].id + 1;
            } else {
                id = 0;
            }
            let firstname = $("#firstname").val();
            let lastname = $("#lastname").val();
            let email = $("#email").val();
            let phone = $("#phone").val();
            let address = $("#address").val();
            let city = $("#city").val();
            let state = $("#state").val();
            let zipcode = $("#zipcode").val();
            let cardname = $("#cardname").val();
            let cardnumber = $("#cardnumber").val();
            let expmonth = $("#expmonth").val();
            let expyear = $("#expyear").val();
            let cvv = $("#cvv").val();
            // $.each($("input[type=checkbox]:checked"), function (key, element) {
            //     phanTramThue += parseInt(element.value);
            // });

            let newUser = new User(
                id,
                firstname,
                lastname,
                email,
                phone,
                address,
                city,
                state,
                zipcode,
                cardname,
                cardnumber,
                expmonth,
                expyear,
                cvv
            );
            users.push(newUser);
            $("#form")[0].reset();
            let str = `
            <tr>
                <td>${newUser.firstname}</td>
                <td>${newUser.lastname}</td>
                <td>${newUser.email}</td>
                <td>${newUser.phone}</td>
                <td>${newUser.address}</td>
                <td>${newUser.city}</td>
                <td>${newUser.state}</td>
                <td>${newUser.zipcode}</td>
                <td>${newUser.cardname}</td>
                <td>${newUser.cardnumber}</td>
                <td>${newUser.expmonth}</td>
                <td>${newUser.expyear}</td>
                <td>${newUser.cvv}</td>
                <td>
                    <button class="btn btn-outline-danger btn_remove" data-index="${newUser.id}" data-toggle="modal" data-target="#myModal">
                        <i class="far fa-trash-alt"></i>
                    </button>
                </td>
            </tr>`;
            $("#tbody").append(str);
        }
        removeButton();
    });
}

function render(users) {
    let str = "";

    for (let index = 0; index < users.length; index++) {
        const element = users[index];
        str += `
        <tr>
            <td>${element.firstname}</td>
            <td>${element.lastname}</td>
            <td>${element.email}</td>
            <td>${element.phone}</td>
            <td>${element.address}</td>
            <td>${element.city}</td>
            <td>${element.state}</td>
            <td>${element.zipcode}</td>
            <td>${element.cardname}</td>
            <td>${element.cardnumber}</td>
            <td>${element.expmonth}</td>
            <td>${element.expyear}</td>
            <td>${element.cvv}</td>
            <td>
                <button class="btn btn-outline-danger btn_remove" data-index="${element.id}" data-toggle="modal" data-target="#myModal">
                    <i class="far fa-trash-alt"></i>
                </button>
            </td>
        </tr>`;
    }
    $("#tbody").html(str);
    removeButton();
}

function removeButton() {
    $(".btn_remove").on("click", function () {
        let that = $(this);
        $("#confirm").on("click", function () {
            let index = that.data("index");
            users = users.filter((user) => user.id !== index);
            render(users);
        });
        $("#cancel").on("click", function () {});
    });
}
