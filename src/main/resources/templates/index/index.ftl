<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>MyBlog</title>
    <#--<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">-->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/main.css">

    <!--加载meta IE兼容文件-->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <style>
        .article-list a:hover{
            color: blue;
        }

        .article-item {
            height: 140px;
            line-height: 24px;
            margin: 10px auto;
            background-color: #f8f9fa;
        }
    </style>
</head>

<body>
<#include "../common/header.ftl"/>

<div class="banner">
    <div class="layui-carousel" id="myCarousel">
        <div carousel-item>
            <div><img src="${ctx}/static/img/bannel/bannel_1.jpg" width="100%" height="600px"></div>
            <div><img src="${ctx}/static/img/bannel/bannel_2.jpg" width="100%" height="600px"></div>
            <div><img src="${ctx}/static/img/bannel/bannel_3.jpg" width="100%" height="600px"></div>
            <div><img src="${ctx}/static/img/bannel/bannel_4.jpg" width="100%" height="600px"></div>
            <div><img src="${ctx}/static/img/bannel/bannel_5.jpg" width="100%" height="600px"></div>
        </div>
    </div>
</div>

<div class="content">
    <div class="cont w1000">
        <div class="title">
        <span class="layui-breadcrumb" lay-separator="|">
          <a href="javascript:;" class="active">设计文章</a>
          <a href="javascript:;">前端文章</a>
          <a href="javascript:;">旅游杂记</a>
        </span>
        </div>
        <div class="list-item">
            <#if articleList??>
                <#list articleList as article>
                    <div class="article-item">
                        <div class="layui-col-md4 layui-col-sm6 layui-col-xs6 layui-col-space30">
                            <img src="./static/images/bg-01.jpg" height="130px" width="80%">
                        </div>
                        <div class="layui-col-md8 layui-col-sm6 layui-col-xs6">
                            <div class="layui-row article-list">
                                <div class="layui-col-md12">
                                    <a href="${ctx}/article/previewArticle?articleId=${(article.articleId)!}"><h3>${(article.title)!}</h3></a>
                                </div>
                                <div class="article-main-body">
                                    <div class="layui-col-md12" style="height:75px; overflow:hidden;">
                                        ${(article.desc)!}这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述
                        这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描述这里是描
                                    </div>
                                    <div class="layui-col-md6 layui-col-xs6 layui-col-sm6" style="line-height: 35px;">
                                        <i class="layui-icon layui-icon-username" style="color: orange;"></i><span>&nbsp;${(article.author)!}</span>
                                        &nbsp; | &nbsp;
                                        <i class="layui-icon layui-icon-read" style="color: orange;"></i><span>&nbsp;javaSript,java,mysql</span>
                                    </div>

                                    <div class="layui-col-md6 layui-col-xs6 layui-col-sm6" style="text-align: right">
                                        <i class="layui-icon layui-icon-praise" style="color: orange"></i>
                                        &nbsp; | &nbsp;
                                        <i class="layui-icon layui-icon-dialogue" style="margin-right: 20px; color: orange;"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </#list>
            <#else>
                你还未发布任何博客哟~
            </#if>

        </div>
        <div id="demo" style="text-align: center;"></div>
    </div>
</div>

<#include "../common/footer.ftl"/>
</body>

<script type="text/javascript" src="${ctx}/static/layui/layui.js"></script>

<script type="text/javascript">
    layui.config({
        base: '${ctx}/static/js/util/'
    }).use(['element', 'laypage', 'jquery', 'menu', 'carousel'], function () {
        var element = layui.element;
        var laypage = layui.laypage;
        var $ = layui.$;
        var menu = layui.menu;
        var carousel = layui.carousel;

        laypage.render({
            elem: 'demo',
            count: 70 //数据总数，从服务端得到
        });

        menu.init();

        //首页轮播图
        carousel.render({
            elem: '#myCarousel',
            height: '600px',
            width: '100%',
            interval: 3000
        });
    })
</script>
</html>