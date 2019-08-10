
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
                autoClose: 2500,
                contentHtml: '<p style="text-align:center;">您登录信息已经过期，请重新登录！</p>'
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
    console.log("asd" + dataArray);
    jsonData = dataArray;
    //加载数据
    var pageSize = 11;
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
    $("#sell-bt").empty();
    $("#sell-list").empty();
    var html = '<div class="col-md-1">客户名称</div>'
        + '<div class="col-md-1">手机号码</div>'
        + ' <div class="col-md-1">商品分类</div>'
        + ' <div class="col-md-1">商品名称</div>'
        + ' <div class="col-md-1">商品单价</div>'
        + ' <div class="col-md-1">商品数量</div>'
        + ' <div class="col-md-1">商品位置</div>'
        + ' <div class="col-md-1">支付方式</div>'
        + ' <div class="col-md-1">实付金额</div>'
        + ' <div class="col-md-1">交易时间</div>'
        + '  <div class="col-md-1">创建时间</div>'
        + '  <div class="col-md-1">修改时间</div>';
    $("#sell-bt").append(html);

    var dataSize = jsonData.length;

    var totalPage = Math.ceil(dataSize / pageSize);
    //table
    var datas = query(curPage, pageSize);

    for (var index = 0; index < datas.length; index++) {
        var data = datas[index];
        var place = ""
        var payType = "";
        if (data.place == 1) {
            place = "仓库 ";
        }
        if (data.place == 2) {
            place = "店面 ";
        }
        if (data.payType == 1) {
            payType = "支付宝";
        }
        if (data.payType == 2) {
            payType = "微信";
        }
        if (data.payType == 3) {
            payType = "现金";
        }
        var endTime = getDate(data.endTime);
        var createTime = getDate(data.createTime);
        var updateTime = getDate(data.updateTime);
        html = '<div class="col-md-1">#{clientName}</div>'
            + '<div class="col-md-1">#{phone}</div>'
            + '<div class="col-md-1">#{categoryName}</div>'
            + '<div class="col-md-1">#{productName}</div>'
            + '<div class="col-md-1">#{price}</div>'
            + '<div class="col-md-1">#{num}</div>'
            + '<div class="col-md-1">#{place}</div>'
            + '<div class="col-md-1">#{payType}</div>'
            + '<div class="col-md-1">#{payment}</div>'
            + '<div class="col-md-1">#{endTime}</div>'
            + '<div class="col-md-1">#{createTime}</div>'
            + '<div class="col-md-1">#{updateTime}</div>';
        html = html.replace(/#{clientName}/g, data.clientName);
        html = html.replace(/#{phone}/g, data.phone);
        html = html.replace(/#{categoryName}/g, data.categoryName);
        html = html.replace(/#{productName}/g, data.productName);
        html = html.replace(/#{price}/g, data.price);
        html = html.replace(/#{num}/g, data.num);
        html = html.replace(/#{place}/g, place);
        html = html.replace(/#{payType}/g, payType);
        html = html.replace(/#{payment}/g, data.payment);
        html = html.replace(/#{endTime}/g, endTime);
        html = html.replace(/#{createTime}/g, createTime);
        html = html.replace(/#{updateTime}/g, updateTime);
        $("#sell-list").append(html);
        /*content.innerHTML = html;*/
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


