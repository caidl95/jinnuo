$(function(){
    var num=0;
    setInterval(function(){
        num++;
        $("#pngDivId").css({"transform":"rotate("+num+"deg)"})
    },8);
})
$("#btn-password").click(function(){
    var isNull = true;
    //获得表单值
    var passwordOld=$("#passwordOld").val();
    var passwordNew=$("#passwordNew").val();
    //如果表单为空，提示用户
    if(passwordOld=="" || passwordNew=="" ){
        isNull = false;
        $.dialog({
            autoClose : 2500,
            contentHtml : '<p style="text-align:center;">密码不能为空！</p>'
        });
    }
    if (isNull) {
        $.ajax({
            "url": "/users/reset_password",
            "data": $("#form-password").serialize(),
            "type": "POST",
            "dataType": "json",
            "success": function (json) {
                if (json.status == 200) {
                    $.dialog({
                        autoClose : 2500,
                        contentHtml :"<p style=\"text-align:center;\">"+json.data+"</p>"
                    });
                    location.href = "../../index.html";
                } else {
                    $.dialog({
                        autoClose : 2500,
                        contentHtml :"<p style=\"text-align:center;\">"+json.msg+"</p>"
                    });
                }
            },
            "error": function () {//所有3字头4字头5字头的回应都会被捕获例如：302；404；405；500等
                $.dialog({
                    autoClose : 2500,
                    contentHtml : '<p style="text-align:center;">您登录信息已经过期，请重新登录！</p>'
                });
                location.href = "../../index.html";
            }
        });
    }
});


