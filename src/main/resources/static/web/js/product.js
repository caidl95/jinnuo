$(function () {
    getProduct()
})

function getProduct(){
    $.ajax({
        "url": "/product/list",
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
    });
}


$("#search").click(function () {
    $.ajax({
        "url": "/product/list",
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
    console.log("asd" + dataArray.list);
    jsonData = dataArray.list;
    //加载数据
    var pageSize = 10;
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
    $("#product-list").empty();

    //构建目录
    var html = " <table style= 'font-size: 20px ;  margin : 20px; border=solid' >" +
        "<tbody align=\"center\" valign=\"center\">" +
        "<tr id=\"xs-mx-ml\"  align=\"center\" valign=\"center\" >" +
        "<td  style='width: 8% ;padding: 5px'>分类</td>" +
        "<td style=\"width: 8%\">名称</td>" +
        "<td style=\"width: 10%\">标题</td>" +
        "<td style=\"width: 6%\">单价</td>" +
        "<td style=\"width: 6%\">数量</td>" +
        "<td style=\"width: 6%\">状态</td>" +
        "<td style=\"width: 6%\">位置</td>" +
        "<td style=\"width: 20%\">详情</td>" +
        "<td style=\"width: 10%\">创建时间</td>" +
        "<td style=\"width: 10%\">修改时间</td>" +
        "<td style=\"width: 10%\">操作</td>" +
        "</tr>";
    var dataSize = jsonData.length;
    var totalPage = Math.ceil(dataSize / pageSize);
    //table
    var datas = query(curPage, pageSize);
    for (var index = 0; index < datas.length; index++) {
        var data = datas[index];
        var place = ""
        var status ="";
        if (data.place == 1) {
            place = "仓库 ";
        }
        if (data.place == 2) {
            place = "店面 ";
        }
        if (data.status == 1) {
            status = "在售";
        }
        if (data.status == 2) {
            status = "下架";
        }
        if (data.status == 3) {
            status = "删除";
        }
        var createTime = getDate(data.createTime);
        var updateTime = getDate(data.updateTime);

        var num = index%2 == 0? 2:1;
        var classs = "xs-mx-m" + num;

        html += "<tr  class="+classs+"  align=\"center\" valign=\"center\" >";
        html += "<td style=\"width: 8%\">#{categoryName}</td>";
        html += "<td style=\"width: 8%\">#{name}</td>";
        html += "<td style=\"width: 10%\">#{subtitle}</td>";
        html += "<td style=\"width: 6%\">#{price}</td>";
        html += "<td style=\"width: 6%\">#{stock}</td>";
        html += "<td style=\"width: 6%\">#{status}</td>"
        html += "<td style=\"width: 6%\">#{place}</td>"
        html += "<td style=\"width: 20%\">#{detail}</td>"
        html += "<td style=\"width: 10%\">#{createTime}</td>"
        html += "<td style=\"width: 10%\">#{updateTime}</td>"
        html += "<td style=\"width: 10%\"><button  type='button' style=' border: 0px solid ;background-color: rgba(0, 0, 0, 0)' onclick=\"condition_delete(#{id})\">删除</button>&nbsp;"
        html += "<button type=\"button\" style=' border: 0px solid ;background-color: rgba(0, 0, 0, 0)' onclick=\"condition_update_style(#{id})\">修改</button></tr>";

        html = html.replace(/#{id}/g, data.id);
        html = html.replace(/#{categoryName}/g, data.categoryName);
        html = html.replace(/#{name}/g, data.name);
        html = html.replace(/#{subtitle}/g, data.subtitle);
        html = html.replace(/#{detail}/g, data.detail);
        html = html.replace(/#{price}/g, data.price);
        html = html.replace(/#{stock}/g, data.stock);
        html = html.replace(/#{status}/g,status);
        html = html.replace(/#{place}/g, place);
        html = html.replace(/#{createTime}/g, createTime);
        html = html.replace(/#{updateTime}/g, updateTime);
    }
    html +=  "</tbody> </table>";
    $("#product-list").append(html);

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
    $("#product-and-input").empty();
    $("#product-and-input").append(phtml);
}
function condition_delete(id) {
    $.ajax({//发出异步请求
        "url":"/product/"+id+"/delete",//url:将请求提交到哪里去
        "type":"POST",//type:提交方式
        "dataType":"json",//dataType:服务器端即将响应的数据类型，取值可以是json xml html,例如服务器端响应的可能是application/json,那么该属性值json
        "success":function(json){//success:当服务器成功响应时(Http响应码是200)的回调函数
            if(json.status==200){
                $.dialog({
                    autoClose : 2500,
                    contentHtml :"<p style=\"text-align:center;\">"+json.data+"</p>"
                });
                getProduct()
            }else{
                $.dialog({
                    autoClose : 2500,
                    contentHtml :"<p style=\"text-align:center;\">"+json.msg+"</p>"
                });
            }
        }, "error": function () {//所有3字头4字头5字头的回应都会被捕获例如：302；404；405；500等
            location.href = "../../err/500.html";
        }
    });
}
function condition_update_style(id){
    var state =$("#update-yy").css("display")=="none"?"block":"none";
    $("#update-yy").css("display",state);
    var isNone =$("#update-yy").css("display")=="none"?false:true;
    if (isNone) {
        $.ajax({
            "url": "/product/"+id+"/product",
            "type": "GET",
            "dataType": "json",
            "success": function (json) {
                if (json.status == 200) {
                    $("#updateDetailsId").attr("value",json.data.id);
                    $("#updateCategoryName").attr("value",json.data.categoryName);
                    $("#updateName").attr("value", json.data.name);
                    $("#updateSubtitle").attr("value", json.data.subtitle);
                    $("#updatePrice").attr("value", json.data.price);
                    $("#updateStock").attr("value", json.data.stock);
                    $("#updateDetail").val(json.data.detail);
                    if (json.data.place === 1){
                        $("#c-radio").attr("checked",'checked');
                    }
                    if (json.data.place === 2){
                        $("#d-radio").attr("checked",'checked');
                    }
                    if (json.data.status === 1) {
                        $("#z-status").attr("checked",'checked');
                    }
                    if (json.data.status === 2) {
                        $("#x-status").attr("checked",'checked');
                    }
                    if (json.data.status === 3) {
                        $("#s-status").attr("checked",'checked');
                    }
                }
            }
        });
    }
}

function condition_update_info() {

    $.ajax({//发出异步请求
        "url":"/product/update",//url:将请求提交到哪里去
        "data":$("#deptFormUpdate").serialize(),//data:提交的请求参数
        "type":"POST",//type:提交方式
        "dataType":"json",
        "success":function(json){
            if(json.status==200){
                $.dialog({
                    autoClose : 1500,
                    contentHtml :"<p style=\"text-align:center;\">"+json.data+"</p>"
                });
                getProduct()
                condition_update_style()
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

function condition_product_style(){
    var state =$("#add-product-yy").css("display")=="none"?"block":"none";
    $("#add-product-yy").css("display",state);
}
function condition_product_add(){
        var isNull = true;
        //获得表单值
        var categoryName=$("#categoryName").val();
        var name=$("#name").val();
        var subtitle=$("#subtitle").val();
        var detail=$("#detail").val();
        var price=$("#price").val();
        var stock=$("#stock").val();
        var place=$('input:radio[name="place"]:checked').val();
        //如果表单为空，提示用户
        if(categoryName=="" || name=="" || subtitle=="" || detail=="" || price=="" || stock=="" || place==null ){
            isNull = false;
            $.dialog({
                autoClose : 2500,
                contentHtml : '<p style="text-align:center;">资料未填写完整！</p>'
            });
        }
        if (isNull) {
            $.ajax({//发出异步请求
                "url": "/product/add",
                "data": new FormData($("#form-product-add")[0]),
                "type": "POST",
                "contentType": false,
                "processData": false,
                "dataType": "json",
                "success": function (json) {
                    if (json.status == 200) {
                        $.dialog({
                            autoClose : 2500,
                            contentHtml :"<p style=\"text-align:center;\">"+json.data+"</p>"
                        });
                        getProduct()
                        condition_product_style()
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
