$("#btn-client-add").click(function(){
    var isNull = true;
    var name=$("#name").val();
    var phone=$("#phone").val();
    var email=$("#email").val();
    var addr=$("#addr").val();
    if(name=="" || phone=="" || email=="" || addr==""){
        isNull = false;
        $.dialog({
            autoClose : 2500,
            contentHtml : '<p style="text-align:center;">信息未填写完整！</p>'
        });
    }
    if (isNull) {
        $.ajax({//发出异步请求
            "url": "/client/add",//url:将请求提交到哪里去
            "data": $("#form-client-add").serialize(),//data:提交的请求参数
            "type": "POST",//type:提交方式
            "dataType": "json",//dataType:服务器端即将响应的数据类型，取值可以是json xml html,例如服务器端响应的可能是application/json,那么该属性值json
            "success": function (json) {//success:当服务器成功响应时(Http响应码是200)的回调函数
                if (json.status == 200) {
                    $.dialog({
                        autoClose : 2500,
                        contentHtml :"<p style=\"text-align:center;\">"+json.msg+"</p>"
                    });
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