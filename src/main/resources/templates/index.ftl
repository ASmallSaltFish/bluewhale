<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>MyBlog</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/main.css">

    <!--加载meta IE兼容文件-->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<#include "common/header.ftl"/>

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
            <div class="item">
                <div class="layui-fluid">
                    <div class="layui-row">
                        <div class="layui-col-xs12 layui-col-sm4 layui-col-md5">
                            <div class="img"><img src="${ctx}/static/img/sy_img1.jpg" alt=""></div>
                        </div>
                        <div class="layui-col-xs12 layui-col-sm8 layui-col-md7">
                            <div class="item-cont">
                                <h3>空间立体效果图，完美呈现最终效果
                                    <button class="layui-btn layui-btn-danger new-icon">new</button>
                                </h3>
                                <h5>设计文章</h5>
                                <p>
                                    室内设计作为一门新兴的学科，尽管还只是近数十年的事，但是人们有意识地对自己生活、生产活动的室内进行安排布置，甚至美化装饰，赋予室内环境以所祈使的气氛，却早巳从人类文明伊始的时期就已存在</p>
                                <a href="${ctx}/article/example" class="go-icon"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="layui-fluid">
                    <div class="layui-row">
                        <div class="layui-col-xs12 layui-col-sm4 layui-col-md5">
                            <div class="img"><img src="${ctx}/static/img/sy_img2.jpg" alt=""></div>
                        </div>
                        <div class="layui-col-xs12 layui-col-sm8 layui-col-md7">
                            <div class="item-cont">
                                <h3>空间立体效果图，完美呈现最终效果
                                    <button class="layui-btn layui-btn-danger new-icon">new</button>
                                </h3>
                                <h5>设计文章</h5>
                                <p>
                                    室内设计作为一门新兴的学科，尽管还只是近数十年的事，但是人们有意识地对自己生活、生产活动的室内进行安排布置，甚至美化装饰，赋予室内环境以所祈使的气氛，却早巳从人类文明伊始的时期就已存在</p>
                                <a href="${ctx}/details.html" class="go-icon"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="layui-fluid">
                    <div class="layui-row">
                        <div class="layui-col-xs12 layui-col-sm4 layui-col-md5">
                            <div class="img"><img src="${ctx}/static/img/sy_img3.jpg" alt=""></div>
                        </div>
                        <div class="layui-col-xs12 layui-col-sm8 layui-col-md7">
                            <div class="item-cont">
                                <h3>空间立体效果图，完美呈现最终效果
                                    <button class="layui-btn layui-btn-danger new-icon">new</button>
                                </h3>
                                <h5>设计文章</h5>
                                <p>
                                    室内设计作为一门新兴的学科，尽管还只是近数十年的事，但是人们有意识地对自己生活、生产活动的室内进行安排布置，甚至美化装饰，赋予室内环境以所祈使的气氛，却早巳从人类文明伊始的时期就已存在</p>
                                <a href="details.html" class="go-icon"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="layui-fluid">
                    <div class="layui-row">
                        <div class="layui-col-xs12 layui-col-sm4 layui-col-md5">
                            <div class="img"><img src="${ctx}/static/img/sy_img4.jpg" alt=""></div>
                        </div>
                        <div class="layui-col-xs12 layui-col-sm8 layui-col-md7">
                            <div class="item-cont">
                                <h3>空间立体效果图，完美呈现最终效果
                                    <button class="layui-btn layui-btn-danger new-icon">new</button>
                                </h3>
                                <h5>设计文章</h5>
                                <p>
                                    室内设计作为一门新兴的学科，尽管还只是近数十年的事，但是人们有意识地对自己生活、生产活动的室内进行安排布置，甚至美化装饰，赋予室内环境以所祈使的气氛，却早巳从人类文明伊始的时期就已存在</p>
                                <a href="details.html" class="go-icon"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="layui-fluid">
                    <div class="layui-row">
                        <div class="layui-col-xs12 layui-col-sm4 layui-col-md5">
                            <div class="img"><img src="${ctx}/static/img/sy_img5.jpg" alt=""></div>
                        </div>
                        <div class="layui-col-xs12 layui-col-sm8 layui-col-md7">
                            <div class="item-cont">
                                <h3>空间立体效果图，完美呈现最终效果
                                    <button class="layui-btn layui-btn-danger new-icon">new</button>
                                </h3>
                                <h5>设计文章</h5>
                                <p>
                                    室内设计作为一门新兴的学科，尽管还只是近数十年的事，但是人们有意识地对自己生活、生产活动的室内进行安排布置，甚至美化装饰，赋予室内环境以所祈使的气氛，却早巳从人类文明伊始的时期就已存在</p>
                                <a href="details.html" class="go-icon"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="demo" style="text-align: center;"></div>
    </div>
</div>

<#include "common/footer.ftl"/>
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