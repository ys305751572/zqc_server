<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../inc/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no">
    <meta charset="UTF-8">
    <meta name="description" content="Violate Responsive Admin Template">
    <meta name="keywords" content="Super Admin, Admin, Template, Bootstrap">
    <title>Super Admin Responsive Template</title>
    <!-- CSS -->
    <%@ include file="../inc/new/css.jsp" %>
</head>
<body id="skin-cloth">
<%@ include file="../inc/new/header.jsp" %>
<div class="clearfix"></div>
<section id="main" class="p-relative" role="main">
    <input type="hidden" value="商城管理">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- 查询条件 -->
        <div class="block-area" id="search">
            <div class="row">
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="name" name="name" placeholder="手机号/团体名称"/>
                </div>
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="nickname" name="nickname" placeholder="昵称/群主"/>
                </div>
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="productName" name="productName" placeholder="商品名称"/>
                </div>
                <div class="col-md-2 form-group">
                    <select id="productType" name="productType" class="select">
                        <option value="">商品类型</option>
                        <option value="0">实物</option>
                        <option value="1">众筹</option>
                        <option value="2">广告位</option>
                    </select>
                </div>
                <div class="col-md-2 form-group">
                    <select id="status" name="status" class="select">
                        <option value="">商品类型</option>
                        <option value="0">待处理</option>
                        <option value="1">已处理</option>
                        <option value="2">已过期</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="block-area" id="alternative-buttons">
            <button id="c_search" class="btn btn-alt m-r-5">查询</button>
        </div>
        <hr class="whiter m-t-20"/>
        <div class="block-area">
            <div class="row">
                <ul class="list-inline list-mass-actions">
                    <li>
                        <a href="" title="刷新" class="tooltips">
                            <i class="sa-list-refresh"></i>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <hr class="whiter m-t-20"/>
        <!-- form表格 -->
        <div class="block-area" id="tableHover">
            <table class="table table-bordered table-hover tile" id="dataTables" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th><input type="checkbox" class="pull-left list-parent-check"/></th>
                    <th>手机/团体名称</th>
                    <th>昵称</th>
                    <th>商品</th>
                    <th>商品类型</th>
                    <th>兑换积分</th>
                    <th>兑换时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
            </table>
        </div>
    </section>
    <br/><br/>
</section>
<!-- JS -->
<%@ include file="../inc/new/foot.jsp" %>
<%@ include file="../inc/new/del.jsp" %>

<script>
    $product = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $product.fn.dataTableInit();
                $("#c_search").click(function () {
                    $product.v.dTable.ajax.reload();
                });

            },
            dataTableInit: function () {
                $product.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/productExchangeRecord/list",
                        "type": "POST"
                    },
                    "columns": [
                        {
                            "data": "id",
                            "render": function (data) {
                                var checkbox = "<input type='checkbox' class='pull-left list-check' value=" + data + ">";
                                return checkbox;
                            }
                        },
                        {"data": "name", "sDefaultContent" : ""},
                        {"data": "nickname","sDefaultContent" : ""},
                        {"data": "productName","sDefaultContent" : ""},
                        {"data": "productTypeName","sDefaultContent" : ""},
                        {"data": "integral","sDefaultContent" : ""},
                        {
                            "data": "createDate",
                            render: function (data) {
                                return new Date(data).format("yyyy-MM-dd hh:mm:ss")
                            },
                            "sDefaultContent" : ""
                        },
                        {
                            "data": "",
                            render : function( data, type, row, meta) {
                                if(row.productType==0){
                                    if(row.status==0){
                                        return "待处理 <br/>验证码:"+row.code;
                                    }
                                    if(row.status==1){
                                        return "已处理<br/>验证码:"+row.code;
                                    }
                                    if(row.status==2){
                                        return "已过期<br/>验证码:"+row.code;
                                    }

                                }else if(row.productType==1){
                                    if(row.status==0){
                                        return "待处理 <br/>"+row.days+"天";
                                    }
                                    if(row.status==1){
                                        return "已处理<br/>"+row.days+"天";
                                    }
                                    if(row.status==2){
                                        return "已过期<br/>"+row.days+"天";
                                    }
                                }else{
                                    return "——";
                                }
                            },
                            "sDefaultContent" : "",
                            'sClass' :'center'
                        },
                        {
                            "data": "id",
                            "render": function (data,type,full) {
                                var type = full.productType;
                                var st = full.status;
                                if(st==0 && type!=2){
                                    var check = "<button title='确认处理' class='btn btn-primary btn-circle detail' onclick=\"$product.fn.check(\'" + data + "\',\'" + st + "\')\">" +
                                            "<i class='fa fa-check'></i></button>";
                                    return check;
                                }else{
                                    return null;
                                }
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.name = $("#name").val();
                        aoData.nickname = $("#nickname").val();
                        aoData.productName = $("#productName").val();
                        aoData.productType = $("#productType").val();
                        aoData.status = $("#status").val();
                    }
                });
            },
            "check": function (id,st) {

                $('#showText').html('您确定要通过该请求吗？');
                $("#delete").modal("show");
                $("#confirm").off("click");
                $("#confirm").on("click",function(){
                    $.ajax({
                        "url": "${contextPath}/admin/productExchangeRecord/check",
                        "data": {
                            "id": id
                        },
                        "dataType": "json",
                        "type": "POST",
                        success: function (result) {
                            if (result.status) {
                                $("#delete").modal("hide");
                                $product.v.dTable.ajax.reload(null,false);
                            } else {
                                $common.fn.notify("操作失败", "error");
                            }
                        }
                    });
                })
            },
            responseComplete: function (result, action) {
                if (result.status) {
                    if (action) {
                        $product.v.dTable.ajax.reload(null, false);
                    } else {
                        $product.v.dTable.ajax.reload();
                    }
                    $common.fn.notify(result.msg);
                } else {
                    $common.fn.notify(result.msg);
                }
            }
        }
    };
    $(function () {
        $product.fn.init();
    })
</script>
<script>
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: "2",
        forceParse: 0,
        showMeridian: 1,
        format: 'yyyy-mm-dd'
    });
</script>
</body>
</html>

