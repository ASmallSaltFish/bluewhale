<script src="${ctx}/static/js/pace.mini.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/static/themes/blue/pace-theme-minimal.css">

<style>
    .layui-nav-item {
        margin: 0 35px;
    }

    .layui-nav {
        background-color: #10456d;
    }

    .nav-header {
        background-color: #10456d;
        line-height: 60px;
        height: 60px;
        text-align: right;
    }

    .nav-login {
        font-size: 15px;
        color: rgba(255, 255, 255, .7);
        line-height: 60px;
    }

    .nav-header a {
        color: rgba(255, 255, 255, .7);
    }
</style>

<div class="layui-row  nav-header">
    <div class="layui-col-md8 layui-col-xs6 layui-col-sm12">
        <ul class="layui-nav">
            <li class="layui-nav-item">
                <a href="${ctx}/index">文章</a>
            </li>
            <li class="layui-nav-item">
                <a href="${ctx}/whisper">微语</a>
            </li>
            <li class="layui-nav-item">
                <a href="${ctx}/leacots">留言</a>
            </li>
            <li class="layui-nav-item">
                <a href="${ctx}/album">相册</a>
            </li>
            <li class="layui-nav-item">
                <a href="${ctx}/about">关于</a>
            </li>
        </ul>
    </div>
    <div class="layui-col-md4 layui-col-xs6 layui-col-sm12">
        <#if loginUser??>
            <ul class="layui-nav">
                <li class="layui-nav-item" style="text-align: center;">
                    <a href="javascript:;">
                        <img src="${ctx}/static/images/user-head.jpg" class="layui-nav-img">我
                    </a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a href="${ctx}/about">个人信息</a>
                        </dd>
                        <dd>
                            <a href="${ctx}/admin/index">博客管理</a>
                        </dd>
                        <dd>
                            <a href="${ctx}/logout">退出</a>
                        </dd>
                    </dl>
                </li>
            </ul>
        <#else>
            <p class="nav-login">
                <a href="${ctx}/login">登录</a> | <a href="${ctx}/register">注册</a>
            </p>
        </#if>
    </div>
</div>
