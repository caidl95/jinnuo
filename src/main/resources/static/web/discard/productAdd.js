$("#btn-product-add").click(function(){
    var isNull = true;
    //获得表单值
    var categoryName=$("#categoryName").val();
    var name=$("#name").val();
    var subtitle=$("#subtitle").val();
    var detail=$("#detail").val();
    var price=$("#price").val();
    var stock=$("#stock").val();
    var radio=$("#radio").val();
    //如果表单为空，提示用户
    if(categoryName=="" || name=="" || subtitle=="" || detail=="" || price=="" || stock=="" || radio=="" ){
        isNull = false;
        $.dialog({
            autoClose : 2500,
            contentHtml : '<p style="text-align:center;">资料未填写完整！</p>'
        });
    }
    if (isNull) {
        $.ajax({//发出异步请求
            "url": "/product/add",//url:将请求提交到哪里去
            "data": new FormData($("#form-product-add")[0]),//data:提交的请求参数
            "type": "POST",//type:提交方式
            "contentType": false,
            "processData": false,
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