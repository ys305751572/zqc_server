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
    <input type="hidden" value="权限管理">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- 查询条件 -->
        <div class="block-area" id="search">
            <div class="row">
                <div class="col-md-2 form-group">
                    <label>账号:</label>
                    <input type="text" class="input-sm form-control" id="username" name="username" placeholder="账号">
                </div>
                <div class="col-md-2 form-group">
                    <label>手机:</label>
                    <input type="text" class="input-sm form-control" id="mobile" name="mobile" placeholder="手机">
                </div>
            </div>
        </div>
        <div class="block-area" id="alternative-buttons">
            <button id="c_search" class="btn btn-alt m-r-5">查询</button>
        </div>
        <div class="block-area">
            <div class="row">
                <ul class="list-inline list-mass-actions">
                    <li>
                        <a data-toggle="modal" onclick="$admin.fn.add();" title="新增" class="tooltips">
                            <i class="sa-list-add"></i>
                        </a>
                    </li>
                    <li class="show-on" style="display: none;">
                        <a onclick="$admin.fn.betchDel();" title="删除" class="tooltips">
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
                    <th>账号</th>
                    <th>添加时间</th>
                    <th>手机号</th>
                    <th>最新登录时间</th>
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
<script>
    $admin = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $admin.fn.dataTableInit();
                $("#c_search").click(function () {
                    $admin.v.dTable.ajax.reload();
                });
            },
            dataTableInit: function () {
                $admin.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/admin/list",
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
                        {"data": "username","sDefaultContent" : ""},
                        {
                            "data": "createDate",
                            "render":function(data){
                                return new Date(data).format("yyyy-MM-dd hh:mm");
                            },
                            "sDefaultContent" : ""
                        },
                        {"data": "mobile"},
                        {
                            "data": "lastLoginDate",
                            "render":function(data){
                                return new Date(data).format("yyyy-MM-dd hh:mm");
                            },
                            "sDefaultContent" : ""
                        },
                        {
                            "data": "id",
                            "render": function (data,type,full) {
                                var edit = "<button title='编辑' class='btn btn-primary btn-circle' onclick=\"$admin.fn.add(\'" + data + "\')\">" +
                                        "<i class='fa fa-pencil-square-o'></i></button>";

                                var reset = "<button title='重置密码' class='btn btn-primary btn-circle' onclick=\"$admin.fn.reset(\'" + data + "\')\">" +
                                        "<i class='fa fa-recycle'></i></button>";

                                var del = "<button title='删除' class='btn btn-primary btn-circle' onclick=\"$admin.fn.del(\'" + data + "\')\">" +
                                        "<i class='fa fa-trash'></i></button>";
                                return edit+ "&nbsp;" + reset +  "&nbsp;" +del;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.username = $("#username").val();
                        aoData.mobile = $("#mobile").val();
                    }
                });
            },
            betchDel : function() {
                var checkBox = $("#dataTables tbody tr").find('input[type=checkbox]:checked');
                var ids = checkBox.getInputId();
                $.ajax({
                    url : "${contextPath}/admin/admin/betchDel",
                    data : {
                        "ids" : JSON.stringify(ids)
                    },
                    type : "post",
                    dataType : "json",
                    success : function(result) {
                        if(result.status) {
                            $common.fn.notify(result.msg);
                            $admin.v.dTable.ajax.reload();
                        }

                    }
                });
            },
            add: function (id){
                if(id == null) {
                    window.location.href = "${contextPath}/admin/admin/add";
                }else {
                    window.location.href = "${contextPath}/admin/admin/add?id=" + id;
                }

            },
            reset : function(id) {
                $.post("${contextPath}/admin/admin/resetPwd",{"id":id},function(data) {
                    if(data.status) {
                        $common.fn.notify("重置成功");
                        $admin.v.dTable.ajax.reload();
                    }
                    else {
                        $common.fn.notify("重置失败");
                    }
                });
            },
            del : function(id) {
                $.post("${contextPath}/admin/admin/delete",{"id":id},function(data) {
                    if(data.status) {
                        $common.fn.notify("删除成功");
                        $admin.v.dTable.ajax.reload();
                    }
                    else {
                        $common.fn.notify("删除失败");
                    }

                });
            },
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        $admin.v.dTable.ajax.reload(null, false);
                    } else {
                        $admin.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            }
        }
    }
    $(function () {
        $admin.fn.init();
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

