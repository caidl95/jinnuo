$(function () {
    $("#users-logout").click(function () {
        $.ajax({
            "url": "/users/logout",
            "type": "POST",
            "dataType": "json",
            "success": function (json) {
                if (json.status == 200) {
                    location.href = "index.html";
                } else {
                    $.dialog({
                        autoClose: 2500,
                        contentHtml: "<p style=\"text-align:center;\">" + json.msg + "</p>"
                    });
                }
            }
        });
    });
})


/* 显示个人信息 */
function condition_user() {
    var state =$("#add-yy").css("display")=="none"?"block":"none";
    $("#add-yy").css("display",state);

    $("#update-password-yy").css("display","none");
    var isNone =$("#add-yy").css("display")=="none"?false:true;

    if (isNone){
        $.ajax({//发出异步请求
            "url":"/users/get_user",
            "type":"GET",//type:提交方式
            "dataType":"json",//dataType:服务器端即将响应的数据类型，取值可以是json xml html,例如服务器端响应的可能是application/json,那么该属性值json
            "success":function(json){
                if(json.status==200){
                    $("#deptUser").attr("value",json.data.username)
                    $("#deptEmail").attr("value",json.data.email)
                    $("#deptSeq").attr("value",json.data.phone)
                    $("#deptQuestion").attr("value",json.data.question)
                    $("#deptAnswer").attr("value",json.data.answer)
                }else{
                    $.dialog({
                        autoClose : 2500,
                        contentHtml :"<p style=\"text-align:center;\">"+json.msg+"</p>"
                    });
                }
            },
            "error": function () {//所有3字头4字头5字头的回应都会被捕获例如：302；404；405；500等

                location.href = "./err/500.html";
            }
        });
    }
}

/*修改个人信息*/
function condition_user_update(){
    var isNull = true;
    //获得表单值
    var email=$("#deptEmail").val();
    var phone=$("#deptSeq").val();
    var question= $("#deptQuestion").val();
    var answer= $("#deptAnswer").val();

    //如果表单为空，提示用户
    if( email==""  || phone=="" || question=="" || answer==""){
        isNull = false;
        $.dialog({
            autoClose : 2500,
            contentHtml : '<p style="text-align:center;">资料未填写完整！</p>'
        });
    }
    if (isNull) {
        $.ajax({//发出异步请求
            "url": "/users/update_information",//url:将请求提交到哪里去
            "data": $("#deptForm").serialize(),//data:提交的请求参数
            "type": "POST",//type:提交方式
            "dataType": "json",
            "success": function (json) {
                if (json.status == 200) {
                    $.dialog({
                        autoClose : 2500,
                        contentHtml :"<p style=\"text-align:center;\">"+json.msg+"</p>"
                    });
                    condition_user();
                } else {
                    $.dialog({
                        autoClose : 2500,
                        contentHtml :"<p style=\"text-align:center;\">"+json.msg+"</p>"
                    });
                }
            },
            "error": function () {//所有3字头4字头5字头的回应都会被捕获例如：302；404；405；500等
                location.href = "./err/500.html";
            }
        });
    }
}

function condition_user_password_style() {
    var state =$("#update-password-yy").css("display")=="none"?"block":"none";
    $("#update-password-yy").css("display",state);
    $("#add-yy").css("display","none");
}

function condition_user_password() {
    var isNone = true;
    //获得表单值
    var passwordOld=$("#deptPassword").val();
    var passwordNew=$("#passwordNew").val();
    var deptPasswordNew=$("#deptPasswordNew").val();
    //如果表单为空，提示用户
    if(passwordOld==="" || passwordNew==="" || deptPasswordNew ===""){
        isNone = false;
        $.dialog({
            autoClose : 2500,
            contentHtml : '<p style="text-align:center;">密码不能为空！</p>'
        });
    }
    if (passwordNew !== deptPasswordNew) {
        $.dialog({
            autoClose : 2500,
            contentHtml : '<p style="text-align:center;">新密码不一致请重新输入！</p>'
        });
       $("#deptPassword").val("");
        $("#passwordNew").val("");
        $("#deptPasswordNew").val("");
    }
    if (isNone){
        $.ajax({
            "url": "/users/reset_password",
            "data": $("#updateForm").serialize(),
            "type": "POST",
            "dataType": "json",
            "success": function (json) {
                if (json.status == 200) {
                    $.dialog({
                        autoClose : 1500,
                        contentHtml :"<p style=\"text-align:center;\">"+json.msg+"</p>"
                    });

                    setTimeout(function () {
                        location.href = "/index.html";
                    }, 1500)

                } else {
                    $.dialog({
                        autoClose : 2500,
                        contentHtml :"<p style=\"text-align:center;\">"+json.msg+"</p>"
                    });
                }
            },
            "error": function () {//所有3字头4字头5字头的回应都会被捕获例如：302；404；405；500等
                location.href = "err/noAuth.html";
            }
        });
    }
}


