$("#btn-get-question").click(function () {
    var isNull = true;
    var username = $("#username").val();
    if (username == "") {
        isNull = false;
        $.dialog({
            autoClose: 2500,
            contentHtml: '<p style="text-align:center;">用户名不能为空！</p>'
        });
    }
    if (isNull) {
        $.ajax({
            "url": "/users/forget_get_question",
            "data": $("#form-get").serialize(),
            "type": "GET",
            "dataType": "json",
            "success": function (json) {
                if (json.status == 200) {
                    $("#div-get-question").css("display", "block");
                    $("#question").val(json.data);
                } else {
                    $.dialog({
                        autoClose: 2500,
                        contentHtml: "<p style=\"text-align:center;\">" + json.msg + "</p>"
                    });
                }
            }
        });
    }
});

$("#btn-get-token").click(function () {
    var isNull = true;
    var answer = $("#answer").val();
    if (answer == "") {
        isNull = false;
        $.dialog({
            autoClose: 2500,
            contentHtml: '<p style="text-align:center;">答案不能为空！</p>'
        });
    }
    if (isNull) {
        $.ajax({
            "url": "/users/forget_check_answer",
            "data": $("#form-get").serialize(),
            "type": "POST",
            "dataType": "json",
            "success": function (json) {
                if (json.status == 200) {
                    $("#div-update-password").css("display", "block");
                    $("#token").val(json.data);
                } else {
                    $.dialog({
                        autoClose: 2500,
                        contentHtml: "<p style=\"text-align:center;\">" + json.msg + "</p>"
                    });
                }
            }
        });
    }
});

$("#btn-get-update").click(function () {
    var isNull = true;
    var password = $("#password").val();
    if (password == "") {
        isNull = false;
        $.dialog({
            autoClose: 2500,
            contentHtml: '<p style="text-align:center;">密码不能为空！</p>'
        });
    }
    if (isNull) {
        $.ajax({
            "url": "/users/forget_rest_password",
            "data": $("#form-get").serialize(),
            "type": "POST",
            "dataType": "json",
            "success": function (json) {
                if (json.status == 200) {
                    $.dialog({
                        autoClose: 2500,
                        contentHtml: '<p style="text-align:center;">修改成功！</p>'
                    });
                    location.href = "../../index.html";
                } else {
                    $.dialog({
                        autoClose: 2500,
                        contentHtml: "<p style=\"text-align:center;\">" + json.msg + "</p>"
                    });
                }
            }
        });
    }
});



	


		

	
