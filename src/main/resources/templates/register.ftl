<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="./static/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="./static/css/main.css">

    <!--加载meta IE兼容文件-->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

    <script src="./static/js/pace.mini.js"></script>
    <link rel="stylesheet" type="text/css" href="./static/themes/blue/pace-theme-minimal.css">
</head>

<body>

<#include "common/header.ftl"/>

<div class="layui-container" style="height: 900px;">
    <div class="layui-row">
        <div class="layui-col-md8 layui-col-md-offset2">
            <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                <ul class="layui-tab-title">
                    <li class="layui-this">注册</li>
                </ul>
                <div class="layui-tab-content">
                    <div id="registerDiv">
                        <form class="layui-form" id="registerForm">
                            <div class="layui-form-item">
                                <label class="layui-form-label">用户名</label>
                                <div class="layui-input-block">
                                    <input type="text" name="title" required lay-verify="required" placeholder="请输入用户名"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">密码</label>
                                <div class="layui-input-block">
                                    <input type="password" name="password" required lay-verify="required"
                                           placeholder="请输入密码" autocomplete="off" class="layui-input">
                                </div>
                                <!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">性别</label>
                                <div class="layui-input-block">
                                    <input type="radio" name="sex" value="1" title="男" checked>
                                    <input type="radio" name="sex" value="2" title="女">
                                    <input type="radio" name="sex" value="3" title="保密">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">角色</label>
                                <div class="layui-input-block">
                                    <select name="userRole" id="userRole">
                                        <option value="" selected="selected">==请选择==</option>
                                        <option value="管理员">管理员</option>
                                        <option value="用户">用户</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-form-item layui-form-text">
                                <label class="layui-form-label">个人签名</label>
                                <div class="layui-input-block">
                                    <textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-input-block">
                                    <button class="layui-btn" type="button" id="btnRegister">立即注册</button>
                                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<#include "common/footer.ftl"/>
</body>

<script type="text/javascript" src="./static/layui/layui.js"></script>
<script>
    //加载弹出层组件
    layui.config({
        base: './static/js/util/'
    }).use(['layer', 'form', 'element'], function () {
        var layer = layui.layer;
        var form = layui.form;
        var element = layui.element;
        var $ = layui.$;

        $("#btnRegister").click(function (e) {
            e.preventDefault();
            var userName = $("input[name='title']").val();
            var password = $("input[name='password']").val();
            var sex = $("input[name='sex']:checked").val();
            var signature = $("textarea[name='desc']").val();
            var role = $("#userRole").find("option:selected").val();
            alert(role);
            if (!userName || !password) {
                alert("用户名或密码不能为空！");
                return false;
            }
            if(role==null){
                alert("请选择角色！");
                return false;
            }
            var regParam = {
                "userName": userName,
                "password": password,
                "sex": sex,
                "signature": signature,
                "role": role
            };

            $.post("ajaxRegister", regParam, function (data) {
                if (data && data.status === "SUCCESS") {
                    window.location.href = 'login';
                } else {
                    alert(data.msg || "注册失败！");
                    window.location.href = 'register';
                }
            });
        });
    })
</script>

</html>