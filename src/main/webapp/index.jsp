<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<body>
<h2>Tomcat 1!</h2>
<h2>Tomcat 1!</h2>
<h2>Tomcat 1!</h2>

<h2>Hello World!</h2>

Spring MVC上传文件
<form action="manager/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="springmvc上传文件">
</form>

富文本图片文件上传
<form action="manager/product/richtext_img_upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="富文本图片上传文件">
</form>

</body>
</html>

<head>
    <meta charset="UTF-8" />
</head>



