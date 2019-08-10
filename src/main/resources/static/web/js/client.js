$(function () {
    getClient()
})

function getClient(){
    $.ajax({
        "url": "/client/list",
        "type": "GET",
        "dataType": "json",
        "success": function (json) {
            if (json.status == 200) {
                getDataList(json.data);
            } else {
                $.dialog({
                    autoClose: 2500,
                    contentHtml: '<p style="text-align:center;">服务器走神啦！请联系管理员！</p>'
                });
            }
        }
    })
}
$("#search").click(function () {
    $.ajax({
        "url": "/client/list",
        "data": $("#form-input").serialize(),
        "type": "GET",
        "dataType": "json",
        "success": function (json) {
            if (json.status == 200) {
                getDataList(json.data);
            } else {
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
});

function getDate(time) {
    var now = new Date(time),
        y = now.getFullYear(),
        m = now.getMonth() + 1,
        d = now.getDate();
    return y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d) + " " + now.toTimeString().substr(0, 8);
}

var jsonData = [];

function getDataList(list) {
    //获取JSON数据
    var dataArray = eval(list);

    jsonData = dataArray.list;

    //加载数据
    var pageSize = 15;
    var curPage = 1;
    pageTo(pageSize, curPage);
}

//获取当前页数据
function query(cur, size) {
    var begin = (cur - 1) * size;
    var end = cur * size;
    return jsonData.slice(begin, end);
}

//加载表格
function pageTo(pageSize, curPage) {
    $("#client-list").empty();
    //构建目录
    var html = " <table style= 'font-size: 20px ;  margin : 20px; border=solid ' >" +
        "<tbody align=\"center\" valign=\"center\">" +
        "<tr id=\"xs-mx-ml\"  align=\"center\" valign=\"center\" >" +
        "<td  style='width: 12.5% ;padding: 5px'>客户名称</td>" +
        "<td style=\"width: 12.5%\">手机号码</td>" +
        "<td style=\"width: 12.5%\">邮　　箱</td>" +
        "<td style=\"width: 20%\">所住地址</td>" +
        "<td style=\"width: 10%\">状　　态</td>" +
        "<td style=\"width: 10%\">创建时间</td>" +
        "<td style=\"width: 10%\">更新时间</td>" +
        "<td style=\"width: 12.5%\">操　　作</td>" +
        "</tr>";

    var dataSize = jsonData.length;
    var totalPage = Math.ceil(dataSize / pageSize);
    //table
    var datas = query(curPage, pageSize);

    for (var index = 0; index < datas.length; index++) {
        var data = datas[index];
        var isDefault ="";
        if (data.isDefault == 0) {
            isDefault = "有效";
        }
        if (data.isDefault == 1) {
            isDefault = "删除";
        }
        var createTime = getDate(data.createTime);
        var updateTime = getDate(data.updateTime);

        var num = index%2 === 0? 2:1;
        var classs = "xs-mx-m" + num;

        html += "<tr  class="+classs+"  align=\"center\" valign=\"center\" >";
        html += "<td style=\"width: 12.5%\">#{name}</td>";
        html += "<td style=\"width: 12.5%\">#{phone}</td>";
        html += "<td style=\"width: 12.5%\">#{email}</td>";
        html += "<td style=\"width: 20%\">#{addr}</td>";
        html += "<td style=\"width: 10%\">#{isDefault}</td>";
        html += "<td style=\"width: 10%\">#{createTime}</td>"
        html += "<td style=\"width: 10%\">#{updateTime}</td>"
        html += "<td style=\"width: 12.5%\"><button  type='button' style=' border: 0px solid ;background-color: rgba(0, 0, 0, 0)' onclick=\"del(#{id})\">删除</button>&nbsp;"
        html += "<button type=\"button\" style=' border: 0px solid ;background-color: rgba(0, 0, 0, 0)' onclick=\"client_update_style(#{id})\">修改</button></tr>";

        html = html.replace(/#{id}/g, data.id);
        html = html.replace(/#{name}/g, data.name);
        html = html.replace(/#{phone}/g, data.phone);
        html = html.replace(/#{email}/g, data.email);
        html = html.replace(/#{addr}/g, data.addr);
        html = html.replace(/#{isDefault}/g, isDefault);
        html = html.replace(/#{createTime}/g, createTime);
        html = html.replace(/#{updateTime}/g, updateTime);
    }
    html +=  "</tbody> </table>";
    $("#client-list").append(html);

    //pager
    var phtml = "<div class='pager'>";
    if (curPage == 1) {
        phtml = phtml + "<a href='#' class='button no-page'>上一页</a>";
    } else {
        phtml = phtml + "<a href='#' class='button' onclick='pageTo(" + pageSize + ", " + (curPage - 1) + ");'>上一页</a>";
    }
    phtml = phtml + "<input id='input-as' type='text' value='" + curPage + "' onkeypress='goto(this, " + pageSize + ");'>";
    if (curPage == totalPage) {
        phtml = phtml + "<a href='#' class='button no-page'>下一页</a>";
    } else {
        phtml = phtml + "<a href='#' class='button' onclick='pageTo(" + pageSize + ", " + (curPage + 1) + ");'>下一页</a>";
    }
    phtml = phtml + "&nbsp;共" + totalPage + "页," + dataSize + "条记录</div>";
    $("#client-and-input").empty();
    $("#client-and-input").append(phtml);
}

function del(id) {
    $.ajax({//发出异步请求
        "url":"/client/"+id+"/delete",//url:将请求提交到哪里去
        "type":"POST",//type:提交方式
        "dataType":"json",//dataType:服务器端即将响应的数据类型，取值可以是json xml html,例如服务器端响应的可能是application/json,那么该属性值json
        "success":function(json){//success:当服务器成功响应时(Http响应码是200)的回调函数
            if(json.status==200){
                $.dialog({
                    autoClose : 2500,
                    contentHtml : '<p style="text-align:center;">删除成功！</p>'
                });
                getClient();
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
}

function client_update_style(id){
    var state =$("#update-client-yy").css("display")=="none"?"block":"none";
    $("#update-client-yy").css("display",state);

    var isNone =$("#update-client-yy").css("display")=="none"?false:true;
    if (isNone) {
        $.ajax({//发出异步请求
            "url":"/client/"+id+"/client",//url:将请求提交到哪里去
            "type":"POST",//type:提交方式
            "dataType":"json",
            "success":function(json){
                if(json.status==200){
                    $("#updateDetailsId").attr("value",json.data.id);
                    $("#updateName").attr("value", json.data.name);
                    $("#updatePhone").attr("value", json.data.phone);
                    $("#updateEmail").attr("value", json.data.email);
                    $("#updateAddr").attr("value", json.data.addr);
                    if (json.data.isDefault === 0){
                        $("#zc-radio").attr("checked",'checked');
                    }
                    if (json.data.isDefault === 1){
                        $("#sc-radio").attr("checked",'checked');
                    }
                }
            },
            "error": function () {//所有3字头4字头5字头的回应都会被捕获例如：302；404；405；500等
                location.href = "../../err/500.html";
            }
        });
    }
}

function update_client() {
    $.ajax({//发出异步请求
        "url":"/client/update",//url:将请求提交到哪里去
        "data":$("#form-client-update").serialize(),//data:提交的请求参数
        "type":"POST",//type:提交方式
        "dataType":"json",
        "success":function(json){
            if(json.status==200){
                $.dialog({
                    autoClose : 2500,
                    contentHtml : '<p style="text-align:center;">修改成功！</p>'
                });
                client_update_style();
                getClient();
            }else{
                $.dialog({
                    autoClose : 2500,
                    contentHtml :"<p style=\"text-align:center;\">"+json.msg+"</p>"
                });
            }
        },
        "error": function () {//所有3字头4字头5字头的回应都会被捕获例如：302；404；405；500等
            location.href = "../../err/500.html";
        }
    });
}

function condition_client_style(){
    var state =$("#add-client-yy").css("display")=="none"?"block":"none";
    $("#add-client-yy").css("display",state);
}

function condition_client_add() {
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
                        condition_client_style();
                        getClient();
                    } else {
                        $.dialog({
                            autoClose : 2500,
                            contentHtml :"<p style=\"text-align:center;\">"+json.msg+"</p>"
                        });
                    }
                },
                "error": function () {//所有3字头4字头5字头的回应都会被捕获例如：302；404；405；500等
                    location.href = "../../err/500.html";
                }
            });
        }

}