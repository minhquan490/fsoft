/**
 * Function is used to check valid date.
 * 
 * @require String of valid date
 * @param {String} date string
 * @return a valid date or null if param is not a valid date
 * @author HoangQuan
 * @email hminhquan0401@gmail.com
 */
export function isValidDate(date) {
    if (typeof date === 'string') {
        let dateComponent = date.split("-");
        let nowComponent = new Date(Date.now()).toString().split(" ").splice(1, 3);

        if (dateComponent.length != 3) {
            return null;
        }

        let year = parseInt(dateComponent[0]);
        let month = parseInt(dateComponent[1]);
        let day = parseInt(dateComponent[2]);
        let nowYear = parseInt(nowComponent[2]);

        if (nowYear < year) {
            return null;
        }

        if (dateComponent[0].length != 4 || day > 31 || month > 12 || (day > 29 && month == 2)) {
            return null;
        }

        if ((day > 32) || ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 ||
                month == 12) && (day > 30))) {
            return null;
        }
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    // Do nothing
                } else {
                    if (month == 2 && day >= 29) {
                        return null;
                    }
                }
            } else {
                if (month == 2 && day >= 29) {
                    return null;
                }
            }
        } else {
            if (month == 2 && day >= 29) {
                return null;
            }
        }
        return date;
    } else {
        return null;
    }
}

/**
 * check string is a valid number
 * 
 * @require string of valid number
 * @param {String} num string
 * @return true if param is a valid number or fail if param is not a number
 * @author HoangQuan
 * @email hminhquan0401@gmail.com
 */
export function isNumber(num) {
    if (typeof num != 'string') {
        return false;
    }
    return !isNaN(num.trim());
}

/**
 * check string is a valid gender
 * 
 * @require string of a valid gender
 * @param {String | Number} gender string (male or female) or number (0 or 1)
 * @return a valid gender
 * @author HoangQuan
 * @email hminhquan0401@gmail.com
 */
export function isValidGender(gender) {
    return (gender.toLowerCase().trim() == 'male' || gender.trim() == '0' || gender == 0) ?
        'male' :
        (gender.toLowerCase().trim() == 'female' || gender.trim() == '1' || gender == 1) ?
        'female' :
        null;
}

/**
 * Check email is valid
 * @require string of email
 * @param  {String} email string
 * @return true if email is valid
 * @author HoangQuan
 * @email hminhquan0401@gmail.com
 */
export function isValidEmail(email) {
    let emailPattern = /^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@(fsoft.com.vn)$/;
    return emailPattern.test(email);
}

/**
 * Check phone is valid
 * @require string of phone
 * @param {String} phone string
 * @return true if phone is valid
 * @author HoangQuan
 * @email hminhquan0401@gmail.com
 */
export function isValidPhone(phone) {
    let phonePattern = /^((03[2-9]{1})|(07([6-9]{1}|0))|(08[1-5]{1})|(05[6|8]{1})|0[5|9]{1}9|(09[6-8]{1})|088|091|094|089|090|093|086|092)[0-9]{7}$/;
    return phonePattern.test(phone);
}

/**
 * Check string is not contain special chacracter
 * @require string need to test
 * @param {String} data string
 * @return true if data is not contain spectial character
 * @author HoangQuan
 * @email hminhquan0401@gmail.com
 */
export function hasNoSpecialCharacter(data) {
    let pattern = /^([a-zA-Z0-9\u0600-\u06FF\u0660-\u0669\u06F0-\u06F9 _.-]+)$/;
    return pattern.test(data);
}

/**
 * Check url is valid
 * @require string url
 * @param {String} url string
 * @return true if url is valid or fail if url is invalid
 * @author HoangQuan
 * @email hminhquan0401@gmail.com
 */
export function isURL(url) {
    let urlPattern = /(https?:\/\/(www\.)?)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_\+.~#?&//=]*)/;
    return urlPattern.test(url);
}

/**
 * Check string is valid
 * @require string need check
 * @param {String} p string
 * @return true if string is not contain white space
 * @author HoangQuan
 * @email hminhquan0401@gmail.com
 */
 export function isNotContainWhiteSpace(p) {
    let stringPattern = /^[-a-zA-Z-()]+(\s+[-a-zA-Z0-9-()]+)*$/;
    return stringPattern.test(p);
}