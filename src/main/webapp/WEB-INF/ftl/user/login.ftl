<!DOCTYPE html>
<html lang="zh-cn">
    <head>
        <meta charset="utf-8">
        <title>Shiro Demo | 登录</title>
        <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
        <link   rel="icon" href="/favicon.ico" type="image/x-icon" />
        <link   rel="shortcut icon" href="/favicon.ico" />
        <!-- CSS -->
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/managementsystem/css/login/reset.css"/>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/managementsystem/css/login/supersized.css"/>
        <link rel="stylesheet" type="text/css" href="http://localhost:8080/managementsystem/css/login/style.css"/>
		<style>
			canvas{position: fixed; top: 0px; left: 0px; }
		</style>
    </head>

    <body id="body">

        <div class="page-container">
            <h1>Login</h1>
            <h1>本项目请使用Tomcat，请勿使用Jetty</h1>
            <form id="_form" action="" method="post">
                <input type="text" name="account" class="username" placeholder="Account">
                <input type="password" name="password" class="password" placeholder="Password">
                <div style="text-align: left; margin-left: 10px;">
                <label><input type="checkbox" checked="checked"  id="rememberMe"style="width: 10px; height: 10px;">记住我</label>
                <p><b style='color:red;'>如果重启Tomcat，还是登录状态，是因为这里勾选了“记住我”。</b></p>
                </div>
                <button type="button" id="login">登录</button>
                <button type="button" id="register" class="register">Register</button>
                <div class="connect" >
	                <p>有问题请这样解决:</p>
	                <p>
	                    <a class="" style="width: auto; color: rgb(255, 255, 255);" target="_blank" href="http://www.sojson.com/shiro">点我看本项目介绍，<br><br>（登录密码请看这里。）</a>
	                    <a class="" style="width: auto; color: rgb(255, 255, 255);" target="_blank" href="http://jq.qq.com/?_wv=1027&k=YpqCNd">各种不会 QQ群：259217951</a>
	                </p>
	            </div>

                <div class="error"><span>+</span></div>
            </form>
        </div>
        <!-- Javascript -->
        <script  src="http://localhost:8080/managementsystem/js/common/jquery/jquery1.8.3.min.js"></script>
        <script  src="http://localhost:8080/managementsystem/js/common/MD5.js"></script>
        <script  src="http://localhost:8080/managementsystem/js/common/supersized.3.2.7.min.js"></script>
        <script  src="http://localhost:8080/managementsystem/js/common/supersized-init.js"></script>
        <script  src="http://localhost:8080/managementsystem/js/common/layer/layer.js"></script>
        <script >
            jQuery(document).ready(function() {
                try{
                    var _href = window.location.href+"";
                    if(_href && _href.indexOf('?kickout')!=-1){
                        layer.msg('您已经被踢出，请重新登录！');
                    }
                }catch(e){

                }
                //回车事件绑定
                document.onkeydown=function(event){
                    var e = event || window.event || arguments.callee.caller.arguments[0];
                    if(e && e.keyCode==13){
                        $('#login').click();
                    }
                };

                //登录操作
                $('#login').click(function(){

                    var username = $('.username').val();
                    var password = $('.password').val();
                    if(username == '') {
                        $('.error').fadeOut('fast', function(){
                            $('.error').css('top', '27px').show();
                        });
                        $('.error').fadeIn('fast', function(){
                            $('.username').focus();
                        });
                        return false;
                    }
                    if(password == '') {
                        $('.error').fadeOut('fast', function(){
                            $('.error').css('top', '96px').show();
                        });
                        $(this).find('.error').fadeIn('fast', function(){
                            $('.password').focus();
                        });
                        return false;
                    }
                    var pswd = MD5(username +"#" + password),
                            data = {pswd:pswd,email:username,rememberMe:$("#rememberMe").is(':checked')};
                    var load = layer.load();

                    $.ajax({
                        url:"http://localhost:8080/managementsystem/user/submitLogin.shtml",
                        data:data,
                        type:"post",
                        dataType:"json",
                        beforeSend:function(){
                            layer.msg('开始登录，请注意后台控制台。');
                        },
                        success:function(result){
                            layer.close(load);
                            if(result && result.status != 200){
                                layer.msg(result.message,function(){});
                                $('.password').val('');
                                return;
                            }else{
                                layer.msg('登录成功！');
                                setTimeout(function(){
                                    //登录返回
                                    window.location.href= result.back_url || "http://localhost:8080/managementsystem/";
                                },1000)
                            }
                        },
                        error:function(e){
                            layer.close(load);
                            console.log(e,e.message);
                            layer.msg('请看后台Java控制台，是否报错，确定已经配置数据库和Redis',new Function());
                        }
                    });
                });
                $('.page-container form .username, .page-container form .password').keyup(function(){
                    $(this).parent().find('.error').fadeOut('fast');
                });
                //注册
                $("#register").click(function(){
                    window.location.href="register.shtml";
                });
            });
        </script>

    </body>

</html>

