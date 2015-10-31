<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <script src="resources/js/jquery-1.11.2.min.js"></script>
    <script>

        $.ajax({
            url: "/admin/dictionary/test.do",
            success: function(res) {
                console.log(res);
            }
        });

    </script>
</head>
<body>

</body>
</html>
