<@override name="title"></@override>
<@override name="head">
<style>

</style>
</@override>
<@override name="app">
<div class="layui-fluid">
<form action="http://127.0.0.1:8082/user/login" method="post">
    <input type="text" name="username" value="admin" title="username"/>
    <input type="text" name="password" value="1234567" title="password"/>
    <input type="text" name="service" value="http://127.0.0.1:8082/login" title="service"/>
    <input type="submit" value="提交">
</form>
</div>
</@override>
<@override name="script">
<script>

</script>
</@override>
<@extends name="/common/base.ftl" />