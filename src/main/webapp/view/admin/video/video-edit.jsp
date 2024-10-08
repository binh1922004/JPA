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
<form action="<c:url value="/admin/video/update"/>" method="post">
    <label for="videoId">Video ID:</label>
    <input type="text" id="videoId" name="videoId" value="${video.videoId}" readonly><br>

    <label for="title">Title:</label>
    <input type="text" id="title" name="title" value="${video.title}"><br>

    <label for="poster">Poster:</label>
    <input type="text" id="poster" name="poster" value="${video.poster}"><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description">${video.description}</textarea><br>

    <label for="views">Views:</label>
    <input type="number" id="views" name="views" value="${video.views}"><br>

    <label for="status">Active</label><br>
    <input type="radio" id="activeon" name="active" value="1" ${video.active==1?'checked' : ''}>
    <label for="activeon">Yes</label>
    <input type="radio" id="activeoff" name="active" value="0" ${video.active==0?'checked' : ''}>
    <label for="activeoff">No</label>

    <input type="submit" value="Update">
</form>
</body>
</html>
