<#assign ctx=request.contextPath/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/main.css">

    <!--加载meta IE兼容文件-->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <script src="${ctx}/js/pace.mini.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/themes/blue/pace-theme-minimal.css">
</head>

<body>
<div class="header">
    <div class="menu-btn">
        <div class="menu"></div>
    </div>
    <h1 class="logo">
        <a href="index">
            <span>MYBLOG</span>
            <img src="${ctx}/img/logo.png">
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
    <p class="welcome-text">
        欢迎来到<span class="name">小明</span>的博客~
    </p>
</div>

<div class="banner">
    <div class="layui-carousel" id="myCarousel">
        <div carousel-item>
            <div><img src="${ctx}/img/bannel/bannel_1.jpg" width="100%" height="600px"></div>
            <div><img src="${ctx}/img/bannel/bannel_2.jpg" width="100%" height="600px"></div>
            <div><img src="${ctx}/img/bannel/bannel_3.jpg" width="100%" height="600px"></div>
            <div><img src="${ctx}/img/bannel/bannel_4.jpg" width="100%" height="600px"></div>
            <div><img src="${ctx}/img/bannel/bannel_5.jpg" width="100%" height="600px"></div>
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
                            <div class="img"><img src="${ctx}/img/sy_img1.jpg" alt=""></div>
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
                            <div class="img"><img src="${ctx}/img/sy_img2.jpg" alt=""></div>
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
                            <div class="img"><img src="${ctx}/img/sy_img3.jpg" alt=""></div>
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
                            <div class="img"><img src="${ctx}/img/sy_img4.jpg" alt=""></div>
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
                            <div class="img"><img src="${ctx}/img/sy_img5.jpg" alt=""></div>
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

<div class="footer-wrap">
    <div class="footer w1000">
        <div class="qrcode">
            <img src="${ctx}/img/erweima.jpg">
        </div>
        <div class="practice-mode">
            <img src="${ctx}/img/down_img.jpg">
            <div class="text">
                <h4 class="title">我的联系方式</h4>
                <p>微信<span class="WeChat">1234567890</span></p>
                <p>手机<span class="iphone">1234567890</span></p>
                <p>邮箱<span class="email">1234567890@qq.com</span></p>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({
        base: '${ctx}/js/util/'
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
            interval: 3000,
            // arrow: 'always'
            //,anim: 'updown' //切换动画方式
        });
    })
</script>
</body>
</html>