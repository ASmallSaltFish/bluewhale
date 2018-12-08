<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="utf-8" />
    <title>编辑和在线预览markdown</title>
    <link rel="stylesheet" href="./static/css/style.css" />
    <link rel="stylesheet" href="./static/css/editormd.css" />
    <link rel="shortcut icon" href="https://pandao.github.io/editor.md/favicon.ico" type="image/x-icon" />
</head>

<body>
<div id="layout">
    <header>
        <h1>Auto height test</h1>
    </header>
    <div class="btns">
        <button id="append-btn">Append markdown</button>
    </div>
    <div id="test-editormd">
            <textarea style="display:none;">### Settings

                ```javascript
                var testEditor = editormd("test-editormd", {
                    autoHeight : true
                });
                ```
            </textarea>
    </div>


    <div style="background-color: yellow">
        <div class="markdown-body editormd-preview-container" previewcontainer="true" style="padding: 20px 20px 50px 40px;">
            <h3 id="h3-settings">
                <a name="Settings" class="reference-link"></a>
                <span class="header-link octicon octicon-link"></span>Settings</h3>
            <pre class="prettyprint linenums prettyprinted" style=""><ol class="linenums"><li class="L0"><code
                                class="lang-javascript"><span class="kwd">var</span><span
                                    class="pln"> testEditor </span><span class="pun">=</span><span
                                    class="pln"> editormd</span><span class="pun">(</span><span
                                    class="str">"test-editormd"</span><span class="pun">,</span><span
                                    class="pln"> </span><span class="pun">{</span></code></li><li class="L1"><code
                                class="lang-javascript"><span class="pln">    autoHeight </span><span
                                    class="pun">:</span><span class="pln"> </span><span class="kwd">true</span></code></li><li
                            class="L2"><code class="lang-javascript"><span class="pun">});</span></code></li></ol></pre>
        </div>
    </div>
</div>

<input type="button" value="获取预览markdown文本" id="btnMarkdown"/>
</body>


<script src="./static/js/jquery.min.js"></script>
<script src="./static/js/editormd.js"></script>
<script type="text/javascript">
    var testEditor;
    $(function () {
        testEditor = editormd("test-editormd", {
            width: "90%",
            autoHeight: true,
            path: "./static/lib/",
            htmlDecode: "style,script,iframe",
            tex: true,
            emoji: true,
            taskList: true,
            flowChart: true,
            sequenceDiagram: true
        });

        $("#append-btn").click(function () {
            $.get("./static/test.md", function (md) {
                testEditor.appendMarkdown(md);
            });
        });

        $("#btnMarkdown").click(function(){
            var prefiewHtml = $(".editormd-preview").html();
            console.log(prefiewHtml);
        });
    });
</script>

</html>