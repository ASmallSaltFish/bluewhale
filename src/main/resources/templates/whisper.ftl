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
</head>
<body>
<div class="header">
    <div class="menu-btn">
        <div class="menu"></div>
    </div>
    <h1 class="logo">
        <a href="${ctx}/index">
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


<div class="content whisper-content">
    <div class="cont">
        <div class="whisper-list">
            <div class="item-box">
                <div class="item">
                    <div class="whisper-title">
                        <i class="layui-icon layui-icon-date"></i><span class="hour">12:25</span><span class="date">2018/06/08</span>
                    </div>
                    <p class="text-cont">
                        一直听说牛油果吃起来像肥皂、肥肉，虽然很难吃，但是价格却很贵，我还是想尝试一下。今天公司新到了新西兰牛油果，这是新西兰牛油果是第一次在中国上市，个头比普通牛油果大了一倍，被誉为“超牛果”。好奇心驱使我尝了一颗，第一次吃牛油果没有见识，切开牛油果费了好大劲，切成了这样。
                    </p>
                    <div class="img-box">
                        <img src="${ctx}/img/wy_img1.jpg">
                    </div>
                    <div class="op-list">
                        <p class="like"><i class="layui-icon layui-icon-praise"></i><span>1200</span></p>
                        <p class="edit"><i class="layui-icon layui-icon-reply-fill"></i><span>1200</span></p>
                        <p class="off"><span>展开</span><i class="layui-icon layui-icon-down"></i></p>
                    </div>
                </div>
                <div class="review-version layui-hide">
                    <div class="form">
                        <img src="${ctx}/img/header2.png">
                        <form class="layui-form" action="">
                            <div class="layui-form-item layui-form-text">
                                <div class="layui-input-block">
                                    <textarea name="desc" class="layui-textarea"></textarea>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-input-block" style="text-align: right;">
                                    <button class="layui-btn definite">確定</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="list-cont">
                        <div class="cont">
                            <div class="img">
                                <img src="${ctx}/img/header.png" alt="">
                            </div>
                            <div class="text">
                                <p class="tit"><span class="name">吳亦凡</span><span class="data">2018/06/06</span></p>
                                <p class="ct">敢问大师，师从何方？上古高人呐逐一地看完你的作品后，我的心久久
                                    不能平静！这世间怎么可能还有如此精辟的作品？我不敢相信自己的眼睛。自从改革开放以后，我就以为再也不会有任何作品能打动我，没想到今天看到这个如此精妙绝伦的作品好厉害！</p>
                            </div>
                        </div>
                        <div class="cont">
                            <div class="img">
                                <img src="${ctx}/img/header.png" alt="">
                            </div>
                            <div class="text">
                                <p class="tit"><span class="name">吳亦凡</span><span class="data">2018/06/06</span></p>
                                <p class="ct">敢问大师，师从何方？上古高人呐逐一地看完你的作品后，我的心久久
                                    不能平静！这世间怎么可能还有如此精辟的作品？我不敢相信自己的眼睛。自从改革开放以后，我就以为再也不会有任何作品能打动我，没想到今天看到这个如此精妙绝伦的作品好厉害！</p>
                            </div>
                        </div>
                        <div class="cont">
                            <div class="img">
                                <img src="${ctx}/img/header.png" alt="">
                            </div>
                            <div class="text">
                                <p class="tit"><span class="name">吳亦凡</span><span class="data">2018/06/06</span></p>
                                <p class="ct">敢问大师，师从何方？上古高人呐逐一地看完你的作品后，我的心久久
                                    不能平静！这世间怎么可能还有如此精辟的作品？我不敢相信自己的眼睛。自从改革开放以后，我就以为再也不会有任何作品能打动我，没想到今天看到这个如此精妙绝伦的作品好厉害！</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="item-box">
                <div class="item">
                    <div class="whisper-title">
                        <i class="layui-icon layui-icon-date"></i><span class="hour">12:25</span><span class="date">2018/06/08</span>
                    </div>
                    <p class="text-cont">
                        一直听说牛油果吃起来像肥皂、肥肉，虽然很难吃，但是价格却很贵，我还是想尝试一下。今天公司新到了新西兰牛油果，这是新西兰牛油果是第一次在中国上市，个头比普通牛油果大了一倍，被誉为“超牛果”。好奇心驱使我尝了一颗，第一次吃牛油果没有见识，切开牛油果费了好大劲，切成了这样。
                    </p>
                    <div class="img-box">
                        <img src="${ctx}/img/wy_img2.jpg">
                        <img src="${ctx}/img/wy_img3.jpg">
                        <img src="${ctx}/img/wy_img4.jpg">
                    </div>
                    <div class="op-list">
                        <p class="like"><i class="layui-icon layui-icon-praise"></i><span>1200</span></p>
                        <p class="edit"><i class="layui-icon layui-icon-reply-fill"></i><span>1200</span></p>
                        <p class="off"><span>展开</span><i class="layui-icon layui-icon-down"></i></p>
                    </div>
                </div>
                <div class="review-version layui-hide">
                    <div class="form">
                        <img src="${ctx}/img/header2.png">
                        <form class="layui-form" action="">
                            <div class="layui-form-item layui-form-text">
                                <div class="layui-input-block">
                                    <textarea name="desc" class="layui-textarea"></textarea>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-input-block" style="text-align: right;">
                                    <button class="layui-btn definite">確定</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="list-cont">
                        <div class="cont">
                            <div class="img">
                                <img src="${ctx}/img/header.png" alt="">
                            </div>
                            <div class="text">
                                <p class="tit"><span class="name">吳亦凡</span><span class="data">2018/06/06</span></p>
                                <p class="ct">敢问大师，师从何方？上古高人呐逐一地看完你的作品后，我的心久久
                                    不能平静！这世间怎么可能还有如此精辟的作品？我不敢相信自己的眼睛。自从改革开放以后，我就以为再也不会有任何作品能打动我，没想到今天看到这个如此精妙绝伦的作品好厉害！</p>
                            </div>
                        </div>
                        <div class="cont">
                            <div class="img">
                                <img src="${ctx}/img/header.png" alt="">
                            </div>
                            <div class="text">
                                <p class="tit"><span class="name">吳亦凡</span><span class="data">2018/06/06</span></p>
                                <p class="ct">敢问大师，师从何方？上古高人呐逐一地看完你的作品后，我的心久久
                                    不能平静！这世间怎么可能还有如此精辟的作品？我不敢相信自己的眼睛。自从改革开放以后，我就以为再也不会有任何作品能打动我，没想到今天看到这个如此精妙绝伦的作品好厉害！</p>
                            </div>
                        </div>
                        <div class="cont">
                            <div class="img">
                                <img src="${ctx}/img/header.png" alt="">
                            </div>
                            <div class="text">
                                <p class="tit"><span class="name">吳亦凡</span><span class="data">2018/06/06</span></p>
                                <p class="ct">敢问大师，师从何方？上古高人呐逐一地看完你的作品后，我的心久久
                                    不能平静！这世间怎么可能还有如此精辟的作品？我不敢相信自己的眼睛。自从改革开放以后，我就以为再也不会有任何作品能打动我，没想到今天看到这个如此精妙绝伦的作品好厉害！</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="item-box">
                <div class="item">
                    <div class="whisper-title">
                        <i class="layui-icon layui-icon-date"></i><span class="hour">12:25</span><span class="date">2018/06/08</span>
                    </div>
                    <p class="text-cont">
                        一直听说牛油果吃起来像肥皂、肥肉，虽然很难吃，但是价格却很贵，我还是想尝试一下。今天公司新到了新西兰牛油果，这是新西兰牛油果是第一次在中国上市，个头比普通牛油果大了一倍，被誉为“超牛果”。好奇心驱使我尝了一颗，第一次吃牛油果没有见识，切开牛油果费了好大劲，切成了这样。
                    </p>
                    <div class="img-box">
                        <img src="${ctx}/img/wy_img5.jpg">
                    </div>
                    <div class="op-list">
                        <p class="like"><i class="layui-icon layui-icon-praise"></i><span>1200</span></p>
                        <p class="edit"><i class="layui-icon layui-icon-reply-fill"></i><span>1200</span></p>
                        <p class="off" off='true'><span>收起</span><i class="layui-icon layui-icon-up"></i></p>
                    </div>
                </div>
                <div class="review-version">
                    <div class="form">
                        <img src="${ctx}/img/header2.png">
                        <form class="layui-form" action="">
                            <div class="layui-form-item layui-form-text">
                                <div class="layui-input-block">
                                    <textarea name="desc" class="layui-textarea"></textarea>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-input-block" style="text-align: right;">
                                    <button class="layui-btn definite">確定</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="list-cont">
                        <div class="cont">
                            <div class="img">
                                <img src="${ctx}/img/header.png" alt="">
                            </div>
                            <div class="text">
                                <p class="tit"><span class="name">吳亦凡</span><span class="data">2018/06/06</span></p>
                                <p class="ct">敢问大师，师从何方？上古高人呐逐一地看完你的作品后，我的心久久
                                    不能平静！这世间怎么可能还有如此精辟的作品？我不敢相信自己的眼睛。自从改革开放以后，我就以为再也不会有任何作品能打动我，没想到今天看到这个如此精妙绝伦的作品好厉害！</p>
                            </div>
                        </div>
                        <div class="cont">
                            <div class="img">
                                <img src="${ctx}/img/header.png" alt="">
                            </div>
                            <div class="text">
                                <p class="tit"><span class="name">吳亦凡</span><span class="data">2018/06/06</span></p>
                                <p class="ct">敢问大师，师从何方？上古高人呐逐一地看完你的作品后，我的心久久
                                    不能平静！这世间怎么可能还有如此精辟的作品？我不敢相信自己的眼睛。自从改革开放以后，我就以为再也不会有任何作品能打动我，没想到今天看到这个如此精妙绝伦的作品好厉害！</p>
                            </div>
                        </div>
                        <div class="cont">
                            <div class="img">
                                <img src="${ctx}/img/header.png" alt="">
                            </div>
                            <div class="text">
                                <p class="tit"><span class="name">吳亦凡</span><span class="data">2018/06/06</span></p>
                                <p class="ct">敢问大师，师从何方？上古高人呐逐一地看完你的作品后，我的心久久
                                    不能平静！这世间怎么可能还有如此精辟的作品？我不敢相信自己的眼睛。自从改革开放以后，我就以为再也不会有任何作品能打动我，没想到今天看到这个如此精妙绝伦的作品好厉害！</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="demo" style="text-align: center;"></div>
    </div>
</div>
<script type="text/html" id="laytplCont">
    <div class="cont">
        <div class="img">
            <img src="{{d.avatar}}" alt="">
        </div>
        <div class="text">
            <p class="tit"><span class="name">{{d.name}}</span><span class="data">2018/06/06</span></p>
            <p class="ct">{{d.cont}}</p>
        </div>
    </div>
</script>
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
    }).use(['element', 'laypage', 'form', 'menu'], function () {
        element = layui.element, laypage = layui.laypage, form = layui.form, menu = layui.menu;
        laypage.render({
            elem: 'demo'
            , count: 70 //数据总数，从服务端得到
        });
        menu.init();
        menu.off();
        menu.submit()
    })
</script>
</body>
</html>