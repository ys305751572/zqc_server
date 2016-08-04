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
                <input type="hidden" value="2" id="type">
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="name" name="name" placeholder="关卡名称"/>
                </div>
                <div class="col-md-2 form-group">
                    <select id="checkpointStatus" name="checkpointStatus" class="select">
                        <option value="">任务状态</option>
                        <option value="1">已结束</option>
                        <option value="2">进行中</option>
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
                        <a data-toggle="modal" onclick="$task.fn.add()" title="新增" class="tooltips">
                            <i class="sa-list-add"></i>
                        </a>
                    </li>
                    <li>
                        <a href="" title="刷新" class="tooltips">
                            <i class="sa-list-refresh"></i>
                        </a>
                    </li>
                    <li class="show-on" style="display: none;">
                        <a href="javascript:void(0)" onclick="$task.fn.del();" title="删除" class="tooltips">
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
                    <th>关卡名称</th>
                    <th>新增时间</th>
                    <th>关卡</th>
                    <th>任务状态</th>
                    <th>积分/益米</th>
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
    $task = {
        v: {
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                $task.fn.dataTableInit();
                $("#c_search").click(function () {
                    $task.v.dTable.ajax.reload();
                });

            },
            dataTableInit: function () {
                $task.v.dTable = $leoman.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ajax": {
                        "url": "${contextPath}/admin/task/list",
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
                        {"data": "checkpoint", "sDefaultContent" : ""},
                        {"data": "checkpointStatus","sDefaultContent" : ""},
                        {
                            "data": "",
                            render: function (data,type,full) {
                                var rewardIntegral = full.rewardIntegral;
                                var rewardYm = full.rewardYm;
                                return rewardIntegral+"/"+rewardYm;
                            },
                            "sDefaultContent" : ""},
                        {
                            "data": "id",
                            "render": function (data,type,full) {
                                var detail = "<button title='查看' class='btn btn-primary btn-circle detail' onclick='$task.fn.detail("+ data +")'> " +
                                        "<i class='fa fa-eye'></i></button>";
                                var id = full.id;
                                var joinType = full.joinType;
                                var del = "<button title='删除' class='btn btn-primary btn-circle detail' onclick=\"$task.fn.del(\'" + data + "\')\">" +
                                        "<i class='fa fa-trash'></i></button>";
                                var taskJoin = "<button title='报名成员' class='btn btn-primary btn-circle detail' onclick=\"$task.fn.taskJoin(\'" + id + "\',\'" + joinType + "\')\">" +
                                        "<i class='fa fa-user'></i></button>";
                                return detail +"&nbsp;"+ del +"&nbsp;"+ taskJoin;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.name = $("#name").val();
                        aoData.checkpointStatus = $("#checkpointStatus").val();
                        aoData.type = $("#type").val();
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
                                $task.v.dTable.ajax.reload(null,false);
                            } else {
                                $common.fn.notify("操作失败", "error");
                            }
                        }
                    });
                })
            },
            del : function(id) {
                var checkBox = $("#dataTables tbody tr").find('input[type=checkbox]:checked');
                var ids = checkBox.getInputId();
                $('#showText').html('您确定要彻底删除所选择关卡吗？');
                $("#delete").modal("show");
                $("#confirm").off("click");
                $("#confirm").on("click",function(){
                    $.ajax({
                        "url": "${contextPath}/admin/task/del",
                        "data": {
                            id : id,
                            ids:JSON.stringify(ids)
                        },
                        "dataType": "json",
                        "type": "POST",
                        success: function (result) {
                            if (result.status) {
                                $("#delete").modal("hide");
                                $task.v.dTable.ajax.reload(null,false);
                            } else {
                                $common.fn.notify("操作失败", "error");
                            }
                        }
                    });
                })
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
                        $task.v.dTable.ajax.reload(null, false);
                    } else {
                        $task.v.dTable.ajax.reload();
                    }
                    $common.fn.notify(result.msg);
                } else {
                    $common.fn.notify(result.msg);
                }
            }
        }
    }
    $(function () {
        $task.fn.init();

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

