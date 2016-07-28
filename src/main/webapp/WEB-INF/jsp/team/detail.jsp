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
        <h1 class="page-title">编辑用户信息</h1>
        <form action="${contextPath}/admin/userinfo/save" id="formId" name="formName" method="post" enctype="multipart/form-data"
              class="box tile animated active form-validation-1">
            <div class="block-area">
                <div class="row">
                    <div class="col-md-6 m-b-15">
                        <label>团队名字</label>
                        <input type="text" id="name" name="name" value="${team.name}"
                               class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>群主昵称</label>
                        <input type="text" id="nickname" name="nickname" value="${team.userinfo.nickname}"
                               class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>团队口号</label>
                        <input type="text" id="slogan" name="slogan" value="${team.slogan}"
                               class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>成立时间</label>
                        <input type="text" id="createDate" name="createDate" value="<date:date value="${team.createDate}" format="yyyy-MM-dd"></date:date>"
                               class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>等级</label>
                        <input type="text" id="level" name="level" value="${team.level}"
                               class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>积分</label>
                        <input type="text" id="integral" name="integral" value="${team.integral}"
                               class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div class="col-md-6 m-b-15">
                        <label>益米</label>
                        <input type="text" id="ym" name="ym" value="${team.ym}"
                               class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <div  class="col-md-6 m-b-15">
                        <label>团队人数</label>
                        <input type="text" id="nums" name="nums" value="${team.nums}"
                               class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>
                    <hr class="whiter m-t-20"/>
                    <div class="col-md-12 m-b-15" style="margin-top: 10px;">
                        <label>团队成员</label><br>
                        <c:forEach items="${list}" var="member">
                            <a data-toggle="modal" href="javascript:void(0)" onclick="$user.fn.userDetail(${member.id})" class="btn btn-sm ">${member.nickname}</a>
                        </c:forEach>
                    </div>
                    <hr class="whiter m-t-20"/>
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
            chart: null,
            dTable: null
        },
        fn: {
            init: function () {
            },

            userDetail : function(userId) {
                console.log(userId);
                window.location.href = "${contextPath}/admin/userinfo/detail?id=" + userId;
            }
        }
    }
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

