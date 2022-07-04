var dataSet = [];

$(document).ready(function () {
    intiliziTable();
});

function intiliziTable(){
    $('#table').DataTable({
        data: dataSet,
        columns: [
          {title: 'STT', class: "row-num"},
          {title: 'Họ và tên'},
          {title: 'Giới tính'},
          {title: 'Ngày sinh'},
          {title: 'CCCD/CMND'},
          {title: 'Vacxin'},
          {title: 'Ngày tiêm'},
          {title: 'Action'},
        ],
    });
}

var fullName = $("#name");
var fullNameRegex = /^[-a-zA-Z-()]+(\s+[-a-zA-Z0-9-()]+)*$/;
fullName.on("blur", function () {
  validateInput(
    fullName,
    fullNameRegex,
    "Regex only accept character and space!",
    "Full Name"
  );
});

var birthDate = $("#birth-date");
birthDate.on("blur", function () {
    validateDateAndNow(birthDate);
});

var gender = $("#gender");
gender.on("blur", function () {
    validateEmptyCombobox(gender, "gender");
});

var address = $("#address");
var addressRegex = /^[-a-zA-Z-()]+(\s+[-a-zA-Z-()]+)*$/;
address.on("blur", function () {
  validateInput(
    address,
    addressRegex,
    "Regex only accept character and space!",
    "Address"
  );
});

var cccd = $("#cccd");
var cccdRegex = /^[0-9]{12}$/
cccd.on("blur", function () {
    validateInput(
      cccd,
      cccdRegex,
      "Accept 12 digit",
      "CCCD/CMND"
    );
});

var vacxinValue = "";
var vacxin = document.getElementsByName("vacxin");
var a = []
vacxin.forEach(ele => {a.push(ele)});
for (let index = 0; index < a.length; index++) {
    if (a[index].checked) {
        vacxinValue = a[index];
        if (vacxinValue.value !== "") {
            vacxinValue.addEventListener('change', function (e) {
                e.preventDefault();
                $("#number-vacxin").removeAttr("disabled");
                var numberVacxin = $("#number-vacxin");
                if (numberVacxin == 1) {
                    $("#vacxin-sl-1").parent().removeClass("vacxin-date").addClass("a");
                } else if (numberVacxin == 2) {
                    $("#vacxin-sl-1").parent().removeClass("vacxin-date").addClass("a");
                    $("#vacxin-sl-2").parent().removeClass("vacxin-date").addClass("a");
                } else if (numberVacxin == 3) {
                    $("#vacxin-sl-1").parent().removeClass("vacxin-date").addClass("a");
                    $("#vacxin-sl-2").parent().removeClass("vacxin-date").addClass("a");
                    $("#vacxin-sl-3").parent().removeClass("vacxin-date").addClass("a");
                }
            });
        } else {
            $("#number-vacxin").attr("disabled", true);
        }
    }
}

var vacxin1 = $("#vacxin-sl-1").val().split("-");
var vacxin2 = $("#vacxin-sl-2").val().split("-");
var vacxin3 = $("#vacxin-sl-3").val().split("-");

var addButton = $("#add-btn");
addButton.on("click", function (e) {
    e.preventDefault();
    var b = [];
    var checkVacxin1 = vacxin1.forEach(ele=>{
        if (ele != "") {
            return false;
        }
        b.push(vacxin1);
        return true;
    });
    var checkVacxin2 = vacxin2.forEach(ele=>{
        if (ele != "") {
            return false;
        }
        b.push(vacxin1);
        return true;
    });
    var checkVacxin3 = vacxin3.forEach(ele=>{
        if (ele != "") {
            return false;
        }
        b.push(vacxin1);
        return true;
    });
    var checkFullName = validateInput(
        fullName,
        fullNameRegex,
        "Regex only accept character and space!",
        "Full Name"
      );
    var checkBirthDate = validateDateAndNow(birthDate, "Birth Date");
    var checkAddress = validateInput(
      address,
      addressRegex,
      "Regex only accept character and space!",
      "Topic"
    );
    var checkCCCD = validateInput(
        cccd,
        cccdRegex,
        "Accept 12 digit",
        "CCCD/CMND"
      );
    var checkGender = validateEmptyCombobox(gender, "gender");
    
    if (
      checkFullName&&checkBirthDate&&checkAddress&&checkCCCD&&checkGender
    ) {
      $('#table').DataTable().destroy();
      $('#table').empty();
      var rowId = Date.now();
      let index = dataSet.length;
      // var dataRow = `
      //   <tr>
      //     <th scope="row" class="row-num">1</th>
      //     <td>${classSelect.val()}</td>
      //     <td>${subjectSelect.val()}</td>
      //     <td class="teacher-name">${teacher.val()}</td>
      //     <td>${topic.val()}</td>
      //     <td>${formatDate(
      //       trainDate.val()
      //     )} ${startTime.val()}-${endTime.val()}</td>
      //     <td>${method.val()}</td>
      //     <td><button id="${rowId}" class="btn btn-outline-danger delete-btn" data-toggle="modal" data-target="#confirm-modal"><i class="far fa-trash-alt"></i></button></td>
      //   </tr>
      // `;
      // bodyTable.append(dataRow);
      var data = [parseInt(index) + 1,`${fullName.val()}`,`${gender.val()}`,`${birthDate.val()}`,`${cccd.val()}`,`${vacxinValue.value}`,`${b}`,`<button id="${rowId}" class="btn btn-outline-danger delete-btn" data-toggle="modal" data-target="#confirm-modal"><i class="far fa-trash-alt"></i></button>`];
      dataSet.push(data);
      intiliziTable();
      resetAllField();
      vacxinValue = "";
      $("#vacxin-sl-1").parent().removeClass("a").addClass("vacxin-date");
      $("#vacxin-sl-2").parent().removeClass("a").addClass("vacxin-date");
      $("#vacxin-sl-3").parent().removeClass("a").addClass("vacxin-date");
    }
  });

  function resetAllField() {
    var inputField = $("input");
    inputField.each(function () {
      $(this).removeClass("is-valid").removeClass("is-invalid");
      $(this).val("");
    });
    var selectEle = $("select");
    selectEle.each(function () {
      $(this).removeClass("is-valid").removeClass("is-invalid");
      $(this).val("");
    });
  }

  let idConfirm;
$("#confirm-modal").on("show.bs.modal", function (e) {
  idConfirm = $(e.relatedTarget).attr("id");
});

$("#remove-btn").on("click", function () {
  let rowData = $(".delete-btn");
  rowData.each(function () {
    if ($(this).attr("id") == idConfirm) {
      $(this).parent().parent().remove();
      $("#confirm-modal").modal("hide");
      dataSet.pop(dataSet[idConfirm]);
      $('#table').DataTable().destroy();
      $('#table').empty();
      intiliziTable();
      return;
    }
  });
});