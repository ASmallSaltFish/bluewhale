<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>markdown预览样例</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/main.css">
    <link rel="stylesheet" href="${ctx}/static/css/style.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/editormd.css"/>
    <link rel="shortcut icon" href="https://pandao.github.io/editor.md/favicon.ico" type="image/x-icon"/>

    <style>
        .article-wrap {
            margin-top: 100px;
        }

        .editormd-preview {
            width: 100%;
        }

        /* 这里可以更改markdown字体和背景 */
        .editormd-preview-container {
            text-align: left;
            font-size: 15px;
            line-height: 1.6;
            padding: 20px;
            overflow: auto;
            width: 100%;
            background-color: #fff;
            font-family: '微软雅黑';
        }
    </style>
</head>

<body>

<#include "../common/header.ftl"/>

<div class="layui-row">
    <div class="editormd-preview article-wrap" style="display: block;">
        <div class="markdown-body editormd-preview-container" previewcontainer="true"
             style="padding: 20px 20px 50px 40px;">
            <h4 id="h4-u6D89u53CAu5230u7684u4E00u4E9Bu6280u672Fu70B9">
                <a name="涉及到的一些技术点" class="reference-link"></a>
                <span class="header-link octicon octicon-link"></span>涉及到的一些技术点</h4>
            <ul>
                <li>
                    <p>RPC</p>
                    <blockquote>
                        <p>远程过程调用(Remote Procedure Call);</p>
                    </blockquote>
                </li>
                <li>
                    <p>org.springframework.web.filter.DelegatingFilterProxy</p>
                    <blockquote>
                        <p>Spring实现的一个过滤器代理，通过这个过滤器代理，Spring可以将过滤器纳入Spring的管理范围，这样过滤器中就可以直接使用注解注入Spring容器中的对象;</p>
                    </blockquote>
                </li>
                <li>
                    <p>HessianProxyFactory</p>
                    <blockquote>
                        <p>Hessian是一个轻量级的remoting onhttp工具，使用简单的方法提供了RMI的功能。
                            相比WebService，Hessian更简单、快捷。采用的是二进制RPC协议，因为采用的是二进制协议，所以它很适合于发送二进制数据;</p>
                    </blockquote>
                </li>
            </ul>
            <h4 id="h4-u6A21u5757u8BBEu8BA1u548Cu8BF4u660E">
                <a name="模块设计和说明" class="reference-link"></a>
                <span class="header-link octicon octicon-link"></span>模块设计和说明</h4>
            <ul>
                <li>
                    <p>sso-server</p>
                    <ul>
                        <li>TokenManager：令牌生成、校验、失效时间管理；
                            <ul>
                                <li>
                                    LocalTokenManager:认证中心系统内存中维护一个tokenMap，用来存储生成的token和登录信息，作为sso-client过滤器校验客户系统是否登录的依据;
                                </li>
                                <li>RedisTokenManager:认证中心将生成的token和用户信息存储在redis中，作为sso-client过滤器校验客户系统是否登录的依据;</li>
                            </ul>
                        </li>
                        <li>
                            LoginController：统一登录、登录后根据backUrl跳转到访问的目标页面将登录生成的token存在认证中心系统的cookie中，在backUrl中携带token，在sso-client过滤器通过TokenManager校验token是否失效，token有效，则该请求就不会被拦截，然后在过滤器中将该token对应的loginUser和token信息存储在当前系统的session中，之后请求当前系统拦截器中从session中获取登录信息即可；
                        </li>
                    </ul>
                </li>
                <li>
                    <p>sso-client</p>
                    <blockquote>
                        <p>作为sso-server、sso-demo依赖模块，定义了SsoFilter、SsoLogoutFilter，统一控制请求的登录校验；</p>
                    </blockquote>
                </li>
                <li>
                    <p>sso-demo</p>
                    <blockquote>
                        <p>集成单点登录的子项目，需要在web.xml中配置sso-client配置的过滤器，配置认证中心的访问链接；</p>
                    </blockquote>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>

<script type="text/javascript" src="${ctx}/static/layui/layui.js"></script>
<script>
    layui.config({
        base: '${ctx}/static/js/util/'
    }).use(['element', 'laypage', 'jquery', 'menu'], function () {
        var element = layui.element;
        var $ = layui.$;
    });
</script>
</html>