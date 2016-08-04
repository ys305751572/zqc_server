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
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- 查询条件 -->
        <div class="block-area" id="search">
            <div class="row">
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="name" name="name" placeholder="商品名称"/>
                </div>
                <div class="col-md-2 form-group">
                    <select id="type" name="type" class="select">
                        <option value="">商品类型</option>
                        <option value="0">实物</option>
                        <option value="1">众筹</option>
                        <option value="2">广告位</option>
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
                        <a data-toggle="modal" onclick="$product.fn.add()" title="新增" class="tooltips">
                            <i class="sa-list-add"></i>
                        </a>
                    </li>
                    <li>
                        <a href="" title="刷新" class="tooltips">
                            <i class="sa-list-refresh"></i>
                        </a>
                    </li>
                    <li class="show-on" style="display: none;">
                        <a href="javascript:void(0)" onclick="$product.fn.del();" title="删除" class="tooltips">
                            <i class="sa-list-delete"></i>
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
                    <th>商品名称</th>
                    <th>新增时间</th>
                    <th>分类</th>
                    <th>益米</th>
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
                        "url": "${contextPath}/admin/product/list",
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
                        {
                            "data": "",
                            render: function (data,type,full) {
                                var coverUrl = full.coverUrlAbsolutePath;
                                var name = full.name;
                                return "<img src='"+ coverUrl + "'>"+name;
                            },
                            "sDefaultContent" : ""
                        },
                        {
                            "data": "createDate",
                            render: function (data) {
                                return new Date(data).format("yyyy-MM-dd hh:mm:ss")
                            },
                            "sDefaultContent" : ""
                        },
                        {
                            "data": "type",
                            render: function(data){
                                if(data==0){
                                    return "实物";
                                }else if(data==1){
                                    return "众筹";
                                }else{
                                    return "广告位";
                                }
                            },
                            "sDefaultContent" : ""
                        },
                        {"data": "ym","sDefaultContent" : ""},
                        {
                            "data": "raiseStatus",
                            render: function (data,type,full) {
                                var type = full.type;
                                if(type==1) {
                                    return data;
                                }else{
                                    return "——";
                                }

                            },
                            "sDefaultContent" : ""},
                        {
                            "data": "id",
                            "render": function (data,type,full) {
                                var type = full.type;
                                var st = full.wishingWell;
                                var detail = "<button title='查看' class='btn btn-primary btn-circle detail' onclick=\"$product.fn.detail(\'" + data + "\')\">" +
                                        "<i class='fa fa-eye'></i></button>";
                                var edit = "<button title='编辑' class='btn btn-primary btn-circle detail' onclick='$product.fn.add("+ data +")'> " +
                                        "<i class='fa fa-pencil-square-o'></i></button>";
                                var del = "<button title='删除' class='btn btn-primary btn-circle detail' onclick=\"$product.fn.del(\'" + data + "\')\">" +
                                        "<i class='fa fa-trash'></i></button>";
                                if(st==0){
                                    var wishingWell = "<button title='推荐' class='btn btn-primary btn-circle detail' onclick=\"$product.fn.wishingWell(\'" + data + "\',\'" + st + "\')\">" +
                                            "<i class='fa fa-check'></i></button>";
                                }else{
                                    var wishingWell = "<button title='取消推荐' class='btn btn-primary btn-circle detail' onclick=\"$product.fn.wishingWell(\'" + data + "\',\'" + st + "\')\">" +
                                            "<i class='fa fa-ban'></i></button>";
                                }
                                if(type==1){
                                    return detail +"&nbsp;"+ edit +"&nbsp;"+ del +"&nbsp;"+wishingWell;
                                }else{
                                    return detail +"&nbsp;"+ edit +"&nbsp;"+ del;
                                }

                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.name = $("#name").val();
                        aoData.type = $("#type").val();
                    }
                });
            },
            wishingWell:function(id,st){
                var index = 0;
                if(st==0){
                    $('#showText').html('您确定要将该商品推荐到许愿池吗?');
                    index = 1;
                }else{
                    $('#showText').html('您确定要取消推荐吗?');
                }
                $("#delete").modal("show");
                $("#confirm").off("click");
                $("#confirm").on("click",function(){
                    $.ajax({
                        "url": "${contextPath}/admin/product/wishingWell",
                        "data": {
                            "id": id,
                            "status":index
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
            "del": function (id) {
                if(id!=null){
                    $('#showText').html('您确定要彻底删除该商品吗？');
                }else{
                    $('#showText').html('您确定要彻底删除这些商品吗？');
                }
                var checkBox = $("#dataTables tbody tr").find('input[type=checkbox]:checked');
                var ids = checkBox.getInputId();
                $("#delete").modal("show");
                $("#confirm").off("click");
                $("#confirm").on("click",function(){
                    $.ajax({
                        "url": "${contextPath}/admin/product/del",
                        "data": {
                            "id": id,
                            "ids":JSON.stringify(ids)
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
            detail: function(id){
                window.location.href = "${contextPath}/admin/product/detail?id="+id;
            },
            add: function(id){
                if(id!=null){
                    window.location.href = "${contextPath}/admin/product/add?id="+id;
                }else{
                    window.location.href = "${contextPath}/admin/product/add";
                }
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

