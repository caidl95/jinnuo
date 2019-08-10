$(function () {
    getCategory();
})


function getCategory(){
    $.ajax({
        "url": "/get_category",
        "type": "GET",
        "dataType": "json",
        "success": function (json) {
            if (json.status == 200) {
                getDataList(json.data);
            } else {
                $.dialog({
                    autoClose : 2500,
                    contentHtml : '<p style="text-align:center;">服务器走神啦！请联系管理员！</p>'
                });
            }
        }
    });
}

$("#search").click(function () {
    $.ajax({
        "url": "/get_category",
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
   /* var dataArray = eval(list);*/
    jsonData = list;/*dataArray.
*/
    //加载数据
    var pageSize = 12;
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

    $("#category-list").empty();
    //构建目录
    var html = " <table style= 'font-size: 20px ;  margin : 20px; width: 97% ' border:solid >" +
        "<tbody align=\"center\" valign=\"center\">" +
        "<tr id=\"xs-mx-ml\"  align=\"center\" valign=\"center\" >" +
        "<td  style='width: 9% ;padding: 5px'>父类ID</td>" +
        "<td style=\"width: 9%\">类别Id</td>" +
        "<td style=\"width: 10%\">名称</td>" +
        "<td style=\"width: 8%\">状态</td>" +
        "<td style=\"width: 10%\">排序编号</td>" +
        "<td style=\"width: 20%\">创建时间</td>" +
        "<td style=\"width: 20%\">修改时间</td>" +
        "<td style=\"width: 14%\">操作</td>" +
        "</tr>";
    var dataSize = jsonData.length;
    var totalPage = Math.ceil(dataSize / pageSize);
    var datas = query(curPage, pageSize);
    for (var index = 0; index < datas.length; index++) {
        var data = datas[index];
        var status = ""
        if (data.status == 1) {
            status = "正常";
        }
        if (data.status == 2) {
            status = "废弃";
        }
        var createTime = getDate(data.createTime);
        var updateTime = getDate(data.updateTime);
        var num = index%2 == 0? 2:1;
        var classs = "xs-mx-m" + num;

        html += "<tr  class="+classs+"  align=\"center\" valign=\"center\" >";
        html += "<td style='width: 9% ;padding: 5px'>#{parentId}</td>";
        html += "<td style=\"width: 9%\">#{Id}</td>";
        html += "<td style=\"width: 10%\">#{name}</td>";
        html += "<td style=\"width: 8%\">#{status}</td>";
        html += "<td style=\"width: 10%\">#{sortOrder}</td>";
        html += "<td style=\"width: 20%\">#{createTime}</td>";
        html += "<td style=\"width: 20%\">#{updateTime}</td>";
        html += "<td style=\"width: 14%\"><button  type='button' style=' border: 0px solid ;background-color: rgba(0, 0, 0, 0)' onclick='del_category(#{Id})'>删除</button>&nbsp;";
        html += "<button type=\"button\" style=' border: 0px solid ;background-color: rgba(0, 0, 0, 0)' onclick='update_category(#{Id})'>修改</button></tr>";

        html = html.replace(/#{parentId}/g, data.parentId);
        html = html.replace(/#{Id}/g, data.id);
        html = html.replace(/#{name}/g, data.name);
        html = html.replace(/#{status}/g, status);
        html = html.replace(/#{sortOrder}/g, data.sortOrder);
        html = html.replace(/#{createTime}/g, createTime);
        html = html.replace(/#{updateTime}/g, updateTime);

    }
    html +=  "</tbody> </table>";
    $("#category-list").append(html);

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
    $("#category-and-input").empty();
    $("#category-and-input").append(phtml);
}

function del_category(id) {
    $.dialog({
        autoClose : 2500,
        contentHtml : '<p style="text-align:center">功能尚未开放！</p>'
    });

}
function update_category(id) {
    $.dialog({
        autoClose : 2500,
        contentHtml : '<p style="text-align:center">功能尚未开放！</p>'
    });
}
function condition_category_style() {
    var state =$("#add-category-yy").css("display")=="none"?"block":"none";
    $("#add-category-yy").css("display",state);
}

function condition_category_add() {
        var isNull = true;
        var categoryName=$("#categoryName").val();
        if(categoryName==""){
            isNull = false;
            $.dialog({
                autoClose : 2500,
                contentHtml : '<p style="text-align:center;">名字不能为空！</p>'
            });
        }
        if (isNull) {
            $.ajax({//发出异步请求
                "url": "/add_category",
                "data": $("#form-category-add").serialize(),
                "type": "POST",//type:提交方式
                "dataType": "json",
                "success": function (json) {
                    if (json.status == 200) {
                        $.dialog({
                            autoClose : 2500,
                            contentHtml :"<p style=\"text-align:center;\">"+json.msg+"</p>"
                        });
                        condition_category_style();
                        getCategory();
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