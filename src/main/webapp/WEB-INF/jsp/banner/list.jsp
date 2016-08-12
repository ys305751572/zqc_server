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
    <input type="hidden" id="mian_module" value="内容管理">
    <input type="hidden" id="child_module" value="banner广告列表">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- 查询条件 -->
        <%--<div class="block-area" id="search">--%>
            <%--<div class="row">--%>
                <%--<div class="col-md-2 form-group">--%>
                    <%--<input type="text" class="input-sm form-control" id="mobile" name="mobile" placeholder="账号"/>--%>
                <%--</div>--%>
                <%--<div class="col-md-2 form-group">--%>
                    <%--<input type="text" class="input-sm form-control" id="nickname" name="nickname" placeholder="昵称" >--%>
                <%--</div>--%>
                <%--<div class="col-md-2 form-group">--%>
                    <%--<input type="text" class="input-sm form-control" id="idCard" name="idCard" placeholder="身份证号" >--%>
                <%--</div>--%>
                <%--<div class="col-md-2 form-group">--%>
                    <%--<select id="gender" name="gender" class="select">--%>
                        <%--<option value="">性别</option>--%>
                        <%--<option value="0">男</option>--%>
                        <%--<option value="1">女</option>--%>
                    <%--</select>--%>
                <%--</div>--%>
                <%--<div class="col-md-2 form-group">--%>
                    <%--<select id="status" name="status" class="select">--%>
                        <%--<option value="">用户状态</option>--%>
                        <%--<option value="0">正常</option>--%>
                        <%--<option value="1">冻结</option>--%>
                    <%--</select>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="block-area" id="alternative-buttons">--%>
            <%--<button id="c_search" class="btn btn-alt m-r-5">查询</button>--%>
        <%--</div>--%>
        <%--<hr class="whiter m-t-20"/>--%>
        <div class="block-area">
            <div class="row">
                <ul class="list-inline list-mass-actions">
                    <li>
                        <a data-toggle="modal" href="${contextPath}/admin/banner/add" title="新增" class="tooltips">
                            <i class="sa-list-add"></i>
                        </a>
                    </li>
                    <li>
                        <a href="" title="刷新" class="tooltips">
                            <i class="sa-list-refresh"></i>
                        </a>
                    </li>
                    <li class="show-on" style="display: none;">
                        <a href="javascript:void(0)" onclick="$banner.fn.del();" title="删除" class="tooltips">
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
                    <th>位置</th>
                    <th>图片</th>
                    <th>有效期</th>
                    <th>创建时间</th>
                    <th>更新时间</th>
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
    $banner = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $banner.fn.dataTableInit();
                $("#c_search").click(function () {
                    $banner.v.dTable.ajax.reload();
                });

            },
            dataTableInit: function () {
                $banner.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/banner/list",
                        "type": "POST"
                    },
                    "columns": [
                        {
                            "data": "id",
                            "render": function (data) {
//                                var checkbox = "<div class=\"icheckbox_minimal\" aria-checked=\"false\" aria-disabled=\"false\" style=\"position: relative;\"><input type=\"checkbox\" value="+ data +" class='pull-left list-check' style=\"position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);\"></div>";
                                var checkbox = "<input type='checkbox' class='pull-left list-check' value=" + data + ">";
                                return checkbox;
                            }
                        },
                        {
                            "data": "position",
                            render : function(data) {
                                if(data == 0) {
                                    return "首页";
                                }
                                else if(data == 1) {
                                    return "商城";
                                }
                                else {
                                    return "未知";
                                }
                            }
                        },
                        {
                            "data": "imageUrl",
                            render : function(data) {
                                return "<img src='"+ data + "'>";
                            }
                        },
                        {"data": "startDate",
                            render: function (data, type, row, meta) {
                                return new Date(row.startDate).format("yyyy-MM-dd") + "~" + new Date(row.endDate).format("yyyy-MM-dd")
                            }
                        },
                        {
                            "data": "createDate",
                            render : function(data) {
                                return new Date(data).format("yyyy-MM-dd");
                            }
                        },
                        {
                            "data": "updateDate",
                            render : function(data) {
                                return new Date(data).format("yyyy-MM-dd");
                            }
                        },
                        {
                            "data": "id",
                            "render": function (data,type,full) {

                                var edit = "<button title='编辑' class='btn btn-primary btn-circle detail' onclick='$banner.fn.edit("+ data +")'> " +
                                        "<i class='fa fa-pencil-square-o'></i></button>";

                                var del = "<button title='删除' class='btn btn-primary btn-circle detail' onclick='$banner.fn.del("+data+",1)'> " +
                                        "<i class='fa fa-trash'></i></button>";
                                return edit +"&nbsp;"  + del;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
//                        aoData.mobile = $("#mobile").val();
//                        aoData.nickname = $("#nickname").val();
//                        aoData.status = $("#status").val();
//                        aoData.gender = $("#gender").val();
                    }
                });
            },
            edit : function(id) {
                window.location.href = "${contextPath}/admin/banner/add?id=" + id;
            },
            "del": function (id) {
                if(id!=null){
                    $('#showText').html('您确定要彻底删除该广告吗？');
                }else{
                    $('#showText').html('您确定要彻底删除这些广告吗？');
                }
                var checkBox = $("#dataTables tbody tr").find('input[type=checkbox]:checked');
                var ids = checkBox.getInputId();
                $("#delete").modal("show");
                $("#confirm").off("click");
                $("#confirm").on("click",function(){
                    $.ajax({
                        "url": "${contextPath}/admin/banner/del",
                        "data": {
                            "id": id,
                            "ids":JSON.stringify(ids)
                        },
                        "dataType": "json",
                        "type": "POST",
                        success: function (result) {
                            if (result.status) {
                                $("#delete").modal("hide");
                                $banner.v.dTable.ajax.reload(null,false);
                            } else {
                                $common.fn.notify("操作失败", "error");
                            }
                        }
                    });
                })
            },
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        $banner.v.dTable.ajax.reload(null, false);
                    } else {
                        $banner.v.dTable.ajax.reload();
                    }
                    $common.fn.notify(result.msg);
                } else {
                    $common.fn.notify(result.msg);
                }
            }
        }
    }
    $(function () {
        $banner.fn.init();

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

