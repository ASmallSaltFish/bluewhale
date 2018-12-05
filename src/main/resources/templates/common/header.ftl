<#assign ctx=request.contextPath/>
<div class="header">
    <div class="menu-btn">
        <div class="menu"></div>
    </div>
    <h1 class="logo">
        <a href="index">
            <span>MYBLOG</span>
            <img src="${ctx}/static/img/logo.png">
        </a>
    </h1>
    <div class="nav">
        <a href="${ctx}/index">文章</a>
        <a href="${ctx}/whisper">微语</a>
        <a href="${ctx}/leacots">留言</a>
        <a href="${ctx}/album">相册</a>
        <a href="${ctx}/about" class="active">关于</a>
    </div>
    <ul class="layui-nav header-down-nav">
        <li class="layui-nav-item"><a href="${ctx}/index">文章</a></li>
        <li class="layui-nav-item"><a href="${ctx}/whisper">微语</a></li>
        <li class="layui-nav-item"><a href="${ctx}/leacots">留言</a></li>
        <li class="layui-nav-item"><a href="${ctx}/album">相册</a></li>
        <li class="layui-nav-item"><a href="${ctx}/about" class="active">关于</a></li>
    </ul>

    <#if loginUser??>
        <p class="welcome-text">
            欢迎您， <a href="${ctx}/userInfo">${(loginUser.userName)!}</a>
        </p>
    <#else>
        <p class="welcome-text">
            <a href="${ctx}/login">登录</a> | <a href="${ctx}/register">注册</a>
        </p>
    </#if>
</div>
