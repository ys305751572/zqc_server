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
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">数据字典</h1>
        <div class="block-area">
            <div class="col-md-2">
                <div class="tile">
                    <h2 class="tile-title">字典列表</h2>
                    <div class="tile-config dropdown">
                        <a data-toggle="dropdown" href="" class="tooltips tile-menu"></a>
                        <ul class="dropdown-menu pull-right text-right">
                            <li><a href="javascript:void(0)" onclick="_userInfo.fn.createDd();">新增</a></li>
                        </ul>
                    </div>
                    <div class="p-10" style="height:520px">
                        <div class="listview narrow sortable">
                            <c:forEach items="${list}" var="dd">
                                <div class="media p-l-5">
                                    <div class="media-body">
                                        <a class="t-overflow" href="javascript:void(0)" onclick="_userInfo.fn.selectKv('${dd.id}');">${dd.name} &nbsp;&nbsp;<button title='删除' class='btn btn-primary' onclick="_userInfo.fn.deleteDd('${dd.id}');"><i class='fa fa-times'></i></button></a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-10">
                <div class="tile">
                    <h2 class="tile-title">KEY-VALUE</h2>
                    <div class="tile-config dropdown">
                        <a data-toggle="dropdown" href="" class="tooltips tile-menu" ></a>
                        <ul class="dropdown-menu pull-right text-right">
                            <li><a href="javascript:void(0)" onclick="_userInfo.fn.createKv();">新增</a></li>
                        </ul>
                    </div>
                    <div class="p-10">
                        <div id="chartdiv" class="main-chart" style="height:500px">
                            <table class="table tile">
                                <thead>
                                <tr>
                                    <th>KEY</th>
                                    <th>VALUE</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${sublist}" var="ddsub">
                                    <tr>
                                        <td>${ddsub.key}</td>
                                        <td>
                                            ${ddsub.value}
                                        </td>
                                        <td>
                                            <button title='删除' class='btn btn-primary' onclick="_userInfo.fn.deleteKv('${ddsub.id}');"><i class='fa fa-times'></i></button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </section>
    <!-- DD Sub Modal -->
    <div class="modal fade" id="ddsub-modal" tabindex="-1" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="close" type="button" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">数据字典KEY_VALUE</h4>
                    <input type="hidden" id="ddId" name="ddId" value="${ddId}">
                </div>
                <div class="modal-body">
                    <form class="form-horizontal form-validation-1" role="form" >
                        <div class="form-group">
                            <label for="key" class="col-md-2 control-label">KEY</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control input-sm validate[required]" id="key" name="key" placeholder="...">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="value" class="col-md-2 control-label">VALUE</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control input-sm validate[required]" id="value" name="value" placeholder="...">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="_userInfo.fn.saveKv();" class="btn btn-sm btn-alt">保存</button>
                    <button type="button" class="btn btn-sm btn-alt" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>

    <!-- DD Modal -->
    <div class="modal fade" id="dd-modal" tabindex="-1" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button class="close" type="button" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">数据字典名字</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal form-validation-1" role="form">
                        <div class="form-group">
                            <label for="name" class="col-md-2 control-label">Name</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control input-sm validate[required]" id="name" name="name"  placeholder="...">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="_userInfo.fn.saveDd();" class="btn btn-sm btn-alt">保存</button>
                    <button type="button" class="btn btn-sm btn-alt" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
    <br/><br/>
</section>
<!-- JS -->
<%@ include file="../inc/new/foot.jsp" %>

<script>
    _userInfo = {
        v: {
            list: [],
            chart : null,
            dTable: null
        },
        fn: {
            init: function () {
            },

            createDd : function() {
                $("#dd-modal").modal("show");
            },

            saveDd : function() {
                $.ajax({
                    url : "${contextPath}/admin/velocity/createDD",
                    type : 'POST',
                    data : {
                        "name" : $("#name").val()
                    },
                    success : function(result) {
                       window.location.href = "${contextPath}/admin/velocity/ddIndex"
                    }
                });
            },

            deleteDd : function(id) {
                $.ajax({
                    url : "${contextPath}/admin/velocity/ddDelete",
                    type : "POST",
                    data : {
                        "id" : id
                    },
                    success : function(result) {
                        if(result.status) {
                           window.location.href = "${contextPath}/admin/velocity/ddIndex";
                        }
                    }
                });
            },

            createKv : function() {
                $("#ddsub-modal").modal("show");
            },

            saveKv : function() {

                console.log("savekv ddId:" + $("#ddId").val());

                $.ajax({
                    url : "${contextPath}/admin/velocity/createDDSub",
                    type : 'POST',
                    data : {
                        "key" : $("#key").val(),
                        "value" : $("#value").val(),
                        "ddId" : $("#ddId").val()
                    },
                    success : function(result) {
                        $("#ddsub-modal").modal("hide");
                        if(result.status) {
                            _userInfo.fn.selectKv($("#ddId").val());
                        }
                    }
                });
            },
            deleteKv : function(id) {
                $.ajax({
                    url : "${contextPath}/admin/velocity/ddSubDelete",
                    type : "POST",
                    data : {
                        "id" : id
                    },
                    success : function(result) {
                        if(result.status) {
                            _userInfo.fn.selectKv($("#ddId").val());
                        }
                    }
                });
            },

            selectKv : function(ddId) {
                $(".table").find("tbody").empty();
                $.ajax({
                    url : "${contextPath}/admin/velocity/ddSubList",
                    type : "POST",
                    data : {
                        "ddId" : ddId
                    },
                    success : function(result) {
                        $("#ddId").val(ddId);
                        var data = eval(result.data);
                        var html = "";
                        for(var i = 0; i < data.length; i++) {
                            html += "<tr>";
                            html += "<td>"+ data[i].key +"</td>";
                            html += "<td>"+ data[i].value +"</td>";
                            html += "<td><button title='删除' class='btn btn-primary' onclick='_userInfo.fn.deleteKv("+ data[i].id +");'><i class='fa fa-times'></i></button></td>";
                            html += "</tr>";
                        }
                        $(".table").find("tbody").append(html);
                    }
                });
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