$(document).ready(function () {
    $("#soLuong").val(1);
    giamSoLuong();
    tangSoLuong();
    kiemTraGiaTri();
})

function giamSoLuong() {
    $("#subBtn").on("click", function () {
        let $soLuong = $("#soLuong");
        if ($soLuong.val() > 1) {
            $soLuong.val($soLuong.val() - 1);
            thanhTien();
        } else {
            alert("Số lượng sản phẩm cần mua tối thiểu là 1");
        }
    })
}

function tangSoLuong() {
    $("#addBtn").on("click", function () {
        let $soLuong = $("#soLuong");
        $soLuong.val(parseInt($soLuong.val()) + 1);
        thanhTien();
    })
}

function kiemTraGiaTri() {
    $("#soLuong").on("input", function () {
        let $soLuong = $("#soLuong").val();
        if (!($soLuong / 2)) {
            alert("Vui lòng nhập số!");
            $("#soLuong").val(1);
        } 
        $("#price").text($("#soLuong").val() * 1000);
    })
}

function thanhTien() {
    $("#price").text($("#soLuong").val() * 1000);
}