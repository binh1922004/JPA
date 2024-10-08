<%--
  Created by IntelliJ IDEA.
  User: binh
  Date: 10/8/2024
  Time: 10:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/conmon/taglib.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Video Information</h1>
<form action="<c:url value="/admin/video/insert"/>" method="post" enctype="multipart/form-data">
    <label for="videoId">Video ID:</label>
    <input type="text" id="videoId" name="videoId"><br>

    <label for="title">Title:</label>
    <input type="text" id="title" name="title"><br>
    <label for="poster">Link poster</label><br>
    <input type="text" id="poster" name="poster"><br>

    <label for="poster1">Upload poster</label><br>
    <input type="file" id="poster1" name="poster1" ><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description"></textarea><br>

    <label for="views">Views:</label>
    <input type="number" id="views" name="views"><br>

    <label for="status">Active</label><br>
    <input type="radio" id="activeon" name="active" value="1">
    <label for="activeon">Yes</label>
    <input type="radio" id="activeoff" name="active" value="0">
    <label for="activeoff">No</label>

    <input type="submit" value="Submit">
</form>
</body>
</html>
