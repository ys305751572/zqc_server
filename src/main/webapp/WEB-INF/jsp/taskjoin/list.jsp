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
                <input type="hidden" value="${taskId}" id="taskId">
                <input type="hidden" value="${joinType}" id="joinType">
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="mobileName" name="mobileName" placeholder="手机/团队"/>
                </div>
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="nickName" name="nickName" placeholder="昵称/群主"/>
                </div>

                <div class="col-md-2 form-group">
                    <select id="status" name="status" class="select">
                        <option value="">任务状态</option>
                        <option value="0">进行中</option>
                        <option value="1">已完成</option>
                        <option value="2">未完成</option>
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
                    <li class="show-on" style="display: none;">
                        <a href="javascript:void(0)" onclick="$taskJoin.fn.batchDel();" title="一键审核通过" class="tooltips">
                            <i class="sa-list-delete"></i>
                        </a>
                    </li>
                    <li class="show-on" style="display: none;">
                        <a href="javascript:void(0)" onclick="$taskJoin.fn.batchDel();" title="一键审核不通过" class="tooltips">
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
                    <th>手机/团队</th>
                    <th>昵称/群主</th>
                    <th>报名时间</th>
                    <th>状态</th>
                    <th>提交时间</th>
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
    $taskJoin = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $taskJoin.fn.dataTableInit();
                $("#c_search").click(function () {
                    $taskJoin.v.dTable.ajax.reload();
                });

            },
            dataTableInit: function () {
                $taskJoin.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/taskJoin/list",
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
                            render : function( data, type, row, meta) {
                                if($("#joinType").val()==0 && row.userinfo!=null){
                                    return row.userinfo.mobile;
                                }else if($("#joinType").val()==1 && row.team!=null){
                                    return row.team.name;
                                }
                            },
                            "sDefaultContent" : ""
                        },
                        {
                            "data": "",
                            render : function( data, type, row, meta) {
                                if($("#joinType").val()==0 && row.userinfo!=null){
                                    return row.userinfo.nickname;
                                }else if($("#joinType").val()==1 && row.team!=null){
                                    return row.team.userinfo.nickname;
                                }
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
                            "data": "status",
                            render: function(data){
                                if(data==0){
                                    return "进行中";
                                }else if(data==1){
                                    return "已完成";
                                }else if(data==2){
                                    return "未完成";
                                }
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
                            "data": "id",
                            "render": function (data,type,full) {
                                var Approve = "<button title='审核通过' class='btn btn-primary btn-circle detail' onclick='$taskJoin.fn.check("+ data +")'> " +
                                        "<i class='fa fa-check'></i></button>";
                                var dontApprove = "<button title='审核不通过' class='btn btn-primary btn-circle detail' onclick='$taskJoin.fn.ban("+ data +")'> " +
                                        "<i class='fa fa-ban'></i></button>";

                                return Approve +"&nbsp;"+ dontApprove ;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.joinType = $("#joinType").val();
                        aoData.taskId = $("#taskId").val();
                        aoData.mobileName = $("#mobileName").val();
                        aoData.nickName = $("#nickName").val();
                        aoData.status = $("#status").val();
                    }
                });
            },
            "changeStatus": function (id,st) {
                var tempStatus = 0;
                if(st==0){
                    $('#showText').html('您确定要禁用该任务吗？');
                    tempStatus = 1;
                }else if(st==1){
                    $('#showText').html('您确定要解禁该任务吗？');
                }
                $("#delete").modal("show");
                $("#confirm").off("click");
                $("#confirm").on("click",function(){
                    $.ajax({
                        "url": "${contextPath}/admin/task/status",
                        "data": {
                            "id": id,
                            "status":tempStatus
                        },
                        "dataType": "json",
                        "type": "POST",
                        success: function (result) {
                            if (result.status) {
                                $("#delete").modal("hide");
                                $taskJoin.v.dTable.ajax.reload(null,false);
                            } else {
                                $common.fn.notify("操作失败", "error");
                            }
                        }
                    });
                })
            },
            batchDel : function() {
                var checkBox = $("#dataTables tbody tr").find('input[type=checkbox]:checked');
                var ids = checkBox.getInputId();
                $('#showText').html('您确定要彻底删除这些活动吗？');
                $("#delete").modal("show");
                $("#confirm").off("click");
                if (ids.length > 0){
                    $("#confirm").on("click",function(){
                        $.ajax({
                            "url": "${contextPath}/admin/task/batchDel",
                            "data": {
                                ids:JSON.stringify(ids)
                            },
                            "dataType": "json",
                            "type": "POST",
                            success: function (result) {
                                if (result.status) {
                                    $("#delete").modal("hide");
                                    $taskJoin.v.dTable.ajax.reload(null,false);
                                } else {
                                    $common.fn.notify("操作失败", "error");
                                }
                            }
                        });
                    })
                }
            },
            detail: function(id){
                var type = $("#type").val();
                window.location.href = "${contextPath}/admin/task/detail?id=" + id+"&type="+type;
            },
            add: function(){
                var type = $("#type").val();
                window.location.href = "${contextPath}/admin/task/add?type="+type;
            },
            taskJoin: function(id,joinType){
                window.location.href = "${contextPath}/admin/taskJoin/index?taskId="+id+"&joinType="+joinType;
            },
            responseComplete: function (result, action) {
                if (result.status) {
                    if (action) {
                        $taskJoin.v.dTable.ajax.reload(null, false);
                    } else {
                        $taskJoin.v.dTable.ajax.reload();
                    }
                    $common.fn.notify(result.msg);
                } else {
                    $common.fn.notify(result.msg);
                }
            }
        }
    }
    $(function () {
        $taskJoin.fn.init();

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

