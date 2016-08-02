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
        <h1 class="page-title">详情信息</h1>
        <form action="" method="post" enctype="multipart/form-data" class="box tile animated active form-validation-1">
            <div class="block-area">
                <input type="hidden" id="id" name="id" value="${product.id}">
                <div class="row">

                    <div class="col-md-6 m-b-15">
                        <label>名称:</label>
                        <input type="text" id="name" name="name" value="${product.name}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15">
                        <label>简短描述:</label>
                        <input type="text" id="shortDesc" name="shortDesc" value="${product.shortDesc}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <c:if test="${product.type eq 0}">
                        <div class="col-md-6 m-b-15">
                        <label>兑换有效期:</label>
                        <input type="text" id="validTime" name="validTime" value="<date:date format='yyyy-MM-dd HH:mm:ss' value='${product.validStartDate}'></date:date>&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;<date:date format='yyyy-MM-dd HH:mm:ss' value='${product.validEndDate}'></date:date>"  class="input-sm form-control validate[required]" placeholder="..." disabled>
                        </div>
                    </c:if>
                    <c:if test="${product.type eq 1}">
                        <div class="col-md-6 m-b-15">
                        <label>状态:</label>
                            <input type="text" id="raiseStatus" name="raiseStatus" value="${product.raiseStatus}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                        </div>
                    </c:if>

                    <div class="col-md-6 m-b-15">
                        <label>礼品类型:</label>
                        <input type="text" id="type" name="type" value="<c:if test="${product.type eq 0}">实体</c:if><c:if test="${product.type eq 1}">众筹</c:if><c:if test="${product.type eq 2}">广告位</c:if>" class="input-sm form-control validate[required]" placeholder="..." disabled>
                    </div>

                    <div class="col-md-6 m-b-15" >
                        <c:if test="${product.type eq 0}">
                            <div><label>所需益米:</label></div>
                            <input type="text" id="ym" name="ym" value="${product.ym}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                        </c:if>
                        <c:if test="${product.type eq 1}">
                            <div><label>所需人数:</label></div>
                            <input type="text" id="nums" name="nums" value="${product.nums}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                        </c:if>
                        <c:if test="${product.type eq 2}">
                            <div><label>所需益米:</label></div>
                            <c:forEach items="${productAdsList}" var="v">
                                <input type="text" id="day" name="day" value="${v.days}天&nbsp;&nbsp;:&nbsp;&nbsp;${v.ym}" class="input-sm form-control validate[required]" style="width: 100px;float:left;margin-right: 10px;" placeholder="..." disabled>
                            </c:forEach>
                        </c:if>
                    </div>

                    <c:if test="${product.type eq 0}">
                        <div class="col-md-6 m-b-15">
                            <label>兑换地点:</label>
                            <input type="text" id="address" name="address" value="${product.address}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                        </div>
                    </c:if>
                    <c:if test="${product.type eq 1}">
                        <div class="col-md-6 m-b-15">
                            <label>单个售价:</label>
                            <input type="text" id="singleYm" name="singleYm" value="${product.ym}" class="input-sm form-control validate[required]" placeholder="..." disabled>
                        </div>
                    </c:if>

                    <hr class="whiter m-t-20"/>
                    <div class="col-md-12 m-b-15">
                        <label>详细描述:</label>
                        <div class="wysiwye-editor" id="detailDesc" name="detailDesc" disabled>${product.detailDesc}</div>
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

