function showCartList() {
    $.ajax({//发出异步请求
        "url":"/users/get_information",//url:将请求提交到哪里去
        "type":"GET",//type:提交方式
        "dataType":"json",//dataType:服务器端即将响应的数据类型，取值可以是json xml html,例如服务器端响应的可能是application/json,那么该属性值json
        "success":function(json){
            if(json.status==200){
                $("#username").attr("value",json.data.username)
                $("#email").attr("value",json.data.email)
                $("#phone").attr("value",json.data.phone)
                $("#question").attr("value",json.data.question)
                $("#answer").attr("value",json.data.answer)
            }else{
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
};

$("#btn-update").click(function(){
    var isNull = true;
    //获得表单值
    var username=$("#username").val();
    var email=$("#email").val();
    var phone=$("#phone").val();
    var question=$("#question").val();
    var answer=$("#answer").val();
    //如果表单为空，提示用户
    if(username=="" || email=="" || phone=="" || question=="" || answer==""){
        isNull = false;
        $.dialog({
            autoClose : 2500,
            contentHtml : '<p style="text-align:center;">资料未填写完整！</p>'
        });
    }
    if (isNull) {
        $.ajax({//发出异步请求
            "url": "/users/update_information",//url:将请求提交到哪里去
            "data": $("#form-update").serialize(),//data:提交的请求参数
            "type": "POST",//type:提交方式
            "dataType": "json",
            "success": function (json) {
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







	


		

	
