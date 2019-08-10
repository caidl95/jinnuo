$(function(){
	var num=0;
	setInterval(function(){
		num++;
		$("#pngDivId").css({"transform":"rotate("+num+"deg)"})
	},8);
})

$("#btn-reg").click(function(){
    var isNull = true;
    //获得表单值
    var username=$("#username").val();
    var password=$("#password").val();
    var email=$("#email").val();
    var phone=$("#phone").val();
    var question=$("#question").val();
    var answer=$("#answer").val();
    //如果表单为空，提示用户
    if(username=="" || password=="" || email=="" || phone=="" || question=="" || answer==""){
        isNull = false;
        $.dialog({
            autoClose : 2500,
            contentHtml : '<p style="text-align:center;">资料未填写完整！</p>'
        });
    }
    if (isNull) {
        $.ajax({//发出异步请求
            "url": "/users/reg",//url:将请求提交到哪里去
            "data": $("#form-reg").serialize(),//data:提交的请求参数
            "type": "POST",//type:提交方式
            "dataType": "json",//dataType:服务器端即将响应的数据类型，取值可以是json xml html,例如服务器端响应的可能是application/json,那么该属性值json
            "success": function (json) {//success:当服务器成功响应时(Http响应码是200)的回调函数
                if (json.status == 200) {
                    $.dialog({
                        autoClose : 2500,
                        contentHtml :"<p style=\"text-align:center;\">"+json.data+"</p>"
                    });
                } else {
                    $.dialog({
                        autoClose : 2500,
                        contentHtml :"<p style=\"text-align:center;\">"+json.msg+"</p>"
                    });
                }
            }
        });
    }
});


