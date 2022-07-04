$("#form").validate({
    rules: {
        firstname: {
            required: true,
            validateNoSpecial: true,
        },
        lastname: {
            required: true,
            validateNoSpecial: true,
        },
        email: {
            required: true,
            email: true,
        },
        phone: {
            required: true,
            onlynumber: true,
            phone: true,
        },
        address: {
            required: true,
            validateNoSpecial: true,
        },
        city: {
            required: true,
            validateNoSpecial: true,
        },
        state: {
            valueNotEquals: "default",
        },
        zipcode: {
            required: true,
            onlynumber: true,
            maxlength: 5,
        },
        cardname: {
            required: true,
            validateNoSpecial: true,
        },
        cardnumber: {
            required: true,
            cardnumber: true,
        },
        expmonth: {
            required: true,
            month: true,
        },
        expyear: {
            required: true,
            year: true,
        },
        cvv: {
            required: true,
            maxlength: 3,
        },
        checkme: {
            valueEquals: "true",
        },
    },
    messages: {
        firstname: {
            required: "First Name không được để trống",
        },
        lastname: {
            required: "Last Name không được để trống",
        },
        email: {
            required: "Email không được để trống",
            email: "not a valid fsoft email address",
        },
        phone: {
            required: "Phone không được để trống",
        },
        address: {
            required: "Address không được để trống",
        },
        city: {
            required: "City không được để trống",
        },
        state: {
            valueNotEquals: "Vui lòng chọn State",
        },
        zipcode: {
            required: "Zipcode không được để trống",
        },
        cardname: {
            required: "Card Name không được để trống",
        },
        cardnumber: {
            required: "Card number không được để trống",
        },
        expmonth: {
            required: "Expire Month không được để trống",
        },
        expyear: {
            required: "Expire Year không được để trống",
        },
        cvv: {
            required: "CVV không được để trống",
        },
        checkme: {
            valueEquals: "Check me",
        },
    },
    errorPlacement: function (error, element) {
        if (element.attr("name") == "checkme") {
            error.appendTo("#error-mess");
        } else {
            error.insertAfter(element);
        }
    },
});

$.validator.addMethod(
    "valueNotEquals",
    function (value, element, arg) {
        return arg !== value;
    },
    "Không được chọn giá trị này."
);

$.validator.addMethod(
    "valueEquals",
    function (value, element, arg) {
        return arg === value;
    },
    "phải chọn giá trị này."
);

$.validator.addMethod(
    "validateNoSpecial",
    function (value, element) {
        return /^[a-z]+([ ]{1}[a-z]+)*$/.test(value);
    },
    "Không chứa khoảng trắng, ký tự đặc biệt"
);

$.validator.addMethod(
    "phone",
    function (value, element) {
        return /^[0-9]{10}$/.test(value);
    },
    "phone dài 10 số"
);
$.validator.addMethod(
    "email",
    function (value, element) {
        return /^[a-z]+[0-9]*@fsoft\.com\.vn$/i.test(value);
    },
    "not a valid fsoft email address"
);
$.validator.addMethod(
    "onlynumber",
    function (value, element) {
        return /^[0-9\,-]*$/.test(value);
    },
    "chỉ chứa giá trị số"
);

$.validator.addMethod(
    "month",
    function (value, element) {
        return /^(0?[1-9]|1[012])$/.test(value);
    },
    "invalid month"
);
$.validator.addMethod(
    "year",
    function (value, element) {
        return /^[2][0-9]{3}$/.test(value);
    },
    "invalid month, year >2000 va year <3000"
);

// $("#giaBan").on("keyup", function (event) {
//     // When user select text in the document, also abort.
//     var selection = window.getSelection().toString();
//     if (selection !== "") {
//         return;
//     }
//     // When the arrow keys are pressed, abort.
//     if ($.inArray(event.keyCode, [38, 40, 37, 39]) !== -1) {
//         return;
//     }
//     var $this = $(this);
//     // Get the value.
//     var input = $this.val();
//     input = input.replace(/[\D\s\._\-]+/g, "");
//     input = input ? parseInt(input, 10) : 0;
//     $this.val(function () {
//         return input === 0 ? "" : input.toLocaleString("en-US");
//     });
// });

$.validator.addMethod(
    "cardnumber",
    function (value, element) {
        return /^[0-9/-]{14}$/i.test(value); //do dai 12 ki tu se tu dong chuyen thanh 3 cum 4 so
    },
    "chi duoc nhap so va do dai la 12"
);

function comma(number) {
    number = "" + number;
    if (number.length > 3) {
        var mod = number.length % 3;
        var output = mod > 0 ? number.substring(0, mod) : "";
        for (i = 0; i < Math.floor(number.length / 3); i++) {
            if (mod == 0 && i == 0) output += number.substring(mod + 3 * i, mod + 3 * i + 3);
            else output += "," + number.substring(mod + 3 * i, mod + 3 * i + 3);
        }
        return output;
    } else return number;
}

$("#cardnumber").keyup(function () {
    //chuyen doi cach nhau 4 so co dau -

    var foo = $(this).val().split("-").join(""); // remove hyphens

    if (foo.length > 0) {
        foo = foo.match(new RegExp(".{1,4}", "g")).join("-");
    }

    $(this).val(foo);
});
