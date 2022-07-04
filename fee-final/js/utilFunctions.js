//Function check empty of combobox
function validateEmptyCombobox(element, elementName) {
  var invalidMess = element.parent().find(".invalid-mess");
  var flag = false;
  if (element.val() == "") {
    invalidMess.text("Please choose " + elementName);
    invalidMess.css("visibility", "visible");
    element.addClass("is-invalid").removeClass("is-valid");
  } else {
    invalidMess.css("visibility", "hidden");
    element.addClass("is-valid").removeClass("is-invalid");
    flag = true;
  }
  return flag;
}

//Function validate input with regex, 3 param
function validateInput(element, regex, message, elementName) {
  var invalidMess = element.parent().find(".invalid-mess");
  var flag = false;
  if (element.val() == "") {
    invalidMess.text(elementName + " is required!");
    invalidMess.css("visibility", "visible");
    element.addClass("is-invalid").removeClass("is-valid");
  } else if (!regex.test(element.val())) {
    invalidMess.text(message);
    invalidMess.css("visibility", "visible");
    element.addClass("is-invalid").removeClass("is-valid");
  } else {
    invalidMess.css("visibility", "hidden");
    element.addClass("is-valid").removeClass("is-invalid");
    flag = true;
  }
  return flag;
}

//Function validate checked checkbox
function validateCheckbox(element, elementName) {
  var invalidMess = element.parent().find(".invalid-mess");
  var flag = false;
  if (!element.prop("checked")) {
    invalidMess.text("Please check " + elementName);
    invalidMess.css("visibility", "visible");
  } else {
    invalidMess.css("visibility", "hidden");
    flag = true;
  }
  return flag;
}

//Validate date and now
function validateDateAndNow(element) {
  var invalidMess = element.parent().find(".invalid-mess");
  var now = new Date();
  var chooseDate = new Date(element.val());
  var flag = false;
  if (chooseDate == "Invalid Date") {
    invalidMess.text("Please choose date");
    invalidMess.css("visibility", "visible");
    element.addClass("is-invalid").removeClass("is-valid");
  } else if (chooseDate <= now) {
    invalidMess.text("Chosed date must be greater than now!");
    invalidMess.css("visibility", "visible");
    element.addClass("is-invalid").removeClass("is-valid");
  } else {
    element.addClass("is-valid").removeClass("is-invalid");
    invalidMess.css("visibility", "hidden");
    flag = true;
  }
  return flag;
}

//Format money without money symbol
function formatMoneyWithoutSymbol(number) {
  let dollarUSLocale = Intl.NumberFormat("en-US");
  return dollarUSLocale.format(number);
}

//Format date to dd/MM/yyyy
function formatDate(stringDate) {
  var date = new Date(stringDate);
  return date.toLocaleDateString("en-GB");
}
