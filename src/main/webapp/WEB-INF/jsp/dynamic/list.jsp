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
    <input type="hidden" value="内容管理">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- 查询条件 -->
        <div class="block-area" id="search">
            <div class="row">
                <div class="col-md-2 form-group">
                    <input type="text" class="input-sm form-control" id="content1" name="content" placeholder="关键字"/>
                </div>
                <div class="col-md-2 form-group">
                    <select id="status" name="status" class="select">
                        <option value="">状态</option>
                        <option value="0">显示</option>
                        <option value="1">隐藏</option>
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
                        <a data-toggle="modal" href="${contextPath}/admin/dynamic/add" title="新增" class="tooltips">
                            <i class="sa-list-add"></i>
                        </a>
                    </li>
                    <li>
                        <a href="" title="刷新" class="tooltips">
                            <i class="sa-list-refresh"></i>
                        </a>
                    </li>
                    <li class="show-on" style="display: none;">
                        <a href="javascript:void(0)" onclick="_userInfo.fn.batchDel();" title="删除" class="tooltips">
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
                    <th>信息</th>
                    <th>创建时间</th>
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
                        "url": "${contextPath}/admin/dynamic/list",
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
                            "data": "content",
                            render : function(data) {
                                if(data.length > 10) {
                                    return data.substring(0,30) + "...";
                                }
                                return data;
                            }
                        },
                        {"data": "createDate",
                            render: function (data) {
                                return new Date(data).format("yyyy-MM-dd hh:mm:ss")
                            }
                        },
                        {"data": "status",
                            render:function(data){
                                if(data==0){
                                    return "显示";
                                }else{
                                    return "隐藏";
                                }
                            }
                        },
                        {
                            "data": "id",
                            "render": function (data,type,full) {
                                var detail = "<button title='查看' class='btn btn-primary btn-circle detail' onclick='_userInfo.fn.detail("+ data +")'> " +
                                        "<i class='fa fa-eye'></i></button>";

                                var edit = "<button title='删除' class='btn btn-primary btn-circle detail' onclick='_userInfo.fn.del("+ data +")'> " +
                                "<i class='fa fa-trash'></i></button>";
                                var st = full.status;
                                if(st==0){
                                    var status = "<button title='设为隐藏' class='btn btn-primary btn-circle detail' onclick='_userInfo.fn.close("+data+",1)'> " +
                                         "<i class='fa fa fa-ban'></i></button>";
                                }else if(st==1){
                                    var status = "<button title='设为显示' class='btn btn-primary btn-circle detail' onclick='_userInfo.fn.open("+ data +",0)'> " +
                                        "<i class='fa fa-check'></i></button>";
                                }
                                return detail +"&nbsp;"  + status + "&nbsp;" + edit;
                            }
                        }
                    ],
                    "fnServerParams": function (aoData) {
                        aoData.content = $("#content1").val();
                        aoData.status = $("#status").val();
                    }
                });
            },
            close:function (id,status){
                if(confirm('您确定要隐藏该记录吗？')){
                    _userInfo.fn.status(id,status);
                }
            },
            open:function (id,status){
                if(confirm('您确定要显示该记录吗？')){
                    _userInfo.fn.status(id,status);
                }
            },
            detail : function(id) {
                window.location.href = "${contextPath}/admin/dynamic/detail?id=" + id;
            },
            del : function(id) {
                if(confirm("确认删除改记录吗?")) {
                    $.ajax({
                        "url": "${contextPath}/admin/dynamic/delete",
                        "data": {
                            "id": id,
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
                }
            },
            batchDel : function() {
                var checkBox = $("#dataTables tbody tr").find('input[type=checkbox]:checked');
                var ids = checkBox.getInputId();

                if (ids.length > 0 && confirm("您确定要删除这些记录吗？")) {
                    $.post("${contextPath}/admin/dynamic/batchDel",{ids:JSON.stringify(ids)},function(result) {
                        console.log(result.status);
                        if(result.status) {
                            _userInfo.fn.responseComplete(result);
                        }
                    })
                }
            },
            status : function(id,status) {
                $.ajax({
                    "url": "${contextPath}/admin/dynamic/status",
                    "data": {
                        "id": id,
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
                if (result.status) {
                    if (action) {
                        _userInfo.v.dTable.ajax.reload(null, false);
                    } else {
                        _userInfo.v.dTable.ajax.reload();
                    }
                    $common.fn.notify(result.msg);
                } else {
                    $common.fn.notify(result.msg);
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

