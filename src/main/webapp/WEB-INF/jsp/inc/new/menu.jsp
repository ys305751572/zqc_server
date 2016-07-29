<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<aside id="sidebar">
    <!-- Sidbar Widgets -->
    <%--<div class="side-widgets overflow">--%>
        <%--<!-- Profile Menu -->--%>
        <%--<div class="text-center s-widget m-b-25 dropdown" id="profile-menu">--%>
            <%--<a href="" data-toggle="dropdown">--%>
                <%--<img class="profile-pic animated" src="${contextPath}/html/img/profile-pic.jpg" alt="">--%>
            <%--</a>--%>
            <%--<ul class="dropdown-menu profile-menu">--%>
                <%--<li><a href="${contextPath}/admin/logout">退&nbsp;&nbsp;&nbsp;出</a> <i class="icon left">&#61903;</i><i class="icon right">&#61815;</i></li>--%>
            <%--</ul>--%>
        <%--</div>--%>
        <%----%>
        <%--<!-- Calendar -->--%>
        <%--<div class="s-widget m-b-25">--%>
            <%--<div id="sidebar-calendar"></div>--%>
        <%--</div>--%>
        <%----%>
        <%--<!-- Feeds -->--%>
        <%--<div class="s-widget m-b-25">--%>
            <%--<h2 class="tile-title">--%>
               <%--热门新闻--%>
            <%--</h2>--%>
            <%----%>
            <%--<div class="s-widget-body">--%>
                <%--<div id="news-feed"></div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    
    <!-- Side Menu -->
    <ul class="list-unstyled side-menu">
        <li class="dropdown">
            <a class="sa-side-form" href="">
                <span class="menu-item">权限管理</span>
            </a>
            <ul class="list-unstyled menu-item">
                <li><a href="${contextPath}/admin/admin/index">管理人员列表</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a class="sa-side-form" href="">
                <span class="menu-item">账号管理</span>
            </a>
            <ul class="list-unstyled menu-item">
                <li><a href="${contextPath}/admin/userinfo/index">会员列表</a></li>
                <li><a href="${contextPath}/admin/team/index">团队列表</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a class="sa-side-form" href="">
                <span class="menu-item">内容管理</span>
            </a>
            <ul class="list-unstyled menu-item">
                <li><a href="${contextPath}/admin/banner/index">bannar广告列表</a></li>
                <li><a href="${contextPath}/admin/dynamic/index">朋友圈置顶列表</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a class="sa-side-form" href="">
                <span class="menu-item">任务管理</span>
            </a>
            <ul class="list-unstyled menu-item">
                <li><a href="${contextPath}/admin/task/yql/index">益起来列表</a></li>
                <li><a href="${contextPath}/admin/task/ndxs/index">脑洞虚设列表</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a class="sa-side-form" href="">
                <span class="menu-item">商城管理</span>
            </a>
            <ul class="list-unstyled menu-item">
                <li><a href="${contextPath}/admin/admin/index">商品列表</a></li>
                <li><a href="${contextPath}/admin/admin/index">兑换列表列表</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a class="sa-side-form" href="">
                <span class="menu-item">站长工具管理</span>
            </a>
            <ul class="list-unstyled menu-item">
                <li><a href="${contextPath}/admin/admin/index">意见反馈列表</a></li>
            </ul>
        </li>
    </ul>
</aside>