<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>登录</title>
    <meta name="keywords" content="HTML5,美观,简洁大气,响应式,第三方登录,网页模板">
    <link rel="stylesheet" type="text/css" href="./static/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./static/fonts/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="./static/fonts/material-design-iconic-font.min.css">
    <link rel="stylesheet" type="text/css" href="./static/css/util.css">
    <link rel="stylesheet" type="text/css" href="./static/css/login.css">
</head>

<body>

<div class="limiter">
    <div class="container-login100" style="background-image: url(&#39;./static/images/bg-01.jpg&#39;);">
        <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
            <form class="login100-form validate-form" id="loginForm">
                <span class="login100-form-title p-b-49">登录</span>

                <div class="wrap-input100 validate-input m-b-23" data-validate="请输入用户名">
                    <span class="label-input100">用户名</span>
                    <input class="input100" type="text" name="userName" placeholder="请输入用户名" autocomplete="off"
                           value="admin">
                    <span class="focus-input100" data-symbol=""></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="请输入密码">
                    <span class="label-input100">密码</span>
                    <input class="input100" type="password" name="password" placeholder="请输入密码" value="admin">
                    <span class="focus-input100" data-symbol=""></span>
                </div>

                <div class="text-right p-t-8 p-b-31">
                    <a href="javascript:">忘记密码？</a>
                </div>

                <div class="container-login100-form-btn">
                    <div class="wrap-login100-form-btn">
                        <div class="login100-form-bgbtn"></div>
                        <button class="login100-form-btn" id="btnLogin">登 录</button>
                    </div>
                </div>

                <div class="txt1 text-center p-t-54 p-b-20">
                    <span>第三方登录</span>
                </div>

                <div class="flex-c-m">
                    <a href="http://yanshi.sucaihuo.com/modals/49/4919/demo/#" class="login100-social-item bg1">
                        <i class="fa fa-wechat"></i>
                    </a>

                    <a href="http://yanshi.sucaihuo.com/modals/49/4919/demo/#" class="login100-social-item bg2">
                        <i class="fa fa-qq"></i>
                    </a>

                    <a href="http://yanshi.sucaihuo.com/modals/49/4919/demo/#" class="login100-social-item bg3">
                        <i class="fa fa-weibo"></i>
                    </a>
                </div>

                <div class="flex-col-c p-t-25">
                    <a href="register" class="txt2">立即注册</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="./static/js/jquery-3.2.1.min.js"></script>
<script src="./static/js/main.js"></script>
<script type="text/javascript" src="./static/layui/layui.js"></script>
</body>

<script type="text/javascript">
    layui.config({
        base: './static/js/util/'
    }).use(['jquery', 'layer'], function () {
        var layer = layui.layer;
        var $ = layui.$;

        $("#btnLogin").click(function (e) {
            e.preventDefault();
            var userName = $("input[name='userName']").val();
            var password = $("input[name='password']").val();
            var isRememberMe = $("input[name='rememberMe']").prop("checked");
            if (!userName || !password) {
                window.setTimeout(function () {
                    layer.msg('用户名密码不能为空！', {icon: 2, time: 3000});
                }, 2000);
                return false;
            }

            var loginParam = {
                "userName": userName,
                "password": password,
                "rememberMe": isRememberMe
            };

            $.post("ajaxLogin", loginParam, function (data) {
                if (data && data.status === "SUCCESS") {
                    layer.msg('登录成功！', {icon: 1, time: 2000}, function () {
                        window.location.href = 'index';
                    });
                } else {
                    layer.msg(data.msg || '用户名密码不能为空！', {icon: 2, time: 3000});
                }
            });
        });
    })
</script>
</html>