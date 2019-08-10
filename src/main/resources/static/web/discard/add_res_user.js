
function condition_add_user() {
    var isNull = true;
    var name=$("#deptName").val();
    var sex=$('input:radio[name="sex"]:checked').val();
    var age = $("#deptAge").val();
    var phone=$("#phone").val();
    var province = $("#province").val();
    var city = $("#city").val();
    var deptDate = $("#deptDate").val();
    var deptItem = $("#deptItem").val();
    var deptRemark = $("#deptRemark").val();
    if(name=="" || sex==null || age=="" || phone=="" || province==0 || city==0  || deptDate =="" ||deptItem =="" ||deptRemark==""){
        isNull = false;
        $.dialog({
            autoClose : 2500,
            contentHtml : '<p style="text-align:center;">信息未填写完整！</p>'
        });
    }
    if (isNull) {
        $.ajax({
            "url": "/indexPage/resAdd",
            "data": $("#deptFormUser").serialize(),
            "type": "POST",
            "dataType": "json",
            "success": function (json) {
                if (json.status == 200) {
                    $.dialog({
                        autoClose: 2500,
                        contentHtml: '<p style="text-align:center;">' + json.data + '</p>'
                    });

                    condition_style();
                    setTimeout(function () {
                        location.reload(true);
                    }, 2500)

                } else {
                    $.dialog({
                        autoClose: 2500,
                        contentHtml: '<p style="text-align:center;">' + json.msg + '</p>'
                    });
                }
            },
            "error": function () {//所有3字头4字头5字头的回应都会被捕获例如：302；404；405；500等

                location.href = "../err/500.html";
            }
        });
    }
}


function condition_style() {
    var state =$("#add-yy").css("display")=="none"?"block":"none";
    $("#add-yy").css("display",state);

    var isNone =$("#add-yy").css("display")=="none"?false:true;
    if (isNone) {
        showProvinceList();
        $("#city").append('<option value="0">----- 请选择 -----</option>');
        $("#area").append('<option value="0">----- 请选择 -----</option>');
    }

}
$("#province").change(function() {
    showCityList();

    $("#area").empty();
    $("#area").append('<option value="0">----- 请选择 -----</option>');
});

$("#city").change(function() {
    showAreaList();
});

function showProvinceList() {
    $("#province").append('<option value="0">----- 请选择 -----</option>');

    $.ajax({
        "url":"/districts/",
        "data":"parent=86",
        "type":"GET",
        "dataType":"json",
        "success":function(json) {
            if (json.status == 200) {
                var list = json.data;
                for (var i = 0; i < list.length; i++) {
                    console.log(list[i].name);
                    var op = '<option value="' + list[i].code + '">' + list[i].name + '</option>';
                    $("#province").append(op);
                }
            } else {

            }
        }
    });
}

function showCityList() {
    $("#city").empty();

    $("#city").append('<option value="0">----- 请选择 -----</option>');

    if ($("#province").val() == 0) {
        return;
    }

    $.ajax({
        "url":"/districts/",
        "data":"parent=" + $("#province").val(),
        "type":"GET",
        "dataType":"json",
        "success":function(json) {
            if (json.status == 200) {
                var list = json.data;
                for (var i = 0; i < list.length; i++) {
                    console.log(list[i].name);
                    var op = '<option value="' + list[i].code + '">' + list[i].name + '</option>';
                    $("#city").append(op);
                }
            } else {

            }
        }
    });
}

function showAreaList() {
    $("#area").empty();

    $("#area").append('<option value="0">----- 请选择 -----</option>');

    if ($("#city").val() == 0) {
        return;
    }

    $.ajax({
        "url":"/districts/",
        "data":"parent=" + $("#city").val(),
        "type":"GET",
        "dataType":"json",
        "success":function(json) {
            if (json.status == 200) {
                var list = json.data;
                for (var i = 0; i < list.length; i++) {
                    console.log(list[i].name);
                    var op = '<option value="' + list[i].code + '">' + list[i].name + '</option>';
                    $("#area").append(op);
                }
            } else {

            }
        }
    });
}

