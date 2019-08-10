$("#btn-login").click(function () {
    var isNull = true;
    //获得表单值
    var username = $("#username").val();
    var password = $("#password").val();
    var imageCode = $("#imageCode").val();
    //如果表单为空，提示用户
    if (username == "" || password == "" || imageCode == "") {
        isNull = false;
        $.dialog({
            autoClose: 2500,
            contentHtml: '<p style="text-align:center;">用户名密码或验证码不能为空！</p>'
        });
    }
    if (isNull) {
        $.ajax({
            "url": "/users/login",
            "data": $("#form-login").serialize(),
            "type": "POST",
            "dataType": "json",
            "success": function (json) {
                if (json.status == 200) {
                    console.log(json.data)
                    //将服务器返回的用户数据json.data存入到cookie
                    //将全局变量存入cookie，参数分别为key名称，key对应的value值，以及cookie的有效期
                    //有效期的单位是"天"
                    setCookie("username", json.data.username, "1");
                    setCookie("role", json.data.role, "1")
                    location.href = "./admin.html";
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