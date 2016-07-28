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
                    <input type="text" class="input-sm form-control" id="mobile" name="mobile" placeholder="账号"/>
                </div>
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="nickname" name="nickname" placeholder="昵称" >
                </div>
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="idCard" name="idCard" placeholder="身份证号" >
                </div>
                <div class="col-md-2 form-group">
                    <select id="gender" name="gender" class="select">
                        <option value="">性别</option>
                        <option value="0">男</option>
                        <option value="1">女</option>
                    </select>
                </div>
                <div class="col-md-2 form-group">
                    <select id="status" name="status" class="select">
                        <option value="">用户状态</option>
                        <option value="0">正常</option>
                        <option value="1">冻结</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="block-area" id="alternative-buttons">
            <button id="c_search" class="btn btn-alt m-r-5">查询</button>
        </div>
        <%--<hr class="whiter m-t-20"/>--%>
        <%--<div class="block-area">--%>
            <%--<div class="row">--%>
                <%--<ul class="list-inline list-mass-actions">--%>
                    <%--<li>--%>
                        <%--<a data-toggle="modal" href="${contextPath}/admin/doctor/add" title="新增" class="tooltips">--%>
                            <%--<i class="sa-list-add"></i>--%>
                        <%--</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="" title="刷新" class="tooltips">--%>
                            <%--<i class="sa-list-refresh"></i>--%>
                        <%--</a>--%>
                    <%--</li>--%>
                    <%--<li class="show-on" style="display: none;">--%>
                        <%--<a href="" title="删除" class="tooltips">--%>
                            <%--<i class="sa-list-delete"></i>--%>
                        <%--</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</div>--%>
        <%--</div>--%>
        <hr class="whiter m-t-20"/>
        <!-- form表格 -->
        <div class="block-area" id="tableHover">
            <table class="table table-bordered table-hover tile" id="dataTables" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th><input type="checkbox" class="pull-left list-parent-check"/></th>
                    <th>账号</th>
                    <th>昵称</th>
                    <th>注册时间</th>
                    <th>身份证号</th>
                    <th>第三方账号</th>
                    <th>性别</th>
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

<script>
    _userInfo = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                _userInfo.fn.dataTableInit();
                $("#c_search").click(function () {
                    _userInfo.v.dTable.ajax.reload();
                });

            },
            dataTableInit: function () {
                _userInfo.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/userinfo/list",
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
                        {"data": "mobile"},
                        {"data": "nickname"},
                        {"data": "createDate",
                            render: function (data) {
                                return new Date(data).format("yyyy-MM-dd hh:mm:ss")
                            }
                        },
                        {"data": "idCard"},
                        {"data": "idCard"},
                        {
                            "data" : "gender",
                            render : function(data) {
                                if(data == 0) {
                                    return "男";
                                }
                                else {
                                    return "女";
                                }
                            }
                        },
                        {"data": "status",
                            render:function(data){
                                if(data==0){
                                    return "—";
                                }else{
                                    return "禁用";
                                }
                            }
                        },
                        {
                            "data": "id",
                            "render": function (data,type,full) {
                                var detail = "<button title='查看' class='btn btn-primary btn-circle detail' onclick='_userInfo.fn.detail("+ data +")'> " +
                                        "<i class='fa fa-eye'></i></button>";

                                var edit = "<button title='编辑' class='btn btn-primary btn-circle detail' onclick='_userInfo.fn.edit("+ data +")'> " +
                                        "<i class='fa fa-pencil-square-o'></i></button>";
                                var st = full.status;
                                if(st==0){
                                    var status = "<button title='禁用' class='btn btn-primary btn-circle detail' onclick='_userInfo.fn.close("+data+",1)'> " +
                                         "<i class='fa fa fa-ban'></i></button>";
                                }else if(st==1){
                                    var status = "<button title='解禁' class='btn btn-primary btn-circle detail' onclick='_userInfo.fn.open("+ data +",0)'> " +
                                        "<i class='fa fa-check'></i></button>";
                                }
                                return detail+ "&nbsp;" + edit +"&nbsp;"  + status;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.mobile = $("#mobile").val();
                        aoData.nickname = $("#nickname").val();
                        aoData.status = $("#status").val();
                        aoData.gender = $("#gender").val();
                    }
                });
            },
            close:function (userId,status){
                if(confirm('您确定要禁用该用户吗？')){
                    _userInfo.fn.status(userId,status);
                }
            },
            open:function (userId,status){
                if(confirm('您确定要解禁该用户吗？')){
                    _userInfo.fn.status(userId,status);
                }
            },
            detail : function(userId) {
                window.location.href = "${contextPath}/admin/userinfo/detail?id=" + userId;
            },

            edit : function(userId) {
                window.location.href = "${contextPath}/admin/userinfo/add?id=" + userId;
            },

            status : function(userId,status) {
                $.ajax({
                    "url": "${contextPath}/admin/userinfo/status",
                    "data": {
                        "userId": userId,
                        "status":status
                    },
                    "dataType": "json",
                    "type": "POST",
                    success: function (result) {
                        if (!result.status) {
                            $common.fn.notify(result.msg);
                            return;
                        }
                        _userInfo.v.dTable.ajax.reload();
                    }
                });
            },
            responseComplete: function (result, action) {
                if (result.status == "0") {
                    if (action) {
                        _userInfo.v.dTable.ajax.reload(null, false);
                    } else {
                        _userInfo.v.dTable.ajax.reload();
                    }
                    $leoman.notify(result.msg, "success");
                } else {
                    $leoman.notify(result.msg, "error");
                }
            }
        }
    }
    $(function () {
        _userInfo.fn.init();

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

