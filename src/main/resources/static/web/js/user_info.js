function condition_user_style(){
    var state =$("#add-user-yy").css("display")=="none"?"block":"none";
    $("#add-user-yy").css("display",state);
}

function condition_user_add() {
    var isNull = true;
    var name=$("#deptName").val();
    var password = $("#deptPassword").val();
    var email = $("#deptEmail").val();
    var phone = $("#deptPhone").val();
    var question = $("#deptQuestion").val();
    var answer = $("#deptAnswer").val();
    if(name=="" || password=="" || nickname=="" || phone=="" || email==0 || question==0  || answer ==""){
        isNull = false;
        $.dialog({
            autoClose : 2500,
            contentHtml : '<p style="text-align:center;">信息未填写完整！</p>'
        });
    }
    if (isNull) {
        $.ajax({
            "url": "/users/reg",
            "data": $("#deptForm").serialize(),
            "type": "POST",
            "dataType": "json",
            "success": function (json) {
                if (json.status == 200) {
                    $.dialog({
                        autoClose: 2000,
                        contentHtml: '<p style="text-align:center;">' + json.data + '</p>'
                    });
                    condition_user_style();
                    setTimeout(function () {
                        location.reload(true);
                    }, 1800)
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

function resizeElementHeight() {
    var height = 0;
    var body = window.document.body;
    if (window.innerHeight) {
        height = window.innerHeight;
    } else if (body.parentElement.clientHeight) {
        height = body.parentElement.clientHeight;
    } else if (body && body.clientHeight) {
        height = body.clientHeight;
    }
    height =  height*0.7 +"px";
    $("#appointment-list").attr("style","height:"+height);
}

$(function () {
  /*  setInterval(function(){
        resizeElementHeight();
    },500);*/
    getUserList();


})
function getUserList() {

    $.ajax({
        "url": "/users/",
        "type": "GET",
        "dataType": "json",
        "success": function (json) {
            if (json.status == 200) {
                getDataList(json.data);
            }
        },
        "error": function () {//所有3字头4字头5字头的回应都会被捕获例如：302；404；405；500等
            /* location.href = "../err/500.html";*/
        }
    });

}

//转换时间格式
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
    console.log("asd" + dataArray);
    jsonData = dataArray;
    //加载数据
    var pageSize = 14;
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
    //调用时清空原来内容
    $("#appointment-list").empty();

    //构建目录
    var html = " <table id='table-users' style= 'font-size: 20px;' >" +
        "<tbody align=\"center\" valign=\"center\">" +
        "<tr id=\"xs-mx-ml\"  align=\"center\" valign=\"center\" bgcolor='#F2F3F8' >" +
        "<td  style='width: 16%;padding: 5px'>用户名</td>" +
        "<td style=\"width: 16%\">角色</td>" +
        "<td style=\"width: 16%\">邮箱</td>" +
        "<td style=\"width: 16%\">手机号码</td>" +
        "<td style=\"width: 16%\">创建时间</td>" +
        "<td style=\"width: 17%\">操作</td>" +
        "</tr>";

    //获取当前页数
    var dataSize = jsonData.length;
    var totalPage = Math.ceil(dataSize / pageSize);
    //table
    var datas = query(curPage, pageSize);
    for (var index = 0; index < datas.length; index++) {
        var data = datas[index];
        var num = index%2 == 0? 2:1;
        var classs = "xs-mx-m" + num;
        var createTime = getDate(data.createTime);
        var role = "";
        if (data.role == 0){
            role = "管理员";
        } else {
            role = "操作员";
        }

        html += "<tr  class="+classs+" id='xs-mx-m1'  align=\"center\" valign=\"center\" >";
        html += "<td style='width: 16%;padding: 5px'>#{username}</td>";
        html += "<td style=\"width: 16%\">#{role}</td>";
        html += "<td style=\"width: 16%\">#{email}</td>";
        html += "<td style=\"width: 16%\">#{phone}</td>";
        html += "<td style=\"width: 16%\">#{createTime}</td>";
        html += "<td style=\"width: 17%\"><input style=' border: 0px solid ;background-color: rgba(0, 0, 0, 0)' type='button' value='删除' onclick='condition_user_delete(#{id})'>&nbsp; <input onclick='condition_users_update(#{id})' style=' border: 0px solid ;background-color: rgba(0, 0, 0, 0)' type='button' value='修改'></td></tr>";

        html = html.replace(/#{id}/g, data.id);
        html = html.replace(/#{username}/g, data.username);
        html = html.replace(/#{role}/g, role);
        html = html.replace(/#{email}/g, data.email);
        html = html.replace(/#{phone}/g, data.phone);
        html = html.replace(/#{createTime}/g, createTime);
    }
    html +=  "</tbody> </table>";
    $("#appointment-list").append(html);


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
    $("#appointment-and-input").empty();
    $("#appointment-and-input").append(phtml);

}


function condition_user_delete(id){
    var isDelete = true;
    if (id <= 10){
        $.dialog({
            autoClose: 2500,
            contentHtml: "<p style=\"text-align:center;\">无法删除 admin 用户</p>"
        });
        isDelete = false;
    }
    if (isDelete) {
        $.ajax({
            "url": "/users/"+id+"/delete",
            "type": "GET",
            "dataType": "json",
            "success": function (json) {
                if (json.status == 200) {
                    $.dialog({
                        autoClose: 2500,
                        contentHtml: "<p style=\"text-align:center;\">" + json.data + "</p>"
                    });
                    getUserList();
                } else {
                    $.dialog({
                        autoClose: 2500,
                        contentHtml: "<p style=\"text-align:center;\">" + json.msg + "</p>"
                    });
                }
            },
            "error": function () {//所有3字头4字头5字头的回应都会被捕获例如：302；404；405；500等
                location.href = "../err/noAuth.html";
            }
        });
    }
}


function condition_users_update(id) {

    var state =$("#update-yy").css("display")=="none"?"block":"none";
    $("#update-yy").css("display",state);
    var isNone =$("#update-yy").css("display")=="none"?false:true;

    $("#updateDetailsId").attr("value", "");
    $("#updateName").attr("value", "");
    $("#updateEmail").attr("value", "");
    $("#updatePhone").attr("value", "");
    if (isNone) {

        $.ajax({
            "url": "/users/"+id+"/get_user_id",
            "type": "GET",
            "dataType": "json",
            "success": function (json) {
                if (json.status == 200) {
                    console.log(json.data);
                    $("#updateDetailsId").attr("value", json.data.id);
                    $("#updateName").attr("value", json.data.loginname);
                    $("#updateEmail").attr("value", json.data.email);
                    $("#updatePhone").attr("value", json.data.phone);
                }
            }
        });
    }
}
function condition_role_update(){

    $.ajax({
        "url": "/users/update_id",
        "data": $("#deptFormUpdate").serialize(),
        "type": "POST",
        "dataType": "json",
        "success": function (json) {
            if (json.status == 200) {
                $.dialog({
                    autoClose: 2500,
                    contentHtml: "<p style=\"text-align:center;\">" + json.msg + "</p>"
                });
                condition_users_update();
                getUserList();
            } else {
                $.dialog({
                    autoClose: 2500,
                    contentHtml: "<p style=\"text-align:center;\">" + json.msg + "</p>"
                });
            }
        }

    });
}
