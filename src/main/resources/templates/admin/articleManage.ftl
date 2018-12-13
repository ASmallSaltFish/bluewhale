<div class="demoTable">
    <div class="layui-inline" style="margin-right: 10px;">
        <input style="width: 250px;" class="layui-input" name="article.keyword" id="keyword" autocomplete="off"
               placeholder="标题/描述/内容/标签">
    </div>

    <div class="layui-form layui-inline" style="margin-right: 10px;">
        <div class="layui-inline">
            <select name="article.personalFlag">
                <option value="" selected>私有设置</option>
                <option value="0">公开</option>
                <option value="1">私有</option>
            </select>
        </div>
    </div>

    发布时间：
    <div class="layui-form layui-inline" style="margin-right: 10px;">
        <div class="layui-inline">
            <input type="text" name="article.publishStartDate" class="layui-input" id="publishStartDate">
        </div>
        -
        <div class="layui-inline">
            <input type="text" name="article.publishEndDate" class="layui-input" id="publishEndDate"
                   value="${.now?string('yyyy-MM-dd')}">
        </div>
    </div>

    <button class="layui-btn" data-type="reload" id="search">搜索</button>

    <button class="layui-btn layui-btn-normal" data-type="export" id="btnExport">导出</button>

    <div class="layui-row" style="margin-top: 20px;">
        <button class="layui-btn layui-btn-primary">新增</button>
    </div>

    <div class="layui-tab" lay-filter="orderTab" style="margin-top: 30px;">
        <ul class="layui-tab-title">
            <li class="layui-this" order-status="">所有文章</li>
            <li order-status="ARTICLE_PUBLIC">公开</li>
            <li order-status="ARTICLE_PRIVATE">私有</li>
        </ul>

        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <table class="layui-hide" id="allOrderListTb" lay-filter="article"></table>
            </div>
            <div class="layui-tab-item">
                <table class="layui-hide" id="articlePublicListTb" lay-filter="article"></table>
            </div>
            <div class="layui-tab-item">
                <table class="layui-hide" id="articlePrivateListTb" lay-filter="article"></table>
            </div>
        </div>
    </div>
</div>


<script src="${ctx}/static/js/jquery.min.js"></script>

<script type="text/javascript" src="${ctx}/static/layui/layui.js"></script>

<!--时间格式化-->
<script id="createTime" type="text/html">
    {{#
    var date = new Date();
    date.setTime(d.createTime);
    return date.Format("yyyy-MM-dd hh:mm:ss");
    }}
</script>

<script>
    //加载弹出层组件
    layui.config({
        dir: '${ctx}/static/layui/'
    }).use(['layer', 'form', 'element', 'table'], function () {
        var layer = layui.layer;
        var form = layui.form;
        var element = layui.element;
        var table = layui.table;
        var $ = layui.$;

        var table_data = [];
        //第一个实例
        table.render({
            elem: '#allOrderListTb',
            height: 315,
            url: '${ctx}/article/listArticles',
            page: true,
            cols: [[
                {checkbox: true, fiexed: true, unresize: true},
                {field: 'title', title: '标题'},
                {field: 'author', title: '作者'},
                {field: 'categoryId', title: '分类'},
                {field: 'imageCover', title: '封面'},
                {field: 'description', title: '描述'},
                {field: 'createBy', title: '创建人'},
                {field: 'createTime', title: '创建时间', templet: "<div>{{layui.util.toDateString(d.createTime, 'yyyy-MM-dd HH:mm:ss')}}</div>"},
                {field: 'lastModifyBy', title: '修改人'},
                {field: 'lastModifyTime', title: '修改时间', templet: "<div>{{layui.util.toDateString(d.lastModifyTime, 'yyyy-MM-dd HH:mm:ss')}}</div>"}
            ]],
            id: 'allOrderListTb',
            limits: [5, 10],
            where: {
                'keyword': $("#keyword").val()
            },
            request: {
                pageName: "pageNum",
                limitName: "pageSize"
            }, done: function (res, curr, count) {
                table_data = res.data;
                console.log("数据渲染成功！");
            }
        });

        //存储选中行的userId
        var selectedArticleIds = [];
        table.on('checkbox(billTb)', function (obj) {
            if (obj.checked === true) {
                if (obj.type === 'one') {
                    selectedArticleIds.push(obj.data.userId);
                } else {
                    for (var i = 0; i < table_data.length; i++) {
                        selectedArticleIds.push(table_data[i].userId);
                    }
                }
            } else {
                if (obj.type === 'one') {
                    for (var i = 0; i < selectedArticleIds.length; i++) {
                        if (selectedArticleIds[i] === obj.data.userId) {
                            selectedArticleIds.remove(i);
                        }
                    }
                } else {
                    selectedArticleIds = [];
                }
            }

            console.log("-->>");
            console.log(selectedArticleIds);
        });

        //新增人员
        $("#btnAdd").on('click', function () {
            layer.open({
                title: '小记一笔',
                type: 2,
                anim: 1,
                area: ['500px', '600px'],
                content: '${ctx}/bill/addBill',
                resize: false,
                cancel: function () {
                    console.log("-->取消了");
                }
            });
        });


        //数组添加remove方法
        Array.prototype.remove = function (dx) {
            if (isNaN(dx) || dx < this.length) {
                return false;
            }

            for (var i = 0, n = 0; i < this.length; i++) {
                if (this[i] != this[dx]) {
                    this[n++] = this[i];
                }
            }

            this.length -= 1;
        }
    });
</script>
</body>

</html>