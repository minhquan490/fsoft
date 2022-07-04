class User {
    constructor(
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
    ) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.cardname = cardname;
        this.cardnumber = cardnumber;
        this.expmonth = expmonth;
        this.expyear = expyear;
        this.cvv = cvv;
    }
}

var users = [
    new User(
        0,
        "Asd",
        "asd",
        "asd@fsoft.com.vn",
        "0123456789",
        "sadasd",
        "asd",
        "CA",
        "12345",
        "asda",
        "1324-1234-1324",
        "02",
        "12",
        "123"
    )
];
