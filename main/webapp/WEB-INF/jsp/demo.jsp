<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>demo</title>
    <%--<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>--%>
    <%--<script src="https://cdnjs.cloudflare.com/ajax/libs/echarts/3.6.2/echarts.min.js"></script>--%>
    <%--<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">--%>


    <script src="/js/jquery-3.2.1.min.js"></script>
    <link rel="stylesheet" href="/css/bootstrap.css"/>
    <script src="/js/echarts.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            var myChart = echarts.init(document.getElementById('main'));
            myChart.setOption(${json});
        });
        function validate(){
            if($("#file").val()==""){
                alert("请上传文件");
                return false;
            }
            return true;
        }
    </script>
    <style>
        #widget {
            position: absolute;
            left: 0;
            right: 0;
            bottom: 0;
            top: 0px;
            background: #f3f3f3;
        }
    </style>
</head>
<body>
<div id="widget">
    <div id="main" style="width:80%;height:80%;"></div>
    <a href="/demo/${type}/${id}/downlaod" class="btn btn-default" role="button">下载模板</a>
    <hr>
    <form class="form-inline" role="form" action="/series/${type}/${id}/upload" enctype="multipart/form-data" method="post"
            onsubmit="return validate()">
        <div class="form-group">
            <input type="file" id="file" name="file" class="form-control"/>
        </div>
        <div class="form-group">
            <input type="submit" value="生成我的图表"/>
        </div>
    </form>
</div>
</body>
</html>
