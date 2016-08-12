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
        <input type="hidden" id="mian_module" value="任务管理">
        <c:if test="${joinType eq 0}">
            <input type="hidden" id="child_module" value="益起来列表">
        </c:if>
        <c:if test="${joinType eq 1}">
            <input type="hidden" id="child_module" value="脑洞虚设列表">
        </c:if>
        <!-- 查询条件 -->
        <div class="block-area" id="search">
            <div class="row">
                <input type="hidden" value="${task.id}" id="taskId">
                <input type="hidden" value="${joinType}" id="joinType">
                <input type="hidden" value="${task.rewardYm}" id="rewardYm">
                <input type="hidden" value="${task.rewardIntegral}" id="rewardIntegral">
                <div class="col-md-2 form-group">
                    <c:if test="${joinType eq 0}">
                        <input type="text" class="input-sm form-control" id="mobile" name="mobile" placeholder="手机"/>
                    </c:if>
                    <c:if test="${joinType eq 1}">
                        <input type="text" class="input-sm form-control" id="teamName" name="teamName" placeholder="团队"/>
                    </c:if>
                </div>
                <div class="col-md-2 form-group">
                    <c:if test="${joinType eq 0}">
                        <input type="text" class="input-sm form-control" id="nickName" name="nickName" placeholder="昵称"/>
                    </c:if>
                    <c:if test="${joinType eq 1}">
                        <input type="text" class="input-sm form-control" id="nickName" name="nickName" placeholder="群主"/>
                    </c:if>
                </div>

                <div class="col-md-2 form-group">
                    <select id="status" name="status" class="select">
                        <option value="">任务状态</option>
                        <option value="0">进行中</option>
                        <option value="1">已通过</option>
                        <option value="2">未通过</option>
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
                        <a href="javascript:void(0)" onclick="$taskJoin.fn.audit(null,1);" title="一键审核通过" class="tooltips">
                            <i class="sa-list-month"></i>
                        </a>
                    </li>
                    <li class="show-on" style="display: none;">
                        <a href="javascript:void(0)" onclick="$taskJoin.fn.audit(null,2);" title="一键审核不通过" class="tooltips">
                            <i class="sa-list-week"></i>
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
                    <c:if test="${joinType eq 0}"><th>手机</th></c:if>
                    <c:if test="${joinType eq 1}"><th>团队</th></c:if>
                    <c:if test="${joinType eq 0}"><th>昵称</th></c:if>
                    <c:if test="${joinType eq 1}"><th>群主</th></c:if>
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
<%@ include file="../inc/new/del.jsp" %>
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
                                    return "未处理";
                                }else {
                                    return "已处理";
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
                                var status = full.status;
                                var id = full.id;
                                if(status==0){
                                    var Approve = "<button title='审核通过' class='btn btn-primary btn-circle detail' onclick=\"$taskJoin.fn.audit(\'" + id + "\',\'" + 1 + "\')\"> " +
                                            "<i class='fa fa-check'></i></button>";
                                    var dontApprove = "<button title='审核不通过' class='btn btn-primary btn-circle detail' onclick=\"$taskJoin.fn.audit(\'" + id + "\',\'" + 2 + "\')\"> " +
                                            "<i class='fa fa-ban'></i></button>";
                                    return Approve +"&nbsp;"+ dontApprove ;
                                }
                                if(status==1){
                                    var rewardYm = $("#rewardYm").val();
                                    var rewardIntegral = $("#rewardIntegral").val();
                                    return "审核通过，获得"+rewardIntegral+"积分和"+rewardYm+"益米奖励";
                                }
                                if(status==2){
                                    return "审核不通过，无奖励";
                                }


                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.joinType = $("#joinType").val();
                        aoData.taskId = $("#taskId").val();
                        aoData.mobile = $("#mobile").val();
                        aoData.teamName = $("#teamName").val();
                        aoData.nickName = $("#nickName").val();
                        aoData.status = $("#status").val();
                    }
                });
            },
            audit : function(id,status) {
                var checkBox = $("#dataTables tbody tr").find('input[type=checkbox]:checked');
                var ids = checkBox.getInputId();
                var rewardYm = $("#rewardYm").val();
                var rewardIntegral = $("#rewardIntegral").val();
                var joinType = $("#joinType").val();
                var name = "";
                if(joinType==0){
                    name = "用户";
                }else if(joinType==1){
                    name = "团队";
                }
                if(status==1){
                    $('#showText').html('审核通过所选'+name+'将获得'+rewardIntegral+'积分和'+rewardYm+'个益米奖励，确认审核通过？');
                }else if(status==2){
                    $('#showText').html('审核不通过所选'+name+'将无法获得积分和益米奖励，确认审核不通过？');
                }

                $("#delete").modal("show");
                $("#confirm").off("click");
                if (ids.length > 0 || id!=null){
                    $("#confirm").on("click",function(){
                        $.ajax({
                            "url": "${contextPath}/admin/taskJoin/audit",
                            "data": {
                                "id": id,
                                ids:JSON.stringify(ids),
                                "status":status,
                                "rewardYm":rewardYm,
                                "rewardIntegral":rewardIntegral,
                                "joinType":joinType
                            },
                            "dataType": "json",
                            "type": "POST",
                            success: function (result) {
                                if (result.status) {
                                    $("#delete").modal("hide");
                                    $taskJoin.v.dTable.ajax.reload(null,false);
                                } else {
                                    $("#delete").modal("hide");
                                    $common.fn.notify(result.msg, "error");
                                }
                            }
                        });
                    })
                }
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
    };
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

