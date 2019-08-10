$(function () {
    getSell();
})
function getSell(){
    $.ajax({
        "url": "/sell/list?endTime",
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
        "url": "/sell/list",
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
    console.log("asd" + list);
    jsonData = dataArray;
    //加载数据
    var pageSize = 3;
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
    $("#sell-mx").empty();
    var dataSize = jsonData.length;
    var totalPage = Math.ceil(dataSize / pageSize);
    //table
    var datas = query(curPage, pageSize);
    for (var index = 0; index < datas.length; index++) {
        var data = datas[index];
        var payType = "";
        if (data.payType === 1){
            payType = "支付宝";
        }
        if (data.payType === 2){
            payType = "微信";
        }
        if (data.payType === 3){
            payType = "现金";
        }
        var endTime = getDate(data.endTime);
        var createTime = getDate(data.createTime);
        var updateTime = getDate(data.updateTime);

        var html ='<table style= "margin : 38px" border="solid"  width="100%"  cellspacing="0" cellpadding="10" frame="solid" rules="solid" >' +
            '<tbody align="center" valign="center">' +
            '<tr id="xs-mx-ml" align="center" valign="center" bgcolor="pink">' +
            '<td style="width: 20%">销售表ID</td>' +
            '<td>' +
            '<table width=\'100%\' style=\' table-layout:fixed\' border="0" cellspacing="0" cellpadding="0">' +
            '<tr align="center" valign="center" bgcolor="pink">' +
            '<td style="width: 20%">支付方式</td>' +
            '<td style="width: 20%">实收金额</td>' +
            '<td style="width: 20%">交易时间</td>' +
            '<td style="width: 20%">创建时间</td>' +
            '<td style="width: 20%">修改时间</td>' +
            '</tr></table></td></tr>' +
            '<tr id="xs-nr">'+
            '<td style="width: 20%">#{sellId}</td>'+
            '<td>'+
            '<table width=\'100%\' style=\' table-layout:fixed\'border="0" cellspacing="0" cellpadding="0">' +
            '<tr align="center" valign="center" bgcolor="pink">'+
            '<td style="width: 20%">#{payType}</td>'+
            '<td style="width: 20%">#{payment}</td>'+
            '<td style="width: 20%">#{endTime}</td>'+
            '<td style="width: 20%">#{createTime}</td>'+
            '<td style="width: 20%">#{updateTime}</td>'+
            '</tr></table></td></tr>'+
            '<tr id="kh-mx-ml" align="center" valign="center" bgcolor="pink">' +
            '<td style="width: 20%">客户明细</td>' +
            '<td>' +
            '<table width=\'100%\' style=\' table-layout:fixed\' border="0" cellspacing="0" cellpadding="0">' +
            '<tr align="center" valign="center" bgcolor="pink">' +
            '<td style="width: 20%">客户ID</td>' +
            '<td style="width: 20%">姓名</td>' +
            '<td style="width: 20%">电话</td>' +
            '</tr></table></td></tr>' +
            '<tr id="kh-mx">' +
            '<td style="width: 20%"></td>'+
            '<td>'+
            '<table width=\'100%\' style=\' table-layout:fixed\'border="0" cellspacing="0" cellpadding="0">'+
            ' <tr align="center" valign="center" bgcolor="pink">'+
            '<td style="width: 20%">#{clientID}</td>'+
            '<td style="width: 20%">#{clientName}</td>'+
            '<td style="width: 20%">#{clientPhone}</td>'+
            '</tr></table></td></tr>' +
            '<tr id="sp-mx-ml" align="center" valign="center" bgcolor="pink">' +
            '<td style="width: 20%">商品明细</td>' +
            '<td>' +
            '<table width=\'100%\' style=\'table-layout:fixed\' border="0" cellspacing="0" cellpadding="0">' +
            '<tr align="center" valign="center" bgcolor="pink">' +
            '<td style="width: 20%">销售表ID</td>' +
            '<td style="width: 20%">商品分类ID</td>' +
            '<td style="width: 20%">商品ID</td>' +
            '<td style="width: 20%">商品名称</td>' +
            '<td style="width: 20%">商品数量</td>' +
            '<td style="width: 20%">商品单价</td>' +
            '<td style="width: 20%">商品存放</td>' +
            '</tr></table></td></tr>';
        html = html.replace(/#{sellId}/g, data.sellId);
        html = html.replace(/#{payType}/g, payType);
        html = html.replace(/#{payment}/g, data.payment);
        html = html.replace(/#{endTime}/g, endTime);
        html = html.replace(/#{createTime}/g, createTime);
        html = html.replace(/#{updateTime}/g, updateTime);
        html = html.replace(/#{clientID}/g, data.clientVo.id);
        html = html.replace(/#{clientName}/g, data.clientVo.name);
        html = html.replace(/#{clientPhone}/g,data.clientVo.phone);
        var sellProductSize = data.sellProductVos.length;
        var dataArray = eval(data.sellProductVos);
        var jsonDataSell = [];
        jsonDataSell = dataArray;
        for (var i=0;i<sellProductSize;i++){
            var info = jsonDataSell[i];
            var place = ""
            if (info.place === 1){
                place = "仓库 ";
            }
            if (info.place === 2){
                place = "店面 ";
            }
            html +='<tr id="sp-mx-"+i>'
            html +='<td style="width: 20%"></td>'
            html +='<td>'
            html +='<table width=\'100%\' style=\' table-layout:fixed\'border="0" cellspacing="0" cellpadding="0">'
            html +=' <tr align="center" valign="center" bgcolor="pink">'
            html +='<td style="width: 20%">#{sellId}</td>'
            html += '<td style="width: 20%">#{categoryId}</td>'
            html += '<td style="width: 20%">#{productId}</td>'
            html += '<td style="width: 20%">#{productName}</td>'
            html += '<td style="width: 20%">#{num}</td>'
            html += '<td style="width: 20%">#{price}</td>'
            html += '<td style="width: 20%">#{place}</td>'
            html += '</tr></table></td></tr>';
            html = html.replace(/#{productId}/g, info.id);
            html = html.replace(/#{sellId}/g, info.sellId);
            html = html.replace(/#{categoryId}/g, info.categoryId);
            html = html.replace(/#{productId}/g, info.productId);
            html = html.replace(/#{productName}/g, info.productName);
            html = html.replace(/#{num}/g, info.num);
            html = html.replace(/#{price}/g, info.price);
            html = html.replace(/#{place}/g, place);
        }
        html+='</table>';
        $("#sell-mx").append(html);
    }

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
    $("#sell-and-input").empty();
    $("#sell-and-input").append(phtml);

}


function condition_sell_style(){
    var state =$("#add-sell-yy").css("display")=="none"?"block":"none";
    $("#add-sell-yy").css("display",state);
}

function condition_sell_add() {
        var isNull = false;
        //获得表单值
        var clientName=$("#clientName").val();
        var phone=$("#phone").val();

        var payment=$("#payment").val();;
        var payType=$('input:radio[name="payType"]:checked').val();
        var place=$('input:radio[name="place"]:checked').val();
        var endTime=$("#endTime").val();
        //如果表单为空，提示用户
        if(clientName=="" || phone=="" ||  payment=="" || payType==null || place==null || endTime==""){
            $.dialog({
                autoClose : 2500,
                contentHtml : '<p style="text-align:center;">基本信息未填写完整！</p>'
            });
            return;
        }
        for (var i=1;i<=6;i++){
            var productName=$("#productName"+i).val();
            var num=$("#num"+i).val();
            if (productName !=="" || num !==""){
                isNull = true;
            }
        }
        if (!isNull){
            $.dialog({
                autoClose : 2500,
                contentHtml : '<p style="text-align:center;">商品至少添加一个！</p>'
            });
        }


        if (isNull) {
            $.ajax({//发出异步请求
                "url": "/sell/add",//url:将请求提交到哪里去
                "data": $("#form-sell-add").serialize(),//data:提交的请求参数
                "type": "POST",//type:提交方式
                "dataType": "json",//dataType:服务器端即将响应的数据类型，取值可以是json xml html,例如服务器端响应的可能是application/json,那么该属性值json
                "success": function (json) {//success:当服务器成功响应时(Http响应码是200)的回调函数
                    if (json.status == 200) {
                        $.dialog({
                            autoClose : 2500,
                            contentHtml :"<p style=\"text-align:center;\">"+json.data+"</p>"
                        });
                        condition_sell_style();
                        getSell();
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