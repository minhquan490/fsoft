import { Product } from "./Product.js";
import * as validate from "./Validate.js";

var productIDObj = $("#productID");
var productNameObj = $("#productName");
var priceObj = $("#price");
var manufacturerObj = $("#manufacturer");
var categoryObj = $("#category");
var currencyObj = $("#currency");
var amountObj = $("#amount");

function productIDBlur() {
    let productID = productIDObj[0].value;
    if (!validate.isValidProductID(productID)) {
        $("#productHelpID span").html("Mã sản phẩm không hợp lệ");
    } else {
        $("#productHelpID span").html("");
    }
}

function productNameBlur() {
    let productName = productNameObj[0].value;
    if (validate.hasNoSpecialCharacter(productName) && validate.isNotContainWhiteSpace(productName)) {
        $("#productNameHelp span").html("");
    } else {
        $("#productNameHelp span").html("");
    }
}