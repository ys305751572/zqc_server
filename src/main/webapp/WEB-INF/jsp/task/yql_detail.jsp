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
    <input type="hidden" value="任务管理">
    <%@ include file="../inc/new/menu.jsp" %>
    <section id="content" class="container">
        <!-- Breadcrumb -->
        <ol class="breadcrumb hidden-xs">
            <li><a href="javascript:history.go(-1);" title="返回"><span class="icon">&#61771;</span></a></li>
        </ol>
        <h1 class="page-title">详情信息</h1>
        <form action="${contextPath}/admin/task/save" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${task.id}">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>名称:</label>
                        <input type="text" id="name" name="name" value="${task.name}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>活动时间:</label>
                        <input type="text" id="taskTime" name="taskTime" value="<date:date format='yyyy-MM-dd HH:mm:ss' value='${task.startDate}'></date:date>&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;<date:date format='yyyy-MM-dd HH:mm:ss' value='${task.endDate}'></date:date>"  class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>活动类型:</label>
                        <input type="text" id="joinType" name="joinType" value="<c:if test="${task.joinType eq 0}">个人</c:if><c:if test="${task.joinType eq 1}">团队</c:if>" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>活动地点:</label>
                        <input type="text" id="address" name="address" value="${task.address}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>主办单位:</label>
                        <input type="text" id="organizers" name="organizers" value="${task.organizers}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>所需人数:</label>
                        <input type="text" id="nums" name="nums" value="${task.nums}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>奖励积分:</label>
                        <input type="text" id="rewardIntegral" name="rewardIntegral" value="${task.rewardIntegral}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <label>奖励益米:</label>
                        <input type="text" id="rewardYm" name="rewardYm" value="${task.rewardYm}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <hr class="whiter m-t-20"/>
                    <div class="col-md-12 m-b-15">
                        <input type="hidden" id="imageIds" name="imageIds" value="">
                        <label>封面:</label>
                        <p></p>
                        <p></p>
                        <a href="${task.coverUrlAbsolutePath}" data-rel="gallery"  class="pirobox_gall img-popup" title="封面">
                            <img src="${task.coverUrlAbsolutePath}" alt="">
                        </a>
                    </div>
                    <hr class="whiter m-t-20"/>
                    <div class="col-md-12 m-b-15">
                        <label>详细描述:</label>
                        <div class="wysiwye-editor" id="detail" name="detail" disabled>${task.detail}</div>
                    </div>
                    <hr class="whiter m-t-20"/>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-5">
                        <button type="button" class="btn btn-info btn-sm m-t-10" onclick="history.go(-1);">返回</button>
                    </div>
                </div>
            </div>
        </form>
    </section>
    <br/><br/>
</section>
<!-- JS -->
<%@ include file="../inc/new/foot.jsp" %>
<script>
    $user = {
        v: {
            list: [],
            chart : null,
            dTable: null
        },
        fn: {
            init: function () {
                $user.fn.initImage();
            }
        }
    };
    $(function () {
        $user.fn.init();
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

